package mods.phlenum.cll.proxy;

import static mods.phlenum.cll.lib.Reference.*;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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
    public void registerBlockRender(Block block){
        registerItemRender(Item.getItemFromBlock(block));
    }

    @Override
    public void registerItemRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(TEXTURE_PREFIX + item.getUnlocalizedName().substring(5), "inventory"));
    }

    @Override
    public void initializeRenderers(){
        registerBlockRender(blockLemonTreePlanks);

        registerItemRender(itemLemon);
        registerItemRender(itemLemonExplosive);
    }

}

