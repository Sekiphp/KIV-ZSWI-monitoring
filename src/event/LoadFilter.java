package event;

import java.io.File;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import window.MainWindow;

/**
 * Handles loading of a filter.
 */
public class LoadFilter implements EventHandler {
	
	/**
	 * LoadFilter logger
	 */
    private final Logger applicationLogger = LogManager.getLogger();

    /**
     * Main window (GUI) of app.
     */
    private final MainWindow mainWindow;
    
    /**
     * Constructor.
     * @param mainWindow	Main window (GUI) of app.
     */
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

        if (applicationLogger.isInfoEnabled()) applicationLogger.info("Loading filter.");

        try {
            this.mainWindow.getMonitoring().getFilter().loadFile(file.getPath());
        } catch (Exception ex) {
        	applicationLogger.error("An error occured while attempting to load a filter file.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Loading filter error.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
        fileChooser = null;
    }
}