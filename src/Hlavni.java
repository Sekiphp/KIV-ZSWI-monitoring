import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class Hlavni {
	
	/*
	 * nastaveni konfigurace pro logovani
	 */
	static {
		System.setProperty("log4j.configurationFile",
				"log4j_config.xml");
	}
	
	/**
	 * Hlavni logger
	 */
	private static Logger mainLogger = LogManager.getLogger();

	public static void main(String[] args) {	
		mainLogger.info("Application started.");
		
		ApplicationContext app = new ClassPathXmlApplicationContext("/application-context.xml");
		mainLogger.debug("Application context loaded.");
		
		PeerFileMonitor pf = (PeerFileMonitor) app.getBean("peerFileMonitor");
		mainLogger.debug("PeerFile monitor created.");
		
		RestTemplate restTemplate = pf.getRestTemplate();
		mainLogger.debug("Rest template created.");
		
		UrlFactory fac = pf.getUrlFactory();
		mainLogger.debug("Retrieved URL factory.");
		
		SystemLoad systemLoad = restTemplate.getForObject(fac.getSystemLoad(), SystemLoad.class);
		mainLogger.debug("Retrieved instance from Rest template: System Load");
		System.out.println("systemLoad:    " + systemLoad.getSystem_load());
		
		InstanceId instanceId = restTemplate.getForObject(fac.getInstanceId(), InstanceId.class);
		mainLogger.debug("Retrieved instance from Rest template: Instance ID");
		System.out.println("instance ID:    " + instanceId.getInstance_id());
		
		SessionsCount sessionsCount = restTemplate.getForObject(fac.getSessionsCount(), SessionsCount.class);
		mainLogger.debug("Retrieved instance from Rest template: Sessions Count");
		System.out.println("sessions count:    " + sessionsCount.getSessions_count());
		
		MemoryInfo memoryInfo = restTemplate.getForObject(fac.getMemoryInfo(), MemoryInfo.class);
		mainLogger.debug("Retrieved instance from Rest template: Memory Info");
		System.out.println("memory info:    " + memoryInfo.getMemory_info());
		
		
		Sessions[] sessionsInfo = restTemplate.getForObject(fac.getSessionsInfo(), Sessions[].class);
		mainLogger.debug("Retrieved instance from Rest template: Sessions Info");
		System.out.println("sessions info:    " + sessionsInfo[0].getSession_code());
		
		mainLogger.info("Application finished.");
		
		/* doprogramovat!!!!
    	"http://peerfile.eu:3000/api/mon/sessions_info", 
    	*/  
	}
}