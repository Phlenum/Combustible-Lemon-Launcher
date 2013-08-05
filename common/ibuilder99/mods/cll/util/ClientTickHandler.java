package ibuilder99.mods.cll.util;

import ibuilder99.mods.cll.blocks.LemonLeaves;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(type.contains(TickType.RENDER)){
			Minecraft client = FMLClientHandler.instance().getClient();
			LemonLeaves.setGraphicsSetting(client.gameSettings.fancyGraphics);
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return Reference.MOD_NAME;
	}

}
