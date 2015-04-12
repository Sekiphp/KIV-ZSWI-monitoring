package event;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import library.InstanceId;
import library.MemoryInfo;
import library.SessionsCount;
import library.SessionsInfo;
import library.SystemLoad;
import library.TypeMonitoring;
import org.controlsfx.dialog.Dialogs;


/**
*
* @author Ondrej Pittl
*/
public class SetInstanceRefreshPeriod  implements EventHandler<MouseEvent> {
	
	/**
	 * "Tree" of instances.
	 */
	private ListView view;
	
	/**
	 * Constructor.
	 * @param view	existing "tree" of instances
	 */
	public SetInstanceRefreshPeriod(ListView view) {
		this.view = view;
	}
	

	/**
	 * Handles action event triggering opening input dialogue
	 * for setting new time period of selected instance.
	 */
	public void handle(MouseEvent e) {
	
		if (e.getClickCount() == 2) {
			//double-click
			
			//Selected item of view of instances.
			TypeMonitoring selectedItem = TypeMonitoring.getTypeBy((String) view.getSelectionModel().getSelectedItem());
			
			/* Response from input dialogue. */
			Optional<String> response = Dialogs.create()
			        .owner(null)
			        .title("Custom time period")
			        .masthead("Set custom time period of refreshing for: \n" + selectedItem + ".")
			        .message("Please enter a period higer than zero (seconds):")
			        .showTextInput("7");

			if (response.isPresent()) {
				
				//if is valid period of time
				if(isValidPeriod(response.get())) {
					
					//chosen time period
					int period = Integer.parseInt(response.get());
					
					switch (selectedItem) {

					//selected view item
					case INSTANCE_ID:
						InstanceId.refreshTimePeriod = period;
						break;
					case MEMORY_INFO:
						MemoryInfo.refreshTimePeriod = period;
						break;
					case SESSIONS_COUNT:
						SessionsCount.refreshTimePeriod = period;
						break;
					case SESSIONS_INFO:
						SessionsInfo.refreshTimePeriod = period;
						break;
					case SYSTEM_LOAD:
						SystemLoad.refreshTimePeriod = period;
						break;
					}
				}
			}			
         }
	}
	
	/**
	 * Tests whether or not is time period inserted by user via input dialogue valid.
	 * @param str	user input
	 * @return		<code>true</code> - input is valid, <code>false</code> - input is invalid
	 */
	private boolean isValidPeriod(String str) {
			
		try {
			int val = Integer.parseInt(str);
			if(val <= 0) return false; 
			return true;
		} catch (NumberFormatException e) {
			System.out.println("INVALID PERIOD!!!");
			
			return false;
		}
	}
}