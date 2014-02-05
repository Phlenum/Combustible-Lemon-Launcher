package mods.ibuilder99.cll.items;

import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.lib.CLLConfiguration;
import mods.ibuilder99.cll.lib.IKeyListener;
import mods.ibuilder99.cll.world.EntityLemon;
import mods.ibuilder99.cll.world.EntityLemon.LemonType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class ItemCombustibleLemonLauncher extends ItemCLL implements IKeyListener {

	private static final String NBTKEY_LEMONTYPE = "LemonType";
	private static final String LOCALIZED_SWITCHED_TYPE = "msg.ItemCombustibleLemonLauncher.switchedType";
	
	public ItemCombustibleLemonLauncher(int id, String unloc){
		super(id, unloc);
		setMaxStackSize(1);
	}
	
	@Override
	public boolean getShareTag(){
		return true;
	}
	
	private static boolean createNBTTagIfNeeded(ItemStack itemstack){
		if(!itemstack.hasTagCompound()){
			itemstack.stackTagCompound = new NBTTagCompound();
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
		String msg = StatCollector.translateToLocal(LOCALIZED_SWITCHED_TYPE).replace("%i", getLemonType(cll).itemReference.getItemStackDisplayName(cll));
		CombustibleLemonLauncher.proxy.sendMessageToPlayer(player, msg);
	}
	
	private static void doAction(EntityPlayer player, ItemStack itemstack){
		if(player.isSneaking()){
			if(createNBTTagIfNeeded(itemstack)){
				setLemonType(itemstack, LemonType.LEMONTYPE_NORMAL);
			}
			toggleLemonType(player, itemstack);
			return;
		}
		
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
	
	/**
	 * Fire a lemon if the player configured 'useKeyToFire=false'
	 */
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		if(!CLLConfiguration.BO_useKeyToFire && !par2World.isRemote){
			doAction(par3EntityPlayer, par1ItemStack);
		}
		return par1ItemStack;
	}

	/**
	 * Fire a lemon if the player configured 'useKeyToFire=true'
	 */
	
	@Override
	public void onKeyPressed(String key, EntityPlayer player, ItemStack itemstack){
		if(CLLConfiguration.BO_useKeyToFire){
			doAction(player, itemstack);
		}
	}

}
