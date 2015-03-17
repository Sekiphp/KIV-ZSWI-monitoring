package ukazka;
public class HelloBean {

	private String name;
		
	public HelloBean(String name) {
		this.name = name;
	}

	public String getGreeting(){
		return "hello, " + this.name;
	}
}
