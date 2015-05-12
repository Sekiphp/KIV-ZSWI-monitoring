package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents SessionsCount service of PF.
 */
public class SessionsCount {
    /**
     * Logger logging SessionsCount service.
     */
    private final Logger sessionsCountLogger = LogManager.getLogger("sessionsCount");

    /**
     * Server response.
     */
    private String sessions_count;

    /**
     * Getter of server response.
     * @return	server response
     */
    public String getSessions_count() {
    	if (sessionsCountLogger.isInfoEnabled()) sessionsCountLogger.info("Getting sessions_count: " + sessions_count);
        return sessions_count;
    }
    
    /**
     * Setter of server response.
     * @param sessions_count	server response
     */
    public void setSessions_count(String sessions_count) {
    	if (sessionsCountLogger.isInfoEnabled()) sessionsCountLogger.info("Setting sessions_count: " + sessions_count);
        this.sessions_count = sessions_count;
    }
}