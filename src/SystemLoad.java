import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SystemLoad {
	
	/**
	 * Hlavni logger
	 */
	private static Logger systemLoadLogger = LogManager.getLogger();
    
	private double system_load;
	
	public double getSystem_load() {
		return system_load;
	}

	public void setSystem_load(double systemLoad) {
		this.system_load = systemLoad;
	}
}