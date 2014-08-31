package mods.phlenum.cll.world;

import java.util.Random;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * Minecraft Forge Modification
 */

public class LemonTreeGen implements IWorldGenerator {

	private static final WorldGenerator LEMON_TREE_WORLD_GEN = new WorldGenLemonTree();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		int x = chunkX + random.nextInt(16) + 8;
		int z = chunkZ + random.nextInt(16) + 8;
		int y = world.getHeightValue(x, z);
		Block base = world.getBlock(x, z, z);
		if(base.canSustainPlant(world, x, y, z, ForgeDirection.UP, CommonProxy.blockLemonTreeSapling)){
			LEMON_TREE_WORLD_GEN.generate(world, random, x, y, z);
		}
	}

}
