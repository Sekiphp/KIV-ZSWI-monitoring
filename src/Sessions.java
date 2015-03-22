import org.codehaus.jackson.annotate.JsonProperty;


public class Sessions {

	@JsonProperty("session_code") private String session_code;
	@JsonProperty("session_name") private String session_name;
	@JsonProperty("session_start") private String session_start;
	@JsonProperty("last_request") private String last_request;
	@JsonProperty("user_name") private String user_name;
	
	public String getSessions_info()
	{
		return ("\nsession_code: " + session_code +
				"\nsession_name: " + session_name + 
				"\nsession_start: " + session_start +
				"\nlast_request: " + last_request + 
				"\nuser_name: " + user_name);
	}
	
	public String getSession_code() {
		return session_code;
	}
	public void setSession_code(String session_code) {
		this.session_code = session_code;
	}
	public String getSession_name() {
		return session_name;
	}
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	public String getSession_start() {
		return session_start;
	}
	public void setSession_start(String session_start) {
		this.session_start = session_start;
	}
	public String getLast_request() {
		return last_request;
	}
	public void setLast_request(String last_request) {
		this.last_request = last_request;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
