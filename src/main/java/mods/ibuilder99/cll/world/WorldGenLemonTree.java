package mods.ibuilder99.cll.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import mods.ibuilder99.cll.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class WorldGenLemonTree implements IWorldGenerator {

	private static final int WORLDGEN_LEMONTREE_MIN_HEIGHT = 5;
	private static final int WORLDGEN_LEMONTREE_MARGIN = 16;
	private static final Block WORLDGEN_LEMONTREE_TRUNK = Blocks.log;
	private static final BlockSapling WORLDGEN_LEMONTREE_PLANT = CommonProxy.blockLemonTreeSapling;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		final int x = chunkX + world.rand.nextInt(16) + WORLDGEN_LEMONTREE_MARGIN;
		final int z = chunkZ + world.rand.nextInt(16) + WORLDGEN_LEMONTREE_MARGIN;
		final int y = world.getHeightValue(x, z);
		generateLemonTree(world, x, y, z);
	}

	public static final void generateLemonTree(World world, int x, int y, int z){
		Block baseBlock = world.getBlock(x, y - 1, z);		
		if(!baseBlock.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, CommonProxy.blockLemonTreeSapling)
				&& !enoughSpaceAround(world, x, y, z, 10)){
			return;
		}
		//System.out.println(baseBlock.getUnlocalizedName() + " " + x + " " + y + " " + z);
		final int lemonTreeHeight = WORLDGEN_LEMONTREE_MIN_HEIGHT + world.rand.nextInt(4);
		placeTrunk(world, x, y, z, lemonTreeHeight);
		generateLeaves(world, x, y, z, lemonTreeHeight);
	}

	private static final void placeTrunk(World world, int x, int y, int z, int lemonTreeHeight){
		for(int selHeight = y; selHeight < (y + lemonTreeHeight - 1); selHeight++){
			world.setBlock(x, selHeight, z, WORLDGEN_LEMONTREE_TRUNK);
		}
		world.setBlock(x, y + lemonTreeHeight, z, CommonProxy.blockLemonTreeLeaves);
	}

	private static final boolean enoughSpaceAround(World world, int x, int y, int z, int offset){
		for(int x1 = (x - offset); x1 <= (x + offset); x1++){
			for(int y1 = (y - offset); y1 <= (y + offset); y1++){
				for(int z1 = (z - offset); z1 <= (z + offset); z1++){
					if(!world.isAirBlock(x1, y1, z1)){
						return false;
					}
				}
			}
		}
		return true;
	}

	private static final void generateLeaves(World world, int x, int y, int z, int lemonTreeHeight){

	}

}
