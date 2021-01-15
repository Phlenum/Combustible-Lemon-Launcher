package mods.phlenum.cll.client;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
//import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 05 Jan 2016
 */

//@SideOnly(Side.CLIENT)
public class HoldLikeBowHook {

	
	// http://www.minecraftforge.net/forum/index.php?topic=34372.0
	// diesieben07.
	// if you're ever gonna read this.
	// thank you for that hint :-)
	// EDIT: May 10th, 2016: looks different now
	
	//@SubscribeEvent
	/*public void onEntityLiving(RenderLivingEvent.Pre<EntityPlayer> event){
		if(event.getEntity() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.getEntity();
			ItemStack stackMainHand = player.getHeldItemMainhand();
			if(stackMainHand != null && stackMainHand.getItem() == CommonProxy.itemCombustibleLemonLauncher){
				/*ModelBiped modelBiped = (ModelBiped)event.getRenderer().getMainModel();
				switch(Minecraft.getMinecraft().gameSettings.mainHand){
				case LEFT:
					modelBiped.leftArmPose = ArmPose.BOW_AND_ARROW;
					break;
				case RIGHT:
					modelBiped.rightArmPose = ArmPose.BOW_AND_ARROW;
					break;
				default:
					break;
				}
			}
		}
	}*/
	
}
