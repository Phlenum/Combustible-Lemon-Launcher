package mods.phlenum.cll.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Combustible Lemon Launcher
 * Minecraft Forge Modification
 */

public interface IKeyListener {

	public void onKeyPressed(String key, EntityPlayer player, ItemStack itemstack);
	
}
