import java.time.LocalDateTime;

public class EOEvent implements EOCSVInterface, EOGUIMultiSelectInterface {

	private EOEventType[] eventtypes;
	private LocalDateTime datetimestart;
	private LocalDateTime datetimeend;
	private double price;
	private String description;
   private int id;

   public EOEvent(int id, String description, LocalDateTime datetimestart, LocalDateTime datetimeend, double price, EOEventType[] eventtypes)
   {
      this.id = id;
      this.description = description;
      this.datetimestart = datetimestart;
      this.datetimeend = datetimeend;
      this.price = price;
      this.eventtypes = eventtypes;
   }
   
	public EOEventType[] getEventTypes() {
		return(eventtypes);
	}

	public LocalDateTime getDateTimeStart() {
		return(datetimestart);
	}

	public LocalDateTime getDateTimeEnd() {
		return(datetimeend);
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
   
   public int getId()
   {
      return(id);
   }
   
   public String exportCSV()
   {
      return("");
   }

}