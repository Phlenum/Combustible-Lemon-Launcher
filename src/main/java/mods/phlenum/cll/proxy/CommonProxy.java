package mods.phlenum.cll.proxy;

import static mods.phlenum.cll.lib.Reference.*;

import mods.phlenum.cll.blocks.BlockLemonLeaves;
import mods.phlenum.cll.blocks.BlockLemonLeavesHarvested;
import mods.phlenum.cll.blocks.BlockLemonTreeLog;
import mods.phlenum.cll.blocks.BlockLemonTreePlanks;
import mods.phlenum.cll.blocks.BlockLemonTreeSapling;
import mods.phlenum.cll.items.ItemLemon;
import mods.phlenum.cll.items.ItemLemonExplosive;
import mods.phlenum.cll.world.DamageSourceExplosiveLemon;
import mods.phlenum.cll.world.WorldGenLemonTree;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
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
    public static BlockLemonTreeLog blockLemonTreeLog;
    public static BlockLemonLeavesHarvested blockLemonLeavesHarvested;
    public static BlockLemonLeaves blockLemonLeaves;
    public static BlockLemonTreeSapling blockLemonTreeSapling;

    public static ItemLemon itemLemon;
    public static ItemLemonExplosive itemLemonExplosive;

    public static final DamageSourceExplosiveLemon DAMAGE_SOURCE_EXPLOSIVE_LEMON = new DamageSourceExplosiveLemon();
    public static final WorldGenLemonTree WORLD_GEN_LEMON_TREE = new WorldGenLemonTree();

    public static final CreativeTabs tabCLL = new CreativeTabs(MOD_ID){

        @Override
        public String getTranslatedTabLabel(){
            return MOD_NAME;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem(){
            return itemLemonExplosive;
        }

    };

    public void initializeBlocks(){
        blockLemonTreePlanks = new BlockLemonTreePlanks(BLOCK_LEMON_TREE_PLANKS, 2.0f, 5.0f, Block.soundTypeWood);
        blockLemonTreeLog = new BlockLemonTreeLog(BLOCK_LEMON_TREE_LOG, Block.soundTypeWood);
        blockLemonLeavesHarvested = new BlockLemonLeavesHarvested(BLOCK_LEMON_LEAVES_HARVESTED);
        blockLemonLeaves = new BlockLemonLeaves(BLOCK_LEMON_LEAVES);
        blockLemonTreeSapling = new BlockLemonTreeSapling(BLOCK_LEMON_TREE_SAPLING, Block.soundTypeGrass);
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
        GameRegistry.addShapelessRecipe(new ItemStack(blockLemonTreePlanks, 4), new Object[]{ blockLemonTreeLog });
        GameRegistry.addSmelting(blockLemonTreeLog, new ItemStack(Items.coal, 1, 1), 0.15F);
        GameRegistry.registerFuelHandler(new CLLFuelHandler());
        OreDictionary.registerOre(OREDICT_LEMON, itemLemon);
        OreDictionary.registerOre("plankWood", blockLemonTreePlanks);
        OreDictionary.registerOre("logWood", blockLemonTreeLog);
        OreDictionary.registerOre("treeLeaves", blockLemonLeavesHarvested);
        OreDictionary.registerOre("treeLeaves", blockLemonLeaves);
        OreDictionary.registerOre("treeSapling", blockLemonTreeSapling);
        GameRegistry.registerWorldGenerator(WORLD_GEN_LEMON_TREE, 60);
    }
    
    private static class CLLFuelHandler implements IFuelHandler {
    	
    	@Override
    	public int getBurnTime(ItemStack fuel){
    		Item fitem = fuel.getItem();
    		// Values taken from here:
    		// http://minecraft.gamepedia.com/Smelting
    		if(fitem == Item.getItemFromBlock(blockLemonTreeLog)){
    			return 300;
    		}else if(fitem == Item.getItemFromBlock(blockLemonTreePlanks)){
    			return 300;
    		}
    		//
    		//
    		return 0;
    	}
    	
    }

}

