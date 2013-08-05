package ibuilder99.mods.cll.blocks;

import ibuilder99.mods.cll.util.ConfigLoader;
import ibuilder99.mods.cll.util.Reference;
import net.minecraft.block.Block;

public class LemonLauncherBlocks {
	
	public static Block LemonLeaves;
	public static Block LemonLeavesHarvested;
	public static Block LemonSapling;
	
	public static Block BlueGel;
	
	public static void initBlocks(){
		LemonLeaves = new LemonLeaves(ConfigLoader.LemonLeavesID, Reference.BLOCK_LEMON_LEAVES, "Lemon Leaves", 0.2F, 0.2F, Block.soundGrassFootstep);
		LemonLeavesHarvested = new LemonLeavesHarvested(ConfigLoader.LemonLeavesHarvestedID, Reference.BLOCK_LEMON_LEAVES_HARVESTED, "Lemon Leaves (Growing)", 0.2F, 0.2F, Block.soundGrassFootstep);
		LemonSapling = new LemonSapling(ConfigLoader.LemonSapling, Reference.BLOCK_LEMON_SAPLING, "Lemon Sapling", Block.soundGrassFootstep, 0.0F, 0.0F);
		
		BlueGel = new BlueGel(ConfigLoader.BlueGelID, Reference.BLOCK_BLUE_GEL, "Blue Gel");
	}
}
