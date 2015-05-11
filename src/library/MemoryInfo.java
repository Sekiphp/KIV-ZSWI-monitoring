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
    private static final Logger memoryInfoLogger = LogManager.getLogger();

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

        memoryInfoLogger.info("Getting memory_info.");
        return memory_info;
    }

    /**
     * Setter of server response.
     */
    public void setMemory_info(String memory_info) {
        memoryInfoLogger.info("Setting memory_info.");
        this.memory_info = memory_info;
    }
    
    /**
     * Getter of server response.
     */
    public long getMem_total() {
        memoryInfoLogger.info("Getting mem_total: " + mem_total);
        return mem_total;
    }
    
    /**
     * Setter of server response.
     */
    public void setMem_total(long mem_total) {
        memoryInfoLogger.info("Setting mem_total: " + mem_total);
        this.mem_total = mem_total;
    }
    
    /**
     * Getter of server response.
     */
    public long getMem_free() {
        memoryInfoLogger.info("Getting mem_free: " + mem_free);
        return mem_free;
    }
    
    /**
     * Setter of server response.
     */
    public void setMem_free(long mem_free) {
        memoryInfoLogger.info("Setting mem_free: " + mem_free);
        this.mem_free = mem_free;
    }
}