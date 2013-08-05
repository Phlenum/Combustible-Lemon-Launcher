package ibuilder99.mods.cll;

import net.minecraft.creativetab.CreativeTabs;
import ibuilder99.mods.cll.blocks.LemonLauncherBlocks;
import ibuilder99.mods.cll.crafting.CraftingRecipes;
import ibuilder99.mods.cll.items.LemonLauncherItems;
import ibuilder99.mods.cll.network.PacketHandler;
import ibuilder99.mods.cll.proxy.CommonProxy;
import ibuilder99.mods.cll.util.ClientTickHandler;
import ibuilder99.mods.cll.util.ConfigLoader;
import ibuilder99.mods.cll.util.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(
	name=Reference.MOD_NAME,
	modid=Reference.MOD_ID,
	version=Reference.VERSION
	)
@NetworkMod(clientSideRequired=true, serverSideRequired=false, packetHandler=PacketHandler.class, channels={PacketHandler.CHANNEL_NAME})
public class CombustibleLemonLauncher {
	
	@Instance(Reference.MOD_ID)
	public static CombustibleLemonLauncher instance;
	
	@SidedProxy(modId=Reference.MOD_ID, serverSide="ibuilder99.mods.cll.proxy.CommonProxy", clientSide="ibuilder99.mods.cll.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs tabLemonLauncher = new TabLemonLauncher(CreativeTabs.getNextID(), Reference.MOD_NAME);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ConfigLoader.loadConfig(event.getSuggestedConfigurationFile());
		LemonLauncherItems.initItems();
		LemonLauncherBlocks.initBlocks();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.loadLocalizations();
		proxy.registerTileEntities();
		proxy.registerRenderers();
		proxy.registerEntities();
		proxy.registerKeyBinding();
		proxy.registerWorldGen();
		CraftingRecipes.initCraftingRecipes();
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}
}
