package stare;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class of application
 * 
 * @author hubacek
 * @version 0.1 alfa
 */
public class Main {
	
	static {
		/*nastaveni XML konfigurace vypisu log4j*/
		System.setProperty("log4j.configurationFile", "log4j_config.xml");
	}
	
	/**
	 * Main logger.
	 */
	private static Logger mainLogger = LogManager.getLogger();
	
	/**
	 * Reads data from url, which are specified in Config
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private static void loadUrl() throws UnsupportedEncodingException, IOException{
		URL url;
		BufferedReader reader;
		
		for(int i = 0; i < Config.sourceArray.length; i++){
			// testovani na prazdne pole
			if(Config.sourceArray[i].equals(""))
				continue;
			
			url = new URL(Config.sourceArray[i]);
			System.out.println(Config.sourceArray[i]);
			
			
			// samotne cteni
			reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		    for (String line; (line = reader.readLine()) != null;) {
		        System.out.println(line);
		        System.out.println(" --- ");
		    }
			    
		    reader.close();
		}		
	}

	/**
	 * Main method of app.
	 * @param args	input args
	 */
	public static void main(String[] args) {
		mainLogger.info("App starts.");
		System.out.println(Config.appName);
		
		try {
			loadUrl();
		} catch (IOException e) {
			mainLogger.error("IO Exception druring getting response from url.");
			e.printStackTrace();
		}
		
		mainLogger.info("App ends.");
	}
}