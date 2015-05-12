package library;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides URLs of server services.
 */
public class UrlFactory {
	/**
     * Application logger
     */
    private final Logger applicationLogger = LogManager.getLogger("application");
    
    /**
     * SystemLoad logger
     */
    private final Logger systemLoadLogger = LogManager.getLogger("systemLoad");
    
    /**
     * InstanceID logger
     */
    private final Logger instanceIdLogger = LogManager.getLogger("instanceID");
    
    /**
     * SessionsCount logger
     */
    private final Logger sessionsCountLogger = LogManager.getLogger("sessionsCount");
    
    /**
     * MemoryInfo logger
     */
    private final Logger memoryInfoLogger = LogManager.getLogger("memoryInfo");
    
    /**
     * SessionsInfo logger
     */
    private final Logger sessionsInfoLogger = LogManager.getLogger("sessionsInfo");
    
   /**
    * Collection of URLs of server services.
    */
    private Map<String, String> urls;

    /**
     * Setter of URLs of server services.
     * @param urls	urls of server services
     */
    public void setUrls(Map<String, String> urls) {
    	if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Setting URLs.");
        this.urls = urls;
    }
    
    /**
     * Getter of array of keys of collection of URLs.
     * @return
     */
    public Object[] toArray() {
        return (Object[]) urls.keySet().toArray();
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getSystemLoad() {
        if (systemLoadLogger.isDebugEnabled()) systemLoadLogger.debug("Getting " + TypeMonitoring.SYSTEM_LOAD.getName() + " service instance.");
        return urls.get(TypeMonitoring.SYSTEM_LOAD.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getInstanceId() {
        if (instanceIdLogger.isDebugEnabled()) instanceIdLogger.debug("Getting " + TypeMonitoring.INSTANCE_ID.getName() + " service instance.");
        return urls.get(TypeMonitoring.INSTANCE_ID.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getSessionsCount() {
        if (sessionsCountLogger.isDebugEnabled()) sessionsCountLogger.debug("Getting " + TypeMonitoring.SESSIONS_COUNT.getName() + " service instance.");
        return urls.get(TypeMonitoring.SESSIONS_COUNT.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getSessionsInfo() {
        if (sessionsInfoLogger.isDebugEnabled()) sessionsInfoLogger.debug("Getting " + TypeMonitoring.SESSIONS_INFO.getName() + " service instance.");
        return urls.get(TypeMonitoring.SESSIONS_INFO.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getMemoryInfo() {
        if (memoryInfoLogger.isDebugEnabled()) memoryInfoLogger.debug("Getting " + TypeMonitoring.MEMORY_INFO.getName() + " service instance.");
        return urls.get(TypeMonitoring.MEMORY_INFO.getName());
    }
}