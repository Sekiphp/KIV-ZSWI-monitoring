/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import library.FilterManager;
import library.TypeMonitoring;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Kohl
 */
public class ChangeFilter implements Callback<String, ObservableValue<Boolean>> {

    private final FilterManager filterManager;
    private static final Logger changeFilterLogger = LogManager.getLogger();

    public ChangeFilter(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    @Override
    public ObservableValue<Boolean> call(String item) {
        BooleanProperty observable = new SimpleBooleanProperty();
        if (filterManager.isSelect(TypeMonitoring.valueOf(item))) {
            observable.setValue(true);
        }
        observable.addListener((ObservableValue<? extends Boolean> observable1, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                this.filterManager.add(item);
            } else {
                this.filterManager.remove(item);
            }
        });
        return observable;
    }

}
