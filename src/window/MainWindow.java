package window;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;

import event.ExitApp;
import event.LoadFilter;
import event.SaveAsFilter;
import event.SaveFilter;
import event.StartPauseMonitor;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import library.Monitoring;

/**
 *
 * @author Kohl
 */
public class MainWindow extends Application {

    /**
     * nastaveni konfiguracniho souboru pro logovani
     */
    static {
        System.setProperty("log4j.configurationFile",
                "log4j-config.xml");
    }

    private TextArea console;
    private Monitoring monitoring;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        this.console = new TextArea();
        this.monitoring = new Monitoring(console);

        BorderPane root = new BorderPane();
        root.setTop(this.getMenuBar());
        root.setLeft(this.monitoring.getFilter().getListView());
        root.setCenter(this.getCenterContent());
        root.setBottom(this.getStatusBar());

        stage.setScene(new Scene(root, 800, 500));

        File f = new File("src/application.css");
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        stage.setTitle("Monitoring");
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        stage.setResizable(true);
        stage.setOnCloseRequest(new ExitApp(this));
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public Monitoring getMonitoring() {
        return monitoring;
    }

    private MenuBar getMenuBar() {
        Menu fileMenu = new Menu("File");

        MenuItem loadItem = new MenuItem("Load");
        loadItem.setMnemonicParsing(true);
        loadItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        loadItem.setOnAction(new LoadFilter(this));

        MenuItem saveItem = new MenuItem("Save");
        saveItem.setMnemonicParsing(true);
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveItem.setOnAction(new SaveFilter(this));

        MenuItem saveAsItem = new MenuItem("Save as...");
        saveAsItem.setMnemonicParsing(true);
        saveAsItem.setOnAction(new SaveAsFilter(this));
    
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setMnemonicParsing(true);
        exitItem.setOnAction(new ExitApp(this));
        exitItem.getStyleClass().add("iconExit");

        fileMenu.getItems().add(loadItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(saveAsItem);
        //fileMenu.getItems().add(startPauseItem);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(exitItem);
        
        
        Menu monitorMenu = new Menu("Monitoring");
        
        MenuItem startPauseItem = new MenuItem("Start");
        startPauseItem.setMnemonicParsing(true);
        startPauseItem.setAccelerator(new KeyCodeCombination(KeyCode.SPACE));
        startPauseItem.setOnAction(new StartPauseMonitor(this.monitoring));
        
        monitorMenu.getItems().add(startPauseItem);
     
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(monitorMenu);
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        return menuBar;
    }

    private TextArea getCenterContent() {
        this.console.setWrapText(true);
        this.console.setEditable(false);
        //this.console.setScrollTop(Double.MIN_VALUE);// scroluje na posledni radek
        return console;
    }


    private ToolBar getStatusBar() {

        Region spring = new Region();
        HBox.setHgrow(spring, Priority.ALWAYS);

        CheckBox autoScroll = new CheckBox("Auto scroll");
        autoScroll.setSelected(true);
        autoScroll.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                //zapnuti autoscroll
            } else {
                //vypnuti autoscroll
            }
        });

        ToolBar toolBar = new ToolBar();
        toolBar.requestLayout();

        toolBar.getItems().add(spring);// right
        toolBar.getItems().add(autoScroll);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().add(this.monitoring.getStatusLabel());

        return toolBar;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
