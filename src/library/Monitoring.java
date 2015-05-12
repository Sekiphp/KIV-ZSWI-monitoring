package library;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 * Monitors instances/services of PeerFile server.
 */
public class Monitoring {

    /**
     * Application logger
     */
    private final Logger applicationLogger = LogManager.getLogger("application");
    
    /**
     * SystemLoad logger
     */
    private final Logger systemLoadLogger = LogManager.getLogger("systemLoad");
    
    /**
     * InstanceID logger
     */
    private final Logger instanceIdLogger = LogManager.getLogger("instanceID");
    
    /**
     * SessionsCount logger
     */
    private final Logger sessionsCountLogger = LogManager.getLogger("sessionsCount");
    
    /**
     * MemoryInfo logger
     */
    private final Logger memoryInfoLogger = LogManager.getLogger("memoryInfo");
    
    /**
     * SessionsInfo logger
     */
    private final Logger sessionsInfoLogger = LogManager.getLogger("sessionsInfo");
    

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
    private Timeline timeline;

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
        applicationLogger.info("Application launched.");
        this.console = console;
        this.statusLabel = new Label("Status: Stop");

        this.app = new ClassPathXmlApplicationContext("./application-context.xml");
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Application context loaded.");

        this.pf = (PeerFileMonitor) app.getBean("peerFileMonitor");
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("PeerFile monitor created.");

        this.restTemplate = pf.getRestTemplate();
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Rest template created.");

        this.fac = pf.getUrlFactory();
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Retrieved URL factory.");

        this.filter = new FilterManager();
        if (applicationLogger.isDebugEnabled()) applicationLogger.debug("Filter manager created.");
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
        applicationLogger.info("Monitoring started.");
        writeConsole("Loading...\n");
        this.statusLabel.setText("Status: Run");
               
        //ukazka nastaveni casove periody
        //SystemLoad.refreshTimePeriod = 1;
        
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            private int time = 0;
            
            private boolean isActiveMonitoring(TypeMonitoring typeMonitoring, String url) {
                if (filter.isSelect(typeMonitoring) && this.time % typeMonitoring.getRefreshPeriod() == 0) {
                	return true;
                }
                
                //blbost! musi prijit jinam
                /*applicationLogger.error("URL is not available: " + url);
                writeConsole("URL is not available: " + url);*/

                return false;
            }

            @Override
            public void handle(ActionEvent e) {


                try {
                    if (isActiveMonitoring(TypeMonitoring.SYSTEM_LOAD, fac.getSystemLoad())) {
                        SystemLoad systemLoad = restTemplate.getForObject(fac.getSystemLoad(), SystemLoad.class);
                        if (systemLoadLogger.isInfoEnabled()) systemLoadLogger.info("Retrieved PeerFile instance: "
                        		+ TypeMonitoring.SYSTEM_LOAD.getName());
                        writeConsole(TypeMonitoring.SYSTEM_LOAD.getName() + ": " + systemLoad.getSystem_load());
                    }
                } catch (Exception e1) {
                	systemLoadLogger.error("Server response error for instance: " + TypeMonitoring.SYSTEM_LOAD.getName());
                	writeConsole("Server response error for instance: " + TypeMonitoring.SYSTEM_LOAD.getName());
                }

                try {
                    if (isActiveMonitoring(TypeMonitoring.INSTANCE_ID, fac.getInstanceId())) {
                        InstanceId instanceId = restTemplate.getForObject(fac.getInstanceId(), InstanceId.class);
                        if (instanceIdLogger.isInfoEnabled()) instanceIdLogger.info("Retrieved PeerFile instance: "
                        		+ TypeMonitoring.INSTANCE_ID.getName());
                        writeConsole(TypeMonitoring.INSTANCE_ID.getName() + ": " + instanceId.getInstance_id());
                    }
                } catch (Exception e1) {
                	instanceIdLogger.error("Server response error for instance: " + TypeMonitoring.INSTANCE_ID.getName());
                	writeConsole("Server response error for instance: " + TypeMonitoring.INSTANCE_ID.getName());
                }

                try {
                    if (isActiveMonitoring(TypeMonitoring.SESSIONS_COUNT, fac.getSessionsCount())) {
                        SessionsCount sessionsCount = restTemplate.getForObject(fac.getSessionsCount(), SessionsCount.class);
                        if (sessionsCountLogger.isInfoEnabled()) sessionsCountLogger.info("Retrieved PeerFile instance: "
                        		+ TypeMonitoring.SESSIONS_COUNT.getName());
                        writeConsole(TypeMonitoring.SESSIONS_COUNT.getName() + ": " + sessionsCount.getSessions_count());
                    }
                } catch (Exception e1) {
                	sessionsCountLogger.error("Server response error for instance: " + TypeMonitoring.SESSIONS_COUNT.getName());
                	writeConsole("Server response error for instance: " + TypeMonitoring.SESSIONS_COUNT.getName());
                }

                try {
                    if (isActiveMonitoring(TypeMonitoring.MEMORY_INFO, fac.getMemoryInfo())) {
                        MemoryInfo memoryInfo = restTemplate.getForObject(fac.getMemoryInfo(), MemoryInfo.class);
                        if (memoryInfoLogger.isInfoEnabled()) memoryInfoLogger.info("Retrieved PeerFile instance: "
                        		+ TypeMonitoring.MEMORY_INFO.getName());
                        writeConsole(TypeMonitoring.MEMORY_INFO.getName() + ": " + memoryInfo.getMemory_info());
                    }
                } catch (Exception e1) {
                	memoryInfoLogger.error("Server response error for instance: " + TypeMonitoring.MEMORY_INFO.getName());
                	writeConsole("Server response error for instance: " + TypeMonitoring.MEMORY_INFO.getName());
                }

                try {
                    if (isActiveMonitoring(TypeMonitoring.SESSIONS_INFO, fac.getSessionsInfo())) {
                        SessionsInfo[] sessionsInfo = restTemplate.getForObject(fac.getSessionsInfo(), SessionsInfo[].class);
                        if (sessionsInfoLogger.isInfoEnabled()) sessionsInfoLogger.info("Retrieved PeerFile instances: "
                        		+ TypeMonitoring.SESSIONS_INFO.getName() + " (count: " + sessionsInfo.length + ")");
                        writeConsole(TypeMonitoring.SESSIONS_INFO.getName() + ": ");

                        for (SessionsInfo sessionsInfo1 : sessionsInfo) {
                            writeConsole(sessionsInfo1.getSessions_info());
                        }
                    }
                } catch (Exception e1) {
                	sessionsInfoLogger.error("Server response error for instances: " + TypeMonitoring.SESSIONS_INFO.getName());
                	writeConsole("Server response error for instances: " + TypeMonitoring.SESSIONS_INFO.getName());
                }
                
                this.time++;
        }}));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
                
    }


    /**
     * Pauses monitoring.
     */
    public void pause() {
        if (timeline != null) {
        	timeline.stop();
        	timeline = null;
        }
        
        applicationLogger.info("Monitoring paused.");
        this.statusLabel.setText("Status: Pause");
    }

    /**
     * Closes app.
     */
    public void close() {
        app.close();
        applicationLogger.info("Application closed.");
    }

    /**
     * Displays terminal output.
     *
     * @param text	terminal output
     */
    private void writeConsole(String text) {
        this.console.appendText(text + "\n\n");
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