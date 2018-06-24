package mods.phlenum.cll.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 12 Feb 2015
 */

public class BlockLemonTreePlanks extends Block {

	public BlockLemonTreePlanks(String unloc, float hardness, float resistance, SoundType sound){
		super(Block.Properties.create(Material.WOOD).hardnessAndResistance(hardness, resistance).sound(sound));
        setRegistryName(unloc);
	}
	
	@Override
	public boolean isFlammable(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face){
		return true;
	}
	
	@Override
	public int getFlammability(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face){
		return 20;
	}
  
}
