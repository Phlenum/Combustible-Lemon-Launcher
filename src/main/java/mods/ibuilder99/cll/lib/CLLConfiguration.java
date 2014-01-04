package mods.ibuilder99.cll.lib;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class CLLConfiguration {
	
	public static int ID_itemLemon;
	public static int ID_itemExplosiveLemon;
	public static int ID_itemCombustibleLemonLauncher;
	
	public static int ID_blockLemonLeaves;
	public static int ID_blockLemonLeavesHarvested;
	public static int ID_blockLemonTreeSapling;
	
	public static boolean BO_useKeyToFire;
	
	private static final String CATEGORY_ITEM = "Item";
	private static final String CATEGORY_BLOCK = "Block";
	
	public static void initializeConfiguration(File configFile){
		Configuration configObj = new Configuration(configFile);
		
		ID_itemLemon = configObj.get(CATEGORY_ITEM, Reference.ITEM_LEMON_LAUNCHER, 3000).getInt();
		ID_itemExplosiveLemon = configObj.get(CATEGORY_ITEM, Reference.ITEM_LEMON_EXPLOSIVE, 3001).getInt();
		ID_itemCombustibleLemonLauncher = configObj.get(CATEGORY_ITEM, Reference.ITEM_LEMON_LAUNCHER, 3002).getInt();
		
		ID_blockLemonLeaves = configObj.get(CATEGORY_BLOCK, Reference.BLOCK_LEMON_LEAVES, 3003).getInt();
		ID_blockLemonLeavesHarvested = configObj.get(CATEGORY_BLOCK, Reference.BLOCK_LEMON_LEAVES_HARVESTED, 3004).getInt();
		ID_blockLemonTreeSapling = configObj.get(CATEGORY_BLOCK, Reference.BLOCK_LEMON_SAPLING, 3005).getInt();
		
		BO_useKeyToFire = configObj.get(CATEGORY_GENERAL, "useKeyToFire", false).getBoolean(false);
		
		if(configObj.hasChanged()){
			configObj.save();
		}
	}

}
