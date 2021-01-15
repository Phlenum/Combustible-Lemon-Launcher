package mods.phlenum.cll.entity;

import static mods.phlenum.cll.lib.Reference.*;

import io.netty.buffer.ByteBuf;
import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.entity.EntityType;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.entity.projectile.EntityThrowable;
//import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
//import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 05 Jan 2016
 */

public class EntityLemon /*extends EntityThrowable implements IEntityAdditionalSpawnData*/ {

	/*protected EntityLemon(EntityType<?> type, double p_i48541_2_, double p_i48541_4_, double p_i48541_6_, World p_i48541_8_){
		super(type, p_i48541_2_, p_i48541_4_, p_i48541_6_, p_i48541_8_);
	}
	//@SideOnly(Side.CLIENT)
	private static class LemonTypeTextures {

		public static final ResourceLocation TEXTURE_LEMON = new ResourceLocation(TEXTURE_PREFIX + "textures/items/" + ITEM_LEMON + ".png");

		public static final ResourceLocation TEXTURE_LEMON_EXPLOSIVE = new ResourceLocation(TEXTURE_PREFIX + "textures/items/" + ITEM_LEMON_EXPLOSIVE + ".png");

	}

	public enum LemonType {

		LEMONTYPE_NORMAL(CommonProxy.itemLemon) {

			@Override
			public void performImpact(EntityLemon lemon, RayTraceResult rtr){
				generateFire(lemon.world, rtr.hitVec);
			}

			@Override
			//@SideOnly(Side.CLIENT)
			public ResourceLocation getTexture(){
				return LemonTypeTextures.TEXTURE_LEMON;
			}

		},
		LEMONTYPE_EXPLOSION(CommonProxy.itemLemonExplosive) {

			@Override
			public void performImpact(EntityLemon lemon, RayTraceResult rtr){
				lemon.world.createExplosion(lemon, lemon.posX, lemon.posY, lemon.posZ, DEFAULT_EXPLOSTION_STRENGTH, true);
				generateFire(lemon.world, rtr.hitVec);
			}

			@Override
			//@SideOnly(Side.CLIENT)
			public ResourceLocation getTexture(){
				return LemonTypeTextures.TEXTURE_LEMON_EXPLOSIVE;
			}

		};

		public final ItemStack itemReference;

		private static final int DEFAULT_OFFSET_X = 5;
		private static final int DEFAULT_OFFSET_Y = 5;
		private static final int DEFAULT_OFFSET_Z = 5;
		private static final float DEFAULT_EXPLOSTION_STRENGTH = 3.0F;

		LemonType(Item itemref){
			itemReference = new ItemStack(itemref);
		}

		private static void generateFire(World world, Vec3d par1Vec3d){
			int x = (int)par1Vec3d.x;
			int y = (int)par1Vec3d.y;
			int z = (int)par1Vec3d.z;

			for(int currX = (x - DEFAULT_OFFSET_X); currX < (x + DEFAULT_OFFSET_X); currX++){
				for(int currY = (y - DEFAULT_OFFSET_Y); currY < (y + DEFAULT_OFFSET_Y); currY++){
					for(int currZ = (z - DEFAULT_OFFSET_Z); currZ < (z + DEFAULT_OFFSET_Z); currZ++){
						BlockPos pos = new BlockPos(currX, currY, currZ);
						if(world.isAirBlock(pos) && world.rand.nextBoolean() && world.rand.nextBoolean()){
							world.setBlockState(pos, Blocks.FIRE.getDefaultState());
						}
					}
				}
			}
		}

		public boolean playerHasItem(EntityPlayer player){
			return player.inventory.hasItemStack(itemReference);
		}
		
		private boolean itemStackEqualsProjectile(ItemStack stackin){
			return stackin != null && stackin.getItem() == itemReference.getItem(); 
		}
		
		private static void consumeFromStack(ItemStack stackin, InventoryPlayer inv){
			stackin.shrink(1);
			if(stackin.getCount() == 0){
				inv.deleteStack(stackin);
			}
		}
		
		public void consumeItem(EntityPlayer player){
			if(itemStackEqualsProjectile(player.getHeldItem(EnumHand.MAIN_HAND))){
				consumeFromStack(player.getHeldItem(EnumHand.MAIN_HAND), player.inventory);
			}else if(itemStackEqualsProjectile(player.getHeldItem(EnumHand.OFF_HAND))){
				consumeFromStack(player.getHeldItem(EnumHand.OFF_HAND), player.inventory);
			}else{
				for(int i = 0; i < player.inventory.getSizeInventory(); ++i){
					ItemStack curr = player.inventory.getStackInSlot(i);
					if(itemStackEqualsProjectile(curr)){
						consumeFromStack(curr, player.inventory);
					}
				}
			}
		}

		public abstract void performImpact(EntityLemon lemon, RayTraceResult rtr);

		//@SideOnly(Side.CLIENT)
		public abstract ResourceLocation getTexture();

	}
	
	private static final String NBTKEY_TYPE = "LemonType";
	private LemonType lemonType;
	@Override
	public void writeSpawnData(PacketBuffer buffer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void readSpawnData(PacketBuffer additionalData) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onImpact(RayTraceResult result) {
		// TODO Auto-generated method stub
		
	}

/*
	public EntityLemon(World par1World){
		//super(par1World);
	}
	
	public EntityLemon(World world, EntityPlayer player, LemonType type){
		//super(world, player);
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
	protected void onImpact(RayTraceResult par1RayTraceResult){
		if(!world.isRemote){
			lemonType.performImpact(this, par1RayTraceResult);
			setDead();
		}
	}
	
	public LemonType getLemonType(){
		return lemonType;
	}
	*/

}
