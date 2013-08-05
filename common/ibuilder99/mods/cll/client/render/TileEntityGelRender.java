package ibuilder99.mods.cll.client.render;

import cpw.mods.fml.client.FMLClientHandler;
import ibuilder99.mods.cll.blocks.tileentity.TileEntityGel;
import ibuilder99.mods.cll.client.render.model.ModelGel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import static org.lwjgl.opengl.GL11.*;

public class TileEntityGelRender extends TileEntitySpecialRenderer {
	
	private ModelGel model;
	
	public TileEntityGelRender(){
		model = new ModelGel();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityGel gel = (TileEntityGel)tileentity;
		glPushMatrix();
		glTranslated(x + 0.5, y + 0.05, z + 0.5);
		glScalef(0.5F, 0.5F, 0.5F);
		FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation(gel.getGelTexture()));
		model.renderAll();
		glPopMatrix();
	}

}
