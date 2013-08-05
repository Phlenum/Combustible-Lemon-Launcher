package ibuilder99.mods.cll.client.render.model;

import ibuilder99.mods.cll.util.Reference;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelGel {
	
	private IModelCustom model;
	
	public ModelGel(){
		model = AdvancedModelLoader.loadModel(Reference.MODEL_GEL);
	}
	
	public void renderAll(){
		model.renderAll();
	}
}
