package ibuilder99.mods.cll.blocks.tileentity;

import ibuilder99.mods.cll.util.Reference;
import net.minecraft.entity.Entity;

public class TileEntityBlueGel extends TileEntityGel {

	@Override
	public String getGelTexture() {
		return Reference.TEXTURE_BLUE_GEL;
	}

	@Override
	public void applyEffectToEntity(Entity toApply) {
		if(toApply.fallDistance > 0.0){
			System.out.println(toApply.fallDistance);
			toApply.motionY = toApply.fallDistance * 0.5;
		}
	}

}
