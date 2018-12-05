import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EOEventType implements EOCSVInterface, EOGUIMultiSelectInterface {

    private int     id;
	private String  name;
	private String  description;
	private String  locationstart;
	private String  locationend;
	private int     time;
	private ExternalContactInfo externalcontactinfo;
	private double price;


   public EOEventType(int id, String name, String description, String locationstart, String locationend, int time, double price, ExternalContactInfo externalcontactinfo)
   {
      this.id = id;
      this.name = name;
      this.description = description;
      this.locationstart = locationstart;
      this.locationend = locationend;
      this.time = time;
      this.price = price;
      this.externalcontactinfo = externalcontactinfo;
   }
   
	public String getLocationStart() {
		return(locationstart);
	}

	public String getLocationEnd() {
		return(locationend);
	}

	public int getTime() {
		return this.time;
	}

	public String getName() {
		return this.name;
	}

	public ExternalContactInfo getExternalContactInfo() {
		return(externalcontactinfo);
	}
   
	public String getDescription() {
		return this.description;
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

   public String exportCSV(){
      String str =
         EOCSV.formatField("EOEventType") + ", " +
         EOCSV.formatField(id) + ", " + 
         EOCSV.formatField(name) + ", " + 
         EOCSV.formatField(description) + ", " +
         EOCSV.formatField(locationstart) + ", " +
         EOCSV.formatField(locationend) + ", " +
         EOCSV.formatField(time) + ", " +
         EOCSV.formatField(price) + "\n";
         if(getExternalContactInfo() != null)
         {
            str += getExternalContactInfo().exportCSV();
         }
      return(str);
   }
   
   /**
   * The equals metode only works on object from the database, where an ID has been set.
   */
   public boolean equals(Object obj)
   {
      boolean returnvar = false;
      if(obj instanceof EOEventType)
      {
         returnvar = (((EOEventType)obj).getId() == this.getId() && this.getId() != -1);
      }
      return(returnvar || super.equals(obj));
   }   

}