package mods.phlenum.cll.client;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 05 Jan 2016
 */

@SideOnly(Side.CLIENT)
public class HoldLikeBowHook {

	
	// http://www.minecraftforge.net/forum/index.php?topic=34372.0
	// diesieben07.
	// if you're ever gonna read this.
	// thank you for that hint :-)
	
	@SubscribeEvent
	public void onEntityLiving(RenderLivingEvent.Pre<EntityPlayer> event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			if(player.getCurrentEquippedItem().getItem() == CommonProxy.itemCombustibleLemonLauncher){
				ModelBiped modelBiped = (ModelBiped)event.renderer.getMainModel();
				modelBiped.aimedBow = true;
			}
		}
	}
	
}
