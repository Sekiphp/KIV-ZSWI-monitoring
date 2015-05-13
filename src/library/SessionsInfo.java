package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Represents SessionsInfo service of PF.
 */
public class SessionsInfo implements IPFInstance {	
    /**
     * Logger logging SessionsInfo service.
     */
    private final Logger sessionsInfoLogger = LogManager.getLogger("sessionsInfo");

    /**
     * Server response.
     */
    @JsonProperty("session_code")
    private String session_code;
    
    /**
     * Server response.
     */
    @JsonProperty("session_name")
    private String session_name;
    
    /**
     * Server response.
     */
    @JsonProperty("session_start")
    private String session_start;
    
    /**
     * Server response.
     */
    @JsonProperty("last_request")
    private String last_request;
    
    /**
     * Server response.
     */
    @JsonProperty("user_name")
    private String user_name;

    
    /**
     * Getter of server response.
     */
    /*public String getSessions_info() {
    	String s = "\nsession_code: " + session_code
                + "\nsession_name: " + session_name
                + "\nsession_start: " + session_start
                + "\nlast_request: " + last_request
                + "\nuser_name: " + user_name;
    	if (sessionsInfoLogger.isInfoEnabled()) sessionsInfoLogger.info("Setting " + TypeMonitoring.SESSIONS_INFO.getName() + ":" + s);
        return s;
    }*/

    /**
     * Getter of server response.
     */
    public String getSession_code() {
        return session_code;
    }

    /**
     * Setter of server response.
     */
    public void setSession_code(String session_code) {
        this.session_code = session_code;
    }

    /**
     * Getter of server response.
     */
    public String getSession_name() {
        return session_name;
    }

    /**
     * Setter of server response.
     */
    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    /**
     * Getter of server response.
     */
    public String getSession_start() {
        return session_start;
    }

    /**
     * Setter of server response.
     */
    public void setSession_start(String session_start) {
        this.session_start = session_start;
    }

    /**
     * Getter of server response.
     */
    public String getLast_request() {
        return last_request;
    }

    /**
     * Setter of server response.
     */
    public void setLast_request(String last_request) {
        this.last_request = last_request;
    }

    /**
     * Getter of server response.
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Setter of server response.
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

	@Override
	public String getInstanceStatus() {
		String s = "\nsession_code: " + session_code
                + "\nsession_name: " + session_name
                + "\nsession_start: " + session_start
                + "\nlast_request: " + last_request
                + "\nuser_name: " + user_name;
    	if (sessionsInfoLogger.isInfoEnabled()) sessionsInfoLogger.info("Setting " + TypeMonitoring.SESSIONS_INFO.getName() + ":" + s);
        return s;
	}
}