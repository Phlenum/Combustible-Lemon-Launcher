package mods.ibuilder99.cll.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;
import mods.ibuilder99.cll.client.RenderEntityLemon;
import mods.ibuilder99.cll.client.RenderItemCombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.CLLConfiguration;
import mods.ibuilder99.cll.lib.CLLKeyBindingHandler;
import mods.ibuilder99.cll.world.EntityLemon;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public void initializeRenderers(){
		RenderItemCombustibleLemonLauncher.initialize();
		MinecraftForgeClient.registerItemRenderer(itemCombustibleLemonLauncher, new RenderItemCombustibleLemonLauncher());
		RenderingRegistry.registerEntityRenderingHandler(EntityLemon.class, new RenderEntityLemon());
	}
	
	@Override
	public void initializeKeyBinding(){
		if(CLLConfiguration.BO_useKeyToFire){
			FMLCommonHandler.instance().bus().register(new CLLKeyBindingHandler());
		}
	}

}
