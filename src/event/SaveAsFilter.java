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
 * Handles saving a filter as.
 */
public class SaveAsFilter implements EventHandler {
	
	/**
	 * SaveAsFilter logger
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

        if (applicationLogger.isInfoEnabled()) applicationLogger.info("Saving filter.");

        try {
            this.mainWindow.getMonitoring().getFilter().saveFile(file.getPath());
        } catch (Exception ex) {
        	applicationLogger.error("An error occured while attempting to save a filter file.");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Saving filter error.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();

        }
        
        fileChooser = null;
    }

}
