import org.springframework.web.client.RestTemplate;


public class PeerFileMonitor {

	private UrlFactory urlFactory;
	private RestTemplate restTemplate;
	

	public PeerFileMonitor(UrlFactory urlFactory, RestTemplate restTemplate) {
		this.urlFactory = urlFactory;
		this.restTemplate = restTemplate;
	}

	public UrlFactory getUrlFactory() {
		return urlFactory;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
}
