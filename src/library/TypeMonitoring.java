package library;

import java.util.Arrays;

/**
 * Enum of server services/instances.
 */
public enum TypeMonitoring {
    SYSTEM_LOAD("system_load", 7, SystemLoad.class),
    INSTANCE_ID("instance_id", 7, InstanceId.class),
    SESSIONS_COUNT("sessions_count", 7, SessionsCount.class),
    SESSIONS_INFO("sessions_info", 7, SessionsInfo.class),
    MEMORY_INFO("memory_info", 7, MemoryInfo.class);

    /**
	*	Name of server service.
	*/
    private final String name;
    
    /**
	*	Time period of server service refreshing.
	*/
    private int refreshPeriod;
    
    private Class typeClass;
    
    /**
	*	Consturctor.
	*/
    private TypeMonitoring(final String name, final int refreshPeriod, Class typeClass) {
        this.name = name;
        this.refreshPeriod=refreshPeriod;
        this.typeClass = typeClass;
    }

	/**
	*	Getter of name of server service.
	*/
    public String getName(){
        return this.name;
    }
    
    /**
	*	Getter of refresh period of time of server service.
	*/
    public int getRefreshPeriod(){
        return this.refreshPeriod;
    }
    
    /**
	*	Getter of class according to type.
	*/
    public Class getTypeClass(){
        return this.typeClass;
    }
    
    /**
	*	Setter of refresh period of time of server service.
	*/
    public void setRefreshPeriod(int refreshPeriod){
        this.refreshPeriod=refreshPeriod;
    }
    
    /**
	*	Getter of server services array.
	*/
    public static String[] toArray() {
        String text = Arrays.toString(values());
        return text.substring(1, text.length() - 1).split(", ");
    }
    
    /**
	*	Getter of service by type.
	*/
    public static TypeMonitoring getTypeBy(String str) {
    	for(TypeMonitoring t : TypeMonitoring.values()) {
    		if(str.equals(t.name())) return t;
    	}
    	return null;
    }
}