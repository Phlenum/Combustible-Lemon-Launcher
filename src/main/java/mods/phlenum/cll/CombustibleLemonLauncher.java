package mods.phlenum.cll;

import static mods.phlenum.cll.lib.Reference.*;

import mods.phlenum.cll.lib.CLLLogger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 Nov 2014
 */

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)
public final class CombustibleLemonLauncher {

	@Mod.Instance(MOD_ID)
	public static CombustibleLemonLauncher instance;

	@Mod.EventHandler
	public void preInitializeMod(FMLPreInitializationEvent preInitEvent){
		CLLLogger.initializeLogging();
	}

	@Mod.EventHandler
	public void initializeMod(FMLInitializationEvent initEvent){

	}

}
