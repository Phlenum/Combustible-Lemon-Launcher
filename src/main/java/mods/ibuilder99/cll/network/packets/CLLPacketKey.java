package mods.ibuilder99.cll.network.packets;

import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import mods.ibuilder99.cll.lib.IKeyListener;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class CLLPacketKey extends CLLPacket {

	private String keyIdentifier;
	
	public CLLPacketKey(){} // <-- Default constructor for Class.newInstance()
	
	public CLLPacketKey(String keyId){
		keyIdentifier = keyId;
	}
	
	@Override
	public void writeDataTo(ByteBufOutputStream buffer) throws IOException{
		buffer.writeUTF(keyIdentifier);
	}

	@Override
	public void readDataFrom(ByteBufInputStream buffer) throws IOException {
		keyIdentifier = buffer.readUTF();
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
