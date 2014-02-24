package mods.ibuilder99.cll.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.Reference;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class BlockLemonLog extends BlockLog {

	@SideOnly(Side.CLIENT)
	private IIcon topBottom;

	public BlockLemonLog(String unloc, float hardness, float resistance, SoundType sound){
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
		topBottom = par1IIconRegister.registerIcon(textureName + "TopBottom");
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int i){
		return topBottom;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int i){
		return blockIcon;
	}
	
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return 200;
	}

}
