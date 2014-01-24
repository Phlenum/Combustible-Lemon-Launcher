package mods.ibuilder99.cll.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import static org.lwjgl.opengl.GL11.*;
// Vertex Buffer Objects
import static org.lwjgl.opengl.GL15.*;

/**
 * Combustible Lemon Launcher
 * Copyright (c) 2014 Phil Julian
 * @author Phil Julian (aka iBuilder99)
 */

public class RenderItemCombustibleLemonLauncher implements IItemRenderer {

	/**
	 * One Model - one (static) instance of a model loader
	 * <p>Featuring OpenGL 1.5 Vertex Buffer Objects!</p>
	 */
	
	public static class Model_CombustibleLemonLauncher {
		
		private static int vertexBuffer;
		
		public static void loadModel(){
			// TODO Parse .obj File and send its data to OpenGL
			vertexBuffer = glGenBuffers();
		}
		
		public static void renderModel(){
			glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
			// TODO Draw Arrays
			glBindBuffer(GL_ARRAY_BUFFER, 0);
		}
		
		public static void deleteBuffer(){
			glDeleteBuffers(vertexBuffer);
			// TODO Delete buffered Data from (V-)RAM
		}
		
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
			glTranslatef(0.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
			glScalef(1.0F, 1.0F, 1.0F);
			Model_CombustibleLemonLauncher.renderModel();
			break;
		case EQUIPPED:
			glTranslatef(0.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
			glScalef(1.0F, 1.0F, 1.0F);
			Model_CombustibleLemonLauncher.renderModel();
			break;
		case EQUIPPED_FIRST_PERSON:
			glTranslatef(0.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
			glScalef(1.0F, 1.0F, 1.0F);
			Model_CombustibleLemonLauncher.renderModel();
			break;
		case FIRST_PERSON_MAP:
			glTranslatef(0.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
			glScalef(1.0F, 1.0F, 1.0F);
			Model_CombustibleLemonLauncher.renderModel();
			break;
		case INVENTORY:
			glTranslatef(0.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
			glScalef(1.0F, 1.0F, 1.0F);
			Model_CombustibleLemonLauncher.renderModel();
			break;
		default:
			break;
		}
		glPopMatrix();
	}
	
}
