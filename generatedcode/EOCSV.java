import java.time.LocalDateTime;
import java.io.File;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.FileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
This class supports 3 different export modes
1) All data from the database is exported
2) All data that specific facilitators are exported. Its possible to select if arrangements that have already been done are exported or not. And if the price should be hidden or not.
3) All data from specific arrangements are exported.
*/

public class EOCSV
{
   File outfile;
   EODatabaseInterface db = null;
   int exportmode = 0;
   
   EOArrangement[] arrangements;
   
   FacilitatorContactInfo[] facilitators;
   boolean showprice;
   boolean showdone;


   public EOCSV(File outfile)
   {
      exportmode = 1;   
      this.outfile = outfile;
   }

   public EOCSV(FacilitatorContactInfo[] facilitators, boolean showprice, boolean showdone, File outfile)
   {
      exportmode = 2;      
      this.facilitators = facilitators;
      this.showprice = showprice;
      this.showdone = showdone;
      this.outfile = outfile;
   }

   public EOCSV(EOArrangement[] arrangements, File outfile)
   {
      exportmode = 3;   
      this.arrangements = arrangements;
      this.outfile = outfile;
   }
   
   public void setDB(EODatabaseInterface db)
   {
      this.db = db;
   }

   public void createCSV() throws Exception
   {
      //This is where the magic happens
      try
      {
         Writer output = null;
         output = new BufferedWriter(new FileWriter(outfile));
         if(true) //db check
         {
            if(exportmode == 1)
            {
               output.write("Export mode = " + Integer.toString(exportmode));
            }
            else if(exportmode == 2)
            {
               output.write("Export mode = " + Integer.toString(exportmode));
            }
            else if(exportmode == 3)
            {
               output.write("Export mode = " + Integer.toString(exportmode));
            }
         }


         output.close();
      }
      catch(Exception e)
      {
         System.out.println("Could not create file: " + e.getMessage());
      }      
   }
   
   /**
  *<pre>
  *This will format the field, according to https://gpdb.docs.pivotal.io/43230/admin_guide/load/topics/g-escaping-in-csv-formatted-files.html
  *
  *
  *Free trip to A,B
  *5.89
  *Special rate "1.79"
  *
  *"Free trip to A,B","5.89","Special rate ""1.79"""
  * </pre>
   */
   public static String formatField(String field)
   {
      return("\"" + field.replace("\"", "\"\"") + "\"");
   }

   public static String formatField(int field)
   {
      return("\"" + Integer.toString(field) + "\"");
   }
   
   public static String formatField(double field)
   {
      return("\"" + Double.toString(field) + "\"");
   }

   public static String formatField(LocalDateTime field)
   {
      return("\"" + field.format(DateTimeFormatter.ofPattern("w/M y k:mm")) + "\"");
   }         
 
   public static String formatField(boolean field)
   {
      return("\"" + Boolean.toString(field) + "\"");
   }           
}