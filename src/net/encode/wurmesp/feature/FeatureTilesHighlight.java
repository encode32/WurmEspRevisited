package net.encode.wurmesp.feature;

import com.wurmonline.client.game.PlayerPosition;
import com.wurmonline.client.game.World;
import net.encode.wurmesp.WurmEspMod;
import net.encode.wurmesp.util.RenderUtils;

public class FeatureTilesHighlight
extends Feature {
    @Override
    public void refresh() {
        WurmEspMod._tilesHighlightTerrain.clear();
        WurmEspMod._terrainBuffer = this.world.getNearTerrainBuffer();
        float ox = this.world.getRenderOriginX();
        float oy = this.world.getRenderOriginY();
        WurmEspMod._tilesHighlightBase.forEach(base -> {
            float curX = (float)(base[0] * 4) - ox;
            float curY = (float)(base[1] * 4) - oy;
            float nextX = (float)((base[0] + 1) * 4) - ox;
            float nextY = (float)((base[1] + 1) * 4) - oy;
            float x0 = curX + 0.1f;
            float y0 = curY + 0.1f;
            float x1 = nextX - 0.1f;
            float y1 = nextY - 0.1f;
            float z0 = WurmEspMod._terrainBuffer.getHeight(base[0], base[1]);
            float z1 = WurmEspMod._terrainBuffer.getHeight(base[0] + 1, base[1]);
            float z2 = WurmEspMod._terrainBuffer.getHeight(base[0], base[1] + 1);
            float z3 = WurmEspMod._terrainBuffer.getHeight(base[0] + 1, base[1] + 1);
            WurmEspMod._tilesHighlightTerrain.add(new float[]{x0, z0, y0, x1, z1, y0, x0, z2, y1, x1, z3, y1, x0, 0.0f, y0, x1, 0.0f, y0, x0, 0.0f, y1, x1, 0.0f, y1});
        });
    }

    @Override
    public void queue() {
        if (WurmEspMod._tilesHighlightTerrain == null) {
            return;
        }
        WurmEspMod._tilesHighlightTerrain.forEach(t -> {
            float[] color = new float[]{1.0f, 0.0f, 0.0f, 1.0f};
            int[] indexdata = new int[]{1, 0, 0, 2, 2, 3, 3, 1, 1, 2, 0, 3, 5, 4, 4, 6, 6, 7, 7, 5, 0, 4, 1, 5, 2, 6, 3, 7};
            RenderUtils.renderPrimitiveLines(8, t, indexdata, this.queuePick, color);
        });
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void addData(String direction, int tiles, int times, int space) {
        PlayerPosition pos = this.world.getPlayer().getPos();
        int baseTileX = pos.getTileX();
        int baseTileY = pos.getTileY();
        WurmEspMod._tilesHighlightBase.add(new int[]{baseTileX, baseTileY});
        block12: for (int i = 0; i < times; ++i) {
            switch (direction) {
                case "n": {
                    int s;
                    baseTileY -= tiles;
                    for (s = 1; s < space + 1; ++s) {
                        WurmEspMod._tilesHighlightBase.add(new int[]{baseTileX, --baseTileY});
                    }
                    continue block12;
                }
                case "s": {
                    int s;
                    baseTileY += tiles;
                    for (s = 1; s < space + 1; ++s) {
                        WurmEspMod._tilesHighlightBase.add(new int[]{baseTileX, ++baseTileY});
                    }
                    continue block12;
                }
                case "e": {
                    int s;
                    baseTileX += tiles;
                    for (s = 1; s < space + 1; ++s) {
                        WurmEspMod._tilesHighlightBase.add(new int[]{++baseTileX, baseTileY});
                    }
                    continue block12;
                }
                case "w": {
                    int s;
                    baseTileX -= tiles;
                    for (s = 1; s < space + 1; ++s) {
                        WurmEspMod._tilesHighlightBase.add(new int[]{--baseTileX, baseTileY});
                    }
                    continue block12;
                }
            }
        }
    }

    public void addData(int x, int y) {
        WurmEspMod._tilesHighlightBase.add(new int[]{x, y});
    }

    public void addData(int radius) {
        int tileX = this.world.getPlayer().getPos().getTileX();
        int tileY = this.world.getPlayer().getPos().getTileY();
        int startX = tileX - radius;
        int startY = tileY - radius;
        int endX = tileX + radius;
        int endY = tileY + radius;
        this.addData(startX, startY, endX, endY);
    }

    public void addData(int startX, int startY, int endX, int endY) {
        WurmEspMod._tilesHighlightBase.add(new int[]{startX, startY});
        WurmEspMod._tilesHighlightBase.add(new int[]{startX, endY});
        WurmEspMod._tilesHighlightBase.add(new int[]{endX, startY});
        WurmEspMod._tilesHighlightBase.add(new int[]{endX, endY});
    }
}

