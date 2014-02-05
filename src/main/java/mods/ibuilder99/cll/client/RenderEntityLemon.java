package mods.ibuilder99.cll.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.ibuilder99.cll.proxy.CommonProxy;
import mods.ibuilder99.cll.world.EntityLemon;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

@SideOnly(Side.CLIENT)
public class RenderEntityLemon extends RenderSnowball {
	
	public RenderEntityLemon(){
		super(CommonProxy.itemLemon);
	}
	
	@Override
	protected void bindEntityTexture(Entity ent){
		EntityLemon lemon = (EntityLemon)ent;
		renderManager.renderEngine.bindTexture(lemon.getLemonType().getTexture());
	}

}
