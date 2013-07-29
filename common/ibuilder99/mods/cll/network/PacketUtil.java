package ibuilder99.mods.cll.network;

import ibuilder99.mods.cll.network.packets.PacketCLL;
import ibuilder99.mods.cll.network.packets.PacketKey;
import ibuilder99.mods.cll.network.packets.PacketLauncherProcess;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.packet.Packet250CustomPayload;

public enum PacketUtil {

	KEY(PacketKey.class),
	LAUNCHER_PROCESS(PacketLauncherProcess.class);
	
	public Class<? extends PacketCLL> packetClass;

	PacketUtil(Class<? extends PacketCLL> packet){
		packetClass = packet;
	}

	public static PacketCLL readPacketData(byte[] data){
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bis);
		int index = 0;
		PacketCLL newPacket = null;
		try{
			index = dis.readInt();
		}catch(IOException e){}
		try{
			newPacket = values()[index].packetClass.newInstance();
		}catch(InstantiationException e){}
		catch(IllegalAccessException e){}
		try{
			newPacket.readData(dis);
		}catch(IOException e){}
		return newPacket;

	}
	
	public static Packet250CustomPayload buildPacket(PacketCLL toProcess){
		Packet250CustomPayload packet = new Packet250CustomPayload();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try{
			dos.writeInt(toProcess.type.ordinal());
			toProcess.writeData(dos);
		}catch(IOException e){}
		byte[] data = bos.toByteArray();
		packet.data = data;
		packet.length = bos.size();
		packet.channel = PacketHandler.CHANNEL_NAME;
		return packet;
	}
}
