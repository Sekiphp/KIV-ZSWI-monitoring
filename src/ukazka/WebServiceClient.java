package ukazka;
import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

//import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Webservice client template. 
 *
 */
public class WebServiceClient {
	
    private static final String MESSAGE = "<message xmlns=\"http://tempuri.org\">Hello World</message>";
    //private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

//    /**
//     * Sets custom default URL.
//     * @param defaultUri custom url
//     */
//    public void setDefaultUri(String defaultUri) {
//        webServiceTemplate.setDefaultUri(defaultUri);
//    }
//
//    /**
//     * Send to the configured default URI.
//     */
//    public void simpleSendAndReceive() {
//        StreamSource source = new StreamSource(new StringReader(MESSAGE));
//        StreamResult result = new StreamResult(System.out);
//        webServiceTemplate.sendSourceAndReceiveToResult(source, result);
//    }
//
//    /**
//     * Send to an explicit URI.
//     */
//    public void customSendAndReceive() {
//        StreamSource source = new StreamSource(new StringReader(MESSAGE));
//        StreamResult result = new StreamResult(System.out);
//        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8080/AnotherWebService",
//            source, result);
//    }
}