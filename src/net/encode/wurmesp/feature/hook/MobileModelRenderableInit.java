package net.encode.wurmesp.feature.hook;

import com.wurmonline.client.renderer.PickableUnit;
import com.wurmonline.client.renderer.cell.CellRenderable;
import com.wurmonline.client.renderer.cell.CreatureCellRenderable;
import java.util.logging.Level;
import net.encode.wurmesp.Unit;
import net.encode.wurmesp.WurmEspMod;
import net.encode.wurmesp.util.SoundUtils;

public class MobileModelRenderableInit
extends Hook {
    public MobileModelRenderableInit() {
        this.prepareHook("com.wurmonline.client.renderer.cell.MobileModelRenderable", "initialize", "()V", () -> (proxy, method, args) -> {
            method.invoke(proxy, args);
            PickableUnit pUnit = (PickableUnit)proxy;
            Unit unit = new Unit(CellRenderable.world, pUnit.getId(), pUnit, ((CreatureCellRenderable)proxy).getModelName().toString(), ((CreatureCellRenderable)proxy).getHoverName());
            if (unit.isPlayer() || unit.isMob()) {
                WurmEspMod.pickableUnits.add(unit);
                if (unit.isUnique() && WurmEspMod.uniques && WurmEspMod.playsoundunique) {
                    SoundUtils.playSound(WurmEspMod.soundunique);
                }
            } else if (unit.isSpecial()) {
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
        WurmEspMod.logger.log(Level.INFO, "[WurmEspMod] MobileModelRenderable.initialize hooked");
    }
}

