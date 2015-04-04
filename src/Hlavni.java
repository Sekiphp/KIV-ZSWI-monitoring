import library.InstanceId;
import library.MemoryInfo;
import library.PeerFileMonitor;
import library.SessionsCount;
import library.SessionsInfo;
import library.SystemLoad;
import library.UrlFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class Hlavni {
	
	/*
	 * nastaveni konfigurace pro logovani
	 */
	static {
		System.setProperty("log4j.configurationFile",
				"log4j-config.xml");
	}
	
	/** 
	 * hlavni logger 
	 * */
	private static final Logger mainLogger = LogManager.getLogger();

	public static void main(String[] args) {	
		mainLogger.info("Application started.");
		
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("/application-context.xml");
		System.out.println();
		if (mainLogger.isDebugEnabled())
			mainLogger.debug("Application context loaded.");
		
		PeerFileMonitor pf = (PeerFileMonitor) app.getBean("peerFileMonitor");
		if (mainLogger.isDebugEnabled())
			mainLogger.debug("PeerFile monitor created.");
		
		RestTemplate restTemplate = pf.getRestTemplate();
		if (mainLogger.isDebugEnabled())
			mainLogger.debug("Rest template created.");
		
		UrlFactory fac = pf.getUrlFactory();
		if (mainLogger.isDebugEnabled())
			mainLogger.debug("Retrieved URL factory.");
		
		SystemLoad systemLoad = restTemplate.getForObject(fac.getSystemLoad(), SystemLoad.class);
		if (mainLogger.isDebugEnabled())
			mainLogger.debug("Retrieved instance from Rest template: System Load");
		System.out.println("systemLoad:    " + systemLoad.getSystem_load());
		
		InstanceId instanceId = restTemplate.getForObject(fac.getInstanceId(), InstanceId.class);
		if (mainLogger.isDebugEnabled())
			mainLogger.debug("Retrieved instance from Rest template: Instance ID");
		System.out.println("instance ID:    " + instanceId.getInstance_id());
		
		SessionsCount sessionsCount = restTemplate.getForObject(fac.getSessionsCount(), SessionsCount.class);
		if (mainLogger.isDebugEnabled())
			mainLogger.debug("Retrieved instance from Rest template: Sessions Count");
		System.out.println("sessions count:    " + sessionsCount.getSessions_count());
		
		MemoryInfo memoryInfo = restTemplate.getForObject(fac.getMemoryInfo(), MemoryInfo.class);
		if (mainLogger.isDebugEnabled())
			mainLogger.debug("Retrieved instance from Rest template: Memory Info");
		System.out.println("memory info:    " + memoryInfo.getMemory_info());

		SessionsInfo[] sessionsInfo = restTemplate.getForObject(fac.getSessionsInfo(), SessionsInfo[].class);
		if (mainLogger.isDebugEnabled())
			mainLogger.debug("Retrieved instances from Rest template: Sessions Info (count: " + sessionsInfo.length + ")");
		System.out.println("sessions info:");
		
		for(int i = 0; i<sessionsInfo.length;i++){
			System.out.println(sessionsInfo[i].getSessions_info());
		}

		System.out.println();
		app.close();
		mainLogger.info("Application finished.");

	}
}