package ibuilder99.mods.cll.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class MiscHelper {
	
	public static void createCompoundIfNeeded(ItemStack stack){
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
	}
	
	public static int getInteger(ItemStack stack, String key){
		createCompoundIfNeeded(stack);
		return stack.stackTagCompound.hasKey(key) ? stack.stackTagCompound.getInteger(key) : 0;
	}
	
	public static void setInteger(ItemStack stack, String key, int value){
		createCompoundIfNeeded(stack);
		stack.stackTagCompound.setInteger(key, value);
	}
	
	public static String translateToLocal(String key, String def){
		return StatCollector.func_94522_b(key) ? StatCollector.translateToLocal(key) : def;
	}
}
