package library;

import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PeerFileMonitor {

    /**
     * logger PeerFileMonitoru
     */
    private static final Logger peerFileLogger = LogManager.getLogger();

    private final UrlFactory urlFactory;
    private final RestTemplate restTemplate;

    public PeerFileMonitor(UrlFactory urlFactory, RestTemplate restTemplate) {
        if (peerFileLogger.isDebugEnabled()) {
            peerFileLogger.debug("Creating PeerFile monitor.");
        }
        this.urlFactory = urlFactory;
        this.restTemplate = restTemplate;
    }

    public UrlFactory getUrlFactory() {
        if (peerFileLogger.isDebugEnabled()) {
            peerFileLogger.debug("Getting URL Factory.");
        }
        return urlFactory;
    }

    public RestTemplate getRestTemplate() {
        if (peerFileLogger.isDebugEnabled()) {
            peerFileLogger.debug("Getting Rest template.");
        }
        return restTemplate;
    }

}
