package mods.ibuilder99.cll.network;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import mods.ibuilder99.cll.lib.Reference;
import mods.ibuilder99.cll.network.packets.CLLPacket;
import mods.ibuilder99.cll.network.packets.CLLPacketKey;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.MessageToMessageCodec;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)<br>
 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
 */

@ChannelHandler.Sharable
public class CLLPacketHandler extends MessageToMessageCodec<FMLProxyPacket, CLLPacket>{

	private static ArrayList<Class<? extends CLLPacket>> registeredPackets = new ArrayList<Class<? extends CLLPacket>>();
	
	public static void registerPacket(Class <? extends CLLPacket> packetClass){
		if(!registeredPackets.contains(packetClass)){
			registeredPackets.add(packetClass);
		}
	}
	
	public static void registerPackets(){
		registerPacket(CLLPacketKey.class);
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, CLLPacket msg, List<Object> out) throws Exception {
		Class<? extends CLLPacket> packetClass = msg.getClass(); 								   // Retrieve packets class
		ByteBuf writeBuffer = Unpooled.buffer();												   // Create a Netty buffer
		ByteBufOutputStream bbos = new ByteBufOutputStream(writeBuffer);						   // Create Netty output stream buffer (wrapper for ByteBuf)
		if(registeredPackets.contains(packetClass)){ 											   // Is this class registered?
			writeBuffer.writeByte(registeredPackets.indexOf(packetClass)); 						   // Write the classes index to determine the class in later decoding
			msg.writeDataTo(bbos); 												   		   		   // Let the CLLPacket instance write to the Netty buffer
			FMLProxyPacket packet = new FMLProxyPacket(writeBuffer.copy(), Reference.MOD_CHANNEL); // Instantiate a FMLProxyPacket with the written data
			bbos.close();																		   // Close the Netty output stream buffer
			out.add(packet); 																	   // Add it to the 'out'-list
		}
	}

	@Override
	protected void decode(ChannelHandlerContext context, FMLProxyPacket msg, List<Object> out) throws Exception {
		ByteBuf packetPayload = msg.payload();
		byte indexOfClass = packetPayload.readByte();
		ByteBufInputStream bbis = new ByteBufInputStream(packetPayload.slice());
		Class<? extends CLLPacket> packetClass = registeredPackets.get(indexOfClass);
		if(packetClass != null){
			CLLPacket packetCLL = packetClass.newInstance();
			packetCLL.readDataFrom(bbis);
			switch(FMLCommonHandler.instance().getEffectiveSide()){
			case CLIENT:				
				packetCLL.handleClientSide(Minecraft.getMinecraft().thePlayer);
				break;
			case SERVER:
				INetHandler netHandler = context.channel().attr(NetworkRegistry.NET_HANDLER).get();
                packetCLL.handleServerSide(((NetHandlerPlayServer) netHandler).playerEntity);
				break;
			}
			bbis.close();
			out.add(packetCLL);
		}
	}

}
