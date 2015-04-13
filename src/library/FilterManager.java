package library;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import event.ChangeFilter;
import event.SetInstanceRefreshPeriod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Kohl
 */
public class FilterManager {

    /**
     * logger FilterManageru
     */
    private static final Logger filterManagerLogger = LogManager.getLogger();

    private boolean isSave;
    private String path;
    private final ListView lvFilters;
    private final List<TypeMonitoring> selectedFilters;

    public FilterManager() {
        if (filterManagerLogger.isDebugEnabled()) {
            filterManagerLogger.debug("Creating Filter manager.");
        }

        this.isSave = true;

        this.lvFilters = new ListView<>();
        this.lvFilters.setItems(FXCollections.observableArrayList(TypeMonitoring.toArray()));
        this.lvFilters.setCellFactory(CheckBoxListCell.forListView(new ChangeFilter(this)));
        this.lvFilters.setOnMouseClicked(new SetInstanceRefreshPeriod(this.lvFilters));

        this.selectedFilters = new ArrayList<>(Arrays.asList(TypeMonitoring.values()));
    }

    public boolean isSave() {
        return this.isSave;
    }

    public void loadFile() throws IOException {
        this.loadFile(path);
    }

    public void loadFile(String filePath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        if (filterManagerLogger.isDebugEnabled()) {
            filterManagerLogger.debug("Filter input file opened.");
        }

        this.selectedFilters.clear();
        for (String type : br.readLine().split(";")) {
            this.selectedFilters.add(TypeMonitoring.valueOf(type));
        }

        if (filterManagerLogger.isDebugEnabled()) {
            filterManagerLogger.debug("Filter loaded.");
        }

        br.close();

        if (filterManagerLogger.isDebugEnabled()) {
            filterManagerLogger.debug("Filter input file closed.");
        }

        this.lvFilters.setCellFactory(CheckBoxListCell.forListView(new ChangeFilter(this)));
        this.path = filePath;
    }

    public void saveFile() throws IOException {
        this.saveFile(path);
    }

    public void saveFile(String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        if (filterManagerLogger.isDebugEnabled()) {
            filterManagerLogger.debug("Filter output file opened.");
        }

        for (int i = 0; i < this.selectedFilters.size(); i++) {
            if (i != 0) {
                bw.write(";");
            }
            bw.write(this.selectedFilters.get(i).name());
        }

        if (filterManagerLogger.isDebugEnabled()) {
            filterManagerLogger.debug("Filter saved.");
        }

        bw.close();

        if (filterManagerLogger.isDebugEnabled()) {
            filterManagerLogger.debug("Filter output file opened.");
        }

        this.isSave = true;
        this.path = filePath;
    }

    public boolean isPath() {
        return path != null;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelect(TypeMonitoring item) {
        return this.selectedFilters.contains(item);
    }

    public ListView getListView() {
        return this.lvFilters;
    }

    public void add(String item) {
        this.selectedFilters.add(TypeMonitoring.valueOf(item));
        this.isSave = false;
    }

    public void remove(String item) {
        this.selectedFilters.remove(TypeMonitoring.valueOf(item));
        this.isSave = false;
    }

}
