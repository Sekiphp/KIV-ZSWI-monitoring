package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Represents MemoryInfo service of PF.
 */
public class MemoryInfo {
    /**
     * logger sluzby MemoryInfo
     */
    private final Logger memoryInfoLogger = LogManager.getLogger("memoryInfo");

    /**
     * Server response.
     */
    @JsonProperty("mem_total")
    private long mem_total;
    
    /**
     * Server response.
     */
    @JsonProperty("mem_free")
    private long mem_free;
    
    /**
     * Server response.
     */
    private String memory_info;

    /**
     * Getter of server response.
     */
    public String getMemory_info() {
        memory_info
                = "\n\tmem_total: " + mem_total
                + "\n\tmem_free: " + mem_free + "\n";

        if (memoryInfoLogger.isInfoEnabled()) memoryInfoLogger.info("Getting " + TypeMonitoring.MEMORY_INFO.getName());
        return memory_info;
    }

    /**
     * Setter of server response.
     */
    public void setMemory_info(String memory_info) {
        this.memory_info = memory_info;
    }
    
    /**
     * Getter of server response.
     */
    public long getMem_total() {
    	if (memoryInfoLogger.isInfoEnabled()) memoryInfoLogger.info("Getting mem_total: " + mem_total);
        return mem_total;
    }
    
    /**
     * Setter of server response.
     */
    public void setMem_total(long mem_total) {
        this.mem_total = mem_total;
    }
    
    /**
     * Getter of server response.
     */
    public long getMem_free() {
    	if (memoryInfoLogger.isInfoEnabled()) memoryInfoLogger.info("Getting mem_free: " + mem_free);
        return mem_free;
    }
    
    /**
     * Setter of server response.
     */
    public void setMem_free(long mem_free) {
        this.mem_free = mem_free;
    }
}