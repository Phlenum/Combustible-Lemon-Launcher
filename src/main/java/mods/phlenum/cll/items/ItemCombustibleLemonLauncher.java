package mods.phlenum.cll.items;

import mods.phlenum.cll.entity.EntityLemon;
import mods.phlenum.cll.entity.EntityLemon.LemonType;
import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
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
		GameRegistry.registerItem(this, unloc);
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
			String msg = StatCollector.translateToLocal(LOCALIZED_SWITCHED_TYPE).replace("%i", getLemonType(cll).itemReference.getItemStackDisplayName(cll));
			player.addChatComponentMessage(new ChatComponentText(msg));
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
