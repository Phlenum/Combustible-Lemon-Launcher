package mods.ibuilder99.cll.lib;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class CLLLogger {
	
	private static Logger modLogger;
	
	public static void initializeLogging(){
		modLogger = LogManager.getLogger(Reference.MOD_ID);
	}
	
	public static void logInfo(String msg){
		modLogger.log(Level.INFO, msg);
	}
	
	public static void logWarning(String msg){
		modLogger.log(Level.WARN, msg);
	}
	
	public static void logError(String msg){
		modLogger.log(Level.ERROR, msg);
	}
	
	public static void debug(String msg){
		modLogger.log(Level.DEBUG, msg);
	}
}
