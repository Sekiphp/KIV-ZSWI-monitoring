/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.File;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;
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
            Dialogs.create()
                    .title("Error")
                    .message("Error load filter?")
                    .showError();
        }
        fileChooser=null;
    }

}
