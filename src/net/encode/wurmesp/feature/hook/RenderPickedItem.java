package net.encode.wurmesp.feature.hook;

import java.lang.reflect.Field;
import java.util.logging.Level;

import org.gotti.wurmunlimited.modloader.ReflectionUtil;

import com.wurmonline.client.game.World;
import com.wurmonline.client.renderer.PickRenderer;
import com.wurmonline.client.renderer.backend.Queue;

import net.encode.wurmesp.WurmEspMod;

public class RenderPickedItem
extends Hook {
	
    public RenderPickedItem() {
        this.prepareHook("com.wurmonline.client.renderer.WorldRender", "renderPickedItem", "(Lcom/wurmonline/client/renderer/backend/Queue;)V", () -> (proxy, method, args) -> {
            method.invoke(proxy, args);
            Class<?> cls = proxy.getClass();
            World world = (World)ReflectionUtil.getPrivateField((Object)proxy, (Field)ReflectionUtil.getField(cls, (String)"world"));
            WurmEspMod._pickRenderer = (PickRenderer)ReflectionUtil.getPrivateField((Object)proxy, (Field)ReflectionUtil.getField(cls, (String)"pickRenderer"));
            Queue queuePick = (Queue)ReflectionUtil.getPrivateField((Object)proxy, (Field)ReflectionUtil.getField(cls, (String)"queuePick"));
            WurmEspMod.pickableUnits.stream().filter(unit -> WurmEspMod.players && unit.isPlayer() || WurmEspMod.uniques && unit.isUnique() || WurmEspMod.conditioned && unit.isConditioned() || WurmEspMod.animals && (unit.isMob() && !unit.isAggroMob()) || WurmEspMod.mobs && unit.isAggroMob() || WurmEspMod.specials && unit.isSpecial() || WurmEspMod.items && unit.isSpotted()).forEachOrdered(unit -> {
                if (unit.isConditioned() && WurmEspMod.conditioned || unit.isConditioned() && WurmEspMod.conditionedcolorsallways || unit.isChampion() && WurmEspMod.championmcoloralways) {
                    unit.renderUnit(queuePick, true);
                } else {
                    unit.renderUnit(queuePick, false);
                }
            });
            if (WurmEspMod.tileshighlight) {
                WurmEspMod.tilesHighlightManager.setWorldQueue(world, queuePick);
                if (WurmEspMod.tilesHighlightManager.first) {
                    WurmEspMod.tilesHighlightManager.refresh();
                    WurmEspMod.tilesHighlightManager.first = false;
                } else if (WurmEspMod.tilesHighlightCronoManager.hasEnded()) {
                    WurmEspMod.tilesHighlightManager.refresh();
                    WurmEspMod.tilesHighlightCronoManager.restart(5000L);
                }
                Thread tilesHighlightThread = new Thread(() -> WurmEspMod.tilesHighlightManager.queue());
                tilesHighlightThread.setPriority(10);
                tilesHighlightThread.start();
            } else {
                WurmEspMod.tilesHighlightManager.setWorld(world);
            }
            if (WurmEspMod.tilescloseby && world.getPlayer().getPos().getLayer() >= 0) {
                WurmEspMod.tilesCloseByManager.setWorldQueue(world, queuePick);
                if (WurmEspMod.tilesCloseByManager.first) {
                    WurmEspMod.tilesCloseByManager.refresh();
                    WurmEspMod.tilesCloseByManager.first = false;
                } else if (WurmEspMod.tilesCloseByCronoManager.hasEnded()) {
                    WurmEspMod.tilesCloseByManager.refresh();
                    WurmEspMod.tilesCloseByCronoManager.restart(1000L);
                }
                Thread tilesThread = new Thread(() -> WurmEspMod.tilesCloseByManager.queue());
                tilesThread.setPriority(10);
                tilesThread.start();
            }
            if (WurmEspMod.tilesclosebynotrideable && world.getPlayer().getPos().getLayer() >= 0) {
                WurmEspMod.tilesCloseByWalkableManager.setWorldQueue(world, queuePick);
                if (WurmEspMod.tilesCloseByWalkableManager.first) {
                    WurmEspMod.tilesCloseByWalkableManager.refresh();
                    WurmEspMod.tilesCloseByWalkableManager.first = false;
                } else if (WurmEspMod.tilesCloseByWalkableCronoManager.hasEnded()) {
                    WurmEspMod.tilesCloseByWalkableManager.refresh();
                    WurmEspMod.tilesCloseByWalkableCronoManager.restart(1000L);
                }
                Thread tilesWalkableThread = new Thread(() -> WurmEspMod.tilesCloseByWalkableManager.queue());
                tilesWalkableThread.setPriority(10);
                tilesWalkableThread.start();
            }
            if (WurmEspMod.tilesFlower && world.getPlayer().getPos().getLayer() >= 0) {
                WurmEspMod.tilesFlowerManager.setWorldQueue(world, queuePick);
                if (WurmEspMod.tilesFlowerManager.first) {
                    WurmEspMod.tilesFlowerManager.refresh();
                    WurmEspMod.tilesFlowerManager.first = false;
                } else if (WurmEspMod.tilesFlowerCronoManager.hasEnded()) {
                    WurmEspMod.tilesFlowerManager.refresh();
                    WurmEspMod.tilesFlowerCronoManager.restart(1000L);
                }
                Thread tilesFlowerThread = new Thread(() -> WurmEspMod.tilesFlowerManager.queue());
                tilesFlowerThread.setPriority(10);
                tilesFlowerThread.start();
            }
            if (WurmEspMod.xray && world.getPlayer().getPos().getLayer() < 0) {
                Thread refreshThread;
                WurmEspMod.xrayManager.setWorldQueue(world, queuePick);
                if (WurmEspMod.xrayManager.first) {
                    if (WurmEspMod.xrayrefreshthread) {
                        refreshThread = new Thread(() -> WurmEspMod.xrayManager.refresh());
                        refreshThread.setPriority(10);
                        refreshThread.start();
                    } else {
                        WurmEspMod.xrayManager.refresh();
                    }
                    WurmEspMod.xrayManager.first = false;
                } else if (WurmEspMod.xrayCronoManager.hasEnded()) {
                    if (WurmEspMod.xrayrefreshthread) {
                        refreshThread = new Thread(() -> WurmEspMod.xrayManager.refresh());
                        refreshThread.setPriority(10);
                        refreshThread.start();
                    } else {
                        WurmEspMod.xrayManager.refresh();
                    }
                    WurmEspMod.xrayCronoManager.restart(WurmEspMod.xrayrefreshrate * 1000);
                }
                if (WurmEspMod.xraythread) {
                    Thread xrayThread = new Thread(() -> WurmEspMod.xrayManager.queue());
                    xrayThread.setPriority(10);
                    xrayThread.start();
                } else {
                    WurmEspMod.xrayManager.queue();
                }
            }
            return null;
        });
        WurmEspMod.logger.log(Level.INFO, "[WurmEspMod] WorldRender.renderPickedItem hooked");
    }
}

