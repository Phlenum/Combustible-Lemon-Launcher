package mods.ibuilder99.cll.proxy;

import java.util.EnumMap;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.blocks.BlockLemonLeaves;
import mods.ibuilder99.cll.blocks.BlockLemonLeavesHarvested;
import mods.ibuilder99.cll.items.ItemCLL;
import mods.ibuilder99.cll.items.ItemCombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.Reference;
import mods.ibuilder99.cll.network.CLLPacketHandler;
import mods.ibuilder99.cll.network.packets.CLLPacket;
import mods.ibuilder99.cll.world.EntityLemon;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class CommonProxy {
	
	public static ItemCLL itemLemon;
	public static ItemCLL itemLemonExplosive;
	public static ItemCombustibleLemonLauncher itemCombustibleLemonLauncher;
	
	public static BlockLemonLeaves blockLemonLeaves;
	public static BlockLemonLeavesHarvested blockLemonLeavesHarvested;
	
	private static EnumMap<Side, FMLEmbeddedChannel> cllChannel;
	
	public void initializeItems(){
		itemLemon = new ItemCLL(0, Reference.ITEM_LEMON);
		itemLemonExplosive = new ItemCLL(1, Reference.ITEM_LEMON_EXPLOSIVE);
		itemCombustibleLemonLauncher = new ItemCombustibleLemonLauncher(2, Reference.ITEM_COMBUSTIBLE_LEMON_LAUNCHER);
	}
	
	public void initializeBlocks(){
		blockLemonLeaves = new BlockLemonLeaves(0, Reference.BLOCK_LEMON_LEAVES, 0.2F, 0.2F, Block.soundTypeGrass);
		blockLemonLeavesHarvested = new BlockLemonLeavesHarvested(1, Reference.BLOCK_LEMON_LEAVES_HARVESTED, 0.2F, 0.2F, Block.soundTypeGrass);
	}
	
	public void initializeCrafting(){
		GameRegistry.addRecipe(new ItemStack(itemCombustibleLemonLauncher), new Object[]{
			"iio", "rp ", "iio", 
			Character.valueOf('i'), new ItemStack(Items.iron_ingot), 
			Character.valueOf('r'), new ItemStack(Items.redstone),
			Character.valueOf('p'), new ItemStack(Blocks.piston)
		});
		GameRegistry.addRecipe(new ItemStack(itemLemonExplosive), new Object[]{
			" s ", "tlt", " s ",
			Character.valueOf('s'), new ItemStack(Items.string),
			Character.valueOf('t'), new ItemStack(Blocks.tnt),
			Character.valueOf('l'), new ItemStack(itemLemon)
		});
	}
	
	public void initializeWorld(){
		EntityRegistry.registerModEntity(EntityLemon.class, Reference.ENTITY_LEMON, 1, CombustibleLemonLauncher.instance, 80, 3, true);
	}
	
	public void initializeRenderers(){}
	

	/**
	 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
	 */
	
	public void initializePacketHandling(){
		cllChannel = NetworkRegistry.INSTANCE.newChannel(Reference.MOD_CHANNEL, new CLLPacketHandler());
		CLLPacketHandler.registerPackets();
	}
	
	/**
	 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
	 */
	
	public void packetCLL_sendToAll(CLLPacket packet){
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		cllChannel.get(Side.SERVER).writeAndFlush(packet);
	}
	
	/**
	 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
	 */
	
	public void packetCLL_sendToPlayer(CLLPacket packet, EntityPlayerMP player){
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		cllChannel.get(Side.SERVER).writeAndFlush(packet);
	}
	
	/**
	 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
	 */
	
	public void packetCLL_sendToServer(CLLPacket packet){
		cllChannel.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		cllChannel.get(Side.CLIENT).writeAndFlush(packet);
	}
	
	public void initializeKeyBinding(){}
	
	
	public static class CommonHelper {
		
		public static void sendMessageToPlayer(EntityPlayer player, String message){
			player.addChatMessage(new ChatComponentText(message));
		}
		
		public static boolean createNBTTagIfNeeded(ItemStack itemstack){
			if(!itemstack.hasTagCompound()){
				itemstack.stackTagCompound = new NBTTagCompound();
				return true;
			}
			return false;
		}
		
	}
	
	
}
