package mods.phlenum.cll.network;

import io.netty.handler.codec.MessageToMessageCodec;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import mods.phlenum.cll.network.packets.CLLPacket;
import mods.phlenum.cll.network.packets.CLLPacketLauncherProcess;

import static mods.phlenum.cll.lib.Reference.*;

import net.minecraft.client.Minecraft;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * 
 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
 * 
 * @author Phil Julian
 * @date 06 Jan 2016 
 */

@Sharable
public class CLLPacketHandler extends MessageToMessageCodec<FMLProxyPacket, CLLPacket> implements ChannelHandler {

	private static ArrayList<Class<? extends CLLPacket>> registeredPackets = new ArrayList<Class<? extends CLLPacket>>();

	public static void registerPacket(Class<? extends CLLPacket> packetClass){
		if(!registeredPackets.contains(packetClass)){
			registeredPackets.add(packetClass);
		}
	}

	public static void registerPackets(){
		registerPacket(CLLPacketLauncherProcess.class);
	}
	
	/*
	 * Minecraft.getMinecraft().thePlayer returns EntityPlayerSP, so dedicated servers crash here. 
	 * This method will be client side only so the dedicated server doesn't throw a ClassNotFoundException.
	 * Workaround...
	 */
	
	@SideOnly(Side.CLIENT)
	private static void handleClient(CLLPacket packet){
		packet.handleClientSide(Minecraft.getMinecraft().player);
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, CLLPacket msg, List<Object> out) throws Exception {
		Class<? extends CLLPacket> packetClass = msg.getClass();
		if(registeredPackets.contains(packetClass)){
			ByteBuf writeBuffer = Unpooled.buffer();
			ByteBufOutputStream bbos = new ByteBufOutputStream(writeBuffer);
			writeBuffer.writeByte(registeredPackets.indexOf(packetClass));
			msg.writeDataTo(bbos);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(writeBuffer.copy()), MOD_CHANNEL);
			bbos.close();
			out.add(packet);
		}
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
		ByteBuf packetPayload = msg.payload();
		byte indexOfClass = packetPayload.readByte();
		ByteBufInputStream bbis = new ByteBufInputStream(packetPayload.slice());
		Class<? extends CLLPacket> packetClass = registeredPackets.get(indexOfClass);
		if(packetClass != null){
			CLLPacket packetCLL = packetClass.newInstance();
			packetCLL.readDataFrom(bbis);
			switch(FMLCommonHandler.instance().getEffectiveSide()){
			case CLIENT:
				handleClient(packetCLL);
				break;
			case SERVER:
				packetCLL.handleServerSide(((NetHandlerPlayServer)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get()).player);
				break;			
			}
			bbis.close();
			out.add(packetCLL);
		}
	}

}
