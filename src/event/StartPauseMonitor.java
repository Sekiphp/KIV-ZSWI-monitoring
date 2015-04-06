/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import library.Monitoring;

/**
 *
 * @author Kohl
 */
public class StartPauseMonitor implements EventHandler {

    private static final Logger startPauseMonitorLogger = LogManager.getLogger();

    private final Monitoring monitoring;

    public StartPauseMonitor(Monitoring monitorig) {
        this.monitoring = monitorig;
    }

    @Override
    public void handle(Event event) {

        MenuItem menu = (MenuItem) event.getSource();
        if (monitoring.isRun()) {
            menu.setText("Start");
            monitoring.pause();
        } else {
            monitoring.start();
            menu.setText("Pause");
        }

    }

}
