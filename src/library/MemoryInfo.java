package library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

public class MemoryInfo {

    /**
     * logger sluzby MemoryInfo
     */
    private static final Logger memoryInfoLogger = LogManager.getLogger();
    
    public static int refreshTimePeriod = Monitoring.DEFAULT_REFRESH_TIME;
    

    @JsonProperty("mem_total")
    private long mem_total;
    @JsonProperty("mem_free")
    private long mem_free;
    private String memory_info;

    public String getMemory_info() {
        memory_info
                = "\n\tmem_total: " + mem_total
                + "\n\tmem_free: " + mem_free + "\n";

        memoryInfoLogger.info("Getting memory_info.");
        return memory_info;
    }

    public void setMemory_info(String memory_info) {
        memoryInfoLogger.info("Setting memory_info.");
        this.memory_info = memory_info;
    }

    public long getMem_total() {
        memoryInfoLogger.info("Getting mem_total: " + mem_total);
        return mem_total;
    }

    public void setMem_total(long mem_total) {
        memoryInfoLogger.info("Setting mem_total: " + mem_total);
        this.mem_total = mem_total;
    }

    public long getMem_free() {
        memoryInfoLogger.info("Getting mem_free: " + mem_free);
        return mem_free;
    }

    public void setMem_free(long mem_free) {
        memoryInfoLogger.info("Setting mem_free: " + mem_free);
        this.mem_free = mem_free;
    }
}
