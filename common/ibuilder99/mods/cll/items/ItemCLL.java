package ibuilder99.mods.cll.items;

import ibuilder99.mods.cll.CombustibleLemonLauncher;
import ibuilder99.mods.cll.util.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCLL extends Item {
	
	public String name;
	
	public ItemCLL(int id, String unloc, String loc_en){
		super(id - 256);
		name = unloc;
		setUnlocalizedName(unloc);
		setCreativeTab(CombustibleLemonLauncher.tabLemonLauncher);
		LanguageRegistry.addName(this, loc_en);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + name);
	}
}