import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
An implementation of an arrangement. An arrangement, contains events and times for when these are happening.
*/

public class EOArrangement implements EOCSVInterface, EOGUIMultiSelectInterface {

   private int id;
	private String name;
	private String description;
	private LocalDateTime datetimestart = null;
	private LocalDateTime datetimeend = null;
	private double price;

	private boolean ispayed;
	private boolean isdone;
	private FacilitatorContactInfo[] facilitators = null;
	private EOEvent[] events = null;
	private CustomerContactInfo customer = null;
   
   public EOArrangement()
   {
      //Empty EOArrangement, is used when an arrangement is created.
      //100 kr is the default value for an arrangement
      this(-1, "", "", null, null, 100, false, false, null, null, null);
   }
   
   public EOArrangement(int id, String name, String description, LocalDateTime datetimestart, LocalDateTime datetimeend, double price, boolean ispayed, boolean isdone, FacilitatorContactInfo[] facilitators, EOEvent[] events, CustomerContactInfo customer)
   {
      this.id             = id;
   	this.name           = name;
   	this.description    = description;
   	this.datetimestart  = datetimestart;
   	this.datetimeend    = datetimeend;
   	this.price          = price;
      this.ispayed        = ispayed;
      this.isdone         = isdone;
   	this.facilitators   = facilitators;
   	this.events         = events;
   	this.customer       = customer;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public void setDescription(String description)
   {
      this.description = description;
   }
   
   public void setDateTimeStart(LocalDateTime datetimestart)
   {
      this.datetimestart = datetimestart;
   }

   public void setDateTimeEnd(LocalDateTime datetimeend)
   {
      this.datetimeend = datetimeend;
   }
   
   public void setPrice(double price)
   {
      this.price = price;
   }
   
   public void isPayed(boolean ispayed)
   {
      this.ispayed = ispayed;
   }

   public void isDone(boolean isdone)
   {
      this.isdone = isdone;
   }
   
   public void setFacilitators(FacilitatorContactInfo[] facilitators)
   {
      this.facilitators = facilitators;
   }
   
   public void setEvents(EOEvent[] events)
   {
      this.events = events;
   }
   
   public void setCustomer(CustomerContactInfo customer)
   {
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

   /**
   Returns the name of the arrangement.
   */
	public String getName() {
		return(name);
   }

   /**
   Returns the description for the arrangement.
   */
	public String getDescription() {
      return(description);
	}

   /**
   Returns when the arrangement is starting.
   */
	public LocalDateTime getDateTimeStart() {
      return(datetimestart);
	}

   /**
   Returns when the arrangement is ending.
   */
	public LocalDateTime getDateTimeEnd() {
      return(datetimeend);
	}

   /**
   Returns the price of the arrrangement.
   */
	public double getPrice() {
		return this.price;
	}

   /**
   Returns the name of the arrangement.
   */   
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
   * <pre>
   * Returns the arrangement as one or more CSV lines.
   * First returning the EOArragement on a line, and then subsequensiable lines are the objects the EOArrangement is containing.
   *
   * In this example there are 2 facilitaros and 1 event that contain 2 eventtypes assigned to the arrangement:
   * Line1: EOArrangement, id, name, description, datetimestart, datetimeend, price, ispayed, isdone
   * Line2: CustomerContactInfo, id, name, phone, email, info, company
   * Line3: FacilitatorContactInfo, id, name, phone, email, info
   * Line4: FacilitatorContactInfo, id, name, phone, email, info
   * Line5: EOEvent, id, description, datetimestart, datetimeend, price
   * Line6: EOEventType, id, name, description, locationstart, locationend, time, price
   * Line7: ExternalContactInfo, id, name, phone, email, info, company
   * Line8: EOEventType, id, name, description, locationstart, locationend, time, price
   * Line9: ExternalContactInfo, id, name, phone, email, info, company
   *
   * Other objects might also return multiple lines.
   * </pre>
   */
   public String exportCSV()
   {
      String str = 
         EOCSV.formatField("EOArrangement") + ",  " + 
         EOCSV.formatField(getId()) + ", " + 
         EOCSV.formatField(getName()) + ", " + 
         EOCSV.formatField(getDescription()) + ", " + 
         EOCSV.formatField(getDateTimeStart()) + ", " +
         EOCSV.formatField(getDateTimeEnd()) + ", " +
         EOCSV.formatField(getPrice()) + ", " + 
         EOCSV.formatField(isPayed()) + ", " + 
         EOCSV.formatField(isDone()) + "\n";
          
         str += getCustomer().exportCSV();
         FacilitatorContactInfo[] f = getFacilitators();
         if(f != null)
         {
            for(int i = 0; i < f.length; i++)
            {
               str += f[i].exportCSV();
            }
         }
         EOEvent[] e = getEvents();
         if(e != null)
         {
            for(int i = 0; i < e.length; i++)
            {
               str += e[i].exportCSV();
            }
         }
      
      return("");
   }
   
   /**
   * The equals metode only works on object from the database, where an ID has been set.
   */
   public boolean equals(Object obj)
   {
      boolean returnvar = false;
      if(obj instanceof EOArrangement)
      {
         returnvar = (((EOArrangement)obj).getId() == this.getId() && this.getId() != -1);
      }
      return(returnvar || super.equals(obj));
   }
   
   public void addEvent(EOEvent event)
   {
      if(this.events == null)
      {
         System.out.println("-------------------------------Ingen andre begivenheder");
         this.events = new EOEvent[1];
         events[0] = event;
      }
      else
      {
         System.out.println("-------------------------------tilføjer begivenheder");      
         EOEvent[] events = new EOEvent[this.events.length+1];
         System.out.println("-------------------------------tilføjer begivenheder " + Integer.toString(this.events.length+1));         
         for(int i = 0; i < this.events.length; i++)
         {
            events[i] = this.events[i];
         }
         System.out.println("-------------------------------tilføjer begivenheder " + Integer.toString(this.events.length)); 
         events[this.events.length] = event;
         System.out.println("-------------------------------tilføjer begivenheder " + Integer.toString(events.length)); 
         this.events = events;
      }
   }
   
   public boolean deleteEvent(EOEvent event)
   {
      boolean found = false;
      EOEvent[] events = new EOEvent[this.events.length-1];
      for(int i = 0; i < this.events.length; i++)
      {
         if(events[i].equals(event))
         {
            found = true;
         }
         else
         {
            events[i] = this.events[i];
         }
      }
      if(found && this.events.length-1 == 0)
      {
         events = null;
      }
      return(found);
   }
}