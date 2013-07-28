package ibuilder99.mods.cll.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import ibuilder99.mods.cll.network.PacketUtil;
import ibuilder99.mods.cll.util.IKeyListener;

public class PacketKey extends PacketCLL {

	public String keyBindingDesc;

	public PacketKey() {
		super(PacketUtil.KEY);
	}

	public PacketKey(String kb){
		super(PacketUtil.KEY);
		keyBindingDesc = kb;
	}

	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeUTF(keyBindingDesc);
	}

	public void readData(DataInputStream dis) throws IOException {
		keyBindingDesc = dis.readUTF();
	}

	public void doAction(EntityPlayer player){
		if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof IKeyListener){
			((IKeyListener)player.getCurrentEquippedItem().getItem()).onKeyPressed(player, keyBindingDesc);
		}
	}

}
