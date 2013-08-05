package ibuilder99.mods.cll.blocks;

import ibuilder99.mods.cll.CombustibleLemonLauncher;
import ibuilder99.mods.cll.util.ConfigLoader;
import ibuilder99.mods.cll.util.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public abstract class BlockGel extends BlockContainer {

	public String name;

	public BlockGel(int id, String unloc, String loc) {
		super(id, Material.rock);
		setUnlocalizedName(unloc);
		name = unloc;
		setStepSound(Block.soundClothFootstep);
		setHardness(1.0F);
		setResistance(0.1F);
		LanguageRegistry.addName(this, loc);
		setCreativeTab(CombustibleLemonLauncher.tabLemonLauncher);
		maxY -= 0.95D;
		GameRegistry.registerBlock(this, unloc);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		return ConfigLoader.ModelGel;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube(){
		return false;
	}

}
