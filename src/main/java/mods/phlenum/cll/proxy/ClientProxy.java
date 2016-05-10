package mods.phlenum.cll.proxy;

import static mods.phlenum.cll.lib.Reference.*;

import mods.phlenum.cll.client.HoldLikeBowHook;
import mods.phlenum.cll.client.render.RenderEntityLemon;
import mods.phlenum.cll.entity.EntityLemon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 Nov 2014
 */

@SideOnly(Side.CLIENT)
public final class ClientProxy extends CommonProxy {

	@Override
	public void initializeModels(){
		OBJLoader.INSTANCE.addDomain(MOD_ID);
		ModelLoader.setCustomModelResourceLocation(itemCombustibleLemonLauncher, 0, new ModelResourceLocation(MOD_ID + ":" + ITEM_COMBUSTIBLE_LEMON_LAUNCHER.toLowerCase(), "inventory"));
	}
	
	
    @Override
    public void initializeRenderers(){
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        mesher.register(Item.getItemFromBlock(blockLemonTreePlanks), 0, new ModelResourceLocation(MOD_ID + ":" + BLOCK_LEMON_TREE_PLANKS, "inventory"));
        mesher.register(Item.getItemFromBlock(blockLemonTreeLog), 0, new ModelResourceLocation(MOD_ID + ":" + BLOCK_LEMON_TREE_LOG, "inventory"));
        mesher.register(Item.getItemFromBlock(blockLemonLeavesHarvested), 0, new ModelResourceLocation(MOD_ID + ":" + BLOCK_LEMON_LEAVES_HARVESTED, "inventory"));
        mesher.register(Item.getItemFromBlock(blockLemonLeaves), 0, new ModelResourceLocation(MOD_ID + ":" + BLOCK_LEMON_LEAVES, "inventory"));
        mesher.register(Item.getItemFromBlock(blockLemonTreeSapling), 0, new ModelResourceLocation(MOD_ID + ":" + BLOCK_LEMON_TREE_SAPLING, "inventory"));

        mesher.register(itemLemon, 0, new ModelResourceLocation(MOD_ID + ":" + ITEM_LEMON, "inventory"));
        mesher.register(itemLemonExplosive, 0, new ModelResourceLocation(MOD_ID + ":" + ITEM_LEMON_EXPLOSIVE, "inventory"));
        mesher.register(itemCombustibleLemonLauncher, 0, new ModelResourceLocation(MOD_ID + ":" + ITEM_COMBUSTIBLE_LEMON_LAUNCHER.toLowerCase(), "inventory"));
        
        MinecraftForge.EVENT_BUS.register(new HoldLikeBowHook());
        MinecraftForge.EVENT_BUS.register(new CLLConfig());
    }
    
    @Override
    public void initializeEntityRender(){
        RenderingRegistry.registerEntityRenderingHandler(EntityLemon.class, new IRenderFactory<EntityLemon>() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Render createRenderFor(RenderManager manager){
				return new RenderEntityLemon(manager);
			}
        	
		});
    }

}

