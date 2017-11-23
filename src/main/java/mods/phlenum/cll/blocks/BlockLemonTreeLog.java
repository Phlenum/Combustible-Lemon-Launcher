package mods.phlenum.cll.blocks;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 July 2015
 */

public class BlockLemonTreeLog extends BlockLog {
	
	public BlockLemonTreeLog(String unloc, SoundType sound){
		setUnlocalizedName(unloc);
		setSoundType(sound);
		setCreativeTab(CommonProxy.tabCLL);
		setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		setRegistryName(unloc);
	}
	
	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[]{ LOG_AXIS });
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		IBlockState iblockstate = getDefaultState().withProperty(LOG_AXIS, ((BlockLog.EnumAxis.values()[meta])));
		return iblockstate;
	}
	
	@Override
	public int getMetaFromState(IBlockState state){
		return ((BlockLog.EnumAxis)state.getValue(LOG_AXIS)).ordinal();
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face){
		return true;
	}
	
	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face){
		return 5;
	}
	
}
