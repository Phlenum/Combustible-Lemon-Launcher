package ibuilder99.mods.cll.network.packets;

import ibuilder99.mods.cll.network.PacketUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;

public class PacketCLL {
	
	public PacketUtil type;
	
	public PacketCLL(PacketUtil packetType){
		type = packetType;
	}
	
	public void writeData(DataOutputStream dos) throws IOException {}
	
	public void readData(DataInputStream dis) throws IOException {}
	
	public void doAction(EntityPlayer player){}
}
