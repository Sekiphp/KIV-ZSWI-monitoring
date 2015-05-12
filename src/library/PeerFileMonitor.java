package library;

import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides URLs (<code>UrlFactory</code>) and REST service (<code>RestTemplate</code>).
 */
public class PeerFileMonitor {	
    /**
     * logger PeerFileMonitoru
     */
    private static final Logger applicationLogger = LogManager.getLogger();

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
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Creating PeerFile monitor.");
        this.urlFactory = urlFactory;
        this.restTemplate = restTemplate;
    }
    
    /**
     * Getter of URL factory.
     * @return	UrlFactory providing URLs
     */
    public UrlFactory getUrlFactory() {
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Getting URL Factory.");
        return urlFactory;
    }

    /**
     * Getter of RestTemplate
     * @return	rest template providing rest service
     */
    public RestTemplate getRestTemplate() {
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Getting Rest template.");
        return restTemplate;
    }
}