package mods.phlenum.cll.blocks;

import java.util.Random;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 30 July 2015
 */

public class BlockLemonTreeSapling extends BlockBush implements IGrowable {

	public BlockLemonTreeSapling(String unloc, SoundType sound){
		super(Material.grass);
		setStepSound(sound);
		setUnlocalizedName(unloc);
		setCreativeTab(CommonProxy.tabCLL);
		setRegistryName(unloc);
		GameRegistry.register(this);
	}
	
	@Override
	public int damageDropped(IBlockState state){
		return 0;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient){
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state){
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state){
		if(rand.nextInt(4) == 1){
			CommonProxy.WORLD_GEN_LEMON_TREE.generate(worldIn, rand, pos.add(0, -1, 0));
		}
	}

	
}
