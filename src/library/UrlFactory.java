package library;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides URLs of server services.
 */
public class UrlFactory {
    /**
     * logger of URLFactory server service
     */
    private static final Logger applicationLogger = LogManager.getLogger();
    
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
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Getting system_load service instance.");
        return urls.get(TypeMonitoring.SYSTEM_LOAD.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getInstanceId() {
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Getting instance_id service instance.");
        return urls.get(TypeMonitoring.INSTANCE_ID.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getSessionsCount() {
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Getting sessions_count service instance.");
        return urls.get(TypeMonitoring.SESSIONS_COUNT.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getSessionsInfo() {
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Getting sessions_info service instance.");
        return urls.get(TypeMonitoring.SESSIONS_INFO.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getMemoryInfo() {
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Getting memory_info service instance.");
        return urls.get(TypeMonitoring.MEMORY_INFO.getName());
    }
}