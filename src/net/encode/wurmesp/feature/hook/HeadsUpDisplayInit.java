package net.encode.wurmesp.feature.hook;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;

import org.gotti.wurmunlimited.modloader.ReflectionUtil;

import com.wurmonline.client.renderer.gui.HeadsUpDisplay;
import com.wurmonline.client.renderer.gui.MainMenu;
import com.wurmonline.client.renderer.gui.WindowSerializer;
import com.wurmonline.client.renderer.gui.WurmComponent;
import com.wurmonline.client.renderer.gui.WurmEspWindow;
import com.wurmonline.client.settings.SavePosManager;

import net.encode.wurmesp.WurmEspMod;

public class HeadsUpDisplayInit
extends Hook {
    public HeadsUpDisplayInit() {
        this.prepareHook("com.wurmonline.client.renderer.gui.HeadsUpDisplay", "init", "(II)V", () -> (proxy, method, args) -> {
            method.invoke(proxy, args);
            WurmEspMod.hud = (HeadsUpDisplay)proxy;
            this.initEspWR();
            return null;
        });
        WurmEspMod.logger.log(Level.INFO, "[WurmEspMod] HeadsUpDisplay.init hooked");
    }

    private void initEspWR() {
        try {
            WurmEspWindow wurmEspWindow = new WurmEspWindow();
            MainMenu mainMenu = (MainMenu)ReflectionUtil.getPrivateField((Object)WurmEspMod.hud, (Field)ReflectionUtil.getField(WurmEspMod.hud.getClass(), (String)"mainMenu"));
            mainMenu.registerComponent("Esp", (WurmComponent)wurmEspWindow);
            @SuppressWarnings("unchecked")
			List<WurmEspWindow> components = (List<WurmEspWindow>)ReflectionUtil.getPrivateField((Object)WurmEspMod.hud, (Field)ReflectionUtil.getField(WurmEspMod.hud.getClass(), (String)"components"));
            components.add(wurmEspWindow);
            SavePosManager savePosManager = (SavePosManager)ReflectionUtil.getPrivateField((Object)WurmEspMod.hud, (Field)ReflectionUtil.getField(WurmEspMod.hud.getClass(), (String)"savePosManager"));
            savePosManager.registerAndRefresh((WindowSerializer)wurmEspWindow, "wurmespwindow");
        }
        catch (ClassCastException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}

