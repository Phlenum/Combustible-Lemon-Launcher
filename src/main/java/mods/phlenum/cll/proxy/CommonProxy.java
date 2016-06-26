package mods.phlenum.cll.proxy;

import static mods.phlenum.cll.lib.Reference.*;
import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.EnumMap;

import mods.phlenum.cll.CombustibleLemonLauncher;
import mods.phlenum.cll.blocks.BlockLemonLeaves;
import mods.phlenum.cll.blocks.BlockLemonLeavesHarvested;
import mods.phlenum.cll.blocks.BlockLemonTreeLog;
import mods.phlenum.cll.blocks.BlockLemonTreePlanks;
import mods.phlenum.cll.blocks.BlockLemonTreeSapling;
import mods.phlenum.cll.entity.EntityLemon;
import mods.phlenum.cll.items.ItemCombustibleLemonLauncher;
import mods.phlenum.cll.items.ItemLemon;
import mods.phlenum.cll.items.ItemLemonExplosive;
import mods.phlenum.cll.network.CLLPacketHandler;
import mods.phlenum.cll.network.packets.CLLPacket;
import mods.phlenum.cll.world.DamageSourceExplosiveLemon;
import mods.phlenum.cll.world.WorldGenLemonTree;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 Nov 2014
 */

public class CommonProxy {

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

	public static EnumMap<Side, FMLEmbeddedChannel> cllChannel;

	public static final CreativeTabs tabCLL = new CreativeTabs(MOD_ID){

		@Override
		public String getTranslatedTabLabel(){
			return MOD_NAME;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return itemCombustibleLemonLauncher;
		}

	};

	public void initializeBlocks(){
		blockLemonTreePlanks = new BlockLemonTreePlanks(BLOCK_LEMON_TREE_PLANKS, 2.0f, 5.0f, SoundType.WOOD);
		blockLemonTreeLog = new BlockLemonTreeLog(BLOCK_LEMON_TREE_LOG, SoundType.WOOD);
		
		blockLemonLeavesHarvested = new BlockLemonLeavesHarvested(BLOCK_LEMON_LEAVES_HARVESTED);
		blockLemonLeaves = new BlockLemonLeaves(BLOCK_LEMON_LEAVES);
		blockLemonTreeSapling = new BlockLemonTreeSapling(BLOCK_LEMON_TREE_SAPLING, SoundType.PLANT);
		
		GameRegistry.register((new ItemBlock(blockLemonTreePlanks).setRegistryName(BLOCK_LEMON_TREE_PLANKS)));
		GameRegistry.register((new ItemBlock(blockLemonTreeLog).setRegistryName(BLOCK_LEMON_TREE_LOG)));
		GameRegistry.register((new ItemBlock(blockLemonLeavesHarvested).setRegistryName(BLOCK_LEMON_LEAVES_HARVESTED)));
		GameRegistry.register((new ItemBlock(blockLemonLeaves).setRegistryName(BLOCK_LEMON_LEAVES)));
		GameRegistry.register((new ItemBlock(blockLemonTreeSapling).setRegistryName(BLOCK_LEMON_TREE_SAPLING)));
	}

	public void initializeItems(){
		itemLemon = new ItemLemon(ITEM_LEMON, 5, 0.2f, false);
		itemLemonExplosive = new ItemLemonExplosive(ITEM_LEMON_EXPLOSIVE, 5, 0.2f, false);
		itemCombustibleLemonLauncher = new ItemCombustibleLemonLauncher(ITEM_COMBUSTIBLE_LEMON_LAUNCHER);
	}
	
	public void initializeSoundEvents(){
		sound_CombustibleLemonLauncher_fire = registerSound(SOUNDEVENT_COMBUSTIBLELEMONLAUNCHER_FIRE);
		sound_CombustibleLemonLauncher_outofammo = registerSound(SOUNDEVENT_COMBUSTIBLELEMONLAUNCHER_OUTOFAMMO);
	}

	public void initializeRenderers(){}

	public void initializeEntityRender(){}

	public void initializeModels(){}

	public void initializeWorld(){
		GameRegistry.addShapedRecipe(new ItemStack(itemCombustibleLemonLauncher), new Object[]{
				"iio",
				"rp ",
				"iio",
				Character.valueOf('i'), new ItemStack(Items.IRON_INGOT),
				Character.valueOf('o'), new ItemStack(Blocks.OBSIDIAN),
				Character.valueOf('r'), new ItemStack(Items.REDSTONE),
				Character.valueOf('p'), new ItemStack(Blocks.PISTON)
		});
		GameRegistry.addRecipe(new ItemStack(itemLemonExplosive), new Object[]{
				" s ",
				"tlt",
				" s ",
				Character.valueOf('s'), new ItemStack(Items.STRING),
				Character.valueOf('t'), new ItemStack(Blocks.TNT),
				Character.valueOf('l'), new ItemStack(itemLemon)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(blockLemonTreePlanks, 4), new Object[]{ blockLemonTreeLog });
		GameRegistry.addSmelting(blockLemonTreeLog, new ItemStack(Items.COAL, 1, 1), 0.15F);
		GameRegistry.registerFuelHandler(new CLLFuelHandler());
		OreDictionary.registerOre(OREDICT_LEMON, itemLemon);
		OreDictionary.registerOre("plankWood", blockLemonTreePlanks);
		OreDictionary.registerOre("logWood", blockLemonTreeLog);
		OreDictionary.registerOre("treeLeaves", blockLemonLeavesHarvested);
		OreDictionary.registerOre("treeLeaves", blockLemonLeaves);
		OreDictionary.registerOre("treeSapling", blockLemonTreeSapling);

		if(CLLConfig.config_GenerateTrees){
			GameRegistry.registerWorldGenerator(WORLD_GEN_LEMON_TREE, 60);
		}

		EntityRegistry.registerModEntity(EntityLemon.class, ENTITY_LEMON, 1, CombustibleLemonLauncher.instance, 80, 3, true);
	}

	public void initializePacketHandler(){
		cllChannel = NetworkRegistry.INSTANCE.newChannel(MOD_CHANNEL, new CLLPacketHandler());
		CLLPacketHandler.registerPackets();
	}

	public void packetCLL_sendToPlayer(CLLPacket packet, EntityPlayerMP player){
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		cllChannel.get(Side.SERVER).writeAndFlush(packet);
	}
	
	private static SoundEvent registerSound(String soundName){
		ResourceLocation soundID = new ResourceLocation(MOD_ID, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}

	private static class CLLFuelHandler implements IFuelHandler {

		@Override
		public int getBurnTime(ItemStack fuel){
			Item fitem = fuel.getItem();
			// Values taken from here:
			// http://minecraft.gamepedia.com/Smelting
			if(fitem == Item.getItemFromBlock(blockLemonTreeLog)){
				return 300;
			}else if(fitem == Item.getItemFromBlock(blockLemonTreePlanks)){
				return 300;
			}
			//
			//
			return 0;
		}

	}
	
	public static class CLLConfig {
		
		public static Configuration configFile;
		
		public static boolean config_GenerateTrees = true;
		
		public static void initializeConfig(File suggestedFile){
			configFile = new Configuration(suggestedFile);
			synchronize();
		}
		
		public static void synchronize(){
			config_GenerateTrees = configFile.getBoolean(CONFIG_GENERATE_TREES, CATEGORY_GENERAL, true, "Controls whether lemon trees are allowed to spawn");
			
			if(configFile.hasChanged()){
				configFile.save();
			}
		}
		
		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent configChangedEvent){
			if(configChangedEvent.getModID().equals(MOD_ID)){
				synchronize();
			}
		}
		
	}

}

