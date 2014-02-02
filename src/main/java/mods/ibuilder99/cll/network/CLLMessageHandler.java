package mods.ibuilder99.cll.network;

import mods.ibuilder99.cll.network.messages.MessageCLL;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class CLLMessageHandler implements IMessageHandler<IMessage, IMessage> {

	@Override
	public IMessage onMessage(IMessage message, MessageContext ctx){
		MessageCLL cllMessage = CLLMessageType.decodeMessage(message);
		cllMessage.executeMessage(ctx.getServerHandler().field_147369_b);
		return null;
	}

}
