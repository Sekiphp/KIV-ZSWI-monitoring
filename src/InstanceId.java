import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InstanceId {
	
	/**
	 * Hlavni logger
	 */
	private static Logger instanceIdLogger = LogManager.getLogger();

	private String instance_id;

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}
	
}
