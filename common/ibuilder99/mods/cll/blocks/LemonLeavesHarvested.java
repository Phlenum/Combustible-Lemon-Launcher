package ibuilder99.mods.cll.blocks;

import java.util.List;
import java.util.Random;

import ibuilder99.mods.cll.CombustibleLemonLauncher;
import ibuilder99.mods.cll.util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.StepSound;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LemonLeavesHarvested extends BlockLeaves {

	public String name;

	public LemonLeavesHarvested(int id, String unloc, String loc_en, float hardness, float resistance, StepSound sound) {
		super(id);
		name = unloc;
		setUnlocalizedName(unloc);
		setHardness(hardness);
		setResistance(resistance);
		setCreativeTab(CombustibleLemonLauncher.tabLemonLauncher);
		setStepSound(sound);
		LanguageRegistry.addName(this, loc_en);
		GameRegistry.registerBlock(this, unloc);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2){
		return blockIcon;
	}

	@Override
	public int quantityDropped(Random par1Random){
		return par1Random.nextInt(10) == 1 ? 1 : 0;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3){
		return LemonLauncherBlocks.LemonSapling.blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1){
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4){
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass(){
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List){}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random){
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
		/*if(par5Random.nextInt(5000) == 42){
			par1World.setBlock(par2, par3, par4, LemonLauncherBlocks.LemonLeaves.blockID);
		}*/
	}

}
