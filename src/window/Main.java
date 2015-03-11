/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Kohl
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        root.setTop(getMenuBar(primaryStage));
        root.setLeft(getLeftContent());
        root.setCenter(getCenterContent());

        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.setTitle("Moni");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(400);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private MenuBar getMenuBar(Stage primaryStage) {
        //
        Menu menu = new Menu("Soubor");

        MenuItem loadItem = new MenuItem("Nahrát", null);
        loadItem.setMnemonicParsing(true);
        menu.getItems().add(loadItem);
        
        MenuItem saveItem = new MenuItem("Uložit", null);
        saveItem.setMnemonicParsing(true);
        menu.getItems().add(saveItem);
        
        MenuItem saveAsItem = new MenuItem("Uložit jako...", null);
        saveAsItem.setMnemonicParsing(true);
        menu.getItems().add(saveAsItem);
        
        MenuItem exitItem = new MenuItem("Konec", null);
        exitItem.setMnemonicParsing(true);
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });
        menu.getItems().add(exitItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        return menuBar;
    }

    private TreeView getLeftContent() {

        TreeItem<String> root = new TreeItem<String>("Filtry");
        root.setExpanded(true);
        root.getChildren().addAll(
                new TreeItem<>("Polozka"),
                new TreeItem<>("Polozka"),
                new TreeItem<>("Polozka")
        );
        TreeView filter= new TreeView<>(root);
        filter.setPrefWidth(200);
        
        return filter;
    }

    private TextArea getCenterContent() {

        TextArea console = new TextArea();
        console.setPrefRowCount(10);
        console.setPrefColumnCount(100);
        console.setWrapText(true);
        console.setPrefWidth(150);

        return console;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
