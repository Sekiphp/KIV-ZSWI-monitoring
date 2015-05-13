package library;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import jdk.nashorn.internal.ir.annotations.Immutable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 * Monitors instances/services of PeerFile server.
 */
public class Monitoring {

	
	/**
	 * HashMap of loggers logging status of components.
	 */
	private final Map<String, Logger> loggers = new HashMap<String , Logger>() {{
		put("application_logger",    LogManager.getLogger("application"));
		put("system_load_logger",    LogManager.getLogger("systemLoad"));
		put("instance_id_logger",    LogManager.getLogger("instanceID"));
		put("sessions_count_logger",    LogManager.getLogger("sessionsCount"));
		put("memory_info_logger",    LogManager.getLogger("memoryInfo"));
		put("sessions_info_logger",    LogManager.getLogger("sessionsInfo"));
	}};


    /**
     * Default period of time of refreshing isntance.
     */
    public static final int DEFAULT_REFRESH_TIME = 7;

    /**
     * Application context.
     */
    private final ClassPathXmlApplicationContext app;

    /**
     * Output terminal GUI component.
     */
    private final TextArea console;

    /**
     * Label informing user about app status.
     */
    private final Label statusLabel;

    /**
     * Manages filters.
     */
    private final FilterManager filter;

    /**
     * Provides URLs and REST service.
     */
    private final PeerFileMonitor pf;

    /**
     * Rest service.
     */
    private final RestTemplate restTemplate;

    /**
     * Provides URLs.
     */
    private final UrlFactory fac;

    /**
     * Timer.
     */
    private Timer timer;

    /**
     * Indicates whether or not is app running.
     */
    private boolean run = false;

    /**
     * Constructor.
     *
     * @param console	Output terminal GUI component.
     */
    public Monitoring(TextArea console) {
        loggers.get("application_logger").info("Application launched.");
        this.console = console;
        this.statusLabel = new Label("Status: Stop");

        this.app = new ClassPathXmlApplicationContext("./application-context.xml");
        if (loggers.get("application_logger").isDebugEnabled()) loggers.get("application_logger").debug("Application context loaded.");

        this.pf = (PeerFileMonitor) app.getBean("peerFileMonitor");
        if (loggers.get("application_logger").isDebugEnabled()) loggers.get("application_logger").debug("PeerFile monitor created.");

        this.restTemplate = pf.getRestTemplate();
        if (loggers.get("application_logger").isDebugEnabled()) loggers.get("application_logger").debug("Rest template created.");

        this.fac = pf.getUrlFactory();
        if (loggers.get("application_logger").isDebugEnabled()) loggers.get("application_logger").debug("Retrieved URL factory.");

        this.filter = new FilterManager();
        if (loggers.get("application_logger").isDebugEnabled()) loggers.get("application_logger").debug("Filter manager created.");
    }
    
    

    /**
     * Getter of filter manager.
     *
     * @return	filter manager
     */
    public FilterManager getFilter() {
        return filter;
    }

    /**
     * Indicates whether or not is app running.
     *
     * @return
     */
    public boolean isRun() {
        run = !run;
        return !run;
    }

    /**
     * Starts monitoring.
     */
    public void start() {
        loggers.get("application_logger").info("Monitoring started.");
        writeConsole("Loading...\n");
        this.statusLabel.setText("Status: Run");
               
        //ukazka nastaveni casove periody
        //SystemLoad.refreshTimePeriod = 1;
        timer = new Timer();
        timer.schedule(new TimerTask() {

            private int time = 0;
            
            @Override
            public void run() {
            	
            	checkAndPrintResponse(TypeMonitoring.SYSTEM_LOAD, time);
            	checkAndPrintResponse(TypeMonitoring.INSTANCE_ID, time);
            	checkAndPrintResponse(TypeMonitoring.SESSIONS_COUNT, time);
            	checkAndPrintResponse(TypeMonitoring.MEMORY_INFO, time);
            	checkAndPrintResponse(TypeMonitoring.SESSIONS_INFO, time);
            	
                time++;            	
            }

        }, 1000, 1000);
    }
    
    private boolean isActiveMonitoring(TypeMonitoring typeMonitoring, int actualTime) {
        if (filter.isSelect(typeMonitoring) && actualTime % typeMonitoring.getRefreshPeriod() == 0) {
        	return true;
        }
        
        /*applicationLogger.error("URL is not available: " + url);
        writeConsole("URL is not available: " + url);*/

        return false;
    }

    private void checkAndPrintResponse(TypeMonitoring instanceType, int actualTime){
    	try {
            if (isActiveMonitoring(instanceType, actualTime)) {
            	
            	IPFInstance instance = null;
            	IPFInstance[] instances = null;
            	
            	if(instanceType == TypeMonitoring.SESSIONS_INFO) {
            		instances = restTemplate.getForObject(fac.getUrlByType(instanceType), SessionsInfo[].class);
            	} else {
            		instance = restTemplate.getForObject(fac.getUrlByType(instanceType), instanceType.getTypeClass());
            	}
            	
                if (loggers.get(instanceType.getName() + "_logger").isInfoEnabled())
                	loggers.get(instanceType.getName() + "_logger").info("Retrieved PeerFile instance: " + instanceType.getName());
                
                if(instanceType == TypeMonitoring.SESSIONS_INFO) {
                	//sessions_info contains large amount of data
                	for(IPFInstance inst : instances)
                    	writeConsole(instanceType.getName() + ": " + inst.getInstanceStatus());
                } else {
                	//other instances contain just one piece of data
                	writeConsole(instanceType.getName() + ": " + instance.getInstanceStatus());
                }
                	
            }
        } catch (Exception e1) {
        	loggers.get(instanceType.getName() + "_logger").error("Server response error for instance: " + instanceType.getName());
        	writeConsole("Server response error for instance: " + instanceType.getName());
        }
    }

    /**
     * Pauses monitoring.
     */
    public void pause() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        
        loggers.get("application_logger").info("Monitoring paused.");
        this.statusLabel.setText("Status: Pause");
    }

    /**
     * Closes app.
     */
    public void close() {
        app.close();
        loggers.get("application_logger").info("Application closed.");
    }

    /**
     * Displays terminal output.
     *
     * @param text	terminal output
     */
    private void writeConsole(final String text) {
    	Platform.runLater(new Runnable() {
            public void run() {
            	console.appendText(text + "\n\n");
            }});
    }

    /**
     * Getter of app status.
     *
     * @return	app status
     */
    public Label getStatusLabel() {
        return this.statusLabel;
    }
}