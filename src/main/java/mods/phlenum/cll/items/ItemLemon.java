package mods.phlenum.cll.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 12 Nov 2015
 */

public class ItemLemon extends ItemFood {

    public ItemLemon(String unloc, int healAmount, float saturation, boolean dogfood){
        super(healAmount, saturation, dogfood, new Item.Properties());
        setRegistryName(unloc);
    }

}
