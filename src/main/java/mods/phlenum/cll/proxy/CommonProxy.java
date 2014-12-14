package mods.phlenum.cll.proxy;

import static mods.phlenum.cll.lib.Reference.*;

import mods.phlenum.cll.blocks.BlockLemonTreeLog;
import mods.phlenum.cll.blocks.BlockLemonTreePlanks;
import mods.phlenum.cll.items.ItemLemon;
import mods.phlenum.cll.items.ItemLemonExplosive;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
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

public class CommonProxy {

    public static ItemLemon itemLemon;
    public static ItemLemonExplosive itemLemonExplosive;

    public static BlockLemonTreePlanks blockLemonTreePlanks;
    public static BlockLemonTreeLog blockLemonTreeLog;

    public static final CreativeTabs tabCLL = new CreativeTabs(MOD_ID){

        @Override
        public String getTranslatedTabLabel(){
            return MOD_NAME;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return itemLemonExplosive;
        }

    };

    public void initializeBlocks(){
        blockLemonTreePlanks = new BlockLemonTreePlanks(BLOCK_LEMON_TREE_PLANKS, Material.wood, 2.0F, 1.0F, Block.soundTypeWood);
        blockLemonTreeLog = new BlockLemonTreeLog(BLOCK_LEMON_TREE_LOG, Material.wood, 2.0F, 1.0F, Block.soundTypeWood);
    }

    public void initializeItems(){
        itemLemon = new ItemLemon(ITEM_LEMON);
        itemLemonExplosive = new ItemLemonExplosive(ITEM_LEMON_EXPLOSIVE);
    }

    public void registerBlockRender(Block block){}

    public void registerItemRender(Item item){}

    public void initializeRenderers(){}

    public void initializeWorld(){

    }

}

