package library;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UrlFactory {

    /**
     * logger URLFactory
     */
    private static final Logger urlFactoryLogger = LogManager.getLogger();
    private Map<String, String> urls;

    public void setUrls(Map<String, String> urls) {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Setting URLs.");
        this.urls = urls;
    }

    public Object[] toArray() {
        return (Object[]) urls.keySet().toArray();
    }

    public String getSystemLoad() {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting system_load service instance.");
        return urls.get(TypeMonitoring.SYSTEM_LOAD.getValue());
    }

    public String getInstanceId() {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting instance_id service instance.");
        return urls.get(TypeMonitoring.INSTANCE_ID.getValue());
    }

    public String getSessionsCount() {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting sessions_count service instance.");
        return urls.get(TypeMonitoring.SESSIONS_COUNT.getValue());
    }

    public String getSessionsInfo() {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting sessions_info service instance.");
        return urls.get(TypeMonitoring.SESSIONS_INFO.getValue());
    }

    public String getMemoryInfo() {
        Logging.logDebugIfEnabled(urlFactoryLogger, "Getting memory_info service instance.");
        return urls.get(TypeMonitoring.MEMORY_INFO.getValue());
    }
}
