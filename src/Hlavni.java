import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class Hlavni {
	
	/**
	 * Main logger.
	 */
	private static Logger mainLogger = LogManager.getLogger();

	public static void main(String[] args) {	
		ApplicationContext app = new ClassPathXmlApplicationContext("/application-context.xml");
		
		/* UKAZKA BEAN */
		/*HelloBean bean = (HelloBean) app.getBean("hello");	//bean id
		System.out.println(bean.getGreeting());*/
		
		
		PeerFileMonitor pf = (PeerFileMonitor) app.getBean("peerFileMonitor");
		
		RestTemplate restTemplate = pf.getRestTemplate();
		UrlFactory fac = pf.getUrlFactory();
		
		SystemLoad systemLoad = restTemplate.getForObject(fac.getSystemLoad(), SystemLoad.class);
		System.out.println("systemLoad:    " + systemLoad.getSystem_load());
		
		InstanceId instanceId = restTemplate.getForObject(fac.getInstanceId(), InstanceId.class);
		System.out.println("instance ID:    " + instanceId.getInstance_id());
		
		SessionsCount sessionsCount = restTemplate.getForObject(fac.getSessionsCount(), SessionsCount.class);
		System.out.println("sessions count:    " + sessionsCount.getSessions_count());
		
		/* doprogramovat!!!!
    	"http://peerfile.eu:3000/api/mon/sessions_info", 
    	"http://peerfile.eu:3000/api/mon/memory_info",
    	*/  
		
	}
}