package instances;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;


public class SessionsInfo {
	
	/** logger sluzby SessionsInfo */
	private static Logger sessionsInfoLogger = LogManager.getLogger();

	@JsonProperty("session_code") private String session_code;
	@JsonProperty("session_name") private String session_name;
	@JsonProperty("session_start") private String session_start;
	@JsonProperty("last_request") private String last_request;
	@JsonProperty("user_name") private String user_name;
	
	public String getSessions_info()
	{
		sessionsInfoLogger.info("Getting sessions_info.");
		return ("\nsession_code: " + session_code +
				"\nsession_name: " + session_name + 
				"\nsession_start: " + session_start +
				"\nlast_request: " + last_request + 
				"\nuser_name: " + user_name);
	}

	public String getSession_code() {
		sessionsInfoLogger.debug("Getting session_code: " + session_code);
		return session_code;
	}
	
	public void setSession_code(String session_code) {
		sessionsInfoLogger.debug("Setting session_code: " + session_code);
		this.session_code = session_code;
	}
	
	public String getSession_name() {
		sessionsInfoLogger.debug("Getting session_name: " + session_name);
		return session_name;
	}
	
	public void setSession_name(String session_name) {
		sessionsInfoLogger.debug("Setting session_name: " + session_name);
		this.session_name = session_name;
	}
	
	public String getSession_start() {
		sessionsInfoLogger.debug("Getting session_start: " + session_start);
		return session_start;
	}
	
	public void setSession_start(String session_start) {
		sessionsInfoLogger.debug("Setting session_start: " + session_start);
		this.session_start = session_start;
	}
	
	public String getLast_request() {
		sessionsInfoLogger.debug("Getting last_request: " + last_request);
		return last_request;
	}
	
	public void setLast_request(String last_request) {
		sessionsInfoLogger.debug("Setting last_request: " + last_request);
		this.last_request = last_request;
	}
	
	public String getUser_name() {
		sessionsInfoLogger.debug("Getting user_name: " + user_name);
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		sessionsInfoLogger.debug("Setting user_name: " + user_name);
		this.user_name = user_name;
	}
	
}
