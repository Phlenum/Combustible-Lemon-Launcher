package mods.ibuilder99.cll.blocks;

import java.util.List;
import java.util.Random;

import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.Reference;
import mods.ibuilder99.cll.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class BlockLemonTreeLeavesHarvested extends BlockLeaves {

	@SideOnly(Side.CLIENT)
	private IIcon blockIconOpaque;

	public BlockLemonTreeLeavesHarvested(String unloc, float hardness, float resistance, SoundType sound){
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
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon(textureName);
		blockIconOpaque = par1IconRegister.registerIcon(textureName + "Opaque");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		return CombustibleLemonLauncher.proxy.doFancyRender() ? blockIcon : blockIconOpaque;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tabs, List list){
		list.add(new ItemStack(item));
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int I_think_its_metadata){
		// From base class
		return 16777215;
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z){
		// From base class
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube() {
		return !CombustibleLemonLauncher.proxy.doFancyRender();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int meta) {
		return CombustibleLemonLauncher.proxy.doFancyRender();
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float I_dont_know, int what_these_are){
		if(!world.isRemote){
			int randomChance = world.rand.nextInt(10);
			// P(sapling drop) = 1/10 = 0.1
			if(randomChance == 1){
				dropBlockAsItem(world, x, y, z, new ItemStack(CommonProxy.blockLemonTreeSapling));
			}
		}
	}

	@Override
	public int damageDropped(int meta){
		return 0;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z){
		return 0;
	}


	@Override
	public Item getItemDropped(int par1, Random rand, int par2){
		return null;
	}

	@Override
	public String[] func_150125_e(){
		return new String[]{Reference.BLOCK_LEMON_TREE_LEAVES_HARVESTED};
	}

}