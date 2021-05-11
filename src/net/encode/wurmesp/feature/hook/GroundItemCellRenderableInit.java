package net.encode.wurmesp.feature.hook;

import com.wurmonline.client.renderer.GroundItemData;
import com.wurmonline.client.renderer.PickableUnit;
import com.wurmonline.client.renderer.cell.CellRenderable;

import java.lang.reflect.Field;
import java.util.logging.Level;
import net.encode.wurmesp.Unit;
import net.encode.wurmesp.WurmEspMod;
import net.encode.wurmesp.util.SoundUtils;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;

public class GroundItemCellRenderableInit
extends Hook {
    public GroundItemCellRenderableInit() {
        this.prepareHook("com.wurmonline.client.renderer.cell.GroundItemCellRenderable", "initialize", "()V", () -> (proxy, method, args) -> {
            method.invoke(proxy, args);
            Class<?> cls = proxy.getClass();
            PickableUnit pUnit = (PickableUnit)proxy;
            GroundItemData item = (GroundItemData)ReflectionUtil.getPrivateField((Object)proxy, (Field)ReflectionUtil.getField(cls, (String)"item"));
            Unit unit = new Unit(CellRenderable.world, item.getId(), pUnit, item.getModelName().toString(), ((PickableUnit)proxy).getHoverName());
            if (unit.isSpecial()) {
                WurmEspMod.pickableUnits.add(unit);
                if (WurmEspMod.specials && WurmEspMod.playsoundspecial) {
                    SoundUtils.playSound(WurmEspMod.soundspecial);
                }
            } else if (unit.isSpotted()) {
                WurmEspMod.pickableUnits.add(unit);
                if (WurmEspMod.items && WurmEspMod.playsounditem) {
                    SoundUtils.playSound(WurmEspMod.sounditem);
                }
            }
            return null;
        });
        WurmEspMod.logger.log(Level.INFO, "[WurmEspMod] GroundItemCellRenderable.initialize hooked");
    }
}

