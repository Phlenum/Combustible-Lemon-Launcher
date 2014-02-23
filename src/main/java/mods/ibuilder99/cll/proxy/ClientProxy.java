package mods.ibuilder99.cll.proxy;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import mods.ibuilder99.cll.client.RenderEntityLemon;
import mods.ibuilder99.cll.client.RenderItemCombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.CLLKeyBindingHandler;
import mods.ibuilder99.cll.world.EntityLemon;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	public static boolean BO_useKeyToFire;
	public static int INT_keyForFiring;
	
	@Override
	public void initializeConfiguration(Configuration configObj) {
		Property PROP_useKeyToFire = configObj.get(CATEGORY_GENERAL, "useKeyToFire", false);
		PROP_useKeyToFire.comment = "Decide whether you want to hit a key or right-click to use the Combustible Lemon Launcher";
		BO_useKeyToFire = PROP_useKeyToFire.getBoolean(false);
		
		Property PROP_keyForFiring = configObj.get(CATEGORY_GENERAL, "keyForFiring", Keyboard.KEY_F);
		PROP_keyForFiring.comment = "Use the LWJGL Keyboard codes to configure. (http://minecraft.gamepedia.com/Key_Codes)";
		INT_keyForFiring = PROP_keyForFiring.getInt();
	}
	
	@Override
	public void initializeRenderers(){
		RenderItemCombustibleLemonLauncher.initialize();
		MinecraftForgeClient.registerItemRenderer(itemCombustibleLemonLauncher, new RenderItemCombustibleLemonLauncher());
		RenderingRegistry.registerEntityRenderingHandler(EntityLemon.class, new RenderEntityLemon());
	}
	
	@Override
	public void initializeKeyBinding(){
		if(BO_useKeyToFire){
			FMLCommonHandler.instance().bus().register(new CLLKeyBindingHandler());
		}
	}
	
	@Override
	public boolean doFancyRender(){
		return Minecraft.getMinecraft().gameSettings.fancyGraphics;
	}

}
