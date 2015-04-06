package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SystemLoad {

    /**
     * logger sluzby SystemLoad
     */
    private static final Logger systemLoadLogger = LogManager.getLogger();

    private double system_load;

    public double getSystem_load() {
        systemLoadLogger.info("Getting system_load: " + system_load);
        return system_load;
    }

    public void setSystem_load(double systemLoad) {
        systemLoadLogger.info("Setting system_load: " + system_load);
        this.system_load = systemLoad;
    }
}
