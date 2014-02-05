package mods.ibuilder99.cll.world;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.ibuilder99.cll.lib.Reference;
import mods.ibuilder99.cll.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class EntityLemon extends EntityThrowable implements IEntityAdditionalSpawnData {
	
	public enum LemonType {
		
		LEMONTYPE_NORMAL(CommonProxy.itemLemon) {
			
			@SideOnly(Side.CLIENT)
			private final ResourceLocation TEXTURE = new ResourceLocation(Reference.TEXTURE_PREFIX + "textures/items/" + Reference.ITEM_LEMON + ".png");
			
			@Override
			public void performImpact(EntityLemon lemon, MovingObjectPosition mop){
				generateFire(lemon.worldObj);
			}
			
			@Override
			@SideOnly(Side.CLIENT)
			public ResourceLocation getTexture(){
				return TEXTURE;
			}
			
		},
		LEMONTYPE_EXPLOSION(CommonProxy.itemLemonExplosive) {
			
			@SideOnly(Side.CLIENT)
			private final ResourceLocation TEXTURE = new ResourceLocation(Reference.TEXTURE_PREFIX + "textures/items/" + Reference.ITEM_LEMON_EXPLOSIVE + ".png");
			
			@Override
			public void performImpact(EntityLemon lemon, MovingObjectPosition mop){
				generateFire(lemon.worldObj);
				lemon.worldObj.createExplosion(lemon, lemon.posX, lemon.posY, lemon.posZ, DEFAULT_EXPLOSTION_STRENGTH, true);
			}
			
			@Override
			@SideOnly(Side.CLIENT)
			public ResourceLocation getTexture(){
				return TEXTURE;
			}
			
		};
		
		public final Item itemReference;
		
		private static final int DEFAULT_LENGTH = 5;
		private static final int DEFAULT_WIDTH = 5;
		private static final int DEFAULT_HEIGHT = 5;
		private static final float DEFAULT_EXPLOSTION_STRENGTH = 3.0F;
		
		LemonType(Item itemref){
			itemReference = itemref;
		}
		
		private static void generateFire(World world){
			
		}
		
		public boolean playerHasItem(EntityPlayer player){
			return player.inventory.hasItem(itemReference);
		}
		
		public void consumeItem(EntityPlayer player){
			player.inventory.consumeInventoryItem(itemReference);
		}
		
		public abstract void performImpact(EntityLemon lemon, MovingObjectPosition mop);
		
		@SideOnly(Side.CLIENT)
		public abstract ResourceLocation getTexture();
		
	}
	
	private static final String NBTKEY_TYPE = "LemonType";
	
	private LemonType lemonType;
	
	public EntityLemon(World par1World){
		super(par1World);
	}
	
	public EntityLemon(World world, EntityPlayer player, LemonType type){
		super(world, player);
		lemonType = type;
	}
	
	@Override
	protected void entityInit(){
		super.entityInit();
		if(lemonType == null){
			lemonType = LemonType.LEMONTYPE_NORMAL;
		}
	}
	
	@Override
	public void writeSpawnData(ByteBuf buffer){
		buffer.writeByte((byte)lemonType.ordinal());
	}
	
	@Override
	public void readSpawnData(ByteBuf additionalData){
		lemonType = LemonType.values()[additionalData.readByte()];
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1){
		super.readEntityFromNBT(var1);
		lemonType = LemonType.values()[var1.getByte(NBTKEY_TYPE)];
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1){
		super.writeEntityToNBT(var1);
		var1.setByte(NBTKEY_TYPE, (byte)lemonType.ordinal());
	}

	@Override
	protected void onImpact(MovingObjectPosition var1){
		lemonType.performImpact(this, var1);
		setDead();
	}
	
	public LemonType getLemonType(){
		return lemonType;
	}

}
