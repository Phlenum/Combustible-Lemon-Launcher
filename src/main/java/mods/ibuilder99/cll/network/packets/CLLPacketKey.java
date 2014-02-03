package mods.ibuilder99.cll.network.packets;

import io.netty.buffer.ByteBuf;
import mods.ibuilder99.cll.lib.IKeyListener;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class CLLPacketKey extends CLLPacket {

	private String keyIdentifier;
	
	public CLLPacketKey(){} // <-- Default constructor for Class.newInstance()
	
	public CLLPacketKey(String keyId){
		keyIdentifier = keyId;
	}
	
	@Override
	public void writeDataTo(ByteBuf buffer){
		buffer.writeBytes(keyIdentifier.getBytes());
	}

	@Override
	public void readDataFrom(ByteBuf buffer){
		byte[] stringBytes = new byte[buffer.readableBytes()];
		keyIdentifier = new String(stringBytes);
	}
	
	private void handlePacket(EntityPlayer player){
		if(player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() instanceof IKeyListener)){
			IKeyListener keyListener = ((IKeyListener) player.getCurrentEquippedItem().getItem());
			keyListener.onKeyPressed(keyIdentifier, player, player.getCurrentEquippedItem());
		}
	}

	@Override
	public void handleClientSide(EntityPlayer playerSP){
		handlePacket(playerSP);
	}

	@Override
	public void handleServerSide(EntityPlayer playerMP){
		handlePacket(playerMP);
	}

}
