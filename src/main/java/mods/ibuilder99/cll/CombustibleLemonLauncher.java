package mods.ibuilder99.cll;

import mods.ibuilder99.cll.lib.CLLLogger;
import mods.ibuilder99.cll.lib.Reference;
import mods.ibuilder99.cll.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

@Mod(
		name = Reference.MOD_NAME,
		modid = Reference.MOD_ID,
		version = Reference.MOD_VERSION
		)
public class CombustibleLemonLauncher {
	
	@Instance(Reference.MOD_ID)
	public static CombustibleLemonLauncher instance;
	
	@SidedProxy(clientSide = "mods.ibuilder99.cll.proxy.ClientProxy", serverSide = "mods.ibuilder99.cll.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInitializeMod(FMLPreInitializationEvent preInitEvent){
		CLLLogger.initializeLogging();
		proxy.initializeItems();
		proxy.initializeBlocks();
	}
	
	@EventHandler
	public void initializeMod(FMLInitializationEvent initEvent){
		proxy.initializeRenderers();
	}
}
