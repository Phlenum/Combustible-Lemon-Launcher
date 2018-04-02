package mods.phlenum.cll.blocks;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 12 Feb 2015
 */

public class BlockLemonTreePlanks extends Block {


    public BlockLemonTreePlanks(String unloc, float hardness, float resistance, SoundType sound){
        super(Material.WOOD);
        setHardness(hardness);
        setResistance(resistance);
        setUnlocalizedName(unloc);
        setSoundType(sound);
        setCreativeTab(CommonProxy.tabCLL);
        setRegistryName(unloc);
    }
    
    @Override
    public boolean isFlammable(IBlockAccess arg0, BlockPos arg1, EnumFacing arg2){
    	return true;
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face){
    	return 20;
    }
  
}
