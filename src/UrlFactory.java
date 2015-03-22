import java.util.Map;

public class UrlFactory {
	
	/**
	 * Hlavni logger
	 */
	//private static Logger urlFactoryLogger = LogManager.getLogger();

	private final String SYSTEM_LOAD = "system_load";
	private final String INSTANCE_ID = "instance_id";
	private final String SESSIONS_COUNT = "sessions_count";
	
	private Map<String, String> urls;
	
	
	public void setUrls(Map<String, String> urls){
		this.urls = urls;
	}

	public String getSystemLoad(){
		return urls.get(SYSTEM_LOAD);
	}

	public String getInstanceId(){
		return urls.get(INSTANCE_ID);
	}
	
	public String getSessionsCount(){
		return urls.get(SESSIONS_COUNT);
	}
	
}
