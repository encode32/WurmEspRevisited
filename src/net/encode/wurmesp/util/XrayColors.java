package net.encode.wurmesp.util;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.mesh.Tiles.Tile;

import java.awt.Color;
import java.util.EnumMap;

public class XrayColors {
    private static final EnumMap<Tiles.Tile, Color> MAPPINGS = new EnumMap<Tile, Color>(Tiles.Tile.class);

    public static void addMapping(Tiles.Tile tile, Color color) {
        MAPPINGS.put(tile, color);
    }

    public static void addMapping(Tiles.Tile tile, float[] color) {
        MAPPINGS.put(tile, new Color(color[0], color[1], color[2]));
    }

    public static Color getColorFor(Tiles.Tile tile) {
        return MAPPINGS.getOrDefault(tile, Color.PINK);
    }
}

