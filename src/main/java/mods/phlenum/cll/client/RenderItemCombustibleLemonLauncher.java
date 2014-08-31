package mods.phlenum.cll.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.phlenum.cll.lib.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.obj.WavefrontObject;
import static org.lwjgl.opengl.GL11.*;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

@SideOnly(Side.CLIENT)
public class RenderItemCombustibleLemonLauncher implements IItemRenderer {

	private static WavefrontObject model_CombustibleLemonLauncher;
	private static final ResourceLocation texture_CombustibleLemonLauncher = new ResourceLocation(Reference.TEXTURE_COMBUSTIBLE_LEMON_LAUNCHER);
	
	public static void initialize(){
		model_CombustibleLemonLauncher = new WavefrontObject(new ResourceLocation(Reference.MODEL_COMBUSTIBLE_LEMON_LAUNCHER));
	}
	
	private static void bindTexture(){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture_CombustibleLemonLauncher);
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type){
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper){
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data){
		glPushMatrix();
		switch(type){
		case ENTITY:
			glTranslatef(0.0F, 0.2F, 0.0F);
			glScalef(0.32F, 0.32F, 0.32F);
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			bindTexture();
			model_CombustibleLemonLauncher.renderAll();
			break;
		case EQUIPPED:
			glTranslatef(0.7F, 0.5F, 0.7F);
			glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
			glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
			glScalef(0.5F, 0.5F, 0.5F);
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			bindTexture();
			model_CombustibleLemonLauncher.renderAll();
			break;
		case EQUIPPED_FIRST_PERSON:
			glTranslatef(0.5F, 0.5F, 0.5F);
			glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
			glScalef(0.5F, 0.5F, 0.5F);
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			bindTexture();
			model_CombustibleLemonLauncher.renderAll();
			break;
		case FIRST_PERSON_MAP:
			glTranslatef(0.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
			glScalef(1.0F, 1.0F, 1.0F);
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			bindTexture();
			model_CombustibleLemonLauncher.renderAll();
			break;
		case INVENTORY:
			glScalef(0.32F, 0.32F, 0.32F);
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			bindTexture();
			model_CombustibleLemonLauncher.renderAll();
			break;
		default:
			break;
		}
		glPopMatrix();
	}
	
}
