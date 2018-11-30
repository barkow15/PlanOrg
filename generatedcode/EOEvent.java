import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
      String str = "";
      EOEventType[] e = getEventTypes();
      if(e != null)
      {
         for(int i = 0; i < e.length; i++)
         {
            if(i > 0)
            {
               str += ", " + e[i].exportCSV();
            }
            else
            {
               str += e[i].exportCSV();
            }
         }
      }
      str += " (" + getDateTimeStart().format(DateTimeFormatter.ofPattern("w/M y k:mm")) + "-" + getDateTimeEnd().format(DateTimeFormatter.ofPattern("w/M y k:mm")) + ")";
      return(str);
   }   
   
   public int getId()
   {
      return(id);
   }
   
   public String exportCSV()
   {
      String str = 
         "EOEvent; " + 
         Integer.toString(id) + "; " + 
         description + "; " + 
         getDateTimeStart().format(DateTimeFormatter.ofPattern("w/M y k:mm")) + "; " +
         getDateTimeEnd().format(DateTimeFormatter.ofPattern("w/M y k:mm")) + "; " +
         Double.toString(getPrice()) + "\n";
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

}