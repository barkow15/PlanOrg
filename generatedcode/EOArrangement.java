/**
An implementation of an arrangement.
*/


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
   
   /**
   Returns the events that are part of the arrangement.
   */
	public EOEvent[] getEvents() {
		return this.events;
	}

   /**
   Returns if the arrangement has been payed or not.
   */
   public boolean isPayed()
   {
      return(ispayed);
   }

   /**
   Returns if the arrangement has been done or not.
   */
   public boolean isDone()
   {
      return(isdone);
   }

   /**
   Returns the customer contact info.
   */
	public CustomerContactInfo getCustomer() {
		return this.customer;
	}

   /**
   Returns the facilitators have responsibility for the arrangement.
   */
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

   /**
   Returns the arrangements database id, this is a unique identifier for the arrangement
   */
   public int getId()
   {
      return(id);
   }

   /**
   Returns the arrangements as one or more CSV lines.
   First returning the EOArragement on a line, and then subsequensiable lines are the objects the EOArrangement is containing.
   in the format (in this example there are 2 facilitaros and 3 events assigned to the arrangement): 
   Line1: EOArrangement, id, name, description, datetimestart, datetimeend, price, ispayed, isdone
   Line2: CustomerContactInfo, id, name, phone, email, info, company
   Line3: FacilitatorContactInfo, id, name, phone, email, info
   Line4: FacilitatorContactInfo, id, name, phone, email, info   
   Line4: EOEvent, events
   */
   public String exportCSV()
   {
      return("");
   }
}