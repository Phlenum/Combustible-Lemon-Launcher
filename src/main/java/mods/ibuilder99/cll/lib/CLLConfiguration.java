package mods.ibuilder99.cll.lib;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class CLLConfiguration {
	
	public static boolean BO_useKeyToFire;
	
	public static void initializeConfiguration(File configFile){
		Configuration configObj = new Configuration(configFile);
		
		BO_useKeyToFire = configObj.get(CATEGORY_GENERAL, "useKeyToFire", false).getBoolean(false);
		
		if(configObj.hasChanged()){
			configObj.save();
		}
	}

}
