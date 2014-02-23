package mods.ibuilder99.cll.lib;

import java.io.File;

import org.lwjgl.input.Keyboard;

import net.minecraftforge.common.config.Configuration;
import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class CLLConfiguration {
	
	public static boolean BO_useKeyToFire;
	public static boolean BO_registerLemonWood;
	public static int INT_keyForFiring;
	
	public static void initializeConfiguration(File configFile){
		Configuration configObj = new Configuration(configFile);
		
		BO_useKeyToFire = configObj.get(CATEGORY_GENERAL, "useKeyToFire", false).getBoolean(false);
		configObj.addCustomCategoryComment(CATEGORY_GENERAL, 
				"Use the LWJGL Keyboard Codes to configure\n"
				+ "http://minecraft.gamepedia.com/Key_Codes");
		BO_registerLemonWood = configObj.get(CATEGORY_GENERAL, "registerLemonWood", true).getBoolean(true);
		INT_keyForFiring = configObj.get(CATEGORY_GENERAL, "keyForFiring", Keyboard.KEY_F).getInt();
		
		if(configObj.hasChanged()){
			configObj.save();
		}
	}

}
