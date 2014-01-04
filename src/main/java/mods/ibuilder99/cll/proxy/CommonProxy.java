package mods.ibuilder99.cll.proxy;

import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.ibuilder99.cll.items.ItemCLL;
import mods.ibuilder99.cll.items.ItemCombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.CLLConfiguration;
import mods.ibuilder99.cll.lib.Reference;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class CommonProxy {
	
	public static ItemCLL itemLemon;
	public static ItemCLL itemCombustibleLemonLauncher;
	
	public void initializeItems(){
		//TODO: Initialize mod items
		itemLemon = new ItemCLL(CLLConfiguration.ID_itemLemon, Reference.ITEM_LEMON);
		itemCombustibleLemonLauncher = new ItemCombustibleLemonLauncher(CLLConfiguration.ID_itemCombustibleLemonLauncher, Reference.ITEM_LEMON_LAUNCHER);
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
