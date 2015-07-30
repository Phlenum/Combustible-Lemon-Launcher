package mods.phlenum.cll.world;

import java.util.Random;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * Hopefully I'll never have to write such an algorithm again ;___;
 * TODO make it not f*** up worlds
 * @author Phil Julian
 * @date 30 July 2015
 */

public class WorldGenLemonTree extends WorldGenerator implements IWorldGenerator {

	private static final byte MINIMUM_TREE_HEIGHT = 4;
	private static final byte MINIMUM_LEAF_CONTAINING_LAYERS = 3;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		int x = chunkX + random.nextInt(16) + 8;
		int z = chunkZ + random.nextInt(16) + 8;
		int y = world.getChunkFromChunkCoords(chunkX, chunkZ).getHeight(new BlockPos(x, 0, z));
		final BlockPos POS = new BlockPos(x, y, z);
		if(world.getBlockState(POS).getBlock().canSustainPlant(world, POS, EnumFacing.UP, CommonProxy.blockLemonTreeSapling)){
			this.generate(world, random, POS);
		}
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos){
		pos = pos.add(0, 1, 0);
		final byte TREE_HEIGHT = (byte)(MINIMUM_TREE_HEIGHT + rand.nextInt(3));
		for(int i = pos.getY(); i < (pos.getY() + TREE_HEIGHT); i++){
			final byte current = (byte)(i - pos.getY());
			final byte blocksLeft = (byte)(TREE_HEIGHT - current);
			if(blocksLeft <= MINIMUM_LEAF_CONTAINING_LAYERS){
				fillLeavesAround(worldIn, rand, Math.min(blocksLeft, 2), pos.getX(), i, pos.getZ());
			}
			worldIn.setBlockState(new BlockPos(pos.getX(), i, pos.getZ()), CommonProxy.blockLemonTreeLog.getDefaultState());
		}
		worldIn.setBlockState(new BlockPos(pos.getX(), (pos.getY() + TREE_HEIGHT), pos.getZ()), randomLeafBlock(rand));
		return false;
	}
	
	private static final IBlockState randomLeafBlock(Random rand){
		int randomChance = rand.nextInt(5);
		return randomChance == 3 ? CommonProxy.blockLemonLeaves.getDefaultState() : CommonProxy.blockLemonLeavesHarvested.getDefaultState();
	}
	
	private static final void fillLeavesAround(World world, Random rand, int range, int xSrc, int ySrc, int zSrc){
		for(int x = (xSrc - range); x < (xSrc + range + 1); x++){
			for(int z = (zSrc - range); z < (zSrc + range + 1); z++){
				world.setBlockState(new BlockPos(x, ySrc, z), randomLeafBlock(rand));
			}
		}
	}

}
