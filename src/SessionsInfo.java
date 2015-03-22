import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

public class SessionsInfo {
	
	/**
	 * Hlavni logger
	 */
	private static Logger sessionsInfoLogger = LogManager.getLogger();
	
	private List<Sessions> sessions_info;

	public List<Sessions> getSessions_info() {
		sessionsInfoLogger.info("Getting sessions_info: " + sessions_info);
		return sessions_info;
	}

	public void setSessions_info(List<Sessions> sessions_info) {
		sessionsInfoLogger.info("Setting sessions_info: " + sessions_info);
		this.sessions_info = sessions_info;
	}
}
