package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InstanceId {

    /**
     * logger sluzby InstanceId
     */
    private static final Logger instanceIdLogger = LogManager.getLogger();

    private String instance_id;

    public String getInstance_id() {
        instanceIdLogger.info("Getting instance_id: " + instance_id);
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        instanceIdLogger.info("Setting instance_id: " + instance_id);
        this.instance_id = instance_id;
    }

}
