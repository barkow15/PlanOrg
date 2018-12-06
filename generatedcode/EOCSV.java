import java.time.LocalDateTime;
import java.io.File;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.FileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;


/**
This class supports 3 different export modes
1) All data from the database is exported
2) All data that specific facilitator are part off (by being part of an arrangement or an event).
3) All data from specific arrangements are exported.

And 1 import mode
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

   //Export or import
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
   
   
   /**
   Starts the import of data.
   
   The EOCSV Object must have been initially called with the File that contains the data and the database must have been set.
   */
   public boolean importCSV()
   {
      //We truncate the database so its clean
      if(db == null)
      {
         return(false);
      }
      db.truncateDB();
      
      //WE know that the line is "value", "value", "value"\n
      //So before the " it must be either nothing or empty space,
      //The end " must be followed by either , or \n
      String s = " \"test string 67\", \", \"\"bbb\"\"aaa\", \"68\"";
      Pattern p = Pattern.compile("(^|\\s)((\"){1}(.*?)(\"){1})(,|$)");
      //Matcher m = p.matcher(s);
      String line;
      Matcher m;
      String value;
      try
      {
         Scanner scanner = new Scanner(outfile);
         if(scanner.hasNextLine())
         {
            line = scanner.nextLine();
            m = p.matcher(line);
            if(m.find())
            {
               value = cleanString(m.group().subSequence(1, m.group().length()-1).toString());
               if(!value.equals("EOEXPORT"))
               {
                  System.out.println("Not an EOCSV file: " + value);
                  return(false);
               }
            }
         }
         System.out.println("Starting import..");
         while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            m = p.matcher(line);
            //We know that there are never more than 10 parameters to any object..
            //This is an ugly approach but it should work =)
            String[] data = new String[10];
            for(int i = 0; i < 10; i++)
            {
               data[i] = null;
            }
            int i = 0;
            while(m.find())
            {
               value = removeFormat(cleanString(m.group().subSequence(1, m.group().length()-1).toString()));
               data[i] = value;
               i++;
            }
            data = trimArray(data);
            csvInsert(data);
         }
         scanner.close();
      } catch (Exception e) 
      {
         e.printStackTrace();
      }  
      return(true);
   }

   private String cleanString(String str)
   {
      if(str != null && str.length() > 0)
      {
         System.out.println("1");
         if(str.charAt(str.length()-1) == '"')
         {
            str = str.substring(0, str.length()-1);
         }
         System.out.println("2");
         if(str.length() > 0 && str.charAt(0) == '"')
         {
            str = str.substring(1);
         }
         System.out.println("3");
      }
      return(str);
   }
   
   private String[] trimArray(String[] array)
   {
      if(array == null || array.length == 0)
      {
         return(array);
      }
      int i = 0;
      for(; array[i] != null && i < 10; i++)
      {
      }
      String[] s = new String[i+1];
      for(int j = 0; j < s.length; j++)
      {
         s[j] = array[j];
      }
      return(s);
   }
   
   /**
   * From a string array, we create an Object.
   * We select the Object from the first element in the String array. The String array contains only simple types, objects that are part of the object created are null.
   */
   private boolean csvInsert(String[] strarray)
   {
      if(strarray == null || strarray.length == 0)
      {
         return(false);
      }
      String key = strarray[0];
      switch(key)
      {
         case "EOArrangement":
            System.out.println("Insert: EOArrangement");
            new EOArrangement(Integer.parseInt(strarray[1]), strarray[2], strarray[3], getLocalDateTime(strarray[4]), getLocalDateTime(strarray[5]), Double.parseDouble(strarray[6]), Boolean.parseBoolean(strarray[7]), Boolean.parseBoolean(strarray[8]), null, null, null);
            break;
         case "EOEvent":
            System.out.println("Insert: EOEvent");
            new EOEvent(Integer.parseInt(strarray[1]), strarray[2], getLocalDateTime(strarray[3]), getLocalDateTime(strarray[4]), Double.parseDouble(strarray[5]), null, null);
            break;
         case "EOEventType":
            System.out.println("Insert: EOEventType");
            return db.createEOEvenType(new EOEventType(Integer.parseInt(strarray[1]), strarray[2], strarray[3], strarray[4], strarray[5], Integer.parseInt(strarray[6]), Double.parseDouble(strarray[7]), null));
         case "FacilitatorContactInfo":
            System.out.println("Insert: FacilitatorContactInfo");
            return db.createFacilitatorContactInfo(new FacilitatorContactInfo(Integer.parseInt(strarray[1]), strarray[2], strarray[3], strarray[4], strarray[5]));
         case "CustomerContactInfo":
            System.out.println("Insert: CustomerContactInfo");
            return db.createCustomerContactInfo(new CustomerContactInfo(Integer.parseInt(strarray[1]), strarray[2], strarray[3], strarray[4], strarray[5], strarray[6]));
         case "ExternalContactInfo":
            System.out.println("Insert: ExternalContactInfo");
            db.createExternalContactInfo(new ExternalContactInfo(Integer.parseInt(strarray[1]), strarray[2], strarray[3], strarray[4], strarray[5], strarray[6]));
            return(true);
         case "Link":
            System.out.println("Insert: Link");
            new Link(strarray[1], strarray[2], Integer.parseInt(strarray[3]), strarray[4], Integer.parseInt(strarray[5]));
            break;
         default:
            System.out.println("Unknown key: " + key);
            return(false);
      }
      return(true);
   }

   /**
   * Creates a CSV file, this database must have been set before you call this metode.
   * The file that is created, is on the location of the file thats passe to EOCASV when the object was initialized.
   */
   public void createCSV() throws Exception
   {
      //This is where the magic happens
      Writer output = null;
      try
      {
         output = new BufferedWriter(new FileWriter(outfile));
         if(db != null) //db check
         {
            //This line we add to all files, so we can check if its a EOEXPORT file, and then when the file is generated
            output.write("\"EOEXPORT\", \"" + LocalDateTime.now().toString() + "\", \"mode="+Integer.toString(exportmode)+"\"\n");
            if(exportmode == 1)
            {
               LinkedHashSet<EOCSVInterface> list = new LinkedHashSet<EOCSVInterface>();
               //Get all EOArrangements
               //getEOArrangement();
               getEOCSVList(db.getEOArrangements(true), null, list);
               //Get all EOEvents
               //getEOEvent();
               getEOCSVList(db.getEOEvents(), null, list);
               //Get all EOEventTypes
               getEOCSVList(db.getEOEventTypes(), null, list);
               //Get all getAllCustomerContactInfo
               getEOCSVList(db.getAllCustomerContactInfo(), null, list);
               //Get all FacilitatorContactInfo
               getEOCSVList(db.getAllFacilitatorContactInfo(), null, list);
               //Get all ExternalContactInfo
               getEOCSVList(db.getAllExternalContactInfo(), null, list);
               Iterator itr = list.iterator();
               System.out.println("while");
               while(itr.hasNext())
               {
                  
                  System.out.println("start");
                  Object obj = itr.next();
                  if(obj instanceof EOCSVInterface)
                  {
                     System.out.println("------");
                     System.out.println("object = " + obj.getClass());
                     output.write( ((EOCSVInterface)obj).exportCSV() );
                  }
                  else
                  {
                     System.out.println("--???????????????????????????????????----");
                  }
               
                  System.out.println("end");
               }
            }
            else if(exportmode == 2)
            {
               LinkedHashSet<EOCSVInterface> list = new LinkedHashSet<EOCSVInterface>();
               EOArrangement[] arrangement2 = db.getAllEOArrangementsFromFacilitator(facilitators[0]);
               System.out.println("getAllEOArrangementsFromFacilitator");
               getEOCSVList(arrangement2 , null, list);
               //Lets print out our list
               System.out.println("iterator");
               Iterator<EOCSVInterface> itr = list.iterator();
               System.out.println("while");
               while(itr.hasNext())
               {
                  System.out.println("start");
                  output.write( itr.next().exportCSV() );
                  System.out.println("end");
               }
            }
            else if(exportmode == 3)
            {
               LinkedHashSet<EOCSVInterface> list = new LinkedHashSet<EOCSVInterface>();
               getEOCSVList(this.arrangements, null, list);
               //Lets print out our list
               Iterator<EOCSVInterface> itr = list.iterator();
               while(itr.hasNext())
               {
                  output.write( itr.next().exportCSV() );
               }
            }
         }
      }
      catch(Exception e)
      {
         System.out.println("Could not create file: " + e.getMessage());
      } finally { 
         if(output != null) output.close(); } 
   }

   /**
   * Adds an Object that implements EOCSVInterface to a LinkedHashSet.
   * If the Object contains other objects, these are also added and the links to the Object are added through the Link object (which is also added to the LinkedHashSet) 
   */
   private void getEOCSVList(EOCSVInterface object, EOCSVInterface parent, LinkedHashSet<EOCSVInterface> list)
   {
      System.out.println("getEOCSVList(EOCSVInterface");   
      if(object == null)
      {
      
      }      
      else if(object instanceof EOArrangement)
      {
         if(!list.contains(object))
         {
            list.add(object);
         }
         getEOCSVList(((EOArrangement)object).getEvents(), object, list);
         getEOCSVList(((EOArrangement)object).getFacilitators(), object, list); 
         getEOCSVList(((EOArrangement)object).getCustomer(), object, list); 
      }
      else if(object instanceof EOEvent)
      {
         if(!list.contains(object))
         {
            list.add(object);
         }
         if(parent != null)
         {
            list.add(new Link("EOArrangements_has_EOEvents", "EOArrangements_idEOArrangements", ((EOArrangement)parent).getId(), "EOEvents_idEOEvents", ((EOEvent)object).getId()));
         }
         getEOCSVList(((EOEvent)object).getFacilitators(), object, list);
         getEOCSVList(((EOEvent)object).getEventTypes(), object, list);         
      }
      else if(object instanceof EOEventType)
      {
         if(!list.contains(object))
         {
            list.add(object);
         }
         if(parent != null)
         {
            list.add(new Link("EOEvents_has_EOEventtypes", "EOEvents_idEOEvents", ((EOEvent)parent).getId(), "EOEventtypes_idEOEventtypes", ((EOEventType)object).getId()));
         }
         getEOCSVList(((EOEventType)object).getExternalContactInfo(), object, list);
      }
      else
      {
         //We know its a contact then
         if(object instanceof ExternalContactInfo)
         {
            if(!list.contains(object))
            {
               list.add(object);
            } 
            if(parent != null)
            {
               list.add(new Link("EOEventtypes_has_EOExternalContactInfo", "EOEventtypes_idEOEventtypes", ((EOEventType)parent).getId(), "EOExternalContactInfo_idEOExternalContactInfo", ((ExternalContactInfo)object).getId()));
            }
         }
         else if(object instanceof CustomerContactInfo)
         {
            if(!list.contains(object))
            {
               list.add(object);
            } 
            if(parent != null)
            {
               list.add(new Link("EOArrangements_has_EOContactInfo", "EOArrangements_idEOArrangements", ((EOArrangement)parent).getId(), "EOCustomerContactInfo_idCustomerContactInfo", ((CustomerContactInfo)object).getId()));
            }
         }
         else if(object instanceof FacilitatorContactInfo)
         {
            if(!list.contains(object))
            {
               list.add(object);
            } 
            if(parent != null)
            {
               if(parent instanceof EOArrangement)
               {
                  list.add(new Link("EOArrangements_has_EOFacilitatorContactInfo", "EOArrangements_idEOArrangements", ((EOArrangement)parent).getId(), "EOFacilitatorContactInfo_idFacilitatorContactInfo", ((FacilitatorContactInfo)object).getId()));
               }
               else
               {
                  list.add(new Link("EOEvents_has_EOFacilitatorContactInfo", "EOEvents_idEOEvents", ((EOEvent)parent).getId(), "EOFacilitatorContactInfo_idEOFacilitatorContactInfo", ((FacilitatorContactInfo)object).getId()));               
               }
            }
         }        
      }
   }

   /**
   Splits an array into single objects, and adds, their links and sub objects to the LinkedHashSet.
   */
   private void getEOCSVList(EOCSVInterface[] objects, EOCSVInterface parent, LinkedHashSet<EOCSVInterface> list)
   {
      System.out.println("getEOCSVList(EOCSVInterface[]");
      if(objects != null)
      {
         for(int i = 0; i < objects.length; i++)
         {
            getEOCSVList(objects[i], parent, list);
         }
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
      if(field == null)
      {
         return("\"\"");
      }
      return("\"" + field.replace("\"", "\"\"") + "\"");
   }

   /**
   Formats the integer to EOCSV field format.
   */
   public static String formatField(int field)
   {  
      return("\"" + Integer.toString(field) + "\"");
   }

   /**
   Formats the double to EOCSV field format.
   */      
   public static String formatField(double field)
   {
      return("\"" + Double.toString(field) + "\"");
   }

   /**
   Formats the LocalDateTime to EOCSV field format.
   */         
   public static String formatField(LocalDateTime field)
   {
      if(field == null)
      {
         return("\"\"");
      }   
      return("\"" + field.format(DateTimeFormatter.ofPattern("w/M y k:mm")) + "\"");
   }         

   /**
   Formats the boolean to EOCSV field format.
   */      
   public static String formatField(boolean field)
   {  
      return("\"" + Boolean.toString(field) + "\"");
   }
   
   /**
   Converts EOCSV DateTime field format to LocalDateTime.
   */      
   private static LocalDateTime getLocalDateTime(String str)
   {
      return(LocalDateTime.parse(str, (DateTimeFormatter.ofPattern("w/M y k:mm"))));  
   }
   
   private static String removeFormat(String field)
   {
      return(field.replace("\"\"", "\""));
   }
  
}

