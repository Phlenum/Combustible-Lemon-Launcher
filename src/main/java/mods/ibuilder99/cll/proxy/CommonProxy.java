package mods.ibuilder99.cll.proxy;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.ibuilder99.cll.blocks.BlockLemonLeaves;
import mods.ibuilder99.cll.blocks.BlockLemonLeavesHarvested;
import mods.ibuilder99.cll.items.ItemCLL;
import mods.ibuilder99.cll.items.ItemCombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.Reference;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class CommonProxy {
	
	public static ItemCLL itemLemon;
	public static ItemCLL itemLemonExplosive;
	public static ItemCombustibleLemonLauncher itemCombustibleLemonLauncher;
	
	public static BlockLemonLeaves blockLemonLeaves;
	public static BlockLemonLeavesHarvested blockLemonLeavesHarvested;
	
	public void initializeItems(){
		itemLemon = new ItemCLL(0, Reference.ITEM_LEMON);
		itemLemonExplosive = new ItemCLL(1, Reference.ITEM_LEMON_EXPLOSIVE);
		itemCombustibleLemonLauncher = new ItemCombustibleLemonLauncher(2, Reference.ITEM_LEMON_LAUNCHER);
		
		blockLemonLeaves = new BlockLemonLeaves(0, Reference.BLOCK_LEMON_LEAVES, 0.2F, 0.2F, Block.field_149779_h);
		blockLemonLeavesHarvested = new BlockLemonLeavesHarvested(1, Reference.BLOCK_LEMON_LEAVES_HARVESTED, 0.2F, 0.2F, Block.field_149779_h);
	}
	
	public void initializeBlocks(){
		//TODO: Initialize mod blocks
	}
	
	public void initializeLocalizations(){
		for(String lang : Reference.AVAIBABLE_LOCALIZATIONS){
			LanguageRegistry.instance().loadLocalization("/assets/" + Reference.MOD_ID.toLowerCase() + "/lang/" + lang + ".lang", lang, false);
		}
	}
	
	public void initializeRenderers(){}
	
}
