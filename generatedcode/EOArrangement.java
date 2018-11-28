import java.time.LocalDateTime;

public class EOArrangement implements EOCSVInterface, EOGUIMultiSelectInterface {

   private int id;
	private String name;
	private String description;
	private LocalDateTime datetimestart;
	private LocalDateTime datetimeend;
	private double price;

   private boolean ispayed;
   private boolean isdone;
	private FacilitatorContactInfo[] facilitators;
	private EOEvent[] events;
	private CustomerContactInfo customer;
   
   public EOArrangement(int id, String name, String description, LocalDateTime datetimestart, LocalDateTime datetimeend, double price, boolean ispayed, boolean isdone, FacilitatorContactInfo[] facilitators, EOEvent[] events, CustomerContactInfo customer)
   {
      this.id = id;
   	this.name = name;
   	this.description = description;
   	this.datetimestart = datetimestart;
   	this.datetimeend = datetimeend;
   	this.price = price;
   
      this.ispayed = ispayed;
      this.isdone = isdone;
   	this.facilitators = facilitators;
   	this.events = events;
   	this.customer = customer;
   }
   
	public EOEvent[] getEvents() {
		return this.events;
	}

   public boolean isPayed()
   {
      return(ispayed);
   }

   public boolean isDone()
   {
      return(isdone);
   }

	public CustomerContactInfo getCustomer() {
		return this.customer;
	}

	public FacilitatorContactInfo[] getFacilitators() {
		return this.facilitators;
	}

	public String getName() {
		return(name);
   }

	public String getDescription() {
      return(description);
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
   
   public String getDisplayName()
   {
      return(this.name);
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