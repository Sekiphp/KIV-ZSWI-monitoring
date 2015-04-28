package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InstanceId {
	
	/**
	 * Represents InstanceID service of PF.
	 */
	
    /**
     * Logger logging InstanceId service.
     */
    private static final Logger instanceIdLogger = LogManager.getLogger();

    /**
     * Response from server.
     */
    private String instance_id;

    /**
     * Getter of server response.
     * @return	server response
     */
    public String getInstance_id() {
        instanceIdLogger.info("Getting instance_id: " + instance_id);
        return instance_id;
    }

    /**
     * Setter server response
     * @param instance_id	server response
     */
    public void setInstance_id(String instance_id) {
        instanceIdLogger.info("Setting instance_id: " + instance_id);
        this.instance_id = instance_id;
    }
}
