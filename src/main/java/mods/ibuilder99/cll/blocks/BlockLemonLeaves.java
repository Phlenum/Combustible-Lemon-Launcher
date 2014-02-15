package mods.ibuilder99.cll.blocks;

import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.Reference;
import mods.ibuilder99.cll.proxy.CommonProxy;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class BlockLemonLeaves extends BlockLeavesBase {

	public BlockLemonLeaves(int id, String unloc, float hardness, float resistance, SoundType sound){
		super(Material.leaves, true);
		setBlockName(unloc);
		setHardness(hardness);
		setResistance(resistance);
		setStepSound(sound);
		setBlockTextureName(Reference.TEXTURE_PREFIX + unloc);
		setCreativeTab(CombustibleLemonLauncher.TAB_COMBUSTIBLE_LEMON_LAUNCHER);
		GameRegistry.registerBlock(this, unloc);
	}
	
	@Override
	public Item getItemDropped(int par1, Random rand, int par2){
		return CommonProxy.itemLemon;
	}
	
	@Override
	public int quantityDropped(Random rand){
		int randomChance = rand.nextInt(5);
		return (randomChance == 3 ? 2 : 1);
	}

}
