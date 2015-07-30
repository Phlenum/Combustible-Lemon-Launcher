package mods.phlenum.cll.blocks;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 30 Nov 2014
 */

public class BlockLemonLeaves extends BlockLemonLeavesHarvested {
	
	public BlockLemonLeaves(String unloc){
		super(unloc);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		super.breakBlock(worldIn, pos, state);
		//spawnAsEntity(worldIn, pos, new ItemStack(CommonProxy.itemLemon));
	}

}
