package ibuilder99.mods.cll.items;

import cpw.mods.fml.common.network.PacketDispatcher;
import ibuilder99.mods.cll.network.PacketUtil;
import ibuilder99.mods.cll.network.packets.PacketLauncherProcess;
import ibuilder99.mods.cll.util.ConfigLoader;
import ibuilder99.mods.cll.util.IKeyListener;
import ibuilder99.mods.cll.util.MiscHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LemonLauncher extends ItemCLL implements IKeyListener {

	public LemonLauncher(int id, String unloc, String loc_en) {
		super(id, unloc, loc_en);
		setFull3D();
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		if(!ConfigLoader.useKeyToFire){
			if(!par1ItemStack.hasTagCompound()){
				MiscHelper.setInteger(par1ItemStack, "Lemon", LemonLauncherItems.Lemon.itemID);
			}
			if(par3EntityPlayer.isSneaking() && !par2World.isRemote){
				MiscHelper.setInteger(par1ItemStack, "Lemon", getNextLemon(par1ItemStack));
				par3EntityPlayer.addChatMessage(MiscHelper.translateToLocal("info.lemonChange", "Changed projectile to %i").replace("%i", Item.itemsList[MiscHelper.getInteger(par1ItemStack, "Lemon")].getItemDisplayName(null)));
				return par1ItemStack;
			}
			if(par2World.isRemote && !par3EntityPlayer.isSneaking()){
				if(!par3EntityPlayer.capabilities.isCreativeMode){
					if(!par3EntityPlayer.inventory.hasItem(MiscHelper.getInteger(par1ItemStack, "Lemon"))){
						return par1ItemStack;
					}
				}
				PacketLauncherProcess packet = new PacketLauncherProcess(MiscHelper.getInteger(par1ItemStack, "Lemon"), par3EntityPlayer.username);
				PacketDispatcher.sendPacketToServer(PacketUtil.buildPacket(packet));
				/*
			if(!par3EntityPlayer.worldObj.isRemote){
				par3EntityPlayer.worldObj.spawnEntityInWorld(new EntityCombustibleLemon(par3EntityPlayer, par3EntityPlayer.worldObj, MiscHelper.getInteger(par1ItemStack, "Lemon")));
			}*/
			}
		}
		return par1ItemStack;
	}

	@Override
	public boolean getShareTag(){
		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack){
		return EnumAction.bow;
	}

	@Override
	public void onKeyPressed(EntityPlayer player, String keybindingdesc) {
		if(!player.getCurrentEquippedItem().hasTagCompound()){
			MiscHelper.setInteger(player.getCurrentEquippedItem(), "Lemon", LemonLauncherItems.Lemon.itemID);
		}
		if(player.isSneaking() && !player.worldObj.isRemote){
			MiscHelper.setInteger(player.getCurrentEquippedItem(), "Lemon", getNextLemon(player.getCurrentEquippedItem()));
			player.addChatMessage(MiscHelper.translateToLocal("info.lemonChange", "Changed projectile to %i").replace("%i", Item.itemsList[MiscHelper.getInteger(player.getCurrentEquippedItem(), "Lemon")].getItemDisplayName(null)));
			return;
		}
		if(!player.isSneaking()){
			if(!player.capabilities.isCreativeMode){
				if(!player.inventory.hasItem(MiscHelper.getInteger(player.getCurrentEquippedItem(), "Lemon"))){
					return;
				}
			}
			PacketLauncherProcess packet = new PacketLauncherProcess(MiscHelper.getInteger(player.getCurrentEquippedItem(), "Lemon"), player.username);
			PacketDispatcher.sendPacketToServer(PacketUtil.buildPacket(packet));
			/*
			if(!par3EntityPlayer.worldObj.isRemote){
				par3EntityPlayer.worldObj.spawnEntityInWorld(new EntityCombustibleLemon(par3EntityPlayer, par3EntityPlayer.worldObj, MiscHelper.getInteger(par1ItemStack, "Lemon")));
			}*/
		}
	}

	private int getNextLemon(ItemStack launcher){
		int lemon = MiscHelper.getInteger(launcher, "Lemon");
		if(lemon == LemonLauncherItems.Lemon.itemID){
			return LemonLauncherItems.LemonExplosive.itemID;
		}else if(lemon == LemonLauncherItems.LemonExplosive.itemID){
			return LemonLauncherItems.Lemon.itemID;
		}
		return LemonLauncherItems.Lemon.itemID;
	}

}
