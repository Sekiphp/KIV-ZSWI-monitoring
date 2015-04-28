package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SystemLoad {

	/**
	 * Represents SystemLoad service of PF.
	 */
	
	/**
     * Logger logging SystemLoad service.
     */
    private static final Logger systemLoadLogger = LogManager.getLogger();

    /**
     * Server response.
     */
    private double system_load;

    /**
     * Getter of server response.
     * @return	server response
     */
    public double getSystem_load() {
        systemLoadLogger.info("Getting system_load: " + system_load);
        return system_load;
    }

    /**
     * Setter of server response.
     * @param systemLoad	server response
     */
    public void setSystem_load(double systemLoad) {
        systemLoadLogger.info("Setting system_load: " + system_load);
        this.system_load = systemLoad;
    }
}
