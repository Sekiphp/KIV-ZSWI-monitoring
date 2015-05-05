package event;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import library.TypeMonitoring;
import library.Logging;

/**
 *
 * @author Ondrej Pittl
 */
public class SetInstanceRefreshPeriod implements EventHandler<MouseEvent> {

	/**
	 * Logger instance refresh period.
	 */
    private static final Logger setInstanceRefreshPeriodLogger = LogManager.getLogger();
	
    /**
     * "Tree" of instances.
     */
    private final ListView view;

    /**
     * Constructor.
     *
     * @param view	existing "tree" of instances
     */
    public SetInstanceRefreshPeriod(ListView view) {
        this.view = view;
    }

    /**
     * Handles action event triggering opening input dialogue for setting new
     * time period of selected instance.
     *
     * @param e
     */
    @Override
    public void handle(MouseEvent e) {

        if (e.getClickCount() == 2) {
			//double-click

            //Selected item of view of instances.
            TypeMonitoring selectedItem = TypeMonitoring.getTypeBy((String) view.getSelectionModel().getSelectedItem());

            /* Response from input dialogue. */
            //Použití JAVA 8u40
            TextInputDialog dialog = new TextInputDialog(Integer.toString(getInsntancePeriod(selectedItem)));
            dialog.setTitle("Custom time period");
            dialog.setHeaderText("Refreshing time settings");
            dialog.setContentText("Set custom time period of refreshing for: \n" + selectedItem + ".");

            Optional<String> response = dialog.showAndWait();
            if (response.isPresent()) {

                //if is valid period of time
                if (isValidPeriod(response.get())) {

                    //chosen time period
                    int period = Integer.parseInt(response.get());
                    selectedItem.setRefreshPeriod(period);
                    
                    setInstanceRefreshPeriodLogger.info("New refreshing period for " + selectedItem + ": " + period + " s.");
                }
            }
        }
    }

    /**
     * Getter of refresh time period of selected instance.
     *
     * @param type	type of selected instance
     * @return	refresh period of selected instance
     */
    private int getInsntancePeriod(TypeMonitoring type) {
        return type.getRefreshPeriod();
    }

    /**
     * Tests whether or not is time period inserted by user via input dialogue
     * valid.
     *
     * @param str	user input
     * @return		<code>true</code> - input is valid, <code>false</code> - input is
     * invalid
     */
    private boolean isValidPeriod(String str) {

        try {
            int val = Integer.parseInt(str);
            return val > 0;
        } catch (NumberFormatException e) {
        	setInstanceRefreshPeriodLogger.warn("Invalid period format!");

            return false;
        }
    }
}
