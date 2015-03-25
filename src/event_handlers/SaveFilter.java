/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_handlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import org.controlsfx.dialog.Dialogs;
import window.MainWindow;

/**
 *
 * @author Kohl
 */
public class SaveFilter implements EventHandler{

    private final MainWindow mainWindow;
    
    public SaveFilter(MainWindow mainWindow) {
        this.mainWindow=mainWindow;
    }
    
    @Override
    public void handle(Event event) {
        
        if (this.mainWindow.getMonitoring().getFilter().isPath()) {
            try {
                this.mainWindow.getMonitoring().getFilter().saveFile();
            } catch (Exception ex) {
                Dialogs.create()
                        .title("Error")
                        .message("Error save filter?")
                        .showError();
            }
        } else {
            new SaveAsFilter(this.mainWindow).handle(null);
        }
       
    }
    
}
