import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

public class SessionsInfo {
	
	/**
	 * Hlavni logger
	 */
	private static Logger sessionsInfoLogger = LogManager.getLogger();

	@JsonProperty("session_code") private String session_code;
	@JsonProperty("session_name") private String session_name;
	@JsonProperty("session_start") private String session_start;
	@JsonProperty("last_request") private String last_request;
	@JsonProperty("user_name") private String user_name;
	
	private String sessions_info;

	public String getSessions_info() {
		sessions_info = 
				"\nsession_code: " + session_code +
				"\nsession_name: " + session_name + 
				"\nsession_start: " + session_start +
				"\nlast_request: " + last_request +
				"\nuser_name: " + user_name + "\n";
		
		sessionsInfoLogger.info("Getting sessions_info: " + sessions_info);
		return sessions_info;
	}

	public void setSessions_info(String sessions_info) {
		sessionsInfoLogger.info("Setting sessions_info: " + sessions_info);
		this.sessions_info = sessions_info;
	}
}
