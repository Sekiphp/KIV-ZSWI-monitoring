package library;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Timer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import javafx.util.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Kohl
 */
public class Monitoring {

    /**
     * hlavni logger
     *
     * Pozn. - regularni vyrazy pro doplneni testu isDebugEnabled: Find:
     * ([a-zA-Z]*Logger)(\.debug) Replace with:	if
     * \($1\.isDebugEnabled\(\)\)\r\n\t\t\t$1$2
     */
    private static final Logger monitoringLogger = LogManager.getLogger();

    public static final int DEFAULT_REFRESH_TIME = 7;
    public static Timer timer;

    private final ClassPathXmlApplicationContext app;
    private final TextArea console;
    private final FilterManager filter;
    private final PeerFileMonitor pf;
    private final RestTemplate restTemplate;
    private final UrlFactory fac;
    private Timeline timeline;

    private boolean run = false;

    public Monitoring(TextArea console) {
        monitoringLogger.info("Application launched.");
        this.console = console;

        this.app = new ClassPathXmlApplicationContext("./application-context.xml");
        if (monitoringLogger.isDebugEnabled()) {
            monitoringLogger.debug("Application context loaded.");
        }

        this.pf = (PeerFileMonitor) app.getBean("peerFileMonitor");
        if (monitoringLogger.isDebugEnabled()) {
            monitoringLogger.debug("PeerFile monitor created.");
        }

        this.restTemplate = pf.getRestTemplate();
        if (monitoringLogger.isDebugEnabled()) {
            monitoringLogger.debug("Rest template created.");
        }

        this.fac = pf.getUrlFactory();
        if (monitoringLogger.isDebugEnabled()) {
            monitoringLogger.debug("Retrieved URL factory.");
        }

        //NEVIM ODKUD ZISKAT POLE SLEDOVANYCH KATEGORII
        this.filter = new FilterManager();
        if (monitoringLogger.isDebugEnabled()) {
            monitoringLogger.debug("Filter manager created.");
        }
    }

    public FilterManager getFilter() {
        return filter;
    }

    public boolean isRun() {
        run = !run;
        return !run;
    }

    public void start() {
        monitoringLogger.info("Monitoring started.");
        writeConsole("Loading...\n");

        //ukazka nastaveni casove periody
        //SystemLoad.refreshTimePeriod = 1;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            private int time = 0;

            @Override
            public void handle(ActionEvent e) {

                if (this.isActiveMonitoring(TypeMonitoring.SYSTEM_LOAD)) {
                    SystemLoad systemLoad = restTemplate.getForObject(fac.getSystemLoad(), SystemLoad.class);
                    if (monitoringLogger.isDebugEnabled()) {
                        monitoringLogger.debug("Retrieved instance from Rest template: System Load");
                    }
                    writeConsole("systemLoad: " + systemLoad.getSystem_load());
                }

                if (this.isActiveMonitoring(TypeMonitoring.INSTANCE_ID)) {
                    InstanceId instanceId = restTemplate.getForObject(fac.getInstanceId(), InstanceId.class);
                    if (monitoringLogger.isDebugEnabled()) {
                        monitoringLogger.debug("Retrieved instance from Rest template: Instance ID");
                    }
                    writeConsole("instance ID: " + instanceId.getInstance_id());
                }

                if (this.isActiveMonitoring(TypeMonitoring.SESSIONS_COUNT)) {
                    SessionsCount sessionsCount = restTemplate.getForObject(fac.getSessionsCount(), SessionsCount.class);
                    if (monitoringLogger.isDebugEnabled()) {
                        monitoringLogger.debug("Retrieved instance from Rest template: Sessions Count");
                    }
                    writeConsole("sessions count: " + sessionsCount.getSessions_count());
                }

                if (this.isActiveMonitoring(TypeMonitoring.MEMORY_INFO)) {
                    MemoryInfo memoryInfo = restTemplate.getForObject(fac.getMemoryInfo(), MemoryInfo.class);
                    if (monitoringLogger.isDebugEnabled()) {
                        monitoringLogger.debug("Retrieved instance from Rest template: Memory Info");
                    }
                    writeConsole("memory info: " + memoryInfo.getMemory_info());
                }

                if (this.isActiveMonitoring(TypeMonitoring.SESSIONS_INFO)) {
                    SessionsInfo[] sessionsInfo = restTemplate.getForObject(fac.getSessionsInfo(), SessionsInfo[].class);
                    if (monitoringLogger.isDebugEnabled()) {
                        monitoringLogger.debug("Retrieved instances from Rest template: Sessions Info (count: " + sessionsInfo.length + ")");
                    }
                    writeConsole("sessions info: ");

                    for (SessionsInfo sessionsInfo1 : sessionsInfo) {
                        writeConsole(sessionsInfo1.getSessions_info());
                    }
                }

                this.time++;
            }

            private boolean isActiveMonitoring(TypeMonitoring typeMonitoring) {
                return filter.isSelect(typeMonitoring) && this.time % typeMonitoring.getRefreshPeriod() == 0;
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void pause() {
        timeline.pause();
        monitoringLogger.info("Monitoring paused.");
    }

    public void close() {
        app.close();
        monitoringLogger.info("Application closed.");
    }

    private void writeConsole(String text) {
        this.console.appendText(text + "\n\n");
    }

}
