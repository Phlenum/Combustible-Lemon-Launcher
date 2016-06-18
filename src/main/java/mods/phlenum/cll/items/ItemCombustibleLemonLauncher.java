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
	
	// Server Code
	private static void doAction(EntityPlayer player, ItemStack itemstack){
		createNBTTagIfNeeded(itemstack);
		if(player.isSneaking()){
			toggleLemonType(player, itemstack);
			return;
		}
		if(!player.worldObj.isRemote){
			LemonType currentType = getLemonType(itemstack);
			if(!player.capabilities.isCreativeMode){
				if(!currentType.playerHasItem(player)){
					player.worldObj.playAuxSFX(1001, player.getPosition(), 0);
					return;
				}
				currentType.consumeItem(player);
			}
			CLLPacketLauncherProcess packetLauncherProcess = new CLLPacketLauncherProcess(currentType);
			CombustibleLemonLauncher.proxy.packetCLL_sendToPlayer(packetLauncherProcess, (EntityPlayerMP)player);
			EntityLemon lemonEnt = new EntityLemon(player.worldObj, player, currentType);
			lemonEnt.func_184538_a(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			player.worldObj.spawnEntityInWorld(lemonEnt);
			player.worldObj.playSound(player, player.getPosition(), SoundEvents.item_firecharge_use, SoundCategory.AMBIENT, 0.3F, itemRand.nextFloat());
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		doAction(playerIn, itemStackIn);
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

}
