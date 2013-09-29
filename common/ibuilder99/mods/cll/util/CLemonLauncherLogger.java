package ibuilder99.mods.cll.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class CLemonLauncherLogger {
	
	private static Logger combustibleLemonLauncherLogger;
	
	public static void initialize(){
		combustibleLemonLauncherLogger = Logger.getLogger(Reference.MOD_ID);
		combustibleLemonLauncherLogger.setParent(FMLLog.getLogger());
	}
	
	public static void logInfo(String msg){
		combustibleLemonLauncherLogger.log(Level.INFO, msg);
	}
	
	@SuppressWarnings("rawtypes")
	public static void logException(Exception e, Class c, String method){
		combustibleLemonLauncherLogger.throwing(c.getName(), method, e);
	}
}
