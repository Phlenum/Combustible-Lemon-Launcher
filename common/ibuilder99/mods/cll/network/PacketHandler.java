package ibuilder99.mods.cll.network;

import ibuilder99.mods.cll.network.packets.PacketCLL;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	public static final String CHANNEL_NAME = "CLLChannel";
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		EntityPlayer entplayer = (EntityPlayer) player;
		PacketCLL cllPacket = PacketUtil.readPacketData(packet.data);
		cllPacket.doAction(entplayer);
	}

}
