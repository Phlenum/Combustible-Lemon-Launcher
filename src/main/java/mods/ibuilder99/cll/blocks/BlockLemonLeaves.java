package mods.ibuilder99.cll.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.Reference;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class BlockLemonLeaves extends BlockLeavesBase {

	public BlockLemonLeaves(int id, String unloc, float hardness, float resistance, SoundType sound){
		super(Material.leaves, true);
		setStepSound(sound);
		setBlockName(unloc);
		setBlockTextureName(Reference.TEXTURE_PREFIX + unloc);
		setCreativeTab(CombustibleLemonLauncher.TAB_COMBUSTIBLE_LEMON_LAUNCHER);
		GameRegistry.registerBlock(this, unloc);
	}

}
