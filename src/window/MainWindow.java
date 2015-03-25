package window;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import instances.Monitoring;
import event_handlers.ExitApp;
import event_handlers.LoadFilter;
import event_handlers.RestoreApp;
import event_handlers.SaveAsFilter;
import event_handlers.SaveFilter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Kohl
 */
public class MainWindow extends Application {

    private TreeView filters;
    private TextArea console;
    private Monitoring monitoring;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage=stage;
        
        this.console = new TextArea();
        
        this.monitoring = new Monitoring(console);
        
        BorderPane root = new BorderPane();
        root.setTop(getMenuBar());
        root.setLeft(getLeftContent());
        root.setCenter(getCenterContent());

        stage.setScene(new Scene(root, 800, 500));
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
        Menu menu = new Menu("File");

        MenuItem loadItem = new MenuItem("Load");
        loadItem.setMnemonicParsing(true);
        loadItem.setAccelerator(new KeyCodeCombination(KeyCode.O,KeyCombination.CONTROL_DOWN));
        loadItem.setOnAction(new LoadFilter(this));

        MenuItem saveItem = new MenuItem("Save");
        saveItem.setMnemonicParsing(true);
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S,KeyCombination.CONTROL_DOWN));
        saveItem.setOnAction(new SaveFilter(this));

        MenuItem saveAsItem = new MenuItem("Save as...");
        saveAsItem.setMnemonicParsing(true);
        saveAsItem.setOnAction(new SaveAsFilter(this));

        MenuItem restoneItem = new MenuItem("Restore");
        restoneItem.setMnemonicParsing(true);
        restoneItem.setAccelerator(new KeyCodeCombination(KeyCode.F5));
        restoneItem.setOnAction(new RestoreApp(this.monitoring));

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setMnemonicParsing(true);
        exitItem.setOnAction(new ExitApp(this));

        menu.getItems().add(loadItem);
        menu.getItems().add(saveItem);
        menu.getItems().add(saveAsItem);
        menu.getItems().add(restoneItem);
        menu.getItems().add(exitItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        return menuBar;
    }

    private TreeView getLeftContent() {
        CheckBoxTreeItem root = new CheckBoxTreeItem<>("Filters");
        root.setExpanded(true);
        root.setSelected(true);
        root.setIndependent(false);

        for (int i = 0; i < 3; i++) {
            CheckBoxTreeItem item = new CheckBoxTreeItem<>("Polozka");
            item.setSelected(true);
            root.getChildren().add(item);
        }

        this.filters = new TreeView<>();
        this.filters.setPrefWidth(200);
        this.filters.setRoot(root);
        this.filters.setEditable(true);
        this.filters.setCellFactory(CheckBoxTreeCell.forTreeView());
        this.filters.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        this.filters.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//                System.out.println("Selected Text : ");
//
//            }
//        });

        return this.filters;
    }

    private TextArea getCenterContent() {
        this.console.setWrapText(true);
        this.console.setEditable(false);
        this.console.setScrollTop(Double.MIN_VALUE);// scroluje na podleni radek
        return console;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
