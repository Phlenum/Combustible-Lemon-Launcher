package mods.phlenum.cll.world;

import java.util.Random;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * Minecraft Forge Modification
 */

public class WorldGenLemonTree extends WorldGenerator {
	
	private static final byte MINIMUM_TREE_HEIGHT = 4;
	private static final byte MINIMUM_LEAF_CONTAINING_LAYERS = 3;
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		final byte TREE_HEIGHT = (byte)(MINIMUM_TREE_HEIGHT + rand.nextInt(3));
		for(int i = y; i < (y + TREE_HEIGHT); i++){
			final byte current = (byte)(i - y);
			final byte blocksLeft = (byte)(TREE_HEIGHT - current);
			if(blocksLeft <= MINIMUM_LEAF_CONTAINING_LAYERS){
				fillLeavesAround(world, rand, Math.min(blocksLeft, 2), x, i, z);
			}
			world.setBlock(x, i, z, CommonProxy.blockLemonTreeLog);
		}
		world.setBlock(x, (y + TREE_HEIGHT), z, randomLeafBlock(rand));
		return false;
	}
	
	private static final Block randomLeafBlock(Random rand){
		int randomChance = rand.nextInt(5);
		return randomChance == 3 ? CommonProxy.blockLemonTreeLeaves : CommonProxy.blockLemonTreeLeavesHarvested;
	}
	
	private static final void fillLeavesAround(World world, Random rand, int range, int xSrc, int ySrc, int zSrc){
		for(int x = (xSrc - range); x < (xSrc + range + 1); x++){
			for(int z = (zSrc - range); z < (zSrc + range + 1); z++){
				world.setBlock(x, ySrc, z, randomLeafBlock(rand));
			}
		}
	}

}
