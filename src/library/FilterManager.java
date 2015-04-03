package library;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private final List<String> filters;

    public FilterManager() {
        this.isSave = true;
        this.filters = new ArrayList<>();
    }

    public boolean isSave() {
        return this.isSave;
    }

    public void loadFile() throws IOException {
        this.loadFile(path);
    }

    public void loadFile(String filePath) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        br.close();
        this.path=filePath;
    }

    public void saveFile() throws IOException {
        this.saveFile(path);
    }

    public void saveFile(String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        bw.close();
        this.isSave = true;
        this.path=filePath;
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
}
