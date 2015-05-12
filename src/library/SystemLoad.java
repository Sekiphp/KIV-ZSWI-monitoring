package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents SystemLoad service of PF.
 */
public class SystemLoad {
	/**
     * Logger logging SystemLoad service.
     */
    private final Logger systemLoadLogger = LogManager.getLogger("systemLoad");

    /**
     * Server response.
     */
    private double system_load;

    /**
     * Getter of server response.
     * @return	server response
     */
    public double getSystem_load() {
        return system_load;
    }

    /**
     * Setter of server response.
     * @param systemLoad	server response
     */
    public void setSystem_load(double systemLoad) {
    	if (systemLoadLogger.isInfoEnabled()) systemLoadLogger.info("Setting " + TypeMonitoring.SYSTEM_LOAD.getName() + ": " + system_load);
        this.system_load = systemLoad;
    }
}