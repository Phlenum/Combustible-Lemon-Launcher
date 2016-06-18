package mods.phlenum.cll.client.render;

import mods.phlenum.cll.entity.EntityLemon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 05 Jan 2016
 */

@SideOnly(Side.CLIENT)
public class RenderEntityLemon extends Render<EntityLemon> {

	public RenderEntityLemon(RenderManager renderManager){
		super(renderManager);
	}
	
	@Override
	public void doRender(EntityLemon entity, double x, double y, double z, float entityYaw, float partialTicks){
		GlStateManager.pushMatrix();
		//GlStateManager.disableLighting();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(renderManager.playerViewY - 180, 0.0F, -1.0F, 0.0F);
		GlStateManager.rotate(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		bindEntityTexture(entity);
		
		Tessellator tess = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tess.getBuffer();
		
		GlStateManager.glNormal3f(0.0F, 0.0F, 1.0F);
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos(-1.0D, -1.0D, 0.0D).tex(0.0D, 1.0D).endVertex();
		vertexbuffer.pos( 1.0D, -1.0D, 0.0D).tex(1.0D, 1.0D).endVertex();
		vertexbuffer.pos( 1.0D,  1.0D, 0.0D).tex(1.0D, 0.0D).endVertex();
		vertexbuffer.pos(-1.0D,  1.0D, 0.0D).tex(0.0D, 0.0D).endVertex();
		tess.draw();
		
		//GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLemon entity){
		return entity.getLemonType().getTexture();
	}
	


}
