package library;


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
 * Provides basic actions with filters - saving, loading, etc.
 */
public class FilterManager {
    /**
     * logger FilterManageru
     */
    private static final Logger filterManagerLogger = LogManager.getLogger();

    /**
     * 
     */
    private boolean isSave;
    
    /**
     * Destination of saved filters.
     */
    private String path;
    
    /**
     * 
     */
    @SuppressWarnings("rawtypes")
	private final ListView lvFilters;
    
    /**
     * Collection of selected filters.
     */
    private final List<TypeMonitoring> selectedFilters;

    /**
     * Constructor.
     */
    public FilterManager() {
        Logging.logDebugIfEnabled(filterManagerLogger, "Creating Filter manager.");

        this.isSave = true;

        this.lvFilters = new ListView<>();
        this.lvFilters.setItems(FXCollections.observableArrayList(TypeMonitoring.toArray()));
        this.lvFilters.setCellFactory(CheckBoxListCell.forListView(new ChangeFilter(this)));
        this.lvFilters.setOnMouseClicked(new SetInstanceRefreshPeriod(this.lvFilters));

        this.selectedFilters = new ArrayList<>(Arrays.asList(TypeMonitoring.values()));
    }

    /**
     * Getter indicating whether or not are filters going to be saved.
     * @return boolean
     */
    public boolean isSave() {
        return this.isSave;
    }

    /**
     * Loads filters.
     * @throws IOException	Input/output exception.
     */
    public void loadFile() throws IOException {
        this.loadFile(path);
    }

    /**
     * Loads filters for filtering terminal output.
     * @param filePath					destination of saved filters
     * @throws FileNotFoundException	File not found exception
     * @throws IOException				Input/output exception
     */
	public void loadFile(String filePath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        Logging.logDebugIfEnabled(filterManagerLogger, "Filter input file opened.");

        this.selectedFilters.clear();
        for (String type : br.readLine().split(";")) {
            TypeMonitoring typeMonitoring = TypeMonitoring.valueOf(type.split(",")[0]);
            typeMonitoring.setRefreshPeriod(Integer.parseInt(type.split(",")[1]));

            this.selectedFilters.add(typeMonitoring);
        }

        Logging.logDebugIfEnabled(filterManagerLogger, "Filter loaded.");

        br.close();

        Logging.logDebugIfEnabled(filterManagerLogger, "Filter input file closed.");

        this.lvFilters.setCellFactory(CheckBoxListCell.forListView(new ChangeFilter(this)));
        this.path = filePath;
    }

    /**
     * Saves filters.
     * @throws IOException Input/output exception.
     */
    public void saveFile() throws IOException {
        this.saveFile(path);
    }

    /**
     * Saves filters.
     * @param filePath		destination of saved filters
     * @throws IOException	input/output exception
     */
    public void saveFile(String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        Logging.logDebugIfEnabled(filterManagerLogger, "Filter output file opened.");

        for (int i = 0; i < this.selectedFilters.size(); i++) {
            if (i != 0) {
                bw.write(";");
            }
            
            String name = this.selectedFilters.get(i).name();
            bw.write(name + "," + TypeMonitoring.valueOf(name).getRefreshPeriod());
        }

        Logging.logDebugIfEnabled(filterManagerLogger, "Filter saved.");

        bw.close();

        Logging.logDebugIfEnabled(filterManagerLogger, "Filter output file opened.");

        this.isSave = true;
        this.path = filePath;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isPath() {
        return path != null;
    }
    
    /**
     * Path getter.
     * @return	destination of saved filters
     */
    public String getPath() {
        return path;
    }
    
    /**
     * Setter of destination for saving filters.
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    /**
     * Indicates whether or not is item selected.
     * @param item	testing item
     * @return		status of selection of item
     */
    public boolean isSelect(TypeMonitoring item) {
        return this.selectedFilters.contains(item);
    }

    /**
     * Getter of filters.
     * @return	list view containing filters
     */
    public ListView getListView() {
        return this.lvFilters;
    }

    /**
     * Adds selected filter into a collection of selected ones.
     * @param item	newly selected item
     */
    public void add(String item) {
        this.selectedFilters.add(TypeMonitoring.valueOf(item));
        this.isSave = false;
    }

    /**
     * Removes selected filter from a collection of selected ones.
     * @param item	newly removed item
     */
    public void remove(String item) {
        this.selectedFilters.remove(TypeMonitoring.valueOf(item));
        this.isSave = false;
    }
}