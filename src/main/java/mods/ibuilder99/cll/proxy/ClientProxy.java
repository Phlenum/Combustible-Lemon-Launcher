package mods.ibuilder99.cll.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import mods.ibuilder99.cll.client.RenderItemCombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.CLLConfiguration;
import mods.ibuilder99.cll.lib.CLLKeyBindingHandler;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initializeRenderers(){
		RenderItemCombustibleLemonLauncher.initialize();
		MinecraftForgeClient.registerItemRenderer(itemCombustibleLemonLauncher, new RenderItemCombustibleLemonLauncher());
	}
	
	@Override
	public void initializeKeyBinding(){
		if(CLLConfiguration.BO_useKeyToFire){
			FMLCommonHandler.instance().bus().register(new CLLKeyBindingHandler());
		}
	}

}
