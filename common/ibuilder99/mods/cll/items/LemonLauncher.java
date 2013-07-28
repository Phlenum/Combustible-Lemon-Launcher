package ibuilder99.mods.cll.items;

import net.minecraft.entity.player.EntityPlayer;
import ibuilder99.mods.cll.entity.EntityCombustableLemon;
import ibuilder99.mods.cll.util.IKeyListener;

public class LemonLauncher extends ItemCLL implements IKeyListener {

	public LemonLauncher(int id, String unloc, String loc_en) {
		super(id, unloc, loc_en);
		setFull3D();
		setMaxStackSize(1);
	}

	@Override
	public void onKeyPressed(EntityPlayer player, String keybindingdesc) {
		if(keybindingdesc.equals("key.fire") && (player.inventory.hasItem(LemonLauncherItems.Lemon.itemID) || player.capabilities.isCreativeMode)){
			if(!player.capabilities.isCreativeMode){
				player.inventory.consumeInventoryItem(LemonLauncherItems.Lemon.itemID);
			}
			if(!player.worldObj.isRemote){
				player.worldObj.spawnEntityInWorld(new EntityCombustableLemon(player, player.worldObj));
			}
		}
	}

}
