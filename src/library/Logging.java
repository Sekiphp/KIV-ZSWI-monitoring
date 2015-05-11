package library;

import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Petr Kozler
 */
public class Logging {
	
	/**
	 * Logs the given string with given logger if DEBUG level is enabled.
	 * 
	 * @param logger used logger
	 * @param s logged string
	 */
	public static void logDebugIfEnabled(Logger logger, String s) {
		if (logger.isDebugEnabled()) {
			logger.debug(s);
        }
	}
}