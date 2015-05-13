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
        return urls.get(TypeMonitoring.SYSTEM_LOAD.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getInstanceId() {
        return urls.get(TypeMonitoring.INSTANCE_ID.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getSessionsCount() {
        return urls.get(TypeMonitoring.SESSIONS_COUNT.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getSessionsInfo() {
        return urls.get(TypeMonitoring.SESSIONS_INFO.getName());
    }

    /**
     * Getter of name of server service.
     * @return	name of server service
     */
    public String getMemoryInfo() {
        return urls.get(TypeMonitoring.MEMORY_INFO.getName());
    }
    
    public String getUrlByType(TypeMonitoring type){
    	return this.urls.get(type.getName());
    }
}