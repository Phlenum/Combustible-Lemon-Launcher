package mods.phlenum.cll.client.render;

import static org.lwjgl.opengl.GL11.*;
import mods.phlenum.cll.entity.EntityLemon;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
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
		
		glPushMatrix();
		glTranslated(x, y, z);
		glRotatef(-renderManager.playerViewY - 180.0F, 0.0F, 1.0F, 0.0F);
		glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);		
		glScalef(0.5F, 0.5F, 0.5F);
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		bindEntityTexture(entity);
		
		Tessellator tess = Tessellator.getInstance();
		WorldRenderer worldrenderer = tess.getWorldRenderer();
		
		worldrenderer.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
		worldrenderer.pos(-1.0D, -1.0D, 0.0D).tex(0.0D, 1.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
		worldrenderer.pos( 1.0D, -1.0D, 0.0D).tex(1.0D, 1.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
		worldrenderer.pos( 1.0D,  1.0D, 0.0D).tex(1.0D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
		worldrenderer.pos(-1.0D,  1.0D, 0.0D).tex(0.0D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
		
		tess.draw();
		
		glPopMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLemon entity){
		return entity.getLemonType().getTexture();
	}
	


}
