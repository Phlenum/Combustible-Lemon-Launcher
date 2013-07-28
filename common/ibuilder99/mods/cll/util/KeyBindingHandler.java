package ibuilder99.mods.cll.util;

import ibuilder99.mods.cll.network.PacketUtil;
import ibuilder99.mods.cll.network.packets.PacketKey;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class KeyBindingHandler extends KeyBindingRegistry.KeyHandler {

	public KeyBindingHandler(KeyBinding[] keyBindings, boolean[] repeatings) {
		super(keyBindings, repeatings);
	}

	@Override
	public String getLabel() {
		return Reference.MOD_NAME;
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		Minecraft client = FMLClientHandler.instance().getClient();
		if(client.inGameHasFocus && tickEnd){
			//Sending a packet
			PacketKey packet = new PacketKey(kb.keyDescription);
			PacketDispatcher.sendPacketToServer(PacketUtil.buildPacket(packet));
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
