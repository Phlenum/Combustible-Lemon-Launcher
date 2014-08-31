package mods.phlenum.cll.world;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.phlenum.cll.lib.Reference;
import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Combustible Lemon Launcher
 * Minecraft Forge Modification
 */

public class EntityLemon extends EntityThrowable implements IEntityAdditionalSpawnData {
	
	@SideOnly(Side.CLIENT)
	private static class LemonTypeTextures {
		
		public static final ResourceLocation TEXTURE_LEMON = new ResourceLocation(Reference.TEXTURE_PREFIX + "textures/items/" + Reference.ITEM_LEMON + ".png");
		
		public static final ResourceLocation TEXTURE_LEMON_EXPLOSIVE = new ResourceLocation(Reference.TEXTURE_PREFIX + "textures/items/" + Reference.ITEM_LEMON_EXPLOSIVE + ".png");
		
	}
	
	public enum LemonType {
		
		LEMONTYPE_NORMAL(CommonProxy.itemLemon) {
			
			@Override
			public void performImpact(EntityLemon lemon, MovingObjectPosition mop){
				generateFire(lemon.worldObj, mop.blockX, mop.blockY, mop.blockZ);
			}
			
			@Override
			@SideOnly(Side.CLIENT)
			public ResourceLocation getTexture(){
				return LemonTypeTextures.TEXTURE_LEMON;
			}
			
		},
		LEMONTYPE_EXPLOSION(CommonProxy.itemLemonExplosive) {
			
			@Override
			public void performImpact(EntityLemon lemon, MovingObjectPosition mop){
				lemon.worldObj.createExplosion(lemon, lemon.posX, lemon.posY, lemon.posZ, DEFAULT_EXPLOSTION_STRENGTH, true);
				generateFire(lemon.worldObj, mop.blockX, mop.blockY, mop.blockZ);
			}
			
			@Override
			@SideOnly(Side.CLIENT)
			public ResourceLocation getTexture(){
				return LemonTypeTextures.TEXTURE_LEMON_EXPLOSIVE;
			}
			
		};
		
		public final Item itemReference;
		
		private static final int DEFAULT_OFFSET_X = 5;
		private static final int DEFAULT_OFFSET_Y = 5;
		private static final int DEFAULT_OFFSET_Z = 5;
		private static final float DEFAULT_EXPLOSTION_STRENGTH = 3.0F;
		
		LemonType(Item itemref){
			itemReference = itemref;
		}
		
		private static void generateFire(World world, int xSrc, int ySrc, int zSrc){
			for(int x = (xSrc - DEFAULT_OFFSET_X); x < (xSrc + DEFAULT_OFFSET_X); x++){
				for(int y = (ySrc - DEFAULT_OFFSET_Y); y < (ySrc + DEFAULT_OFFSET_Y); y++){
					for(int z = (zSrc - DEFAULT_OFFSET_Z); z < (zSrc + DEFAULT_OFFSET_Z); z++){
						if((world.getBlock(x, y, z) == Blocks.air) && (world.rand.nextBoolean())){
							world.setBlock(x, y, z, Blocks.fire);
						}
					}
				}
			}
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
