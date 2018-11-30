import java.time.LocalDateTime;

public class EOEventType implements EOCSVInterface, EOGUIMultiSelectInterface {

   private int id;
	private String name;
	private String description;   
	private String locationstart;
	private String locationend;
	private int time;

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
   
   public String exportCSV(){
      return("");
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

}