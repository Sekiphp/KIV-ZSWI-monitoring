/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import window.MainWindow;

/**
 *
 * @author Kohl
 */
public class SaveAsFilter implements EventHandler {
	
	private static final Logger saveAsFilterLogger = LogManager.getLogger();

    private final MainWindow mainWindow;

    public SaveAsFilter(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(Event event) {

        String fileName = "untitled.filter";

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save filter");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Filter Files", "*.filter"));

        if (this.mainWindow.getMonitoring().getFilter().isPath()) {
            fileName = this.mainWindow.getMonitoring().getFilter().getPath();
            fileChooser.setInitialFileName(new File(fileName).getName());
            fileChooser.setInitialDirectory(new File(fileName).getParentFile());
        } else {
            fileChooser.setInitialFileName(fileName);
        }

        File file = fileChooser.showSaveDialog(this.mainWindow.getStage());
        if (file == null) {
            return;
        }

        try {
            this.mainWindow.getMonitoring().getFilter().saveFile(file.getPath());
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error save filter");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();

        }
        fileChooser = null;
    }

}
