package mods.ibuilder99.cll.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import mods.ibuilder99.cll.client.RenderItemCombustibleLemonLauncher;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initializeRenderers(){
		RenderItemCombustibleLemonLauncher.initialize();
		MinecraftForgeClient.registerItemRenderer(itemCombustibleLemonLauncher, new RenderItemCombustibleLemonLauncher());
	}

}
