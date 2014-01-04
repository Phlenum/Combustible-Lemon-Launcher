package mods.ibuilder99.cll.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class ItemCombustibleLemonLauncher extends ItemCLL {

	public ItemCombustibleLemonLauncher(int id, String unloc, String en_US) {
		super(id, unloc, en_US);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		return par1ItemStack;
	}

}
