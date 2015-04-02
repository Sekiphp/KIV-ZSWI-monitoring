package library;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.control.TextArea;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author Kohl
 */
public class Monitoring{

    static {
        System.setProperty("log4j.configurationFile", "log4j_config.xml");
    }

    /**
     * hlavni logger
     */
    private final Logger mainLogger;
    private final ClassPathXmlApplicationContext app;
    private final TextArea console;
    private final FilterManager filter;
    private final PeerFileMonitor pf;
    private final RestTemplate restTemplate ;
    private final UrlFactory fac;

    private Boolean run=false;
    
    public Monitoring(TextArea console) {
        this.console = console;

        this.mainLogger = LogManager.getLogger();
        this.mainLogger.info("Application started.");

        this.filter = new FilterManager();

        this.app = new ClassPathXmlApplicationContext("./application-context.xml");
        this.mainLogger.debug("Application context loaded.");
        
        this.pf = (PeerFileMonitor) app.getBean("peerFileMonitor");
        this.mainLogger.debug("PeerFile monitor created.");

        this.restTemplate= pf.getRestTemplate();
        this.mainLogger.debug("Rest template created.");

        this.fac = pf.getUrlFactory();
        this.mainLogger.debug("Retrieved URL factory.");
    }

    public FilterManager getFilter() {
        return filter;
    }

    public boolean isRun(){
        run=!run;
        return !run;
    } 
    
    public void start() {

        writeConsole("Loading...\n");
        SystemLoad systemLoad = restTemplate.getForObject(fac.getSystemLoad(), SystemLoad.class);
        mainLogger.debug("Retrieved instance from Rest template: System Load");
        writeConsole("systemLoad: " + systemLoad.getSystem_load());

        InstanceId instanceId = restTemplate.getForObject(fac.getInstanceId(), InstanceId.class);
        mainLogger.debug("Retrieved instance from Rest template: Instance ID");
        writeConsole("instance ID: " + instanceId.getInstance_id());

        SessionsCount sessionsCount = restTemplate.getForObject(fac.getSessionsCount(), SessionsCount.class);
        mainLogger.debug("Retrieved instance from Rest template: Sessions Count");
        writeConsole("sessions count: " + sessionsCount.getSessions_count());

        MemoryInfo memoryInfo = restTemplate.getForObject(fac.getMemoryInfo(), MemoryInfo.class);
        mainLogger.debug("Retrieved instance from Rest template: Memory Info");
        writeConsole("memory info: " + memoryInfo.getMemory_info());

        SessionsInfo[] sessionsInfo = restTemplate.getForObject(fac.getSessionsInfo(), SessionsInfo[].class);
        writeConsole("sessions info: ");

        for (int i = 0; i < sessionsInfo.length; i++) {
            writeConsole(sessionsInfo[i].getSessions_info());
        }
        mainLogger.debug("Retrieved instances from Rest template: Sessions Info");
      
        this.writeConsole("");

    }

    public void pause(){
    
    }
    
    public void  close(){
        app.close();
        mainLogger.info("Application finished.");
    }


    private void writeConsole(String text) {
        this.console.appendText(text+"\n");
    }

}
