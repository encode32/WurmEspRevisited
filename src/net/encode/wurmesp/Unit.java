package net.encode.wurmesp;

import com.wurmonline.client.game.World;
import com.wurmonline.client.renderer.Color;
import com.wurmonline.client.renderer.PickRenderer;
import com.wurmonline.client.renderer.PickableUnit;
import com.wurmonline.client.renderer.WorldRender;
import com.wurmonline.client.renderer.backend.Primitive;
import com.wurmonline.client.renderer.backend.Queue;
import com.wurmonline.client.renderer.backend.RenderState;
import net.encode.wurmesp.util.ReUtils;

public class Unit {
	private World world;
    private long id;
    private String modelName;
    private String hoverName;
    private PickableUnit pickableUnit;
    private float[] color = new float[]{0.0f, 0.0f, 0.0f};
    private float[] conditionedcolor = new float[]{0.0f, 0.0f, 0.0f};
    private String condition;
    public static float[] colorPlayers = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorPlayersEnemy = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorMobs = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorMobsAggro = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorSpecials = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorSpotted = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorUniques = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorAlert = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorAngry = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorChampion = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorDiseased = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorFierce = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorGreenish = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorHardened = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorLurking = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorRaging = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorScared = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorSlow = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] colorSly = new float[]{0.0f, 0.0f, 0.0f};
    public static String[] aggroMOBS;
    public static String[] uniqueMOBS;
    public static String[] specialITEMS;
    public static String[] spottedITEMS;
    public static String[] conditionedMOBS;

    public Unit(World world, long id, PickableUnit pickableUnit, String modelName, String hoverName) {
    	this.world = world;
        this.id = id;
        this.pickableUnit = pickableUnit;
        this.modelName = modelName;
        this.hoverName = hoverName;
        this.determineColor();
    }

    public long getId() {
        return this.id;
    }

    public PickableUnit getPickableUnit() {
        return this.pickableUnit;
    }

    public float[] getColor() {
        return this.color;
    }

    public float[] getConditionedColor() {
        return this.conditionedcolor;
    }

    public String getHoverName() {
        return this.hoverName;
    }

    public String getModelName() {
        return this.modelName;
    }

    public boolean isPlayer() {
        return this.getModelName().contains("model.creature.humanoid.human.player") && !this.getModelName().contains("zombie");
    }

    public boolean isEnemyPlayer() {
        return this.getModelName().contains("ENEMY");
    }

    public boolean isMob() {
        return this.getModelName().contains("model.creature") && !this.getModelName().contains("humanoid.human");
    }

    public boolean isAggroMob() {
        for (String mob : aggroMOBS) {
            if (!this.getHoverName().contains(mob)) continue;
            return true;
        }
        return false;
    }

    public boolean isChampion() {
        return this.getHoverName().contains("champion");
    }

    public boolean isConditioned() {
        for (String condition : conditionedMOBS) {
            if (!this.getHoverName().contains(condition)) continue;
            this.condition = condition;
            return true;
        }
        return false;
    }

    public boolean isUnique() {
        for (String mob : uniqueMOBS) {
            if (!this.getHoverName().contains(mob)) continue;
            return true;
        }
        return false;
    }

    public boolean isSpecial() {
        for (String item : specialITEMS) {
            if (this.getHoverName().contains(item)) {
                return true;
            }
            if (!this.getModelName().contains(WurmEspMod.search)) continue;
            return true;
        }
        return false;
    }

    public boolean isSpotted() {
        for (String item : spottedITEMS) {
            if (this.getHoverName().contains(item)) {
                return true;
            }
            if (!this.getModelName().contains(WurmEspMod.search)) continue;
            return true;
        }
        if (WurmEspMod.searchType == WurmEspMod.SEARCHTYPE.HOVER) {
            if (this.getHoverName().contains(WurmEspMod.search)) {
                return true;
            }
        } else if (WurmEspMod.searchType == WurmEspMod.SEARCHTYPE.MODEL) {
            if (this.getModelName().contains(WurmEspMod.search)) {
                return true;
            }
        } else if (WurmEspMod.searchType == WurmEspMod.SEARCHTYPE.BOTH) {
            if (this.getHoverName().contains(WurmEspMod.search)) {
                return true;
            }
            if (this.getModelName().contains(WurmEspMod.search)) {
                return true;
            }
        }
        return false;
    }

    private void determineColor() {
        if (this.isPlayer()) {
            this.color = !this.isEnemyPlayer() ? colorPlayers : colorPlayersEnemy;
        } else if (this.isUnique()) {
            this.color = colorUniques;
        } else if (this.isAggroMob()) {
            this.color = colorMobsAggro;
        } else if (this.isMob()) {
            this.color = colorMobs;
        } else if (this.isSpecial()) {
            this.color = colorSpecials;
        } else if (this.isSpotted()) {
            this.color = colorSpotted;
        }
        if (this.isConditioned()) {
            float[] color = new float[]{0.0f, 0.0f, 0.0f};
            switch (this.condition) {
                case "alert": {
                    color = colorAlert;
                    break;
                }
                case "angry": {
                    color = colorAngry;
                    break;
                }
                case "champion": {
                    color = colorChampion;
                    break;
                }
                case "diseased": {
                    color = colorDiseased;
                    break;
                }
                case "fierce": {
                    color = colorFierce;
                    break;
                }
                case "greenish": {
                    color = colorGreenish;
                    break;
                }
                case "hardened": {
                    color = colorHardened;
                    break;
                }
                case "lurking": {
                    color = colorLurking;
                    break;
                }
                case "raging": {
                    color = colorRaging;
                    break;
                }
                case "scared": {
                    color = colorScared;
                    break;
                }
                case "slow": {
                    color = colorSlow;
                    break;
                }
                case "sly": {
                    color = colorSly;
                }
            }
            this.conditionedcolor = color;
        }
    }

    public void renderUnit(Queue queue, boolean showconditioned) {
        if (this.pickableUnit == null) {
            return;
        }
        float br = 3.5f;
        RenderState renderStateFill = new RenderState();
        RenderState renderStateFillDepth = new RenderState();
        RenderState renderStateOutline = new RenderState();
        Color color = new Color();
        if (this.isConditioned() && showconditioned) {
            color.set(this.conditionedcolor[0], this.conditionedcolor[1], this.conditionedcolor[2]);
        } else {
            color.set(this.color[0], this.color[1], this.color[2]);
        }
        renderStateFill.alphaval = color.a = br;
        color.a *= this.pickableUnit.getOutlineColor().a;
        
        WorldRender worldRenderer = (WorldRender)ReUtils.getField(this.world, "worldRenderer");
        PickRenderer.CustomPickFillRender customPickFill = (PickRenderer.CustomPickFillRender)ReUtils.getField(worldRenderer, "customPickFill");
        PickRenderer.CustomPickFillDepthRender customPickFillDepth = (PickRenderer.CustomPickFillDepthRender)ReUtils.getField(worldRenderer, "customPickFillDepth");
        PickRenderer.CustomPickOutlineRender customPickOutline = (PickRenderer.CustomPickOutlineRender)ReUtils.getField(worldRenderer, "customPickOutline");
        renderStateFill.twosided = false;
        renderStateFill.depthtest = Primitive.TestFunc.ALWAYS;
        renderStateFill.depthwrite = true;
        renderStateFill.customstate = customPickFill;
        this.pickableUnit.renderPicked(queue, renderStateFill, color);
        renderStateOutline.alphaval = color.a = br * 0.25f;
        color.a *= this.pickableUnit.getOutlineColor().a;
        renderStateOutline.twosided = false;
        renderStateOutline.depthtest = Primitive.TestFunc.LESS;
        renderStateOutline.depthwrite = false;
        renderStateOutline.blendmode = Primitive.BlendMode.ALPHABLEND;
        renderStateOutline.customstate = customPickOutline;
        this.pickableUnit.renderPicked(queue, renderStateOutline, color);
        renderStateFillDepth.customstate = customPickFillDepth;
        renderStateFillDepth.depthtest = Primitive.TestFunc.ALWAYS;
        this.pickableUnit.renderPicked(queue, renderStateFillDepth, color);
    }
}

