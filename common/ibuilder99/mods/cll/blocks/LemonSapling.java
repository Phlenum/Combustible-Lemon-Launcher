package ibuilder99.mods.cll.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ibuilder99.mods.cll.CombustibleLemonLauncher;
import ibuilder99.mods.cll.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.StepSound;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class LemonSapling extends BlockSapling {

	public String name;

	public LemonSapling(int id, String unloc, String loc_en, StepSound sound, float hardness, float resistance) {
		super(id);
		setHardness(hardness);
		setResistance(resistance);
		name = unloc;
		setUnlocalizedName(unloc);
		setStepSound(sound);
		setCreativeTab(CombustibleLemonLauncher.tabLemonLauncher);
		LanguageRegistry.addName(this, loc_en);
		GameRegistry.registerBlock(this, unloc);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2){
		return blockIcon;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List){}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + name);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		if(par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == Item.dyePowder.itemID && par5EntityPlayer.getCurrentEquippedItem().getItemDamage() == 15){
			par5EntityPlayer.getCurrentEquippedItem().stackSize--;
			this.markOrGrowMarked(par1World, par2, par3, par4, par1World.rand);
			return true;
		}
		return false;
	}

	@Override
	public void growTree(World world, int x, int y, int z, Random par5Random){
		CombustibleLemonLauncher.proxy.generateTree(x, y, z, world, LemonLauncherBlocks.LemonLeaves.blockID, Block.wood.blockID, LemonLauncherBlocks.LemonLeavesHarvested.blockID);
	}

}
