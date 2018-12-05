import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EOEvent implements EOCSVInterface, EOGUIMultiSelectInterface {

	private EOEventType[] eventtypes;
   private FacilitatorContactInfo[] facilitators;
	private LocalDateTime datetimestart;
	private LocalDateTime datetimeend;
	private double price;
	private String description;
   private int id;

   public EOEvent(int id, String description, LocalDateTime datetimestart, LocalDateTime datetimeend, double price, FacilitatorContactInfo[] facilitators, EOEventType[] eventtypes)
   {
      this.id = id;
      this.description = description;
      this.datetimestart = datetimestart;
      this.datetimeend = datetimeend;
      this.price = price;
      this.facilitators = facilitators;
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
   
   public FacilitatorContactInfo[] getFacilitators()
   {
      return(facilitators);
   }
   
   public String getDisplayName()
   {
      String str = "";
      EOEventType[] e = getEventTypes();
      if(e != null)
      {
         for(int i = 0; i < e.length; i++)
         {
            if(i > 0)
            {
               str += ", " + e[i].getName();
            }
            else
            {
               str += e[i].getName();
            }
         }
         System.out.println("EVENTTYPES: " + str);
      }
      else
      {
         System.out.println("EVENTTYPES: NULL " + str);
      }
      str += " (" + getDateTimeStart().format(DateTimeFormatter.ofPattern("d/M k:mm")) + "-" + getDateTimeEnd().format(DateTimeFormatter.ofPattern("d/M k:mm")) + ")";
      return(str);
   }   
   
   public int getId()
   {
      return(id);
   }
   
   public String exportCSV()
   {
      String str = 
         EOCSV.formatField("EOEvent") + ", " + 
         EOCSV.formatField(id) + ", " + 
         EOCSV.formatField(description) + ", " + 
         EOCSV.formatField(getDateTimeStart()) + ", " +
         EOCSV.formatField(getDateTimeEnd()) + ", " +
         EOCSV.formatField(getPrice()) + "\n";
      FacilitatorContactInfo[] f = getFacilitators();
      if(f != null)
      {
         for(int i = 0; i < f.length; i++)
         {
            str += f[i].exportCSV();
         }
      }
      EOEventType[] e = getEventTypes();
      if(e != null)
      {
         for(int i = 0; i < e.length; i++)
         {
            str += e[i].exportCSV();
         }
      }
      return(str);
   }
   
   /**
   * The equals metode only works on object from the database, where an ID has been set.
   */
   public boolean equals(Object obj)
   {
      boolean returnvar = false;
      if(obj instanceof EOEvent)
      {
         returnvar = (((EOEvent)obj).getId() == this.getId() && this.getId() != -1);
      }
      return(returnvar || super.equals(obj));
   }   

}