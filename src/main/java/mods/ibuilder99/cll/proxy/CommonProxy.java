package mods.ibuilder99.cll.proxy;

import java.util.EnumMap;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import mods.ibuilder99.cll.blocks.BlockLemonLeaves;
import mods.ibuilder99.cll.blocks.BlockLemonLeavesHarvested;
import mods.ibuilder99.cll.items.ItemCLL;
import mods.ibuilder99.cll.items.ItemCombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.Reference;
import mods.ibuilder99.cll.network.CLLPacketHandler;
import mods.ibuilder99.cll.network.packets.CLLPacket;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
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
		blockLemonLeaves = new BlockLemonLeaves(0, Reference.BLOCK_LEMON_LEAVES, 0.2F, 0.2F, Block.field_149779_h);
		blockLemonLeavesHarvested = new BlockLemonLeavesHarvested(1, Reference.BLOCK_LEMON_LEAVES_HARVESTED, 0.2F, 0.2F, Block.field_149779_h);
	}
	
	public void initializeWorld(){
		
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
	
	public void packetCLL_sendToAll(CLLPacket packet, EntityPlayerMP clientPlayer){
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(clientPlayer);
		cllChannel.get(Side.SERVER).writeAndFlush(packet);
	}
	
	public void initializeKeyBinding(){}
	
}
