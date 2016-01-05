package mods.phlenum.cll.entity;

import static mods.phlenum.cll.lib.Reference.*;

import io.netty.buffer.ByteBuf;
import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 05 Jan 2016
 */

public class EntityLemon extends EntityThrowable implements IEntityAdditionalSpawnData {

	@SideOnly(Side.CLIENT)
	private static class LemonTypeTextures {

		public static final ResourceLocation TEXTURE_LEMON = new ResourceLocation(TEXTURE_PREFIX + "textures/items/" + ITEM_LEMON + ".png");

		public static final ResourceLocation TEXTURE_LEMON_EXPLOSIVE = new ResourceLocation(TEXTURE_PREFIX + "textures/items/" + ITEM_LEMON_EXPLOSIVE + ".png");

	}

	public enum LemonType {

		LEMONTYPE_NORMAL(CommonProxy.itemLemon) {

			@Override
			public void performImpact(EntityLemon lemon, MovingObjectPosition mop){
				generateFire(lemon.worldObj, mop.hitVec);
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
				generateFire(lemon.worldObj, mop.hitVec);
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

		private static void generateFire(World world, Vec3 par1Vec3){
			Vec3i vec = new Vec3i(par1Vec3.xCoord, par1Vec3.yCoord, par1Vec3.zCoord);
			for(int x = (vec.getX() - DEFAULT_OFFSET_X); x < (vec.getX() + DEFAULT_OFFSET_X); x++){
				for(int y = (vec.getY() - DEFAULT_OFFSET_Y); y < (vec.getY() + DEFAULT_OFFSET_Y); y++){
					for(int z = (vec.getZ() - DEFAULT_OFFSET_Z); z < (vec.getZ() + DEFAULT_OFFSET_Z); z++){
						if(world.isAirBlock(new BlockPos(vec)) && (world.rand.nextBoolean())){
							world.setBlockState(new BlockPos(vec), Blocks.fire.getDefaultState());
							
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
	public void writeEntityToNBT(NBTTagCompound tagCompound){
		super.readEntityFromNBT(tagCompound);
		lemonType = LemonType.values()[tagCompound.getByte(NBTKEY_TYPE)];
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund){
		super.writeEntityToNBT(tagCompund);
		tagCompund.setByte(NBTKEY_TYPE, (byte)lemonType.ordinal());
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition){
		lemonType.performImpact(this, par1MovingObjectPosition);
		setDead();
	}
	
	public LemonType getLemonType(){
		return lemonType;
	}

}
