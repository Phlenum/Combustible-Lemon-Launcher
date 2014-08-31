package mods.phlenum.cll.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.phlenum.cll.world.EntityLemon;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import static org.lwjgl.opengl.GL11.*;

/**
 * Combustible Lemon Launcher
 * Minecraft Forge Modification
 */

@SideOnly(Side.CLIENT)
public class RenderEntityLemon extends Render {

	@Override
	public void doRender(Entity ent, double x, double y, double z, float var8, float var9){
		glPushMatrix();
		glTranslated(x, y, z);
		glRotatef(180.0F -renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
	    glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
	    glScalef(0.5F, 0.5F, 0.5F);
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		bindEntityTexture(ent);
		
		Tessellator localTess = Tessellator.instance;
		localTess.startDrawingQuads();
		localTess.setNormal(0.0F, 0.0F, 1.0F);
		localTess.addVertexWithUV(-1.0D, -1.0D, 0.0D, 	0.0D, 1.0D);
		localTess.addVertexWithUV( 1.0D, -1.0D, 0.0D, 	1.0D, 1.0D);
		localTess.addVertexWithUV( 1.0D,  1.0D, 0.0D, 	1.0D, 0.0D);
		localTess.addVertexWithUV(-1.0D,  1.0D, 0.0D, 	0.0D, 0.0D);
		localTess.draw();
		
		glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity ent){
		EntityLemon lemon = (EntityLemon)ent;
		return lemon.getLemonType().getTexture();
	}

}
