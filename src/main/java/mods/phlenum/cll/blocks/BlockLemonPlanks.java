package mods.phlenum.cll.blocks;

import java.util.List;

import mods.phlenum.cll.CombustibleLemonLauncher;
import mods.phlenum.cll.lib.Reference;
import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Combustible Lemon Launcher
 * Minecraft Forge Modification
 */

public class BlockLemonPlanks extends BlockWood {
	
	public BlockLemonPlanks(String unloc, float hardness, float resistance, SoundType sound){
		super();
		setBlockName(unloc);
		setHardness(hardness);
		setResistance(resistance);
		setStepSound(sound);
		setBlockTextureName(Reference.TEXTURE_PREFIX + unloc);
		setCreativeTab(CombustibleLemonLauncher.TAB_COMBUSTIBLE_LEMON_LAUNCHER);
		GameRegistry.registerBlock(this, unloc);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister){
		blockIcon = par1IIconRegister.registerIcon(textureName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		return blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list){
		list.add(new ItemStack(item, 1, 0));
	}
	
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return 200;
	}

}
