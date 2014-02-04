package mods.ibuilder99.cll.network.packets;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)<br>
 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
 */

public abstract class CLLPacket {
	
	public abstract void writeDataTo(ByteBufOutputStream buffer) throws IOException;
	
	public abstract void readDataFrom(ByteBufInputStream buffer) throws IOException;
	
	public abstract void handleClientSide(EntityPlayer playerSP);
	
	public abstract void handleServerSide(EntityPlayer playerMP);
	
}
