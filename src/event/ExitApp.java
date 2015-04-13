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
import javafx.scene.control.ButtonType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import window.MainWindow;

/**
 *
 * @author Kohl
 */
public class ExitApp implements EventHandler {

    private static final Logger exitAppLogger = LogManager.getLogger();

    private final MainWindow mainWindow;

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

    private Optional<ButtonType> dialogYesNo(String title, String text) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.getButtonTypes().set(0, ButtonType.YES);
        alert.getButtonTypes().set(1, ButtonType.NO);
        return alert.showAndWait();
    }
}
