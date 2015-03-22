import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PeerFileMonitor {
	
	/**
	 * Hlavni logger
	 */
	private static Logger peerFileLogger = LogManager.getLogger();

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
