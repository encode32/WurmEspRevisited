package net.encode.wurmesp.util;

import net.encode.wurmesp.WurmEspMod;

public class TerrainUtils {
    public static boolean isFlat(float[] tile) {
        return tile[1] == tile[4] && tile[4] == tile[7] && tile[7] == tile[10];
    }

    public static boolean isNotRideable(float[] tile) {
        return TerrainUtils.getTileSteepness(tile)[1] >= WurmEspMod.tilenotrideable;
    }

    public static short[] getTileSteepness(float[] tile) {
        short highest = -100;
        short lowest = 32000;
        for (int i = 1; i <= 10; i += 3) {
            short height = 0;
            height = (short)(tile[i] * 10.0f);
            if (height > highest) {
                highest = height;
            }
            if (height >= lowest) continue;
            lowest = height;
        }
        int med = (highest + lowest) / 2;
        return new short[]{(short)med, (short)(highest - lowest)};
    }
}

