/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_handlers;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import window.MainWindow;

/**
 *
 * @author Kohl
 */
public class ExitApp implements EventHandler{


    private final MainWindow mainWindow;
    
    public ExitApp(MainWindow mainWindow) {
        this.mainWindow=mainWindow;
    }
    
    @Override
    public void handle(Event event) {
        
        if(!this.mainWindow.getMonitoring().getFilter().isSave()){       
                Action response = Dialogs.create()
                        .owner(null)
                        .title("Confirm Save filter")
                        .message("Save filter?")
                        .actions(Dialog.ACTION_YES,Dialog.ACTION_NO)
                        .showConfirm();
                if (response == Dialog.ACTION_YES) {
                    new SaveFilter(this.mainWindow).handle(null);
                }
        }
 
        Action response = Dialogs.create()
                        .owner(null)
                        .title("Confirm Exit")
                        .message("Exit Monitoring?")
                        .actions(Dialog.ACTION_NO, Dialog.ACTION_YES)
                        .showConfirm();

                if (response == Dialog.ACTION_NO) {
                    event.consume();
                }else{
                    Platform.exit();
                }
    }
    
}
