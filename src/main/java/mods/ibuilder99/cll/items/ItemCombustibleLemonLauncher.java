package mods.ibuilder99.cll.items;

import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.network.packets.CLLPacketLauncherProcess;
import mods.ibuilder99.cll.proxy.CommonProxy.CommonHelper;
import mods.ibuilder99.cll.world.EntityLemon;
import mods.ibuilder99.cll.world.EntityLemon.LemonType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class ItemCombustibleLemonLauncher extends ItemCLL {

	private static final String NBTKEY_LEMONTYPE = "LemonType";
	private static final String LOCALIZED_SWITCHED_TYPE = "msg.ItemCombustibleLemonLauncher.switchedType";
	
	public ItemCombustibleLemonLauncher(String unloc){
		super(unloc);
		setMaxStackSize(1);
	}
	
	@Override
	public boolean getShareTag(){
		return true;
	}
	
	private static boolean createNBTTagIfNeeded(ItemStack itemstack){
		if(!itemstack.hasTagCompound()){
			itemstack.stackTagCompound = new NBTTagCompound();
			setLemonType(itemstack, LemonType.LEMONTYPE_NORMAL);
			return true;
		}
		return false;
	}
	
	private static void setLemonType(ItemStack cll, LemonType type){
		cll.getTagCompound().setByte(NBTKEY_LEMONTYPE, (byte)type.ordinal());
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
			String msg = StatCollector.translateToLocal(LOCALIZED_SWITCHED_TYPE).replace("%i", getLemonType(cll).itemReference.getItemStackDisplayName(cll));
			CommonHelper.sendMessageToPlayer(player, msg);
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
					return;
				}
				currentType.consumeItem(player);
			}
			CLLPacketLauncherProcess packetLaunchProcess = new CLLPacketLauncherProcess(currentType);
			CombustibleLemonLauncher.proxy.packetCLL_sendToPlayer(packetLaunchProcess, (EntityPlayerMP)player);
			EntityLemon lemonEnt = new EntityLemon(player.worldObj, player, currentType);
			player.worldObj.spawnEntityInWorld(lemonEnt);
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		doAction(par3EntityPlayer, par1ItemStack);
		return par1ItemStack;
	}

}
