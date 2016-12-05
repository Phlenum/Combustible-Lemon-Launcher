package mods.phlenum.cll.lib;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 Nov 2014
 */

public final class Reference {

	public static final String MOD_NAME = "Combustible Lemon Launcher";
	public static final String MOD_ID = "cll";
	public static final String MOD_VERSION = "@VERSION@";
	public static final String MOD_CHANNEL = "cll";
	public static final String MOD_FINGERPRINT = "@FINGERPRINT@";
	public static final String TEXTURE_PREFIX = Reference.MOD_ID + ":";

	public static final String ITEM_LEMON = "lemon";
	public static final String ITEM_LEMON_EXPLOSIVE = "lemon_explosive";
	public static final String ITEM_COMBUSTIBLE_LEMON_LAUNCHER = "combustiblelemonlauncher";
	
	public static final String OREDICT_LEMON = "foodLemon";

	public static final String BLOCK_LEMON_LEAVES = "lemon_leaves";
	public static final String BLOCK_LEMON_LEAVES_HARVESTED = "lemon_leaves_harvested";
	public static final String BLOCK_LEMON_TREE_LOG = "lemon_tree_log";
	public static final String BLOCK_LEMON_TREE_PLANKS = "lemon_tree_planks";
	public static final String BLOCK_LEMON_TREE_SAPLING = "lemon_tree_sapling";
	
	public static final String SOUNDEVENT_COMBUSTIBLELEMONLAUNCHER_FIRE = "item.combustiblelemonlauncher.fire";
	public static final String SOUNDEVENT_COMBUSTIBLELEMONLAUNCHER_OUTOFAMMO = "item.combustiblelemonlauncher.outofammo";

	public static final String MODEL_COMBUSTIBLE_LEMON_LAUNCHER = MOD_ID.toLowerCase() + ":models/combustible_lemon_launcher.obj";
	public static final String TEXTURE_COMBUSTIBLE_LEMON_LAUNCHER = MOD_ID.toLowerCase() + ":textures/models/combustible_lemon_launcher.png";

	public static final String ENTITY_LEMON = MOD_ID.toLowerCase() + ":entity_lemon";
	public static final String DAMAGE_SOURCE_EXPLOSIVE_LEMON = "explosive_lemon";
	
	public static final String CONFIG_GENERATE_TREES = "Generate lemon trees";

}
