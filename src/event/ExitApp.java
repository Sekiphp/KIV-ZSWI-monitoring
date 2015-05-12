package event;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import window.MainWindow;

/**
 * Class handling application exit.
 */
public class ExitApp implements EventHandler {

    /**
     * Window (GUI) of application.
     */
    private final MainWindow mainWindow;

    /**
     * Constructor.
     * @param mainWindow	main window of application
     */
    public ExitApp(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(Event event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setContentText("Save filter before closing?");
        
        alert.getButtonTypes().setAll( ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.CANCEL) {
            event.consume();
        } else if (result.get() == ButtonType.NO) {
            mainWindow.getMonitoring().close();
            Platform.exit();
        } else if (result.get() == ButtonType.YES) {
            if (!this.mainWindow.getMonitoring().getFilter().isSave()) {

                new SaveFilter(this.mainWindow).handle(null);
                mainWindow.getMonitoring().close();
            }
            Platform.exit();
        }
    }

    /**
     * Method creating dialogue asking user to confirm action.
     * @param title	title of dialogue window
     * @param text	text of dialogue
     * @return
     */
    private Optional<ButtonType> dialogYesNo(String title, String text) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.getButtonTypes().set(0, ButtonType.YES);
        alert.getButtonTypes().set(1, ButtonType.NO);
        return alert.showAndWait();
    }
}