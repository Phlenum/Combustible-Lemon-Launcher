package mods.phlenum.cll.proxy;

import static mods.phlenum.cll.lib.Reference.*;

import mods.phlenum.cll.blocks.BlockLemonTreePlanks;
import mods.phlenum.cll.items.ItemLemon;
import mods.phlenum.cll.items.ItemLemonExplosive;
import mods.phlenum.cll.lib.DamageSourceExplosiveLemon;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 Nov 2014
 */

public class CommonProxy {

    public static BlockLemonTreePlanks blockLemonTreePlanks;

    public static ItemLemon itemLemon;
    public static ItemLemonExplosive itemLemonExplosive;

    public static final DamageSourceExplosiveLemon DAMAGE_SOURCE_EXPLOSIVE_LEMON = new DamageSourceExplosiveLemon();

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
        blockLemonTreePlanks = new BlockLemonTreePlanks(BLOCK_LEMON_TREE_PLANKS, 2.0f, 5.0f, Block.soundTypeWood);
    }

    public void initializeItems(){
        itemLemon = new ItemLemon(ITEM_LEMON, 5, 0.2f, false);
        itemLemonExplosive = new ItemLemonExplosive(ITEM_LEMON_EXPLOSIVE, 5, 0.2f, false);
    }

    public void initializeRenderers(){}

    public void initializeWorld(){
        GameRegistry.addRecipe(new ItemStack(itemLemonExplosive), new Object[]{
                " s ",
                "tlt",
                " s ",
                Character.valueOf('s'), new ItemStack(Items.string),
                Character.valueOf('t'), new ItemStack(Blocks.tnt),
                Character.valueOf('l'), new ItemStack(itemLemon)
        });
        OreDictionary.registerOre(OREDICT_LEMON, itemLemon);
        OreDictionary.registerOre("plankWood", blockLemonTreePlanks);
    }

}

