package mods.phlenum.cll.items;

import mods.phlenum.cll.CombustibleLemonLauncher;
import mods.phlenum.cll.entity.EntityLemon;
import mods.phlenum.cll.entity.EntityLemon.LemonType;
import mods.phlenum.cll.network.packets.CLLPacketLauncherProcess;
import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 05 Jan 2016
 */

public class ItemCombustibleLemonLauncher extends Item {
	
	private static final String NBTKEY_LEMONTYPE = "LemonType";
	private static final String LOCALIZED_SWITCHED_TYPE = "msg.ItemCombustibleLemonLauncher.switchedType";
	
	public ItemCombustibleLemonLauncher(String unloc){
		super();
		setCreativeTab(CommonProxy.tabCLL);
		setUnlocalizedName(unloc);
		setMaxStackSize(1);
		setRegistryName(unloc);
		GameRegistry.register(this);
	}
	
	@Override
	public boolean getShareTag(){
		return true;
	}
	
	private static void setLemonType(ItemStack cll, LemonType type){
		cll.getTagCompound().setByte(NBTKEY_LEMONTYPE, (byte)type.ordinal());
	}
	
	private static boolean createNBTTagIfNeeded(ItemStack itemstack){
		if(!itemstack.hasTagCompound()){
			itemstack.setTagCompound(new NBTTagCompound());
			setLemonType(itemstack, LemonType.LEMONTYPE_NORMAL);
			return true;
		}
		return false;
	}
	
	private static LemonType getLemonType(ItemStack cll){
		return LemonType.values()[cll.getTagCompound().getByte(NBTKEY_LEMONTYPE)];
	}
	
	private static void toggleLemonType(EntityPlayer player, ItemStack cll){
		switch(getLemonType(cll)){
		case LEMONTYPE_NORMAL:
			setLemonType(cll, LemonType.LEMONTYPE_EXPLOSION);
			break;
		case LEMONTYPE_EXPLOSION:
			setLemonType(cll, LemonType.LEMONTYPE_NORMAL);
			break;
		}
		if(player.worldObj.isRemote){
			String msg = I18n.translateToLocal(LOCALIZED_SWITCHED_TYPE).replace("%i", getLemonType(cll).itemReference.getDisplayName());
			player.addChatComponentMessage(new TextComponentString(msg));
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		createNBTTagIfNeeded(itemStackIn);
		if(playerIn.isSneaking()){
			toggleLemonType(playerIn, itemStackIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
		}
		if(!playerIn.worldObj.isRemote){
			LemonType currentType = getLemonType(itemStackIn);
			if(!playerIn.capabilities.isCreativeMode){
				if(!currentType.playerHasItem(playerIn)){
					playerIn.worldObj.playAuxSFX(1001, playerIn.getPosition(), 0);
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
				}
				currentType.consumeItem(playerIn);
			}
			CLLPacketLauncherProcess packetLauncherProcess = new CLLPacketLauncherProcess(currentType);
			CombustibleLemonLauncher.proxy.packetCLL_sendToPlayer(packetLauncherProcess, (EntityPlayerMP)playerIn);
			EntityLemon lemonEnt = new EntityLemon(playerIn.worldObj, playerIn, currentType);
			lemonEnt.func_184538_a(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			playerIn.worldObj.spawnEntityInWorld(lemonEnt);
			playerIn.worldObj.playSound(null, playerIn.getPosition(), SoundEvents.entity_egg_throw, SoundCategory.AMBIENT, 0.3F, itemRand.nextFloat());
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}

}
