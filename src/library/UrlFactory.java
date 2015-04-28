package library;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UrlFactory {

	/**
	 * Provides URLs of server services.
	 */
	
    /**
     * logger of URLFactory server service
     */
    private static final Logger urlFactoryLogger = LogManager.getLogger();
    
   /**
    * Collection of URLs of server services.
    */
    private Map<String, String> urls;

    /**
     * Setter of URLs of server services.
     * @param urls	urls of server services
     */
    public void setUrls(Map<String, String> urls) {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Setting URLs.");
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
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting system_load service instance.");
        return urls.get(TypeMonitoring.SYSTEM_LOAD.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getInstanceId() {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting instance_id service instance.");
        return urls.get(TypeMonitoring.INSTANCE_ID.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getSessionsCount() {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting sessions_count service instance.");
        return urls.get(TypeMonitoring.SESSIONS_COUNT.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getSessionsInfo() {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting sessions_info service instance.");
        return urls.get(TypeMonitoring.SESSIONS_INFO.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getMemoryInfo() {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting memory_info service instance.");
        return urls.get(TypeMonitoring.MEMORY_INFO.getName());
    }
}
