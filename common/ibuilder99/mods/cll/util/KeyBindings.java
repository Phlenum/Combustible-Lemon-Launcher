package ibuilder99.mods.cll.util;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {
	
	private static List<KeyBinding> keyBindings = new ArrayList<KeyBinding>();
	private static List<Boolean> doesRepeat = new ArrayList<Boolean>();
	
	public static void addKeyBinding(String unloc, String loc_en, int key, boolean repeat){
		keyBindings.add(new KeyBinding(unloc, key));
		doesRepeat.add(Boolean.valueOf(repeat));
		LanguageRegistry.instance().addStringLocalization(unloc, Reference.MOD_ID + ": " + loc_en);
	}
	
	public static boolean[] getRepeat(){
		Object[] array = doesRepeat.toArray();
		boolean[] repeat = new boolean[array.length];
		for(int i = 0; i < array.length; i++){
			repeat[i] = (Boolean)array[i];
		}
		return repeat;
	}
	
	public static KeyBinding[] getKeyBindings(){
		Object[] array = keyBindings.toArray();
		KeyBinding[] kb = new KeyBinding[array.length];
		for(int i = 0; i < array.length; i++){
		KeyBinding keyb = (KeyBinding)array[i];
			kb[i] = keyb;
		}
		return kb;
	}
}
