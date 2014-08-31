package mods.phlenum.cll.items;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.phlenum.cll.CombustibleLemonLauncher;
import mods.phlenum.cll.lib.Reference;
import net.minecraft.item.Item;

/**
 * Combustible Lemon Launcher
 * Minecraft Forge Modification
 */

public class ItemCLL extends Item {
	
	public ItemCLL(String unloc){
		super();
		setUnlocalizedName(unloc);
		setTextureName(Reference.TEXTURE_PREFIX + unloc);
		setCreativeTab(CombustibleLemonLauncher.TAB_COMBUSTIBLE_LEMON_LAUNCHER);
		GameRegistry.registerItem(this, unloc);
	}
	
}
