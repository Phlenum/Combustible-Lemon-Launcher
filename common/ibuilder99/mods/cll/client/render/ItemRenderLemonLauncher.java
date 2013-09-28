package ibuilder99.mods.cll.client.render;

import cpw.mods.fml.client.FMLClientHandler;
import ibuilder99.mods.cll.util.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import static org.lwjgl.opengl.GL11.*;

public class ItemRenderLemonLauncher implements IItemRenderer {
	
	private ModelLemonLauncher LemonLauncher;
	private final ResourceLocation LemonLauncherTexture = new ResourceLocation(Reference.TEXTURE_LEMON_LAUNCHER);
	
	public ItemRenderLemonLauncher(){
		LemonLauncher = new ModelLemonLauncher();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type){
		case ENTITY:
			renderLemonLauncher(0.0, 0.1, 0.0, 0.2F);
			break;
		case EQUIPPED:
			glRotatef(140.0F, 0.0F, 1.0F, 0.0F);
			glRotatef(-70.0F, 0.0F, 0.02F, 0.9F);
			renderLemonLauncher(-0.5, -0.9, 0.0, 0.5F);
			break;
		case EQUIPPED_FIRST_PERSON:
			glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
			renderLemonLauncher(-0.5, 0.8, 0.8, 0.4F);
			break;
		case INVENTORY:
			renderLemonLauncher(-0.5, -0.5, -0.5, 0.35F);
			break;
		default:
			break;
		}
	}
	
	private void renderLemonLauncher(double x, double y, double z, float scale){
		glPushMatrix();
		glTranslated(x, y, z);
		glScalef(scale, scale, scale);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(LemonLauncherTexture);
		LemonLauncher.renderAll();
		glPopMatrix();

	}
	
	private class ModelLemonLauncher {
		
		private IModelCustom model;
		
		public ModelLemonLauncher(){
			model = AdvancedModelLoader.loadModel(Reference.MODEL_LEMON_LAUNCHER);
		}
		
		public void renderAll(){
			model.renderAll();
		}
	}

}
