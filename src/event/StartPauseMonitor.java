package event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import library.Monitoring;

/**
 * Handles start/pause of app.
 */
public class StartPauseMonitor implements EventHandler {	
	
	/**
	 * StartPauseMonitor logger
	 */
    private final Logger applicationLogger = LogManager.getLogger();
    
    /**
     * Object providing monitoring.
     */
    private final Monitoring monitoring;

    /**
     * Constructor.
     * @param monitorig	object providing monitoring
     */
    public StartPauseMonitor(Monitoring monitorig) {
        this.monitoring = monitorig;
    }

    @Override
    public void handle(Event event) {

        MenuItem menu = (MenuItem) event.getSource();
        if (monitoring.isRun()) {
        	if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Starting monitoring...");
        	
            menu.setText("Start");
            monitoring.pause();
        } else {
        	if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Pausing monitoring...");
        	
            monitoring.start();
            menu.setText("Pause");
        }
    }
}