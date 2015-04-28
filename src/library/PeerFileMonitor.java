package library;

import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PeerFileMonitor {

	/**
	 * Provides URLs (<code>UrlFactory</code>) and REST service (<code>RestTemplate</code>).
	 */
	
    /**
     * logger PeerFileMonitoru
     */
    private static final Logger peerFileLogger = LogManager.getLogger();

    /**
     * Url factory provides URLs to server instances.
     */
    private final UrlFactory urlFactory;
    
    /**
     * REST service.
     */
    private final RestTemplate restTemplate;

    /**
     * Constructor.
     * @param urlFactory	provides server instance URLs
     * @param restTemplate	provides REST service
     */
    public PeerFileMonitor(UrlFactory urlFactory, RestTemplate restTemplate) {
        Logging.logDebugIfEnabled(peerFileLogger, "Creating PeerFile monitor.");
        this.urlFactory = urlFactory;
        this.restTemplate = restTemplate;
    }
    
    /**
     * Getter of URL factory.
     * @return	UrlFactory providing URLs
     */
    public UrlFactory getUrlFactory() {
        Logging.logDebugIfEnabled(peerFileLogger, "Getting URL Factory.");
        return urlFactory;
    }

    /**
     * Getter of RestTemplate
     * @return	rest template providing rest service
     */
    public RestTemplate getRestTemplate() {
        Logging.logDebugIfEnabled(peerFileLogger, "Getting Rest template.");
        return restTemplate;
    }

}
