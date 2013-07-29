package ibuilder99.mods.cll.items;

import ibuilder99.mods.cll.util.ConfigLoader;
import ibuilder99.mods.cll.util.Reference;
import net.minecraft.item.Item;

public class LemonLauncherItems {
	
	public static Item Lemon;
	public static Item LemonExplosive;
	public static Item LemonLauncher;
	
	public static void initItems(){
		Lemon = new Lemon(ConfigLoader.LemonID, Reference.ITEM_LEMON, "Lemon");
		LemonExplosive = new LemonExplosive(ConfigLoader.LemonExplosiveID, Reference.ITEM_LEMON_EXPLOSIVE, "Explosive Lemon");
		LemonLauncher = new LemonLauncher(ConfigLoader.LemonLauncherID, Reference.ITEM_LEMON_LAUNCHER, "Combustible Lemon Launcher");
	}
}
