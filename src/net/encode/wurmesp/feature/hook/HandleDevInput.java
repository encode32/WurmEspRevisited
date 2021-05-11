package net.encode.wurmesp.feature.hook;

import com.wurmonline.client.game.PlayerPosition;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import net.encode.wurmesp.WurmEspMod;
import net.encode.wurmesp.util.ConfigUtils;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;

public class HandleDevInput {
    public HandleDevInput() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ctWurmConsole = classPool.getCtClass("com.wurmonline.client.console.WurmConsole");
            ctWurmConsole.getMethod("handleDevInput", "(Ljava/lang/String;[Ljava/lang/String;)Z").insertBefore("if (net.encode.wurmesp.feature.hook.HandleDevInput.handleInput($1,$2)) return true;");
            WurmEspMod.logger.log(Level.INFO, "[WurmEspMod] Return inserted on handleDevInput");
        }
        catch (CannotCompileException | NotFoundException ex) {
            Logger.getLogger(HandleDevInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        WurmEspMod.logger.log(Level.INFO, "[WurmEspMod] Return inserted on handleDevInput");
    }

    public static boolean handleInput(String cmd, String[] data) {
        if (cmd.equals("esp")) {
            if (data.length == 2) {
                switch (data[1]) {
                    case "players": {
                        WurmEspMod.players = !WurmEspMod.players;
                        WurmEspMod.hud.consoleOutput("ESP players changed to: " + Boolean.toString(WurmEspMod.players));
                        break;
                    }
                    case "mobs": {
                        WurmEspMod.mobs = !WurmEspMod.mobs;
                        WurmEspMod.hud.consoleOutput("ESP mobs changed to: " + Boolean.toString(WurmEspMod.mobs));
                        break;
                    }
                    case "specials": {
                        WurmEspMod.specials = !WurmEspMod.specials;
                        WurmEspMod.hud.consoleOutput("ESP specials changed to: " + Boolean.toString(WurmEspMod.specials));
                        break;
                    }
                    case "uniques": {
                        WurmEspMod.uniques = !WurmEspMod.uniques;
                        WurmEspMod.hud.consoleOutput("ESP uniques changed to: " + Boolean.toString(WurmEspMod.uniques));
                        break;
                    }
                    case "conditioned": {
                        WurmEspMod.conditioned = !WurmEspMod.conditioned;
                        WurmEspMod.hud.consoleOutput("ESP champions changed to: " + Boolean.toString(WurmEspMod.conditioned));
                        break;
                    }
                    case "xray": {
                        WurmEspMod.xray = !WurmEspMod.xray;
                        WurmEspMod.hud.consoleOutput("ESP xray changed to: " + Boolean.toString(WurmEspMod.xray));
                        break;
                    }
                    case "tilescloseby": {
                        WurmEspMod.tilescloseby = !WurmEspMod.tilescloseby;
                        WurmEspMod.hud.consoleOutput("ESP tilescloseby changed to: " + Boolean.toString(WurmEspMod.tilescloseby));
                        break;
                    }
                    case "deedsize": {
                        WurmEspMod.deedsize = !WurmEspMod.deedsize;
                        WurmEspMod.hud.consoleOutput("ESP deedsize changed to: " + Boolean.toString(WurmEspMod.deedsize));
                        break;
                    }
                    case "search": {
                        WurmEspMod.hud.consoleOutput("Usage: esp search {h/m/hm/off} <name>");
                        break;
                    }
                    case "planner": {
                        WurmEspMod.hud.consoleOutput("Usage: esp planner {n/s/e/w} <tiles> <times> <space>");
                        WurmEspMod.hud.consoleOutput("Usage: esp planner square <startX> <startY> <endX> <endY>");
                        WurmEspMod.hud.consoleOutput("Usage: esp planner square <radius>");
                        WurmEspMod.hud.consoleOutput("Usage: esp planner tile <tileX> <tileY>");
                        WurmEspMod.hud.consoleOutput("Usage: esp planner clear");
                        break;
                    }
                    case "reload": {
                        ConfigUtils.loadProperties("wurmesp");
                        ConfigUtils.DoConfig(WurmEspMod.modProperties);
                        WurmEspMod.hud.consoleOutput("[WurmEspMod] Config Reloaded");
                        break;
                    }
                    default: {
                        WurmEspMod.hud.consoleOutput("Usage: esp {players|mobs|specials|uniques|conditioned|xray|tilescloseby|deedsize|reload}");
                    }
                }
                return true;
            }
            if (data.length > 2) {
                switch (data[1]) {
                    case "search": {
                        if (data[2].equals("h")) {
                            WurmEspMod.search = data[3];
                            WurmEspMod.searchType = WurmEspMod.SEARCHTYPE.HOVER;
                            WurmEspMod.hud.consoleOutput("Searching for " + WurmEspMod.search + " in HoverName");
                            break;
                        }
                        if (data[2].equals("m")) {
                            WurmEspMod.search = data[3];
                            WurmEspMod.searchType = WurmEspMod.SEARCHTYPE.MODEL;
                            WurmEspMod.hud.consoleOutput("Searching for " + WurmEspMod.search + " in ModelName");
                            break;
                        }
                        if (data[2].equals("hm")) {
                            WurmEspMod.search = data[3];
                            WurmEspMod.searchType = WurmEspMod.SEARCHTYPE.BOTH;
                            WurmEspMod.hud.consoleOutput("Searching for " + WurmEspMod.search + " in HoverName and ModelName");
                            break;
                        }
                        if (data[2].equals("off")) {
                            WurmEspMod.search = "";
                            WurmEspMod.searchType = WurmEspMod.SEARCHTYPE.NONE;
                            WurmEspMod.hud.consoleOutput("Searching off");
                            break;
                        }
                        WurmEspMod.hud.consoleOutput("Usage: esp search {h/m/hm/off} <name>");
                        break;
                    }
                    case "planner": {
                        if (data.length == 3 && data[2].equals("clear")) {
                            WurmEspMod._tilesHighlightBase.clear();
                            WurmEspMod.tileshighlight = false;
                            WurmEspMod.hud.consoleOutput("Planner data cleared.");
                            break;
                        }
                        if (data.length == 3 && data[2].equals("tile")) {
                            PlayerPosition pos = WurmEspMod.hud.getWorld().getPlayer().getPos();
                            int tileX = pos.getTileX();
                            int tileY = pos.getTileY();
                            WurmEspMod.tilesHighlightManager.addData(tileX, tileY);
                            WurmEspMod.tileshighlight = true;
                            WurmEspMod.hud.consoleOutput("Added planner data. [TileX: " + String.valueOf(tileX) + "][tileY: " + String.valueOf(tileY) + "]");
                            break;
                        }
                        if (data.length == 4 && data[2].equals("square")) {
                            int radius = Integer.parseInt(data[3]);
                            WurmEspMod.tilesHighlightManager.addData(radius);
                            WurmEspMod.tileshighlight = true;
                            WurmEspMod.hud.consoleOutput("Added planner data. [radius: " + data[3] + "]");
                            break;
                        }
                        if (data.length == 5 && data[2].equals("tile")) {
                            int tileX = Integer.parseInt(data[3]);
                            int tileY = Integer.parseInt(data[4]);
                            WurmEspMod.tilesHighlightManager.addData(tileX, tileY);
                            WurmEspMod.tileshighlight = true;
                            WurmEspMod.hud.consoleOutput("Added planner data. [TileX: " + data[3] + "][tileY: " + data[4] + "]");
                            break;
                        }
                        if (data.length == 6 && "nsew".contains(data[2])) {
                            String direction = data[2];
                            int tiles = Integer.parseInt(data[3]);
                            int times = Integer.parseInt(data[4]);
                            int space = Integer.parseInt(data[5]);
                            WurmEspMod.tilesHighlightManager.addData(direction, tiles, times, space);
                            WurmEspMod.tileshighlight = true;
                            WurmEspMod.hud.consoleOutput("Added planner data. [direction: " + direction + "][tiles: " + data[3] + "][times: " + data[4] + "][space: " + data[5] + "]");
                            break;
                        }
                        if (data.length == 7 && data[2].equals("square")) {
                            int startX = Integer.parseInt(data[3]);
                            int startY = Integer.parseInt(data[4]);
                            int endX = Integer.parseInt(data[5]);
                            int endY = Integer.parseInt(data[6]);
                            WurmEspMod.tilesHighlightManager.addData(startX, startY, endX, endY);
                            WurmEspMod.tileshighlight = true;
                            WurmEspMod.hud.consoleOutput("Added planner data. [startX: " + data[3] + "][startY: " + data[4] + "][endX: " + data[5] + "][endY: " + data[6] + "]");
                            break;
                        }
                        WurmEspMod.hud.consoleOutput("Usage: esp planner {n/s/e/w} <tiles> <times> <space>");
                        WurmEspMod.hud.consoleOutput("Usage: esp planner square <startX> <startY> <endX> <endY>");
                        WurmEspMod.hud.consoleOutput("Usage: esp planner square <radius>");
                        WurmEspMod.hud.consoleOutput("Usage: esp planner tile <tileX> <tileY>");
                        WurmEspMod.hud.consoleOutput("Usage: esp planner clear");
                        break;
                    }
                    default: {
                        WurmEspMod.hud.consoleOutput("Error.");
                    }
                }
                return true;
            }
            WurmEspMod.hud.consoleOutput("Error.");
            return true;
        }
        return false;
    }
}

