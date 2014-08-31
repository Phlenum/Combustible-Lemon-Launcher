package mods.phlenum.cll.items;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.phlenum.cll.CombustibleLemonLauncher;
import mods.phlenum.cll.lib.Reference;
import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class ItemLemonExplosive extends ItemFood {

	public ItemLemonExplosive(int healAmount, String unloc, float saturation, boolean dogsFood){
		super(healAmount, saturation, dogsFood);
		setUnlocalizedName(unloc);
		setTextureName(Reference.TEXTURE_PREFIX + unloc);
		setCreativeTab(CombustibleLemonLauncher.TAB_COMBUSTIBLE_LEMON_LAUNCHER);
		GameRegistry.registerItem(this, unloc);
	}
	
	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		par2World.createExplosion(par3EntityPlayer, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, 4.0F, true);
		par3EntityPlayer.attackEntityFrom(CommonProxy.DAMAGE_SOURCE_EXPLOSIVE_LEMON, 9999.9F);
		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}

}
