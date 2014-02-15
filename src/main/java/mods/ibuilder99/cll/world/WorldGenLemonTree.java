package mods.ibuilder99.cll.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import mods.ibuilder99.cll.blocks.BlockLemonTreeLeaves;
import mods.ibuilder99.cll.blocks.BlockLemonTreeLeavesHarvested;
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
	
	/**
	 * <b>W</b>orld<b>G</b>en<b>L</b>emon<b>T</b>ree minimum height
	 */
	
	private static final int WGLT_MINIMUM_HEIGHT = 5;
	
	/**
	 * What's the percentage of log blocks of the total tree height
	 * the tree will begin with?
	 * The remaining 60% will contain leaves
	 */
	private static final float WGLT_ONLYLOG_SECTION_PERCENTAGE = 40.0F; 
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		/*
		 * Randomly determine the height of our tree. 
		 * This will be something like WGLT_MINIMUM_HEIGHT + a random number between 0 (inclusive) and 4.  
		 */
		int treeHeight = WGLT_MINIMUM_HEIGHT + world.rand.nextInt(4);
		 /*
		  * A chunk is 16x16 so add a random integer between 0 and 16 to
		  * the chunkX and chunkZ to get a random 2D position. 
		  */
		int X = chunkX + world.rand.nextInt(16);
		int Z = chunkZ + world.rand.nextInt(16);
		/*
		 * Start off with a zero'd integer.
		 */
		int Y = 0;
		/* 
		 * Add 1 to Y and check whether the tree can spawn there. Stop counting
		 * when y exceeds the worlds maximum block height.
		 */		
		for(Y = 0; Y < world.provider.getActualHeight(); Y++){
			if(canTreeSpawnAt(world, X, Y, Z, treeHeight)){
				/*
				 * We found a nice spot for our tree.
				 */
				break;
			}
			// Try again until you reach the maximum block height :-( ...
		}
		/*
		 * Finally launch the generator-algorithm!
		 */
		generateLemonTree(world, X, Y, Z, treeHeight);
	}
	
	/**
	 * Launches the generator-algorithm
	 * @author Phil Julian (aka iBuilder99)
	 * @param world - What world?
	 * @param X - what x coordinate?
	 * @param Y - what y coordinate?
	 * @param Z - what z coordinate?
	 * @param height - how high will the tree be?
	 */
	
	private static void generateLemonTree(World world, int X, int Y, int Z, int height){
		// Reference of the 'selected' block.
		Block selectedBlock;
		// Current height from the tree's point of view.
		int deltaHeight;
		/*
		 * Start at Y, iterate up until you reach the treeHeight.
		 */
		for(int currY = Y; currY < (Y + height - 1); currY++){
			// self explaining 
			selectedBlock = world.getBlock(X, Y, Z);
			// Compute the current delta height.
			deltaHeight = currY - Y;
			
			/*
			 *  I split up my tree into 2 sections:
			 *  - the 'OnlyLog'-section
			 *  - the 'AsWellAsLeaf'-section
			 *  Now determine what section you are in at the moment.
			 *  First there are 3 blocks of only 'Logs'. After that
			 *  start generating leaves as well.
			 */
			if(deltaHeight <= computeIntPercentage(WGLT_ONLYLOG_SECTION_PERCENTAGE, height)){
				// OnlyLog section - simply place a log block
				world.setBlock(X, currY, Z, Blocks.log);
			}else{
				// AsWellAsLeaf section
				// Don't override any blocks when placing leaves
				if(world.isAirBlock(X, currY, Z)){
					placeLeavesRandomType(world, X + 1, currY, Z);
					placeLeavesRandomType(world, X - 1, currY, Z);
					placeLeavesRandomType(world, X, currY, Z + 1);
					placeLeavesRandomType(world, X, currY, Z - 1);
					placeLeavesRandomType(world, X + 1, currY, Z + 1);
					placeLeavesRandomType(world, X - 1, currY, Z + 1);
				}
				// Continue the trunk ...
				world.setBlock(X, currY, Z, Blocks.log);
			}
		}
		// ... and place the tree's last block
		placeLeavesRandomType(world, X, Y + height, Z);
	}
	
	/**
	 * Checks whether a tree can spawn at the specified coordinates
	 * @author Phil Julian (aka iBuilder99)
	 * @param world - What world?
	 * @param X - what x coordinate?
	 * @param Y - what y coordinate?
	 * @param Z - what z coordinate?
	 * @param height - how high will the tree be?
	 * @return Can a tree spawn here?
	 */
	
	private static boolean canTreeSpawnAt(World world, int X, int Y, int Z, int height){
		// This is the block our tree will stand on
		Block baseBlock = world.getBlock(X, Y, Z);
		if(
				baseBlock.canSustainPlant(world, X, Y, Z, ForgeDirection.UP, (BlockSapling)CommonProxy.blockLemonTreeSapling) && // Is the baseBlock capable of holding plants? 
				baseBlock.isReplaceable(world, X, Y, Z) && // Is the baseBlock replaceable?
				((Y + height) < world.getActualHeight()) // Is there enough space for our tree?
		){
			// Notify the baseBlock about this growth
			baseBlock.onPlantGrow(world, X, Y - 1, Z, X, Y, Z);
			return true;
			// *method exit*
		}
		return false;
	}
	
	/**
	 * Places either a {@link BlockLemonTreeLeaves} or a {@link BlockLemonTreeLeavesHarvested} depending on
	 * a random chance
	 * @author Phil Julian (aka iBuilder99)
	 * @param world - What world?
	 * @param X - what x coordinate?
	 * @param Y - what y coordinate?
	 * @param Z - what z coordinate?
	 */
	
	private static void placeLeavesRandomType(World world, int X, int Y, int Z){
		// A chance of 0.16 for a matured lemon tree leaf to generate
		int randomChance = world.rand.nextInt(6);
		world.setBlock(X, Y, Z, randomChance == 5 ? CommonProxy.blockLemonTreeLeaves : CommonProxy.blockLemonTreeLeaves);
	}
	
	/**
	 * Helper method for computing percentages
	 * @author Phil Julian (aka iBuilder99)
	 * @param percentage - what's the percentage of this section?
	 * @param treeHeight - how high will the tree be?
	 * @return The amount of blocks for this section
	 */
	
	private static int computeIntPercentage(float percentage, int treeHeight){
		// Compute the result
		float result = ((float)treeHeight * (percentage / 100.0F));
		// Multiply it by the treeHeight and cast it to an integer
		return (int)(treeHeight * percentage);
	}

}
