package mods.phlenum.cll.network.packets;

import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
//import net.minecraft.entity.player.EntityPlayer;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 06 Jan 2016
 */

public abstract class CLLPacket {

	public abstract void writeDataTo(ByteBufOutputStream buffer) throws IOException;

	public abstract void readDataFrom(ByteBufInputStream buffer) throws IOException;

//	@SideOnly(Side.CLIENT)
	//public abstract void handleClientSide(EntityPlayer playerSP);

	//public abstract void handleServerSide(EntityPlayer playerMP);

}
