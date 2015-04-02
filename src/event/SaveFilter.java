/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import window.MainWindow;

/**
 *
 * @author Kohl
 */
public class SaveFilter implements EventHandler {

    private final MainWindow mainWindow;

    public SaveFilter(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(Event event) {

        if (this.mainWindow.getMonitoring().getFilter().isPath()) {
            try {
                this.mainWindow.getMonitoring().getFilter().saveFile();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error save filter");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        } else {
            new SaveAsFilter(this.mainWindow).handle(null);
        }

    }

}
