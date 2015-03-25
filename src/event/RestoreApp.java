/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import javafx.event.Event;
import javafx.event.EventHandler;
import library.Monitoring;

/**
 *
 * @author Kohl
 */
public class RestoreApp implements EventHandler{

    private final Monitoring monitoring;
    
    public RestoreApp(Monitoring monitorig) {
        this.monitoring=monitorig;
    }

    
    
    @Override
    public void handle(Event event) {
        this.monitoring.start();
    }
    
}
