package mods.ibuilder99.cll.items;

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
		setCreativeTab(CombustibleLemonLauncher.tabCombustibleLemonLauncher);
		field_150901_e.func_148756_a(id, unloc, this);
	}
	
}
