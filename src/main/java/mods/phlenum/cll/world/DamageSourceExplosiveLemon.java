package mods.phlenum.cll.world;

import static mods.phlenum.cll.lib.Reference.DAMAGE_SOURCE_EXPLOSIVE_LEMON;

import net.minecraft.client.resources.I18n;
//import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TextComponentString;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 14 Dec 2014
 */

public class DamageSourceExplosiveLemon extends DamageSource {

    public DamageSourceExplosiveLemon(){
        super(DAMAGE_SOURCE_EXPLOSIVE_LEMON);
    }

    /*@Override
    public ITextComponent getDeathMessage(EntityLivingBase entity){
        String deathMessage = I18n.format("msg.damagesourceexplosivelemon", entity.getName());
        return new TextComponentString(deathMessage);
    }*/
}
