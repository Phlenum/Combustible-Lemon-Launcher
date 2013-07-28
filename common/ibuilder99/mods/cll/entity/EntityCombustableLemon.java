package ibuilder99.mods.cll.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCombustableLemon extends EntityThrowable {

	public EntityCombustableLemon(World par1World) {
		super(par1World);
	}

	public EntityCombustableLemon(EntityLivingBase entthrower, World world){
		super(world, entthrower);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		//worldObj.createExplosion(getThrower(), posX, posY, posZ, 4.0F, true);
		for(int x = (int)posX - 5; x < (int)posX + 5; x++){
			for(int y = (int)posY - 5; y < (int)posY + 5; y++){
				for(int z =(int) posZ - 5; z < (int)posZ + 5; z++){
					if(worldObj.getBlockId(x, y, z) == 0 && (rand.nextBoolean() && rand.nextBoolean())){
						worldObj.setBlock(x, y, z, Block.fire.blockID);
					}
				}
			}
		}
		setDead();
	}

}
