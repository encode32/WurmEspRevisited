/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.wurmonline.client.game.CaveDataBuffer
 *  com.wurmonline.client.game.NearTerrainDataBuffer
 *  com.wurmonline.client.renderer.PickRenderer
 *  com.wurmonline.client.renderer.gui.HeadsUpDisplay
 *  org.gotti.wurmunlimited.modloader.interfaces.Configurable
 *  org.gotti.wurmunlimited.modloader.interfaces.Initable
 *  org.gotti.wurmunlimited.modloader.interfaces.WurmClientMod
 */
package net.encode.wurmesp;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmClientMod;

import com.wurmonline.client.game.CaveDataBuffer;
import com.wurmonline.client.game.NearTerrainDataBuffer;
import com.wurmonline.client.renderer.PickRenderer;
import com.wurmonline.client.renderer.gui.HeadsUpDisplay;

import net.encode.wurmesp.feature.FeatureFlowerTiles;
import net.encode.wurmesp.feature.FeatureTilesCloseBy;
import net.encode.wurmesp.feature.FeatureTilesHighlight;
import net.encode.wurmesp.feature.FeatureTilesWalkable;
import net.encode.wurmesp.feature.FeatureXRay;
import net.encode.wurmesp.feature.hook.CmdShowDeedPlan;
import net.encode.wurmesp.feature.hook.GroundItemCellRenderableInit;
import net.encode.wurmesp.feature.hook.GroundItemCellRenderableRemove;
import net.encode.wurmesp.feature.hook.HandleDevInput;
import net.encode.wurmesp.feature.hook.HeadsUpDisplayInit;
import net.encode.wurmesp.feature.hook.MobileModelRenderableInit;
import net.encode.wurmesp.feature.hook.MobileModelRenderableRemove;
import net.encode.wurmesp.feature.hook.ProjectileCellRenderable;
import net.encode.wurmesp.feature.hook.RenderPickedItem;
import net.encode.wurmesp.util.ConfigUtils;
import net.encode.wurmesp.util.CronoManager;

public class WurmEspMod
implements WurmClientMod,
Initable,
Configurable {
    public static HeadsUpDisplay hud;
    public static Logger logger;
    public static Properties modProperties;
    public static List<Unit> pickableUnits;
    public static List<Unit> toRemove;
    public static CronoManager xrayCronoManager;
    public static CronoManager tilesFlowerCronoManager;
    public static CronoManager tilesCloseByCronoManager;
    public static CronoManager tilesHighlightCronoManager;
    public static CronoManager tilesCloseByWalkableCronoManager;
    public static FeatureXRay xrayManager;
    public static FeatureFlowerTiles tilesFlowerManager;
    public static FeatureTilesCloseBy tilesCloseByManager;
    public static FeatureTilesHighlight tilesHighlightManager;
    public static FeatureTilesWalkable tilesCloseByWalkableManager;
    public static CaveDataBuffer _caveBuffer;
    public static NearTerrainDataBuffer _terrainBuffer;
    public static NearTerrainDataBuffer _terrainBuffer2;
    public static CopyOnWriteArrayList<float[]> _terrain;
    public static CopyOnWriteArrayList<float[]> _flowerTerrain;
    public static CopyOnWriteArrayList<float[]> _closeByTerrain;
    public static CopyOnWriteArrayList<float[]> _closeByWalkableTerrain;
    public static CopyOnWriteArrayList<int[]> _tilesHighlightBase;
    public static CopyOnWriteArrayList<float[]> _tilesHighlightTerrain;
    public static String search;
    public static SEARCHTYPE searchType;
    public static boolean players;
    public static boolean mobs;
    public static boolean animals;
    public static boolean specials;
    public static boolean items;
    public static boolean tilesFlower;
    public static boolean uniques;
    public static boolean conditioned;
    public static boolean tilescloseby;
    public static boolean deedsize;
    public static boolean tileshighlight;
    public static boolean tilesclosebynotrideable;
    public static boolean xray;
    public static boolean xraythread;
    public static boolean xrayrefreshthread;
    public static int xraydiameter;
    public static int xrayrefreshrate;
    public static int tilenotrideable;
    public static int flowerdiameter;
    public static boolean playsoundspecial;
    public static boolean playsounditem;
    public static boolean playsoundunique;
    public static String soundspecial;
    public static String sounditem;
    public static String soundunique;
    public static boolean conditionedcolorsallways;
    public static boolean championmcoloralways;
    public static String[] tilesFlowerSearch;
    public static PickRenderer _pickRenderer;

    public void init() {
        logger.log(Level.INFO, "[WurmEspMod] Initializing");
        xrayManager = new FeatureXRay();
        tilesFlowerManager = new FeatureFlowerTiles();
        tilesCloseByManager = new FeatureTilesCloseBy();
        tilesHighlightManager = new FeatureTilesHighlight();
        tilesCloseByWalkableManager = new FeatureTilesWalkable();
        xrayCronoManager = new CronoManager(xrayrefreshrate * 1000);
        tilesFlowerCronoManager = new CronoManager(1000L);
        tilesCloseByCronoManager = new CronoManager(1000L);
        tilesHighlightCronoManager = new CronoManager(1000L);
        tilesCloseByWalkableCronoManager = new CronoManager(1000L);
        new HandleDevInput();
        new ProjectileCellRenderable();
        new HeadsUpDisplayInit();
        new RenderPickedItem();
        new MobileModelRenderableInit();
        new MobileModelRenderableRemove();
        new GroundItemCellRenderableInit();
        new GroundItemCellRenderableRemove();
        new CmdShowDeedPlan();
    }

    public void configure(Properties properties) {
        ConfigUtils.DoConfig(properties);
        logger.log(Level.INFO, "[WurmEspMod] Config loaded");
    }

    static {
        logger = Logger.getLogger("WurmEspMod");
        modProperties = new Properties();
        pickableUnits = new ArrayList<Unit>();
        toRemove = new ArrayList<Unit>();
        _caveBuffer = null;
        _terrainBuffer = null;
        _terrainBuffer2 = null;
        _terrain = new CopyOnWriteArrayList<float[]>();
        _flowerTerrain = new CopyOnWriteArrayList<float[]>();
        _closeByTerrain = new CopyOnWriteArrayList<float[]>();
        _closeByWalkableTerrain = new CopyOnWriteArrayList<float[]>();
        _tilesHighlightBase = new CopyOnWriteArrayList<int[]>();
        _tilesHighlightTerrain = new CopyOnWriteArrayList<float[]>();
        search = "defaultnosearch";
        searchType = SEARCHTYPE.NONE;
        players = true;
        mobs = false;
        animals = false;
        specials = true;
        items = true;
        tilesFlower = false;
        uniques = true;
        conditioned = true;
        tilescloseby = false;
        deedsize = false;
        tileshighlight = false;
        tilesclosebynotrideable = false;
        xray = false;
        xraythread = true;
        xrayrefreshthread = true;
        xraydiameter = 32;
        xrayrefreshrate = 5;
        tilenotrideable = 40;
        flowerdiameter = 64;
        playsoundspecial = true;
        playsounditem = true;
        playsoundunique = true;
        soundspecial = "sound.fx.conch";
        sounditem = "sound.fx.conch";
        soundunique = "sound.fx.conch";
        conditionedcolorsallways = false;
        championmcoloralways = false;
    }

    public static enum SEARCHTYPE {
        NONE,
        HOVER,
        MODEL,
        BOTH;

    }
}

