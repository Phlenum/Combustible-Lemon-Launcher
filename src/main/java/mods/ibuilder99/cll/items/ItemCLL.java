package mods.ibuilder99.cll.items;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.Reference;
import net.minecraft.item.Item;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class ItemCLL extends Item {
	
	public ItemCLL(int id, String unloc){
		super();
		setUnlocalizedName(unloc);
		setTextureName(Reference.TEXTURE_PREFIX + unloc);
		setCreativeTab(CombustibleLemonLauncher.TAB_COMBUSTIBLE_LEMON_LAUNCHER);
		GameRegistry.registerItem(this, unloc);
	}
	
}
