package mods.phlenum.cll.network.packets;

import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import mods.phlenum.cll.world.EntityLemon.LemonType;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Combustible Lemon Launcher
 * Minecraft Forge Modification
 */

public class CLLPacketLauncherProcess extends CLLPacket {
	
	private LemonType launchType;
	
	public CLLPacketLauncherProcess(){} // <-- Default constructor for Class.newInstance()
	
	public CLLPacketLauncherProcess(LemonType type){
		launchType = type;
	}
	
	@Override
	public void writeDataTo(ByteBufOutputStream buffer) throws IOException {
		buffer.writeByte((byte)launchType.ordinal());
	}

	@Override
	public void readDataFrom(ByteBufInputStream buffer) throws IOException {
		launchType = LemonType.values()[buffer.readByte()];
	}
	
	private void handlePacket(EntityPlayer player){
		if(!player.capabilities.isCreativeMode){
			if(!launchType.playerHasItem(player)){
				return;
			}
			launchType.consumeItem(player);
		}
	}

	@Override
	public void handleClientSide(EntityPlayer playerSP){
		handlePacket(playerSP);
	}

	@Override
	public void handleServerSide(EntityPlayer playerMP){}

}
