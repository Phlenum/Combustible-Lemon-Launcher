package mods.phlenum.cll.proxy;

import static mods.phlenum.cll.lib.Reference.*;

import mods.phlenum.cll.items.ItemLemon;
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

    public static final CreativeTabs tabCLL = new CreativeTabs(MOD_ID){

        @Override
        public String getTranslatedTabLabel(){
            return MOD_NAME;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return itemLemon;
        }

    };

    public void initializeBlocks(){

    }

    public void initializeItems(){
        itemLemon = new ItemLemon(ITEM_LEMON);
    }

    public void registerItemRender(Item item){}

    public void initializeRenderers(){}

    public void initializeWorld(){

    }

}

