import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;



/**
 * Main class of application
 * 
 * @author hubacek
 * @version 0.1 alfa
 */
public class Main {

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

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		System.out.println(Config.appName);
		
		loadUrl();           
	}
}
