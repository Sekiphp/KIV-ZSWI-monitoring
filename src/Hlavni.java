import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Hlavni {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("/application-context.xml");
		HelloBean bean = (HelloBean) app.getBean("hello");	//bean id
		System.out.println(bean.getGreeting());
	}
	
}