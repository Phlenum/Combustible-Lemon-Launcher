package mods.phlenum.cll.items;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 12 Nov 2015
 */

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemLemonExplosive extends ItemFood {

    public ItemLemonExplosive(String unloc, int heal, float saturation, boolean dogfood){
        super(heal, saturation, dogfood);
        GameRegistry.registerItem(this, unloc);
        setUnlocalizedName(unloc);
        setCreativeTab(CommonProxy.tabCLL);
    }

    @Override
    protected void onFoodEaten(ItemStack p_onFoodEaten_1_, World p_onFoodEaten_2_, EntityPlayer p_onFoodEaten_3_){
        p_onFoodEaten_2_.createExplosion(p_onFoodEaten_3_, p_onFoodEaten_3_.posX, p_onFoodEaten_3_.posY, p_onFoodEaten_3_.posZ, 5.0f, true);
        p_onFoodEaten_3_.attackEntityFrom(CommonProxy.DAMAGE_SOURCE_EXPLOSIVE_LEMON, Integer.MAX_VALUE);
    }
}
