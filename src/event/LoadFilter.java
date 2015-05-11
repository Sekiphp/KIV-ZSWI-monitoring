package event;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import window.MainWindow;
import library.Logging;

/**
 * Handles loading of a filter.
 */
public class LoadFilter implements EventHandler {
	
	/**
	 * Messenger
	 */
    private static final Logger loadFilterLogger = LogManager.getLogger();

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

        loadFilterLogger.info("Loading filter.");

        try {
            this.mainWindow.getMonitoring().getFilter().loadFile(file.getPath());
        } catch (Exception ex) {
            loadFilterLogger.error("An error occured while attempting to load a filter file.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Loading filter error.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
        fileChooser = null;
    }
}