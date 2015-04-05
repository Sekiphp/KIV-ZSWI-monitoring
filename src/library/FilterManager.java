package library;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import event.ChangedFilter;
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
import javafx.collections.ObservableList;
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
    private final List<String> selectedFilters;
     

    public FilterManager(String[] allFilters) {
    	if (filterManagerLogger.isDebugEnabled())
    		filterManagerLogger.debug("Creating Filter manager.");
        this.isSave = true;
        
        this.lvFilters =new ListView<>();
        this.lvFilters.setItems(FXCollections.observableArrayList(allFilters));
        this.lvFilters.setCellFactory(CheckBoxListCell.forListView(new ChangedFilter(this)));
        
        this.selectedFilters=new ArrayList<>(Arrays.asList(allFilters));
    }

    public boolean isSave() {
        return this.isSave;
    }

    public void loadFile() throws IOException {
        this.loadFile(path);
    }

    public void loadFile(String filePath) throws FileNotFoundException, IOException {
    	if (filterManagerLogger.isDebugEnabled())
    		filterManagerLogger.debug("Loading file.");
    	
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        this.selectedFilters.clear();
        this.selectedFilters.addAll(Arrays.asList(br.readLine().split(";")));

        br.close();

        this.lvFilters.setCellFactory(CheckBoxListCell.forListView(new ChangedFilter(this)));
        this.path = filePath;
    }

    public void saveFile() throws IOException {
        this.saveFile(path);
    }

    public void saveFile(String filePath) throws IOException {
    	if (filterManagerLogger.isDebugEnabled())
    		filterManagerLogger.debug("Saving file.");
    	
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        for (int i = 0; i < this.selectedFilters.size(); i++) {
            if(i!=0){
                bw.write(";");
            }
            bw.write(this.selectedFilters.get(i));
        }
        
        bw.close();
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

    public void remove(String item) {
        
        this.selectedFilters.add(item);
        
        this.isSave=false;
    }

    public boolean isSelect(String item){
        return this.selectedFilters.contains(item);
    }
    
    public ListView getListView(){
        return this.lvFilters;
    }
    
    public void add(String item) {
       
        this.selectedFilters.remove(item);
        
        this.isSave=false;
    }
}
