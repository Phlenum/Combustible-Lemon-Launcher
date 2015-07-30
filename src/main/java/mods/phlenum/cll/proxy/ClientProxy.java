package mods.phlenum.cll.proxy;

import static mods.phlenum.cll.lib.Reference.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
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
    public void initializeRenderers(){
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        mesher.register(Item.getItemFromBlock(blockLemonTreePlanks), 0, new ModelResourceLocation(MOD_ID + ":" + BLOCK_LEMON_TREE_PLANKS, "inventory"));
        mesher.register(Item.getItemFromBlock(blockLemonTreeLog), 0, new ModelResourceLocation(MOD_ID + ":" + BLOCK_LEMON_TREE_LOG, "inventory"));
        mesher.register(Item.getItemFromBlock(blockLemonLeavesHarvested), 0, new ModelResourceLocation(MOD_ID + ":" + BLOCK_LEMON_LEAVES_HARVESTED, "inventory"));
        mesher.register(Item.getItemFromBlock(blockLemonLeaves), 0, new ModelResourceLocation(MOD_ID + ":" + BLOCK_LEMON_LEAVES, "inventory"));

        mesher.register(itemLemon, 0, new ModelResourceLocation(MOD_ID + ":" + ITEM_LEMON, "inventory"));
        mesher.register(itemLemonExplosive, 0, new ModelResourceLocation(MOD_ID + ":" + ITEM_LEMON_EXPLOSIVE, "inventory"));
    }

}

