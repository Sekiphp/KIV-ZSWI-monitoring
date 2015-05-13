package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents InstanceID service of PF.
 */
public class InstanceId implements IPFInstance {
    /**
     * Logger logging InstanceId service.
     */
    private final Logger instanceIdLogger = LogManager.getLogger("instanceID");

    /**
     * Response from server.
     */
    private String instance_id;

    /**
     * Getter of server response.
     * @return	server response
     */
    /*public String getInstance_id() {
        return instance_id;
    }*/

    /**
     * Setter server response
     * @param instance_id	server response
     */
    public void setInstance_id(String instance_id) {
    	if (instanceIdLogger.isInfoEnabled()) instanceIdLogger.info("Setting " + TypeMonitoring.INSTANCE_ID.getName() + ": " + instance_id);
        this.instance_id = instance_id;
    }

	@Override
	public String getInstanceStatus() {
		return instance_id;
	}
}