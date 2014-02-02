package mods.ibuilder99.cll.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import mods.ibuilder99.cll.network.messages.CLLMessageKey;
import mods.ibuilder99.cll.network.messages.CLLMessageLauncherProcess;
import mods.ibuilder99.cll.network.messages.MessageCLL;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public enum CLLMessageType {
	MESSAGE_KEY(CLLMessageKey.class),
	MESSAGE_LAUNCHER_PROCESS(CLLMessageLauncherProcess.class);
	
	public final Class<? extends MessageCLL> referencedClass;
	
	CLLMessageType(Class<? extends MessageCLL> refclass){
		referencedClass = refclass;
	}
	
	public static MessageCLL decodeMessage(IMessage message){
		MessageCLL decodedMessage = null;
		ByteBuf byteBuffer = Unpooled.buffer();
		int typeIndex = 0;
		
		message.toBytes(byteBuffer);
		typeIndex = byteBuffer.readInt();
		try{
			decodedMessage = CLLMessageType.values()[typeIndex].referencedClass.newInstance();
		}catch(Exception e){}
		
		return decodedMessage;
	}
	
}
