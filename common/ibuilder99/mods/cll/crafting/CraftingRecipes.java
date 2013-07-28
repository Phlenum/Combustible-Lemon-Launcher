package ibuilder99.mods.cll.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ibuilder99.mods.cll.items.LemonLauncherItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipes {
	
	public static void initCraftingRecipes(){
		GameRegistry.addRecipe(new ItemStack(LemonLauncherItems.LemonLauncher), new Object[]{
			"iii", " pr", "iii", Character.valueOf('i'), Item.ingotIron, Character.valueOf('p'), Block.pistonBase, Character.valueOf('r'), Item.redstone
		});
	}
}
