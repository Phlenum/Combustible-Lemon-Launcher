package mods.phlenum.cll;

import static mods.phlenum.cll.lib.Reference.*;

import mods.phlenum.cll.blocks.BlockLemonLeaves;
import mods.phlenum.cll.blocks.BlockLemonLeavesHarvested;
import mods.phlenum.cll.blocks.BlockLemonTreeLog;
import mods.phlenum.cll.blocks.BlockLemonTreePlanks;
import mods.phlenum.cll.blocks.BlockLemonTreeSapling;
import mods.phlenum.cll.items.ItemCombustibleLemonLauncher;
import mods.phlenum.cll.items.ItemLemon;
import mods.phlenum.cll.items.ItemLemonExplosive;
import mods.phlenum.cll.world.DamageSourceExplosiveLemon;
import mods.phlenum.cll.world.WorldGenLemonTree;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 Nov 2014
 */

//@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION, guiFactory="mods.phlenum.cll.client.gui.CLLGuiFactory", /*certificateFingerprint=MOD_FINGERPRINT,*/ canBeDeactivated=false)
@Mod(MOD_ID)
public class CombustibleLemonLauncher {
	
	public CombustibleLemonLauncher(){
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public static BlockLemonTreePlanks blockLemonTreePlanks;
	public static BlockLemonTreeLog blockLemonTreeLog;
	public static BlockLemonLeavesHarvested blockLemonLeavesHarvested;
	public static BlockLemonLeaves blockLemonLeaves;
	public static BlockLemonTreeSapling blockLemonTreeSapling;

	public static ItemLemon itemLemon;
	public static ItemLemonExplosive itemLemonExplosive;
	public static ItemCombustibleLemonLauncher itemCombustibleLemonLauncher;
	
	public static SoundEvent sound_CombustibleLemonLauncher_fire;
	public static SoundEvent sound_CombustibleLemonLauncher_outofammo;

	public static final DamageSourceExplosiveLemon DAMAGE_SOURCE_EXPLOSIVE_LEMON = new DamageSourceExplosiveLemon();
	public static final WorldGenLemonTree WORLD_GEN_LEMON_TREE = new WorldGenLemonTree();
	/*
	public static final ItemGroup CLL_GROUP = (new ItemGroup(101, MOD_ID){
		
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon(){
			return new ItemStack(itemLemon);
		}
	});
	*/
	
	private void commonSetup(final FMLCommonSetupEvent event){
		
	}
	
	private void clientSetup(FMLClientSetupEvent event){
		
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent){
			blockLemonTreePlanks = new BlockLemonTreePlanks(BLOCK_LEMON_TREE_PLANKS, 2.0f, 5.0f, SoundType.WOOD);
			
			blockRegistryEvent.getRegistry().registerAll(
					blockLemonTreePlanks
					);
		}
		
		@SubscribeEvent
		public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent){
			itemLemon = new ItemLemon(ITEM_LEMON, 5, 0.2f, false);
			
			itemRegistryEvent.getRegistry().registerAll(
					new ItemBlock(blockLemonTreePlanks, new Item.Properties()) {
						{
							setRegistryName(BLOCK_LEMON_TREE_PLANKS);
						}
						
						@Override
						public int getBurnTime(ItemStack itemStack){
							return 300;
						}
					},
					itemLemon
					);
		}
	}
	
	/*
	@Mod.EventHandler
	public void preInitializeMod(FMLPreInitializationEvent preInitEvent){
		CLLLogger.logInfo("Initializing Combustible Lemon Launcher " + MOD_VERSION);

		proxy.initializeBlocks();

		proxy.initializeItems();
		
		proxy.initializeModels();
		
		proxy.initializeEntityRender();
		
		proxy.initializeSoundEvents();
		
		CLLConfig.initializeConfig(preInitEvent.getSuggestedConfigurationFile());
	}

	@Mod.EventHandler
	public void initializeMod(FMLInitializationEvent initEvent){
		proxy.initializeWorld();

		proxy.initializeRenderers();
		
		proxy.initializePacketHandler();
	}*/

}

