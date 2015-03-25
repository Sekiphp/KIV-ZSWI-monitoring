package library;

import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PeerFileMonitor {
	
	/** logger PeerFileMonitoru */
	private static Logger peerFileLogger = LogManager.getLogger();

	private UrlFactory urlFactory;
	private RestTemplate restTemplate;

	public PeerFileMonitor(UrlFactory urlFactory, RestTemplate restTemplate) {
		peerFileLogger.debug("Creating PeerFile monitor.");
		this.urlFactory = urlFactory;
		this.restTemplate = restTemplate;
	}

	public UrlFactory getUrlFactory() {
		peerFileLogger.debug("Getting URL Factory.");
		return urlFactory;
	}

	public RestTemplate getRestTemplate() {
		peerFileLogger.debug("Getting Rest template.");
		return restTemplate;
	}
	
}
