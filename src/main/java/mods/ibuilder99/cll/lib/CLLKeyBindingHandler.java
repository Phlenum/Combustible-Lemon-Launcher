package mods.ibuilder99.cll.lib;

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
	
	private static final KeyBinding keyBindFire = new KeyBinding("key.fire", CLLConfiguration.INT_keyForFiring, Reference.MOD_ID);
	
	public CLLKeyBindingHandler(){
		ClientRegistry.registerKeyBinding(keyBindFire);
	}
	
	@SubscribeEvent
	public void onKeyInputEvent(KeyInputEvent event){
		if(keyBindFire.func_151468_f()){
			//TODO: Send packet to server
		}
	}
	
}
