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
 * @date 30 July 2015
 */

public class BlockLemonLeaves extends BlockLemonLeavesHarvested {
	
	public BlockLemonLeaves(String unloc){
		super(unloc);
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune){
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
		spawnAsEntity(worldIn, pos, new ItemStack(CommonProxy.itemLemon));
	}

}
