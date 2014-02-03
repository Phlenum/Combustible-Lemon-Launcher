package mods.ibuilder99.cll.items;

import mods.ibuilder99.cll.lib.CLLConfiguration;
import mods.ibuilder99.cll.lib.IKeyListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class ItemCombustibleLemonLauncher extends ItemCLL implements IKeyListener {

	public ItemCombustibleLemonLauncher(int id, String unloc) {
		super(id, unloc);
		setMaxStackSize(1);
	}
	
	private static void doAction(EntityPlayer player, ItemStack itemstack){
		
	}
	
	/**
	 * Fire a lemon if the player configured 'useKeyToFire=false'
	 */
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		if(!CLLConfiguration.BO_useKeyToFire){
			doAction(par3EntityPlayer, par1ItemStack);
		}
		return par1ItemStack;
	}

	/**
	 * Fire a lemon if the player configured 'useKeyToFire=true'
	 */
	
	@Override
	public void onKeyPressed(String key, EntityPlayer player, ItemStack itemstack){
		if(CLLConfiguration.BO_useKeyToFire){
			doAction(player, itemstack);
		}
	}

}
