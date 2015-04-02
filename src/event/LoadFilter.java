/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.File;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import window.MainWindow;

/**
 *
 * @author Kohl
 */
public class LoadFilter implements EventHandler {

    private final MainWindow mainWindow;

    public LoadFilter(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(Event event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load filter");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Filter Files", "*.filter"));

        File file = fileChooser.showOpenDialog(this.mainWindow.getStage());
        if (file == null) {
            return;
        }

        try {
            this.mainWindow.getMonitoring().getFilter().loadFile(file.getPath());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error load filter");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        fileChooser=null;
    }

}
