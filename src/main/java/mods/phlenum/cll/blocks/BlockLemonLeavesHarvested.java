package mods.phlenum.cll.blocks;

import java.util.List;
import java.util.Random;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 30 Nov 2014
 */

public class BlockLemonLeavesHarvested extends BlockLeaves {
	
	protected static final byte BIT_DECAYABLE = 1;
	protected static final byte BIT_CHECK_DECAY = 2;
	
	public BlockLemonLeavesHarvested(String unloc){
		super();
		setUnlocalizedName(unloc);
		setCreativeTab(CommonProxy.tabCLL);
		setDefaultState(getDefaultState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
		GameRegistry.registerBlock(this, unloc);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor(){
		return 16777215;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(IBlockState state){
		return 16777215;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass){
		return 16777215;
	}
	
	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance){}
	
	@Override
	public int damageDropped(IBlockState state){
		return 0;
	}
	
	@Override
	public int getDamageValue(World worldIn, BlockPos pos){
		return 0;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list){}
	
	@Override
	protected ItemStack createStackedBlock(IBlockState state){
		return new ItemStack(Item.getItemFromBlock(this));
	}
	
	@Override
	public int getMetaFromState(IBlockState state){
		int i = 0;
		if(!((Boolean)state.getValue(DECAYABLE)).booleanValue()){
			i |= BIT_DECAYABLE;
		}
		if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue()){
			i |= BIT_CHECK_DECAY;
		}
		return i;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		return getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & BIT_DECAYABLE) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & BIT_CHECK_DECAY) == 0));
	}
	
	@Override
	protected BlockState createBlockState(){
		return new BlockState(this, new IProperty[]{ CHECK_DECAY, DECAYABLE });
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune){
		return new java.util.ArrayList<ItemStack>(java.util.Arrays.asList(new ItemStack(this)));
	}

	@Override
	public EnumType getWoodType(int meta){
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer(){
		return Blocks.leaves.getBlockLayer();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face){
		return true;
	}
	
	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face){
		return 60;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(CommonProxy.blockLemonTreeSapling);
	}

}
