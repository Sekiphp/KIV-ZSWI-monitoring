package stare;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kohl
 */
public class Config {
    
    public static final String appName = "Monitoring";
    
    public static String sourceArray[] = {
    	"http://peerfile.eu:3000/api/mon/instance_id", 
    	"http://peerfile.eu:3000/api/mon/system_load", 
    	"http://peerfile.eu:3000/api/mon/sessions_count", 
    	"http://peerfile.eu:3000/api/mon/sessions_info", 
    	"http://peerfile.eu:3000/api/mon/memory_info",  
    	""
    };
}
