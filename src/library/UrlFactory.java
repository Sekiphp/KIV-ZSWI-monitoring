package library;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UrlFactory {

    /**
     * logger URLFactory
     */
    private static final Logger urlFactoryLogger = LogManager.getLogger();

    private final String SYSTEM_LOAD = "system_load";
    private final String INSTANCE_ID = "instance_id";
    private final String SESSIONS_COUNT = "sessions_count";
    private final String SESSIONS_INFO = "sessions_info";
    private final String MEMORY_INFO = "memory_info";

    private Map<String, String> urls;

    public void setUrls(Map<String, String> urls) {
        if (urlFactoryLogger.isDebugEnabled()) {
            urlFactoryLogger.debug("Setting URLs.");
        }
        this.urls = urls;
    }

    public Object[] toArray() {
        return (Object[]) urls.keySet().toArray();
    }

    public String getSystemLoad() {
        if (urlFactoryLogger.isDebugEnabled()) {
            urlFactoryLogger.debug("Getting system_load service instance.");
        }
        return urls.get(SYSTEM_LOAD);
    }

    public String getInstanceId() {
        if (urlFactoryLogger.isDebugEnabled()) {
            urlFactoryLogger.debug("Getting instance_id service instance.");
        }
        return urls.get(INSTANCE_ID);
    }

    public String getSessionsCount() {
        if (urlFactoryLogger.isDebugEnabled()) {
            urlFactoryLogger.debug("Getting sessions_count service instance.");
        }
        return urls.get(SESSIONS_COUNT);
    }

    public String getSessionsInfo() {
        if (urlFactoryLogger.isDebugEnabled()) {
            urlFactoryLogger.debug("Getting sessions_info service instance.");
        }
        return urls.get(SESSIONS_INFO);
    }

    public String getMemoryInfo() {
        if (urlFactoryLogger.isDebugEnabled()) {
            urlFactoryLogger.debug("Getting memory_info service instance.");
        }
        return urls.get(MEMORY_INFO);
    }

}
