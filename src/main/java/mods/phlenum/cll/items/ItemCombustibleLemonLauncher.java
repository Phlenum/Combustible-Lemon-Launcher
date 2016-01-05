package mods.phlenum.cll.items;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 05 Jan 2016
 */

public class ItemCombustibleLemonLauncher extends Item {
	
	public ItemCombustibleLemonLauncher(String unloc){
		super();
		setCreativeTab(CommonProxy.tabCLL);
		setUnlocalizedName(unloc);
		GameRegistry.registerItem(this, unloc);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack){
		return EnumAction.BOW;
	}

}
