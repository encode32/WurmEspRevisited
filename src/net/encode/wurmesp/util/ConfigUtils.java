package net.encode.wurmesp.util;

import com.wurmonline.mesh.Tiles;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import net.encode.wurmesp.Unit;
import net.encode.wurmesp.WurmEspMod;

public class ConfigUtils {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void loadProperties(String name) {
        WurmEspMod.modProperties.clear();
        InputStream inputStream = null;
        Path path = Paths.get("mods", new String[0]);
        path = path.resolve(name + ".properties");
        try {
            inputStream = Files.newInputStream(path, new OpenOption[0]);
            WurmEspMod.modProperties.load(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void DoConfig(Properties properties) {
        WurmEspMod.players = Boolean.valueOf(properties.getProperty("players", Boolean.toString(WurmEspMod.players)));
        WurmEspMod.mobs = Boolean.valueOf(properties.getProperty("mobs", Boolean.toString(WurmEspMod.mobs)));
        WurmEspMod.animals = Boolean.valueOf(properties.getProperty("animals", Boolean.toString(WurmEspMod.animals)));
        WurmEspMod.specials = Boolean.valueOf(properties.getProperty("specials", Boolean.toString(WurmEspMod.specials)));
        WurmEspMod.items = Boolean.valueOf(properties.getProperty("items", Boolean.toString(WurmEspMod.items)));
        WurmEspMod.uniques = Boolean.valueOf(properties.getProperty("uniques", Boolean.toString(WurmEspMod.uniques)));
        WurmEspMod.conditioned = Boolean.valueOf(properties.getProperty("conditioned", Boolean.toString(WurmEspMod.conditioned)));
        WurmEspMod.tilescloseby = Boolean.valueOf(properties.getProperty("tilescloseby", Boolean.toString(WurmEspMod.tilescloseby)));
        WurmEspMod.deedsize = Boolean.valueOf(properties.getProperty("deedsize", Boolean.toString(WurmEspMod.deedsize)));
        WurmEspMod.xray = Boolean.valueOf(properties.getProperty("xray", Boolean.toString(WurmEspMod.xray)));
        WurmEspMod.xraythread = Boolean.valueOf(properties.getProperty("xraythread", Boolean.toString(WurmEspMod.xraythread)));
        WurmEspMod.xrayrefreshthread = Boolean.valueOf(properties.getProperty("xrayrefreshthread", Boolean.toString(WurmEspMod.xrayrefreshthread)));
        WurmEspMod.xraydiameter = Integer.parseInt(properties.getProperty("xraydiameter", Integer.toString(WurmEspMod.xraydiameter)));
        WurmEspMod.xrayrefreshrate = Integer.parseInt(properties.getProperty("xrayrefreshrate", Integer.toString(WurmEspMod.xrayrefreshrate)));
        WurmEspMod.tilenotrideable = Integer.parseInt(properties.getProperty("tilenotrideable", Integer.toString(WurmEspMod.tilenotrideable)));
        WurmEspMod.flowerdiameter = Integer.parseInt(properties.getProperty("flowerdiameter", Integer.toString(WurmEspMod.flowerdiameter)));
        WurmEspMod.playsoundspecial = Boolean.valueOf(properties.getProperty("playsoundspecial", Boolean.toString(WurmEspMod.playsoundspecial)));
        WurmEspMod.playsounditem = Boolean.valueOf(properties.getProperty("playsounditem", Boolean.toString(WurmEspMod.playsounditem)));
        WurmEspMod.playsoundunique = Boolean.valueOf(properties.getProperty("playsoundunique", Boolean.toString(WurmEspMod.playsoundunique)));
        WurmEspMod.soundspecial = properties.getProperty("soundspecial", WurmEspMod.soundspecial);
        WurmEspMod.sounditem = properties.getProperty("sounditem", WurmEspMod.sounditem);
        WurmEspMod.soundunique = properties.getProperty("soundunique", WurmEspMod.soundunique);
        WurmEspMod.conditionedcolorsallways = Boolean.valueOf(properties.getProperty("conditionedcolorsallways", Boolean.toString(WurmEspMod.conditionedcolorsallways)));
        WurmEspMod.championmcoloralways = Boolean.valueOf(properties.getProperty("championmcoloralways", Boolean.toString(WurmEspMod.championmcoloralways)));
        Unit.colorPlayers = ConfigUtils.colorStringToFloatA(properties.getProperty("colorPlayers", ConfigUtils.colorFloatAToString(Unit.colorPlayers)));
        Unit.colorPlayersEnemy = ConfigUtils.colorStringToFloatA(properties.getProperty("colorPlayersEnemy", ConfigUtils.colorFloatAToString(Unit.colorPlayersEnemy)));
        Unit.colorMobs = ConfigUtils.colorStringToFloatA(properties.getProperty("colorMobs", ConfigUtils.colorFloatAToString(Unit.colorMobs)));
        Unit.colorMobsAggro = ConfigUtils.colorStringToFloatA(properties.getProperty("colorMobsAggro", ConfigUtils.colorFloatAToString(Unit.colorMobsAggro)));
        Unit.colorSpecials = ConfigUtils.colorStringToFloatA(properties.getProperty("colorSpecials", ConfigUtils.colorFloatAToString(Unit.colorSpecials)));
        Unit.colorSpotted = ConfigUtils.colorStringToFloatA(properties.getProperty("colorSpotted", ConfigUtils.colorFloatAToString(Unit.colorSpotted)));
        Unit.colorUniques = ConfigUtils.colorStringToFloatA(properties.getProperty("colorUniques", ConfigUtils.colorFloatAToString(Unit.colorUniques)));
        Unit.colorAlert = ConfigUtils.colorStringToFloatA(properties.getProperty("colorAlert", ConfigUtils.colorFloatAToString(Unit.colorAlert)));
        Unit.colorAngry = ConfigUtils.colorStringToFloatA(properties.getProperty("colorAngry", ConfigUtils.colorFloatAToString(Unit.colorAngry)));
        Unit.colorChampion = ConfigUtils.colorStringToFloatA(properties.getProperty("colorChampion", ConfigUtils.colorFloatAToString(Unit.colorChampion)));
        Unit.colorDiseased = ConfigUtils.colorStringToFloatA(properties.getProperty("colorDiseased", ConfigUtils.colorFloatAToString(Unit.colorDiseased)));
        Unit.colorFierce = ConfigUtils.colorStringToFloatA(properties.getProperty("colorFierce", ConfigUtils.colorFloatAToString(Unit.colorFierce)));
        Unit.colorGreenish = ConfigUtils.colorStringToFloatA(properties.getProperty("colorGreenish", ConfigUtils.colorFloatAToString(Unit.colorGreenish)));
        Unit.colorHardened = ConfigUtils.colorStringToFloatA(properties.getProperty("colorHardened", ConfigUtils.colorFloatAToString(Unit.colorHardened)));
        Unit.colorLurking = ConfigUtils.colorStringToFloatA(properties.getProperty("colorLurking", ConfigUtils.colorFloatAToString(Unit.colorLurking)));
        Unit.colorRaging = ConfigUtils.colorStringToFloatA(properties.getProperty("colorRaging", ConfigUtils.colorFloatAToString(Unit.colorRaging)));
        Unit.colorScared = ConfigUtils.colorStringToFloatA(properties.getProperty("colorScared", ConfigUtils.colorFloatAToString(Unit.colorScared)));
        Unit.colorSlow = ConfigUtils.colorStringToFloatA(properties.getProperty("colorSlow", ConfigUtils.colorFloatAToString(Unit.colorSlow)));
        Unit.colorSly = ConfigUtils.colorStringToFloatA(properties.getProperty("colorSly", ConfigUtils.colorFloatAToString(Unit.colorSly)));
        String oreColorOreIron = properties.getProperty("oreColorOreIron", "default");
        if (!oreColorOreIron.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_IRON, ConfigUtils.colorStringToFloatA(oreColorOreIron));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_IRON, Color.RED.darker());
        }
        String oreColorOreCopper = properties.getProperty("oreColorOreCopper", "default");
        if (!oreColorOreCopper.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_COPPER, ConfigUtils.colorStringToFloatA(oreColorOreCopper));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_COPPER, Color.GREEN);
        }
        String oreColorOreTin = properties.getProperty("oreColorOreTin", "default");
        if (!oreColorOreTin.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_TIN, ConfigUtils.colorStringToFloatA(oreColorOreTin));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_TIN, Color.GRAY);
        }
        String oreColorOreGold = properties.getProperty("oreColorOreGold", "default");
        if (!oreColorOreGold.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_GOLD, ConfigUtils.colorStringToFloatA(oreColorOreGold));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_GOLD, Color.YELLOW.darker());
        }
        String oreColorOreAdamantine = properties.getProperty("oreColorOreAdamantine", "default");
        if (!oreColorOreAdamantine.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_ADAMANTINE, ConfigUtils.colorStringToFloatA(oreColorOreAdamantine));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_ADAMANTINE, Color.CYAN);
        }
        String oreColorOreGlimmersteel = properties.getProperty("oreColorOreGlimmersteel", "default");
        if (!oreColorOreGlimmersteel.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_GLIMMERSTEEL, ConfigUtils.colorStringToFloatA(oreColorOreGlimmersteel));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_GLIMMERSTEEL, Color.YELLOW.brighter());
        }
        String oreColorOreSilver = properties.getProperty("oreColorOreSilver", "default");
        if (!oreColorOreSilver.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_SILVER, ConfigUtils.colorStringToFloatA(oreColorOreSilver));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_SILVER, Color.LIGHT_GRAY);
        }
        String oreColorOreLead = properties.getProperty("oreColorOreLead", "default");
        if (!oreColorOreLead.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_LEAD, ConfigUtils.colorStringToFloatA(oreColorOreLead));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_LEAD, Color.PINK.darker().darker());
        }
        String oreColorOreZinc = properties.getProperty("oreColorOreZinc", "default");
        if (!oreColorOreZinc.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_ZINC, ConfigUtils.colorStringToFloatA(oreColorOreZinc));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ORE_ZINC, new Color(235, 235, 235));
        }
        String oreColorSlate = properties.getProperty("oreColorSlate", "default");
        if (!oreColorSlate.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_SLATE, ConfigUtils.colorStringToFloatA(oreColorSlate));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_SLATE, Color.BLACK);
        }
        String oreColorMarble = properties.getProperty("oreColorMarble", "default");
        if (!oreColorMarble.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_MARBLE, ConfigUtils.colorStringToFloatA(oreColorMarble));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_MARBLE, Color.WHITE);
        }
        String oreColorSandstone = properties.getProperty("oreColorSandstone", "default");
        if (!oreColorSandstone.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_SANDSTONE, ConfigUtils.colorStringToFloatA(oreColorSandstone));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_SANDSTONE, Color.YELLOW.darker().darker());
        }
        String oreColorRocksalt = properties.getProperty("oreColorRocksalt", "default");
        if (!oreColorRocksalt.equals("default")) {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ROCKSALT, ConfigUtils.colorStringToFloatA(oreColorRocksalt));
        } else {
            XrayColors.addMapping(Tiles.Tile.TILE_CAVE_WALL_ROCKSALT, Color.WHITE.darker());
        }
        Unit.aggroMOBS = properties.getProperty("aggroMOBS").split(";");
        Unit.uniqueMOBS = properties.getProperty("uniqueMOBS").split(";");
        Unit.specialITEMS = properties.getProperty("specialITEMS").split(";");
        Unit.spottedITEMS = properties.getProperty("spottedITEMS").split(";");
        Unit.conditionedMOBS = properties.getProperty("conditionedMOBS").split(";");
        WurmEspMod.tilesFlowerSearch = properties.getProperty("tilesFlowerSearch").split(";");
    }

    private static float[] colorStringToFloatA(String color) {
        String[] colors = color.split(",");
        float[] colorf = new float[]{Float.valueOf(colors[0]).floatValue() / 255.0f, Float.valueOf(colors[1]).floatValue() / 255.0f, Float.valueOf(colors[2]).floatValue() / 255.0f};
        return colorf;
    }

    private static String colorFloatAToString(float[] color) {
        String colors = String.valueOf(color[0] * 255.0f) + "," + String.valueOf(color[1] * 255.0f) + "," + String.valueOf(color[2] * 255.0f);
        return colors;
    }
}

