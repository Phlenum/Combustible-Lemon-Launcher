package mods.ibuilder99.cll.network.packets;

import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import mods.ibuilder99.cll.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class CLLPacketLauncherProcess extends CLLPacket {

	private static final ItemStack ITEMSTACK_LEMON = new ItemStack(CommonProxy.itemLemon);
	private static final ItemStack ITEMSTACK_COMBUSTIBLE_LEMON = new ItemStack(CommonProxy.itemLemonExplosive);
	
	private boolean isCombustible;
	
	public CLLPacketLauncherProcess(){} // <-- Default constructor for Class.newInstance()
	
	public CLLPacketLauncherProcess(boolean combustible){
		isCombustible = combustible;
	}
	
	@Override
	public void writeDataTo(ByteBufOutputStream buffer) throws IOException {
		buffer.writeBoolean(isCombustible);
	}

	@Override
	public void readDataFrom(ByteBufInputStream buffer) throws IOException {
		isCombustible = buffer.readBoolean();
	}
	
	private void handlePacket(EntityPlayer player){
		if(!player.capabilities.isCreativeMode){
			if((isCombustible && player.inventory.hasItemStack(ITEMSTACK_COMBUSTIBLE_LEMON)) || (!isCombustible && player.inventory.hasItemStack(ITEMSTACK_LEMON))){
				player.inventory.consumeInventoryItem(isCombustible ? CommonProxy.itemLemonExplosive : CommonProxy.itemLemon);
			}else{
				return;
			}
		}
	}

	@Override
	public void handleClientSide(EntityPlayer playerSP){
		handlePacket(playerSP);
	}

	@Override
	public void handleServerSide(EntityPlayer playerMP){
		handlePacket(playerMP);
		// TODO: Create 'EntityLemon' and spawn it (!!)server(!!) side
		//player.worldObj.spawnEntityInWorld(par1Entity)
	}

}
