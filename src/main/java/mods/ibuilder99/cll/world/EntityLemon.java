package mods.ibuilder99.cll.world;

import mods.ibuilder99.cll.proxy.CommonProxy;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityLemon extends EntityThrowable {
	
	public enum LemonType {
		
		LEMONTYPE_NORMAL(CommonProxy.itemLemon) {
			
			@Override
			public void performImpact(EntityLemon lemon, MovingObjectPosition mop){
				generateFire(lemon.worldObj);
			}
			
		},
		LEMONTYPE_EXPLOSION(CommonProxy.itemLemonExplosive) {
			
			@Override
			public void performImpact(EntityLemon lemon, MovingObjectPosition mop){
				generateFire(lemon.worldObj);
				lemon.worldObj.createExplosion(lemon, lemon.posX, lemon.posY, lemon.posZ, DEFAULT_EXPLOSTION_STRENGTH, true);
			}
			
		};
		
		public final Item itemReference;
		
		private static final int DEFAULT_LENGTH = 5;
		private static final int DEFAULT_WIDTH = 5;
		private static final int DEFAULT_HEIGHT = 5;
		private static final float DEFAULT_EXPLOSTION_STRENGTH = 10.0F;
		
		LemonType(Item itemref){
			itemReference = itemref;
		}
		
		private static void generateFire(World world){
			
		}
		
		public abstract void performImpact(EntityLemon lemon, MovingObjectPosition mop);
		
	}
	
	private static final String NBTKEY_TYPE = "LemonType";
	
	private LemonType lemonType;
	
	public EntityLemon(World par1World){
		super(par1World);
		if(lemonType == null){
			lemonType = LemonType.LEMONTYPE_NORMAL;
		}
	}
	
	public EntityLemon(World par1World, LemonType type){
		this(par1World);
		lemonType = type;
	}

	@Override
	protected void entityInit(){}
	
	@Override
	public void onUpdate(){
		
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1){
		lemonType = LemonType.values()[var1.getByte(NBTKEY_TYPE)];
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1){
		var1.setByte(NBTKEY_TYPE, (byte)lemonType.ordinal());
	}

	@Override
	protected void onImpact(MovingObjectPosition var1){
		lemonType.performImpact(this, var1);
	}

}
