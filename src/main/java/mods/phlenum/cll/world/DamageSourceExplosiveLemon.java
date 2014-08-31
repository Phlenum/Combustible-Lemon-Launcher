package mods.phlenum.cll.world;

import mods.phlenum.cll.lib.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class DamageSourceExplosiveLemon extends DamageSource {

	public DamageSourceExplosiveLemon(){
		super(Reference.DAMAGE_SOURCE_EXPLOSIVE_LEMON);
	}
	
	@Override
	public IChatComponent func_151519_b(EntityLivingBase livingPlayer){
		String msg = StatCollector.translateToLocal("msg.DamageSourceExplosiveLemon").replace("%i", ((EntityPlayer)livingPlayer).getCommandSenderName());
		return new ChatComponentText(msg);
	}

}
