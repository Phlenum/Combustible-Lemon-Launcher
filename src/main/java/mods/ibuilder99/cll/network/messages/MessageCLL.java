package mods.ibuilder99.cll.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import mods.ibuilder99.cll.network.CLLMessageType;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public abstract class MessageCLL implements IMessage {

	public final CLLMessageType messageType;
	
	public MessageCLL(CLLMessageType messageType){
		this.messageType = messageType;
	}
	
	@Override
	public final void toBytes(ByteBuf buf){
		buf.writeInt(messageType.ordinal());
		writeData(buf);
	}
	
	public abstract void writeData(ByteBuf buf);
	
	public abstract void executeMessage(EntityPlayerMP player);

}
