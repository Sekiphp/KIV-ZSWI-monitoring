package library;

import org.apache.logging.log4j.Logger;

public class Logging {
	
	/**
	 * 
	 */
	
	/*
	 * if\s*\((.+Logger)\.isDebugEnabled\(\)\)\s*\{\r\n\s*.+Logger\.debug\((.*)\);\r\n\s*\}
	 * Logging\.logDebugIfEnabled\($1, $2\);
	 */
	
	/**
	 * 
	 * @param logger
	 * @param s
	 */
	public static void logDebugIfEnabled(Logger logger, String s) {
		if (logger.isDebugEnabled()) {
			logger.debug(s);
        }
	}
}
