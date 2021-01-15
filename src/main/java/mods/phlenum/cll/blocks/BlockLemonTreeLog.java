package mods.phlenum.cll.blocks;

//import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MaterialColor;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 July 2015
 */

public class BlockLemonTreeLog /*extends BlockLog*/ {

	/*public BlockLemonTreeLog(MaterialColor p_i48367_1_, Properties p_i48367_2_){
		super(p_i48367_1_, p_i48367_2_);
	}*/
	
	/*
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
	*/
}
