import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
EOEventType can be linked to one or more EOEvents.

Contains information about an event, but with out the specifics about when the event is going to happen etc. One way to think about EventTypes is that they are generic events that can be used over and over.
*/
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

   public String exportCSV(boolean includeprice){
      String str =
         EOCSV.formatField("EOEventType") + ", " +
         EOCSV.formatField(getId()) + ", " + 
         EOCSV.formatField(getName()) + ", " + 
         EOCSV.formatField(getDescription()) + ", " +
         EOCSV.formatField(getLocationStart()) + ", " +
         EOCSV.formatField(getLocationEnd()) + ", " +
         EOCSV.formatField(getTime()) + ", ";
      if(includeprice)
      {
         str += EOCSV.formatField(getPrice()) + "\n"; 
      }
      else
      {
         str += "0\n";
      }
      /*
      if(getExternalContactInfo() != null)
      {
         str += getExternalContactInfo().exportCSV();
      }
      */
      return(str);
   }

   public String exportCSV(){
      System.out.println("EOEventType.exportCSV()");
      return(exportCSV(true));
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

   @Override      
   public int hashCode()
   {
      return(getId()+1500000001);
   }   
}