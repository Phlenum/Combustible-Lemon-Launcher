package mods.ibuilder99.cll.lib;

import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.network.packets.CLLPacketKey;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class CLLKeyBindingHandler {
	
	private static final KeyBinding keyBindFire = new KeyBinding("key.fire", CLLConfiguration.INT_keyForFiring, Reference.MOD_NAME);
	
	public CLLKeyBindingHandler(){
		ClientRegistry.registerKeyBinding(keyBindFire);
	}
	
	@SubscribeEvent
	public void onKeyInputEvent(KeyInputEvent event){
		if(keyBindFire.func_151468_f()){ // Has the player just pressed this key?
			CLLPacketKey packetKey = new CLLPacketKey(keyBindFire.func_151464_g());
			CombustibleLemonLauncher.proxy.packetCLL_sendToServer(packetKey);
		}
	}
	
}
