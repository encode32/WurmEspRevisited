package net.encode.wurmesp.feature;

import com.wurmonline.client.game.PlayerPosition;
import com.wurmonline.mesh.Tiles;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.encode.wurmesp.WurmEspMod;
import net.encode.wurmesp.util.RenderUtils;
import net.encode.wurmesp.util.XrayColors;

public class FeatureXRay
extends Feature {
    @Override
    public void refresh() {
        if (!this.world.isOwnBodyAdded()) {
            return;
        }
        WurmEspMod._terrain.clear();
        WurmEspMod._caveBuffer = this.world.getCaveBuffer();
        PlayerPosition pos = this.world.getPlayer().getPos();
        int px = pos.getTileX();
        int py = pos.getTileY();
        int size = WurmEspMod.xraydiameter;
        int sx = px - size / 2;
        int sy = py - size / 2;
        float ox = this.world.getRenderOriginX();
        float oy = this.world.getRenderOriginY();
        for (int x = 0; x < size; ++x) {
            for (int y = size - 1; y >= 0; --y) {
                try {
                    int tileX = x + sx;
                    int tileY = y + sy;
                    Tiles.Tile tile = WurmEspMod._caveBuffer.getTileType(tileX, tileY);
                    if (tile == null || !tile.isOreCave()) continue;
                    Color color = XrayColors.getColorFor(tile);
                    float[] colorF = new float[]{(float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f};
                    float curX = (float)(tileX * 4) - ox;
                    float curY = (float)(tileY * 4) - oy;
                    float nextX = (float)((tileX + 1) * 4) - ox;
                    float nextY = (float)((tileY + 1) * 4) - oy;
                    float x0 = curX + 0.2f;
                    float y0 = curY + 0.2f;
                    float x1 = nextX - 0.2f;
                    float y1 = nextY - 0.2f;
                    WurmEspMod._terrain.add(new float[]{x0, y0, x1, y1, colorF[0], colorF[1], colorF[2]});
                    continue;
                }
                catch (IllegalArgumentException | SecurityException ex) {
                    Logger.getLogger(FeatureXRay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void queue() {
    	if (WurmEspMod._terrain == null) {
            return;
        }
    	WurmEspMod._terrain.stream().forEach(t -> {
        	float x0 = t[0];
			float y0 = t[1];
			float x1 = t[2];
			float y1 = t[3];
			
			float[] color = new float[]{t[4],t[5],t[6], 1.0F};
			PlayerPosition pos = this.world.getPlayer().getPos();

			float z0 = pos.getH();
			float z1 = z0 + 3;
			
			float[] vertexdata = new float[] { 
					x1, z0, y0, 
					x1, z1, y0, 
					x0, z1, y0, 
					x0, z0, y0, 
					x1, z0, y1, 
					x1, z1, y1, 
					x0, z1, y1, 
					x0, z0, y1 };
			
			int[] indexdata = new int[] { 0, 1, 1, 2, 2, 3, 3, 0, 4, 5, 5, 6, 6, 7, 7, 4, 0, 4, 1, 5, 2, 6, 3, 7 };
            RenderUtils.renderPrimitiveLines(8, vertexdata, indexdata, this.queuePick, color);
        });
    	/*
        if (!this.world.isOwnBodyAdded()) {
            return;
        }
        WurmEspMod._terrain.clear();
        WurmEspMod._caveBuffer = this.world.getCaveBuffer();
        PlayerPosition pos = this.world.getPlayer().getPos();
        int px = pos.getTileX();
        int py = pos.getTileY();
        int size = WurmEspMod.xraydiameter;
        int sx = px - size / 2;
        int sy = py - size / 2;
        WorldRender worldRenderer = (WorldRender)ReUtils.getField(this.world, "worldRenderer");
        CaveRender caveRenderer = (CaveRender)ReUtils.getField(worldRenderer, "caveRenderer");
        for (int x = 0; x < size; ++x) {
            for (int y = size - 1; y >= 0; --y) {
                try {
                    int tileX = x + sx;
                    int tileY = y + sy;
                    for (int side = 0; side < 7; ++side) {
                        IntBuffer intBuffer = IntBuffer.allocate(3);
                        intBuffer.put(tileX);
                        intBuffer.put(tileY);
                        intBuffer.put(side);
                        Class<HitNamesData> cls = HitNamesData.class;
                        Constructor<HitNamesData> constructor = cls.getDeclaredConstructor(IntBuffer.class, Integer.TYPE);
                        constructor.setAccessible(true);
                        HitNamesData hitNames = (HitNamesData)constructor.newInstance(intBuffer, 3);
                        caveRenderer.getPickedWall(hitNames).renderPicked(this.queuePick, RenderState.RENDERSTATE_DEFAULT, com.wurmonline.client.renderer.Color.GREEN);
                    }
                    continue;
                }
                catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
                    Logger.getLogger(FeatureXRay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }*/
    }
}

