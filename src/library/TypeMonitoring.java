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

    SYSTEM_LOAD("system_load"),
    INSTANCE_ID("instance_id"),
    SESSIONS_COUNT("sessions_count"),
    SESSIONS_INFO("sessions_info"),
    MEMORY_INFO("memory_info");

    private final String value;

    private TypeMonitoring(final String value) {
        this.value = value;
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
