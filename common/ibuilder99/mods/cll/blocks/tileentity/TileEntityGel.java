package ibuilder99.mods.cll.blocks.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public abstract class TileEntityGel extends TileEntity {

	public AxisAlignedBB effective;
	
	public abstract String getGelTexture();
	
	public abstract void applyEffectToEntity(Entity toApply);
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateEntity(){
		setupAABB();
		List<Entity> entityAround = worldObj.getEntitiesWithinAABB(Entity.class, effective);
		for(int i = 0; i < entityAround.size(); i++){
			applyEffectToEntity(entityAround.get(i));
		}
	}
	
	private void setupAABB(){
		effective = getRenderBoundingBox().copy();
		effective.maxY += 0.5;
	}
}
