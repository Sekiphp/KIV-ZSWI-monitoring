/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.Arrays;

/**
 *
 * @author Kohl
 */
public enum TypeMonitoring {

    SYSTEM_LOAD("system_load",7),
    INSTANCE_ID("instance_id",7),
    SESSIONS_COUNT("sessions_count",7),
    SESSIONS_INFO("sessions_info",7),
    MEMORY_INFO("memory_info",7);

    private final String value;
    private int refreshPeriod;
    
    private TypeMonitoring(final String value, final int refreshPeriod) {
        this.value = value;
        this.refreshPeriod=refreshPeriod;
    }

    public String getValue(){
        return this.value;
    }
    
    public int getRefreshPeriod(){
        return this.refreshPeriod;
    }
    
    public void setRefreshPeriod(int refreshPeriod){
        this.refreshPeriod=refreshPeriod;
    }
    
    public static String[] toArray() {
        String text = Arrays.toString(values());
        return text.substring(1, text.length() - 1).split(", ");
    }
    
    public static TypeMonitoring getTypeBy(String str) {
    	for(TypeMonitoring t : TypeMonitoring.values()) {
    		if(str.equals(t.name())) return t;
    	}
    	return null;
    }
}
