package event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import window.MainWindow;
import library.Logging;

/**
 * Handles saving filter.
 */
public class SaveFilter implements EventHandler {	
	/**
	 * Messenger.
	 */
    private static final Logger saveFilterLogger = LogManager.getLogger();
    
    /**
     * Main window (GUI) of app.
     */
    private final MainWindow mainWindow;
    
    /**
     * Constructor.
     * @param mainWindow	Main window (GUI) of app.
     */
    public SaveFilter(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(Event event) {

        if (this.mainWindow.getMonitoring().getFilter().isPath()) {
        	
        	saveFilterLogger.info("Saving filter.");

            try {
                this.mainWindow.getMonitoring().getFilter().saveFile();
            } catch (Exception ex) {
            	saveFilterLogger.error("An error occured when attempting to save a filter file.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Saving filter error.");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        } else {
            new SaveAsFilter(this.mainWindow).handle(null);
        }

    }
}