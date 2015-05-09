package library;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 * Monitors instances/services of PeerFile server.
 *
 * @author Kohl
 */
public class Monitoring {

    /**
     * Main monitoring logger
     */
    private static final Logger monitoringLogger = LogManager.getLogger();

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
        monitoringLogger.info("Application launched.");
        this.console = console;
        this.statusLabel = new Label("Status: Stop");

        this.app = new ClassPathXmlApplicationContext("./application-context.xml");
        Logging.logDebugIfEnabled(monitoringLogger, "Application context loaded.");

        this.pf = (PeerFileMonitor) app.getBean("peerFileMonitor");
        Logging.logDebugIfEnabled(monitoringLogger, "PeerFile monitor created.");

        this.restTemplate = pf.getRestTemplate();
        Logging.logDebugIfEnabled(monitoringLogger, "Rest template created.");

        this.fac = pf.getUrlFactory();
        Logging.logDebugIfEnabled(monitoringLogger, "Retrieved URL factory.");

        //TODO: NEVIM ODKUD ZISKAT POLE SLEDOVANYCH KATEGORII
        this.filter = new FilterManager();
        Logging.logDebugIfEnabled(monitoringLogger, "Filter manager created.");
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
        monitoringLogger.info("Monitoring started.");
        writeConsole("Loading...\n");
        this.statusLabel.setText("Status: Run");
               
        //ukazka nastaveni casove periody
        //SystemLoad.refreshTimePeriod = 1;
        timer = new Timer();
        timer.schedule(new TimerTask() {

            private int time = 0;

            private boolean isActiveMonitoring(TypeMonitoring typeMonitoring, String url) {
                if (filter.isSelect(typeMonitoring) && this.time % typeMonitoring.getRefreshPeriod() == 0) {
                    if (checkUrl(url)) {
                        return true;
                    }
                    writeConsole("Tato url je nedostupná: " + url);
                }

                return false;
            }

            @Override
            public void run() {

                try {
                    if (isActiveMonitoring(TypeMonitoring.SYSTEM_LOAD, fac.getSystemLoad())) {
                        SystemLoad systemLoad = restTemplate.getForObject(fac.getSystemLoad(), SystemLoad.class);
                        Logging.logDebugIfEnabled(monitoringLogger, "Retrieved PeerFile instance: System Load");
                        writeConsole("systemLoad: " + systemLoad.getSystem_load());
                    }
                } catch (Exception e1) {
                    monitoringLogger.error("Server response error for instance: System Load!");

                }

                try {
                    if (isActiveMonitoring(TypeMonitoring.INSTANCE_ID, fac.getInstanceId())) {
                        InstanceId instanceId = restTemplate.getForObject(fac.getInstanceId(), InstanceId.class);
                        Logging.logDebugIfEnabled(monitoringLogger, "Retrieved PeerFile instance: Instance ID");
                        writeConsole("instance ID: " + instanceId.getInstance_id());
                    }
                } catch (Exception e1) {
                    monitoringLogger.error("Server response error for instance: Instance ID!");
                }

                try {
                    if (isActiveMonitoring(TypeMonitoring.SESSIONS_COUNT, fac.getSessionsCount())) {
                        SessionsCount sessionsCount = restTemplate.getForObject(fac.getSessionsCount(), SessionsCount.class);
                        Logging.logDebugIfEnabled(monitoringLogger, "Retrieved PeerFile instance: Sessions Count");
                        writeConsole("sessions count: " + sessionsCount.getSessions_count());
                    }
                } catch (Exception e1) {
                    monitoringLogger.error("Server response error for instance: Sessions Count!");
                }

                try {
                    if (isActiveMonitoring(TypeMonitoring.MEMORY_INFO, fac.getMemoryInfo())) {
                        MemoryInfo memoryInfo = restTemplate.getForObject(fac.getMemoryInfo(), MemoryInfo.class);
                        Logging.logDebugIfEnabled(monitoringLogger, "Retrieved PeerFile instance: Memory Info");
                        writeConsole("memory info: " + memoryInfo.getMemory_info());
                    }
                } catch (Exception e1) {
                    monitoringLogger.error("Server response error for instance: Memory Info!");
                }

                try {
                    if (isActiveMonitoring(TypeMonitoring.SESSIONS_INFO, fac.getSessionsInfo())) {
                        SessionsInfo[] sessionsInfo = restTemplate.getForObject(fac.getSessionsInfo(), SessionsInfo[].class);
                        Logging.logDebugIfEnabled(monitoringLogger, "Retrieved PeerFile instances: Sessions Info (count: " + sessionsInfo.length + ")");
                        writeConsole("sessions info: ");

                        for (SessionsInfo sessionsInfo1 : sessionsInfo) {
                            writeConsole(sessionsInfo1.getSessions_info());
                        }
                    }
                } catch (Exception e1) {
                    monitoringLogger.error("Server response error for instances: Sessions Info!");
                }

                monitoringLogger.info("Monitoring cycle finished.");

                this.time++;
            }

        }, 1000, 1000);

    }

    /**
     * Checkurl
     *
     * @param url	url address
     * @return status url
     */
    public static boolean checkUrl(String url) {

        boolean result = false;
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200) {
                result = true;
            }
        } catch (Exception e) {
        	monitoringLogger.error("URL is not available: " + url);
            result = false;
        }
        return result;
    }

    /**
     * Pauses monitoring.
     */
    public void pause() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        monitoringLogger.info("Monitoring paused.");
        this.statusLabel.setText("Status: Pause");
    }

    /**
     * Closes app.
     */
    public void close() {
        app.close();
        monitoringLogger.info("Application closed.");
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
