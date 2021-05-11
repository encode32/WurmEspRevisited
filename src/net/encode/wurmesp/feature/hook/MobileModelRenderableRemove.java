package net.encode.wurmesp.feature.hook;

import java.util.logging.Level;

import com.wurmonline.client.renderer.PickableUnit;

import net.encode.wurmesp.Unit;
import net.encode.wurmesp.WurmEspMod;

public class MobileModelRenderableRemove
extends Hook {
    public MobileModelRenderableRemove() {
        this.prepareHook("com.wurmonline.client.renderer.cell.MobileModelRenderable", "removed", "(Z)V", () -> (proxy, method, args) -> {
            method.invoke(proxy, args);
            PickableUnit item = (PickableUnit)proxy;
            for (Unit unit : WurmEspMod.pickableUnits) {
                if (unit.getId() != item.getId()) continue;
                WurmEspMod.toRemove.add(unit);
            }
            for (Unit unit : WurmEspMod.toRemove) {
                if (unit.getId() != item.getId()) continue;
                WurmEspMod.pickableUnits.remove(unit);
            }
            WurmEspMod.toRemove.clear();
            return null;
        });
        WurmEspMod.logger.log(Level.INFO, "[WurmEspMod] MobileModelRenderable.removed hooked");
    }
}

