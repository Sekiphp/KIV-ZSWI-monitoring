import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionsCount {
	
	/**
	 * Hlavni logger
	 */
	private static Logger sessionsCountLogger = LogManager.getLogger();

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
