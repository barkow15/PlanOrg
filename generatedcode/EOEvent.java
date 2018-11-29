import java.time.LocalDateTime;

public class EOEvent implements EOCSVInterface, EOGUIMultiSelectInterface {

	private EOEventType[] eventtypes;
	private LocalDateTime datetimestart;
	private LocalDateTime datetimeend;
	private double price;
	private String description;

	public EOEventType[] getEventTypes() {
		// TODO - implement EOEvent.getEventTypes
		throw new UnsupportedOperationException();
	}

	public LocalDateTime getDateTimeStart() {
		// TODO - implement EOEvent.getDateTimeStart
		throw new UnsupportedOperationException();
	}

	public LocalDateTime getDateTimeEnd() {
		// TODO - implement EOEvent.getDateTimeEnd
		throw new UnsupportedOperationException();
	}

	public double getPrice() {
		return this.price;
	}

	public String getDescription() {
		return this.description;
	}
   
   public String getDisplayName()
   {
      return(datetimestart.toString() + "-" + datetimeend.toString());
   }   
   
   public String exportCSV()
   {
      return("");
   }

}