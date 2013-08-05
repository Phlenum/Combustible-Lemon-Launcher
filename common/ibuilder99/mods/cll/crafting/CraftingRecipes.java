package ibuilder99.mods.cll.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ibuilder99.mods.cll.items.LemonLauncherItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipes {
	
	public static void initCraftingRecipes(){
		GameRegistry.addRecipe(new ItemStack(LemonLauncherItems.LemonLauncher), new Object[]{
			"oii", " pr", "oii", Character.valueOf('i'), Item.ingotIron, Character.valueOf('p'), Block.pistonBase, Character.valueOf('r'), Item.redstone, Character.valueOf('o'), Block.obsidian
		});
		GameRegistry.addRecipe(new ItemStack(LemonLauncherItems.LemonExplosive), new Object[]{
			" t ", "sls", " t ", Character.valueOf('t'), Block.tnt, Character.valueOf('l'), LemonLauncherItems.Lemon, Character.valueOf('s'), Item.silk
		});
	}
}
