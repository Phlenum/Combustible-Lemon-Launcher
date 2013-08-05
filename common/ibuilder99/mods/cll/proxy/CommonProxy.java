package ibuilder99.mods.cll.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import ibuilder99.mods.cll.CombustibleLemonLauncher;
import ibuilder99.mods.cll.blocks.LemonLauncherBlocks;
import ibuilder99.mods.cll.blocks.tileentity.TileEntityBlueGel;
import ibuilder99.mods.cll.entity.EntityCombustibleLemon;
import ibuilder99.mods.cll.util.EventListener;
import ibuilder99.mods.cll.util.Reference;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void loadLocalizations(){}

	public void registerRenderers(){}

	public void registerEntities(){
		EntityRegistry.registerModEntity(EntityCombustibleLemon.class, Reference.ENTITY_COMBUSTABLE_LEMON, EntityRegistry.findGlobalUniqueEntityId(), CombustibleLemonLauncher.instance, 50, 20, true);
	}

	public void registerKeyBinding(){}

	public void registerWorldGen(){
		GameRegistry.registerWorldGenerator(new WorldGenLemonTree());
		ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(LemonLauncherBlocks.LemonSapling), 3, 5, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(LemonLauncherBlocks.LemonSapling), 3, 5, 10));
		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(LemonLauncherBlocks.LemonSapling), 3, 5, 10));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(LemonLauncherBlocks.LemonSapling), 3, 5, 10));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(LemonLauncherBlocks.LemonSapling), 3, 5, 10));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(LemonLauncherBlocks.LemonSapling), 3, 5, 10));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(LemonLauncherBlocks.LemonSapling), 3, 5, 10));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(LemonLauncherBlocks.LemonSapling), 3, 5, 10));
	}
	
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityBlueGel.class, Reference.TILE_ENTITY_BLUE_GEL);
	}
	
	public void registerEventListener() {
		MinecraftForge.EVENT_BUS.register(new EventListener());
	}

	public void generateTree(int x, int y, int z, World world, int leaves, int log, int leavesgrowing){
		//Block soil = Block.blocksList[world.getBlockId(x, y, z)];
		//System.out.println(x + " " + y + " " + z);
		for(int x1 = x - 2; x1 < x + 3; x1++){
			for(int z1 = z - 2; z1 < z + 3; z1++){
				world.setBlock(x1, y + 3, z1, world.rand.nextInt(2) == 1 ? leaves : leavesgrowing);
				world.setBlock(x1, y + 4, z1, world.rand.nextInt(2) == 1 ? leaves : leavesgrowing);
			}
		}
		for(int x2 = x - 1; x2 < x + 2; x2++){
			for(int z2 = z - 1; z2 < z + 2; z2++){
				world.setBlock(x2, y + 5, z2, world.rand.nextInt(2) == 1 ? leaves : leavesgrowing);
			}
		}
		world.setBlock(x, y, z, log);
		world.setBlock(x, y + 1, z, log);
		world.setBlock(x, y + 2, z, log);
		world.setBlock(x, y + 3, z, log);
		world.setBlock(x, y + 4, z, log);
		world.setBlock(x, y + 5, z, log);
		world.setBlock(x, y + 6, z, world.rand.nextInt(2) == 1 ? leaves : leavesgrowing);

	}

	private class WorldGenLemonTree implements IWorldGenerator {

		private List<Integer> validBiomes = new ArrayList<Integer>();

		public WorldGenLemonTree(){
			validBiomes.add(BiomeGenBase.forest.biomeID);
			validBiomes.add(BiomeGenBase.forestHills.biomeID);
			validBiomes.add(BiomeGenBase.swampland.biomeID);
			validBiomes.add(BiomeGenBase.extremeHillsEdge.biomeID);
			validBiomes.add(BiomeGenBase.extremeHills.biomeID);
			validBiomes.add(BiomeGenBase.taiga.biomeID);
		}

		private final Block LOG = Block.wood;
		private final Block LEAVES = LemonLauncherBlocks.LemonLeaves;

		@Override
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
			if(world.provider.dimensionId == 0){
				int x = chunkX + random.nextInt(16);
				int z = chunkZ + random.nextInt(16);
				int y = 0;
				for(y = world.getHeightValue(x, z) - 3; y < 80; y++){
					Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
					if(block != null && block.blockID == Block.grass.blockID){
						break;
					}
				}

				if(world.getBlockId(x, y - 1, z) != Block.grass.blockID){
					return;
				}
				for(int checkX = x - 5; checkX < x + 6; checkX++){
					for(int checkZ = z - 5; checkZ < z + 6; checkZ++){
						for(int checkY = y - 5; checkY < y + 5; checkY++){
							if(world.getBlockId(checkX, checkY, checkZ) == LEAVES.blockID || world.getBlockId(checkX, checkY, checkZ) == LOG.blockID){
								return;
							}	
						}
					}
				}
				BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
				if(isValidBiome(biome)){
					CombustibleLemonLauncher.proxy.generateTree(x, y, z, world, LEAVES.blockID, LOG.blockID, LemonLauncherBlocks.LemonLeavesHarvested.blockID);
				}
			}
		}

		private boolean isValidBiome(BiomeGenBase biome){
			for(int i = 0; i < validBiomes.size(); i++){
				Integer id = validBiomes.get(i);
				if(id.equals(Integer.valueOf(biome.biomeID))){
					return true;
				}
			}
			return false;
		}

	}
}
