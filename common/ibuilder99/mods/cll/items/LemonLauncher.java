package ibuilder99.mods.cll.items;

import ibuilder99.mods.cll.entity.EntityCombustibleLemon;
import ibuilder99.mods.cll.util.ConfigLoader;
import ibuilder99.mods.cll.util.IKeyListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LemonLauncher extends ItemCLL implements IKeyListener {

	public LemonLauncher(int id, String unloc, String loc_en) {
		super(id, unloc, loc_en);
		setFull3D();
		setMaxStackSize(1);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		if(!ConfigLoader.useKeyToFire){
			if(!par3EntityPlayer.capabilities.isCreativeMode){
				if(!par3EntityPlayer.inventory.hasItem(LemonLauncherItems.Lemon.itemID)){
					return par1ItemStack;
				}
				par3EntityPlayer.inventory.consumeInventoryItem(LemonLauncherItems.Lemon.itemID);
			}
			if(!par3EntityPlayer.worldObj.isRemote){
				par3EntityPlayer.worldObj.spawnEntityInWorld(new EntityCombustibleLemon(par3EntityPlayer, par3EntityPlayer.worldObj));
			}
		}
		return par1ItemStack;
	}

	@Override
	public void onKeyPressed(EntityPlayer player, String keybindingdesc) {
		if(keybindingdesc.equals("key.fire") && (player.inventory.hasItem(LemonLauncherItems.Lemon.itemID) || player.capabilities.isCreativeMode)){
			if(!player.capabilities.isCreativeMode){
				player.inventory.consumeInventoryItem(LemonLauncherItems.Lemon.itemID);
			}
			if(!player.worldObj.isRemote){
				player.worldObj.spawnEntityInWorld(new EntityCombustibleLemon(player, player.worldObj));
			}
		}
	}

}
