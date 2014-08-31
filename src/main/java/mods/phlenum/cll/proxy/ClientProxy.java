package mods.phlenum.cll.proxy;

import mods.phlenum.cll.client.RenderEntityLemon;
import mods.phlenum.cll.client.RenderItemCombustibleLemonLauncher;
import mods.phlenum.cll.world.EntityLemon;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Combustible Lemon Launcher
 * Minecraft Forge Modification
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
	public boolean doFancyRender(){
		return Minecraft.getMinecraft().gameSettings.fancyGraphics;
	}

}
