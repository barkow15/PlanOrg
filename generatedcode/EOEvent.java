import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
EOEvents is one or more events that are linked to EOArrangement, an EOEvent cannot exist if its not linked to and EOArrangement.

An EOEvent 
*/

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

   public void setEventTypes(EOEventType[] eventtypes)
   {
      this.eventtypes = eventtypes;
   }

   public LocalDateTime getDateTimeStart() {
      return(datetimestart);
   }
   
   public void setDateTimeStart(LocalDateTime datetimestart)
   {
      this.datetimestart = datetimestart;
   }

   public LocalDateTime getDateTimeEnd() {
      return(datetimeend);
   }

   public void setDateTimeEnd(LocalDateTime datetimeend)
   {
      this.datetimeend = datetimeend;
   }

   public double getPrice() {
      return this.price;
   }
   
   public void setPrice(double price)
   {
      this.price = price;
   }

   public String getDescription() {
      return this.description;
   }
   
   public void setDescription(String description)
   {
      this.description = description;
   }
   
   public FacilitatorContactInfo[] getFacilitators()
   {
      return(facilitators);
   }
   
   public void setFacilitators(FacilitatorContactInfo[] facilitators)
   {
      this.facilitators = facilitators;
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

   public String exportCSV(boolean includeprice)
   {
      System.out.println("EOEvent.exportCSV()");
      String str = 
         EOCSV.formatField("EOEvent") + ", " + 
         EOCSV.formatField(id) + ", " + 
         EOCSV.formatField(description) + ", " + 
         EOCSV.formatField(getDateTimeStart()) + ", " +
         EOCSV.formatField(getDateTimeEnd()) + ", ";
      if(includeprice)
      {
         str += EOCSV.formatField(getPrice()) + "\n"; 
      }
      else
      {
         str += "0\n";
      }
      /*
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
      */
      return(str);
   }
   
   public String exportCSV()
   {
      return(exportCSV(true));
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