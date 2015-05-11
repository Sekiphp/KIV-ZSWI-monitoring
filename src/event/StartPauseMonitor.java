package event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import library.Monitoring;
import library.Logging;

/**
 * Handles start/pause of app.
 */
public class StartPauseMonitor implements EventHandler {	
	/**
	 * Logger logging start/pause app status.
	 */
    private static final Logger startPauseMonitorLogger = LogManager.getLogger();
    
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
        	Logging.logDebugIfEnabled(startPauseMonitorLogger, "Starting monitoring...");
        	
            menu.setText("Start");
            monitoring.pause();
        } else {
        	Logging.logDebugIfEnabled(startPauseMonitorLogger, "Pausing monitoring...");
        	
            monitoring.start();
            menu.setText("Pause");
        }
    }
}