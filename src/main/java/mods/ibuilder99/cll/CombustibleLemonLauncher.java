package mods.ibuilder99.cll;

import mods.ibuilder99.cll.lib.CLLLogger;
import mods.ibuilder99.cll.lib.Reference;
import mods.ibuilder99.cll.network.CLLPacketHandler;
import mods.ibuilder99.cll.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
//import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Combustible Lemon Launcher
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
	
	public static final CLLPacketHandler PACKET_HANDLER = new CLLPacketHandler();
	
	public static final CreativeTabs TAB_COMBUSTIBLE_LEMON_LAUNCHER = new CreativeTabs(Reference.MOD_NAME){
		
		public String getTranslatedTabLabel(){
			return Reference.MOD_NAME;
		};
		
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem(){
            return CommonProxy.itemCombustibleLemonLauncher;
        }
        
    };
    
	@EventHandler
	public void preInitializeMod(FMLPreInitializationEvent preInitEvent){
		CLLLogger.initializeLogging();
		/*Configuration configObj = new Configuration(preInitEvent.getSuggestedConfigurationFile());
		CommonProxy.CLLConfiguration.initializeConfiguration(configObj);
		if(configObj.hasChanged()){
			configObj.save();
		}*/
		proxy.initializeItems();
		proxy.initializeBlocks();
	}
	
	@EventHandler
	public void initializeMod(FMLInitializationEvent initEvent){
		proxy.initializeCrafting();
		proxy.initializeWorld();
		proxy.initializeRenderers();
		proxy.initializePacketHandling();
	}

}
