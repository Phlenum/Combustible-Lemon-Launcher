package mods.phlenum.cll.items;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 14 Dec 2014
 */

public class ItemLemonExplosive extends ItemFood {

    public ItemLemonExplosive(String unloc, int healAmount, float saturation, boolean dogsFood){
        super(healAmount, saturation, dogsFood);
        setUnlocalizedName(unloc);
        setCreativeTab(CommonProxy.tabCLL);
        GameRegistry.registerItem(this, unloc);
    }

    @Override
    protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
        par2World.createExplosion(par3EntityPlayer, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, 4.0F, true);
        par3EntityPlayer.attackEntityFrom(CommonProxy.damageSourceExplosiveLemon, Float.MAX_VALUE);
    }
}
