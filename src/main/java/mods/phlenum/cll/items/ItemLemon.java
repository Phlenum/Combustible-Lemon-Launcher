package mods.phlenum.cll.items;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 14 Dec 2014
 */

public class ItemLemon extends ItemFood {

    public ItemLemon(String unloc, int healAmount, float saturation, boolean dogsFood){
        super(healAmount, saturation, dogsFood);
        setUnlocalizedName(unloc);
        setCreativeTab(CommonProxy.tabCLL);
        GameRegistry.registerItem(this, unloc);
    }

}
