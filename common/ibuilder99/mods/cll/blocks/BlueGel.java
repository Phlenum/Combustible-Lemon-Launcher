package ibuilder99.mods.cll.blocks;

import ibuilder99.mods.cll.blocks.tileentity.TileEntityBlueGel;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlueGel extends BlockGel {

	public BlueGel(int id, String unloc, String loc) {
		super(id, unloc, loc);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBlueGel();
	}

}
