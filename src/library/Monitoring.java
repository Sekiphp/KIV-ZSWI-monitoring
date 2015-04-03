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
public class Monitoring {

    /**
     * logger monitoringu
     * 
     * Pozn. - regularni vyrazy pro doplneni testu isDebugEnabled:
	 * Find:			([a-zA-Z]*Logger)(\.debug)
	 * Replace with:	if \($1\.isDebugEnabled\(\)\)\r\n\t\t\t$1$2
     */
    private static final Logger monitoringLogger = LogManager.getLogger();
    
    private final ClassPathXmlApplicationContext app;
    private final TextArea console;
    private final FilterManager filter;
    private final PeerFileMonitor pf;
    private final RestTemplate restTemplate ;
    private final UrlFactory fac;

    private boolean run = false;
    
    public Monitoring(TextArea console) {
        this.console = console;

        monitoringLogger.info("Application started.");

        this.filter = new FilterManager();
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("Filter manager created.");

        this.app = new ClassPathXmlApplicationContext("./application-context.xml");
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("Application context loaded.");
        
        this.pf = (PeerFileMonitor) app.getBean("peerFileMonitor");
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("PeerFile monitor created.");

        this.restTemplate = pf.getRestTemplate();
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("Rest template created.");

        this.fac = pf.getUrlFactory();
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("Retrieved URL factory.");
    }

    public FilterManager getFilter() {
        return filter;
    }

    public boolean isRun(){
        run = !run;
        return !run;
    } 
    
    public void start() {

        writeConsole("Loading...\n");
        SystemLoad systemLoad = restTemplate.getForObject(fac.getSystemLoad(), SystemLoad.class);
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("Retrieved instance from Rest template: System Load");
        writeConsole("systemLoad: " + systemLoad.getSystem_load());

        InstanceId instanceId = restTemplate.getForObject(fac.getInstanceId(), InstanceId.class);
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("Retrieved instance from Rest template: Instance ID");
        writeConsole("instance ID: " + instanceId.getInstance_id());

        SessionsCount sessionsCount = restTemplate.getForObject(fac.getSessionsCount(), SessionsCount.class);
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("Retrieved instance from Rest template: Sessions Count");
        writeConsole("sessions count: " + sessionsCount.getSessions_count());

        MemoryInfo memoryInfo = restTemplate.getForObject(fac.getMemoryInfo(), MemoryInfo.class);
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("Retrieved instance from Rest template: Memory Info");
        writeConsole("memory info: " + memoryInfo.getMemory_info());

        SessionsInfo[] sessionsInfo = restTemplate.getForObject(fac.getSessionsInfo(), SessionsInfo[].class);
        writeConsole("sessions info: ");

        for (int i = 0; i < sessionsInfo.length; i++) {
            writeConsole(sessionsInfo[i].getSessions_info());
        }
        if (monitoringLogger.isDebugEnabled())
			monitoringLogger.debug("Retrieved instances from Rest template: Sessions Info");
      
        this.writeConsole("");

    }

    public void pause(){
    	monitoringLogger.info("Application paused.");
    }
    
    public void  close(){
        app.close();
        monitoringLogger.info("Application finished.");
    }


    private void writeConsole(String text) {
        this.console.appendText(text+"\n");
    }

}
