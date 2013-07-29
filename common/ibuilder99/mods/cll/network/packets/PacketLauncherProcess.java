package ibuilder99.mods.cll.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import ibuilder99.mods.cll.entity.EntityCombustibleLemon;
import ibuilder99.mods.cll.network.PacketUtil;

public class PacketLauncherProcess extends PacketCLL {

	public PacketLauncherProcess() {
		super(PacketUtil.LAUNCHER_PROCESS);
	}

	public int LemonID;
	public String playerName;

	public PacketLauncherProcess(int lemon, String player) {
		super(PacketUtil.LAUNCHER_PROCESS);
		playerName = player;
		LemonID = lemon;
	}

	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeUTF(playerName);
		dos.writeInt(LemonID);
	}

	public void readData(DataInputStream dis) throws IOException {
		playerName = dis.readUTF();
		LemonID = dis.readInt();
	}

	public void doAction(EntityPlayer player){
		if(player.username.equals(playerName)){
			player.worldObj.spawnEntityInWorld(new EntityCombustibleLemon(player, player.worldObj, LemonID));
			if(!player.capabilities.isCreativeMode){
				player.inventory.consumeInventoryItem(LemonID);
			}
		}
	}

}
