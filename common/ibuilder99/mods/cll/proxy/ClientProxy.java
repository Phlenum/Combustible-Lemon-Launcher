package ibuilder99.mods.cll.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ibuilder99.mods.cll.blocks.tileentity.TileEntityBlueGel;
import ibuilder99.mods.cll.client.render.ItemRenderLemonLauncher;
import ibuilder99.mods.cll.client.render.TileEntityGelRender;
import ibuilder99.mods.cll.entity.EntityCombustibleLemon;
import ibuilder99.mods.cll.items.LemonLauncherItems;
import ibuilder99.mods.cll.util.ConfigLoader;
import ibuilder99.mods.cll.util.KeyBindingHandler;
import ibuilder99.mods.cll.util.KeyBindings;
import ibuilder99.mods.cll.util.Reference;

public class ClientProxy extends CommonProxy {

	@Override
	public void loadLocalizations(){
		for(String s : Reference.AVAIBABLE_LOCALIZATIONS){
			LanguageRegistry.instance().loadLocalization("/assets/" + Reference.MOD_ID.toLowerCase() + "/lang/" + s + ".lang", s, false);
		}
	}

	@Override
	public void registerRenderers(){
		MinecraftForgeClient.registerItemRenderer(LemonLauncherItems.LemonLauncher.itemID, new ItemRenderLemonLauncher());
		RenderingRegistry.registerEntityRenderingHandler(EntityCombustibleLemon.class, new RenderSnowball(LemonLauncherItems.Lemon));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlueGel.class, new TileEntityGelRender());
	}

	@Override
	public void registerKeyBinding(){
		if(ConfigLoader.useKeyToFire){
			KeyBindings.addKeyBinding("key.fire", "Fire", Keyboard.KEY_F, false);
		}
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler(KeyBindings.getKeyBindings(), KeyBindings.getRepeat()));
	}
	
	@Override
	public void registerEventListener(){
		MinecraftForge.EVENT_BUS.register(new SoundLoader());
	}

	public class SoundLoader {
		
		@ForgeSubscribe
		public void onSoundLoad(SoundLoadEvent event){
			
		}
	}
}
