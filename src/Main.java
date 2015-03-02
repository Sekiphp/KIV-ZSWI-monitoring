import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;



public class Main {
	
	private static void loadUrl() throws UnsupportedEncodingException, IOException{
		URL url;
		//BufferedReader br;
		
		
		url = new URL("http://peerfile.eu:3000/api/mon/memory_info");

		try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    for (String line; (line = br.readLine()) != null;) {
		        System.out.println(line);
		    }
		}
		catch(Exception e){
			System.out.println("spadlo ti to máchale, asi vítr");
		}		
	}

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		System.out.println(Config.appName);
		
		loadUrl();
	
		/*
		URL url = new URL("http://peerfile.eu:3000/api/mon/memory_info");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    for (String line; (line = reader.readLine()) != null;) {
		        System.out.println(line);
		    }
		}
		catch(Exception e){
			System.out.println("spadlo ti to máchale, asi vítr");
		}*/
            
	}
}
