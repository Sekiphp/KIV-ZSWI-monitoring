import org.codehaus.jackson.annotate.JsonProperty;


public class Memory {

	@JsonProperty("mem_total") private long mem_total;
	@JsonProperty("mem_free") private long mem_free;
	
	public long getMem_total() {
		return mem_total;
	}
	public void setMem_total(long mem_total) {
		this.mem_total = mem_total;
	}
	public long getMem_free() {
		return mem_free;
	}
	public void setMem_free(long mem_free) {
		this.mem_free = mem_free;
	}
	
}
