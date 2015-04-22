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
        Logging.logDebugIfEnabled(peerFileLogger, "Creating PeerFile monitor.");
        this.urlFactory = urlFactory;
        this.restTemplate = restTemplate;
    }

    public UrlFactory getUrlFactory() {
        Logging.logDebugIfEnabled(peerFileLogger, "Getting URL Factory.");
        return urlFactory;
    }

    public RestTemplate getRestTemplate() {
        Logging.logDebugIfEnabled(peerFileLogger, "Getting Rest template.");
        return restTemplate;
    }

}
