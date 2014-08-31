package mods.phlenum.cll.items;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.phlenum.cll.CombustibleLemonLauncher;
import mods.phlenum.cll.lib.Reference;
import net.minecraft.item.ItemFood;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class ItemLemon extends ItemFood {

	public ItemLemon(int healAmount, String unloc, float saturation, boolean dogsFood){
		super(healAmount, saturation, dogsFood);
		setUnlocalizedName(unloc);
		setTextureName(Reference.TEXTURE_PREFIX + unloc);
		setCreativeTab(CombustibleLemonLauncher.TAB_COMBUSTIBLE_LEMON_LAUNCHER);
		GameRegistry.registerItem(this, unloc);
	}

}
