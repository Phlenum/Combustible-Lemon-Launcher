package ibuilder99.mods.cll;

import ibuilder99.mods.cll.items.LemonLauncherItems;
import ibuilder99.mods.cll.util.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabLemonLauncher extends CreativeTabs {

	public TabLemonLauncher(int par1, String par2Str) {
		super(par1, par2Str);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel(){
		return Reference.MOD_NAME;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack(){
		return new ItemStack(LemonLauncherItems.LemonLauncher);
	}

}
