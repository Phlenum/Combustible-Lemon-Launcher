package mods.phlenum.cll.network.packets;

import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import mods.phlenum.cll.entity.EntityLemon.LemonType;
import net.minecraft.entity.player.EntityPlayer;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 06 Jan 2016
 */

public class CLLPacketLauncherProcess extends CLLPacket {

	LemonType launchType;
	
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
/*
	@Override
	@SideOnly(Side.CLIENT)
	public void handleClientSide(EntityPlayer playerSP){
		if(!playerSP.capabilities.isCreativeMode){
			if(!launchType.playerHasItem(playerSP)){
				return;
			}
			launchType.consumeItem(playerSP);
		}
	}
	*/

	@Override
	public void handleServerSide(EntityPlayer playerMP){
		
	}

	@Override
	public void handleClientSide(EntityPlayer playerSP) {
		
	}

}
