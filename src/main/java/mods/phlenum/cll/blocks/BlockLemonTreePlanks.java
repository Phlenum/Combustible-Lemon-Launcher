package mods.phlenum.cll.blocks;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 14 Dec 2014
 */

public class BlockLemonTreePlanks extends Block {

    public BlockLemonTreePlanks(String unloc, Material mat, float hardness, float resistance, SoundType sound){
        super(mat);
        setUnlocalizedName(unloc);
        setHardness(hardness);
        setResistance(resistance);
        setStepSound(sound);
        setCreativeTab(CommonProxy.tabCLL);
        GameRegistry.registerBlock(this, unloc);
    }

}
