import java.util.Map;


public class UrlFactory {

	private final String SYSTEM_LOAD = "system_load";
	private final String INSTANCE_ID = "instance_id";
	
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
	
}
