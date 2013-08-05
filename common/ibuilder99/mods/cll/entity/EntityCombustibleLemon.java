package ibuilder99.mods.cll.entity;

import ibuilder99.mods.cll.items.LemonLauncherItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCombustibleLemon extends EntityThrowable {

	public int Lemon;

	public EntityCombustibleLemon(World par1World) {
		super(par1World);
	}

	public EntityCombustibleLemon(EntityLivingBase entthrower, World world, int lemonid){
		super(world, entthrower);
		Lemon = lemonid;
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if(Lemon == LemonLauncherItems.Lemon.itemID){
			spreadFire(movingobjectposition, 5);
		}else if(Lemon == LemonLauncherItems.LemonExplosive.itemID){
			worldObj.createExplosion(getThrower(), posX, posY, posZ, 5.0F, true);
			spreadFire(movingobjectposition, 5);
		}
		setDead();
	}

	private void spreadFire(MovingObjectPosition mop, int range){
		if(mop.sideHit == 1){
			worldObj.setBlock(mop.blockX, mop.blockY + 1, mop.blockZ, Block.fire.blockID);
		}
		for(int x = (int)posX - range; x < (int)posX + range; x++){
			for(int y = (int)posY - range; y < (int)posY + range; y++){
				for(int z =(int) posZ - range; z < (int)posZ + range; z++){
					if((worldObj.getBlockId(x, y, z) == 0 || worldObj.getBlockId(x, y, z) == Block.snow.blockID) && rand.nextBoolean()){
						worldObj.setBlock(x, y, z, Block.fire.blockID);
					}
				}
			}
		}
	}

}
