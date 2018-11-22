import java.time.LocalDateTime;

public class EOEventType implements EOExport {

	private String locationstart;
	private String locationend;
	private int time;
	private String name;
	private ExternalContactInfo externalcontactinfo;
	private String description;
	private double price;

	public String getLocationStart() {
		// TODO - implement EOEventType.getLocationStart
		throw new UnsupportedOperationException();
	}

	public String getLocationEnd() {
		// TODO - implement EOEventType.getLocationEnd
		throw new UnsupportedOperationException();
	}

	public int getTime() {
		return this.time;
	}

	public String getName() {
		return this.name;
	}

	public ExternalContactInfo getExternalContactInfo() {
		// TODO - implement EOEventType.getExternalContactInfo
		throw new UnsupportedOperationException();
	}
   
   public String exportCSV(){ return(""); }

	public String getDescription() {
		return this.description;
	}

	public double getPrice() {
		return this.price;
	}

}