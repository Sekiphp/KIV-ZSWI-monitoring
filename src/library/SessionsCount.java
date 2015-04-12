package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionsCount {

    /**
     * logger sluzby SessionsCount
     */
    private static final Logger sessionsCountLogger = LogManager.getLogger();
    
    public static int refreshTimePeriod = Monitoring.DEFAULT_REFRESH_TIME;
    

    private String sessions_count;

    public String getSessions_count() {
        sessionsCountLogger.info("Getting sessions_count: " + sessions_count);
        return sessions_count;
    }

    public void setSessions_count(String sessions_count) {
        sessionsCountLogger.info("Setting sessions_count: " + sessions_count);
        this.sessions_count = sessions_count;
    }
}
