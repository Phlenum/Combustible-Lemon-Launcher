package mods.phlenum.cll.client.gui;

import static mods.phlenum.cll.lib.Reference.MOD_ID;
import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

import mods.phlenum.cll.proxy.CommonProxy.CLLConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 06 Jan 2016
 */

@SideOnly(Side.CLIENT)
public class CLLConfigGUI extends GuiConfig {
	
	public CLLConfigGUI(GuiScreen parent){
		// https://github.com/Minalien/BlogArchive/blob/master/ForgeTutorials/Spotlight__Config_GUIs.md
		super(parent, new ConfigElement(CLLConfig.configFile.getCategory(CATEGORY_GENERAL)).getChildElements(), MOD_ID, false, true, GuiConfig.getAbridgedConfigPath(CLLConfig.configFile.toString()));
	}

}
