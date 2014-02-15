package mods.ibuilder99.cll.blocks;

import java.util.Random;

import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class BlockLemonTreeLeavesHarvested extends BlockLeavesBase {

	@SideOnly(Side.CLIENT)
	private IIcon blockIconOpaque;
	
	public BlockLemonTreeLeavesHarvested(int id, String unloc, float hardness, float resistance, SoundType sound){
		super(Material.leaves, true);
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
		super.registerBlockIcons(par1IconRegister);
		blockIconOpaque = par1IconRegister.registerIcon(textureName + "Opaque");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		return CombustibleLemonLauncher.proxy.doFancyRender() ? blockIcon : blockIconOpaque;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube() {
		return !CombustibleLemonLauncher.proxy.doFancyRender();
	}
	
	@Override
	public Item getItemDropped(int par1, Random rand, int par2){
		return null;
	}

}