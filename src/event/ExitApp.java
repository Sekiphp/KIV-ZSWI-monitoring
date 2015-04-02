/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.Optional;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import org.springframework.util.comparator.ComparableComparator;
import window.MainWindow;

/**
 *
 * @author Kohl
 */
public class ExitApp implements EventHandler {

    private final MainWindow mainWindow;

    public ExitApp(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(Event event) {

        if (!this.mainWindow.getMonitoring().getFilter().isSave()) {           
            Optional<ButtonType> result = dialogYesNo("Confirm Save filter","Save filter?");
            if (result.get() == ButtonType.YES) {
                new SaveFilter(this.mainWindow).handle(null);
            }
        }
        
        Optional<ButtonType> result = dialogYesNo("Confirm Exit","Exit Monitoring?");

        if (result.get() == ButtonType.NO) {
            event.consume();
        } else {
            Platform.exit();
        }
    }

    private Optional<ButtonType> dialogYesNo(String title, String text){ 
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.getButtonTypes().set(0, ButtonType.YES);
        alert.getButtonTypes().set(1, ButtonType.NO);
        return alert.showAndWait();
    }  
}
