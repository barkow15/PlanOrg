import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
The EODatabaseInterface handles all connections to the database.

A connection to the database is only active, while the query to the dabase is executed. This means that each query call is realatively slow, but the upside is that multiple users can use the database at the same time.
*/
public class EODatabaseInterface {
   Connection conn = null;
   private boolean debug = true;
   String dbPathAbsolute = "jdbc:sqlite:/Users/philipbarkow/Library/Mobile Documents/com~apple~CloudDocs/Datamatiker/1. semester/PlanOrg/generatedcode/finaldb.db";
   String dbPathRelative = "jdbc:sqlite:finaldb.db";

   public void test()
   {
      System.out.println("DB method \"Test\" running...");
      ResultSet rs = querySql("SELECT * FROM EOCustomerContactInfo LIMIT 100");
      //ResultSet rs = querySql("INSERT INTO 'EOCustomerContactInfo' ('name', 'deletedStatus') VALUES ('Test', 2)");
   
      try
      {
         while(rs.next())
         {
            System.out.println("id: " +  rs.getInt("idEOContactInfo") + " Deleted: " + rs.getString("deletedStatus") + " Name: " + rs.getString("name"));
         }
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }      
   }
   
   public void truncateDB()
   {
      String[] tables = new String[12];
      tables[0] = "EOArrangements";
      tables[1] = "EOArrangements_has_EOContactInfo";
      tables[2] = "EOArrangements_has_EOEvents";
      tables[3] = "EOArrangements_has_EOFacilitatorContactInfo";
      tables[4] = "EOCustomerContactInfo";
      tables[5] = "EOEvents";
      tables[6] = "EOEvents_has_EOEventtypes";
      tables[7] = "EOEvents_has_EOFacilitatorContactInfo";
      tables[8] = "EOEventtypes";
      tables[9] = "EOEventtypes_has_EOExternalContactInfo";
      tables[10] = "EOExternalContactInfo";
      tables[11] = "EOFacilitatorContactInfo";
      for(int i = 0; i < tables.length; i++)
      {
         executeSql("DELETE FROM " + tables[i]);
      }
   }
   
   public boolean createLink(Link c)
   {
      String sql = "INSERT INTO " + c.getTable() + " ("+c.getIdentifier1() + ", " + c.getIdentifier2() + ") VALUES (" + Integer.toString(c.getValue1()) + ", " + Integer.toString(c.getValue2()) + ")";
      return(executeSql(sql) == 1);
   }
   
   public boolean createLink(String table, String identifier1, int value1, String identifier2, int value2)
   {
      return(createLink(new Link(table, identifier1, value1, identifier2, value2)));
   }
   
   public EOArrangement[] getAllEOArrangementsFromFacilitator(FacilitatorContactInfo facilitator)
   {
      if(facilitator == null)
      {
      }
      int size = 0;
      EOArrangement[] a1 = getEOArrangementsFromEventsFacilitator(facilitator);
      if(a1 != null)
      {
         size = a1.length;
      }    
      EOArrangement[] a2 = getEOArrangementsFromFacilitator(facilitator);
      if(a2 != null)
      {
         size += a2.length;
      }
      EOArrangement[] a3 = new EOArrangement[size];
      int i = 0;
      if(a1 != null)
      {
         for(i = 0; i < a1.length; i++)
         {
            a3[i] = a1[i];
         } 
      }
      if(a2 != null)
      {
         for(int j = 0; j < a2.length; j++)
         {
            a3[i] = a2[j];
            i++;
         }
      }
      return(a3);
   }
   
   public EOArrangement[] getEOArrangementsFromEventsFacilitator(FacilitatorContactInfo facilitator)
   {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT EOArrangements.* FROM EOEvents_has_EOFacilitatorContactInfo, EOEvents, EOArrangements_has_EOEvents, EOArrangements WHERE "+
            "EOEvents_has_EOFacilitatorContactInfo.EOFacilitatorContactInfo_idEOFacilitatorContactInfo = " + Integer.toString(facilitator.getId()) + " AND " +
            "EOEvents_has_EOFacilitatorContactInfo.EOFacilitatorContactInfo_idEOFacilitatorContactInfo = EOEvents.idEOEvents AND " +
            "EOEvents.idEOEvents = EOArrangements_has_EOEvents.EOEvents_idEOEvents AND " +
            "EOArrangements_has_EOEvents.EOArrangements_idEOArrangements = EOArrangements.idEOArrangements";
      String sqlcount = "SELECT count(*) FROM EOArrangements_has_EOEvents, EOEvents_has_EOFacilitatorContactInfo, EOEvents, EOArrangements WHERE "+
            "EOEvents_has_EOFacilitatorContactInfo.EOFacilitatorContactInfo_idEOFacilitatorContactInfo = " + Integer.toString(facilitator.getId()) + " AND " +
            "EOEvents_has_EOFacilitatorContactInfo.EOFacilitatorContactInfo_idEOFacilitatorContactInfo = EOEvents.idEOEvents AND " +
            "EOEvents.idEOEvents = EOArrangements_has_EOEvents.EOEvents_idEOEvents AND " +
            "EOArrangements_has_EOEvents.EOArrangements_idEOArrangements = EOArrangements.idEOArrangements";
      int rows = numRows(sqlcount);
      if(rows == 0)
      {
         return(null);
      }
      System.out.println(rows);
      rs = querySql(sql);
      EOArrangement[] arrangements = new EOArrangement[rows];
      for(int i = 0; i < rows; i++)
      {
         arrangements[i] = null;
      }
      
      try
      {
         
         for(int i = 0; rs.next(); i++)
         {
               arrangements[i] = new EOArrangement(
               rs.getInt("idEOArrangements"), 
               rs.getString("name"), 
               rs.getString("description"), 
               sqliteDateTimeConvert(rs.getInt("datetimestart")), 
               sqliteDateTimeConvert(rs.getInt("datetimestart")), 
               rs.getDouble("price"), 
               rs.getBoolean("ispayed"), 
               rs.getBoolean("isdone"), 
               getFacilitatorContactInfoFromEOArrangement(rs.getInt("idEOArrangements")), 
               getEOEvents(rs.getInt("idEOArrangements")), 
               getCustomerContactInfoFromEOArrangement(rs.getInt("idEOArrangements"))
            );
         }
      }
      catch(Exception e)
      {
         
         System.err.println("getEOArrangementsFromEventsFacilitator: " +  e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(arrangements);
   }
   
   public EOArrangement[] getEOArrangementsFromFacilitator(FacilitatorContactInfo facilitator)
   {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT EOArrangements.* FROM EOArrangements_has_EOFacilitatorContactInfo, EOArrangements WHERE EOArrangements_has_EOFacilitatorContactInfo.EOFacilitatorContactInfo_idFacilitatorContactInfo = " + Integer.toString(facilitator.getId()) + " AND EOArrangements_has_EOFacilitatorContactInfo.EOArrangements_idEOArrangements = EOArrangements.idEOArrangements";
      int rows = numRows("SELECT count(*) FROM EOArrangements_has_EOFacilitatorContactInfo, EOArrangements WHERE EOArrangements_has_EOFacilitatorContactInfo.EOFacilitatorContactInfo_idFacilitatorContactInfo = " + Integer.toString(facilitator.getId()) + " AND EOArrangements_has_EOFacilitatorContactInfo.EOArrangements_idEOArrangements = EOArrangements.idEOArrangements");
      System.out.println("DATA");
      if(rows == 0)
      {
         return(null);
      }
      System.out.println("DATA");
      rs = querySql(sql);
      EOArrangement[] arrangements = new EOArrangement[rows];
      System.out.println("DATA");
      for(int i = 0; i < rows; i++)
      {
         arrangements[i] = null;
      }
      System.out.println("DATA");
      try
      {
         
         for(int i = 0; rs.next(); i++)
         {
               arrangements[i] = new EOArrangement(
               rs.getInt("idEOArrangements"), 
               rs.getString("name"), 
               rs.getString("description"), 
               sqliteDateTimeConvert(rs.getInt("datetimestart")), 
               sqliteDateTimeConvert(rs.getInt("datetimestart")), 
               rs.getDouble("price"), 
               rs.getBoolean("ispayed"), 
               rs.getBoolean("isdone"), 
               getFacilitatorContactInfoFromEOArrangement(rs.getInt("idEOArrangements")), 
               getEOEvents(rs.getInt("idEOArrangements")), 
               getCustomerContactInfoFromEOArrangement(rs.getInt("idEOArrangements"))
            );
         }
      }
      catch(Exception e)
      {
         
         System.err.println("getEOArrangementsFromFacilitator: " +  e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(arrangements);
   }

	/**
	 * 
	 * @param includeIsDone
	 *
	 */

   public EOArrangement[] getEOArrangements(boolean includeIsDone) {
   	System.out.println("DB method \"Test\" running...");
      
      ResultSet rs = null;
      String sql = null;
      int rows = 0;
      if(includeIsDone)
      {   
         sql = "SELECT * FROM EOArrangements";
         rows = numRows("SELECT count(*) FROM EOArrangements");
      }
      else
      {
         sql = "SELECT * FROM EOArrangements WHERE isdone = '" + Boolean.toString(includeIsDone) + "'";
         rows = numRows("SELECT count(*) FROM EOArrangements WHERE isdone = '" + Boolean.toString(includeIsDone) + "'");
      }


      if(rows == 0)
      {
         return(null);
      }
      rs = querySql(sql);
      EOArrangement[] arrangements = new EOArrangement[rows];
      for(int i = 0; i < rows; i++)
      {
         arrangements[i] = null;
      }
      
      try
      {
         
         for(int i = 0; rs.next(); i++)
         {
               arrangements[i] = new EOArrangement(
               rs.getInt("idEOArrangements"), 
               rs.getString("name"), 
               rs.getString("description"), 
               sqliteDateTimeConvert(rs.getInt("datetimestart")), 
               sqliteDateTimeConvert(rs.getInt("datetimestart")), 
               rs.getDouble("price"), 
               rs.getBoolean("ispayed"), 
               rs.getBoolean("isdone"), 
               getFacilitatorContactInfoFromEOArrangement(rs.getInt("idEOArrangements")), 
               getEOEvents(rs.getInt("idEOArrangements")), 
               getCustomerContactInfoFromEOArrangement(rs.getInt("idEOArrangements"))
            );
         }
      }
      catch(Exception e)
      {
         
         System.err.println("getEOArrangements: " +  e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(arrangements);
   }

   private LocalDateTime sqliteDateTimeConvert(int datetime)
   {
      return(LocalDateTime.ofEpochSecond(datetime, 0, java.time.ZoneOffset.UTC));
   }
   
   public FacilitatorContactInfo[] getFacilitatorContactInfoFromEOArrangement(int arrangementid)
   {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT EOFacilitatorContactInfo.idEOContactInfo, "+ 
             "EOFacilitatorContactInfo.name, " +
             "EOFacilitatorContactInfo.phone, " +
             "EOFacilitatorContactInfo.email, " +
             "EOFacilitatorContactInfo.info " +
             " FROM EOArrangements_has_EOFacilitatorContactInfo, EOFacilitatorContactInfo WHERE EOArrangements_idEOArrangements = " + Integer.toString(arrangementid) + " AND EOFacilitatorContactInfo_idFacilitatorContactInfo = EOFacilitatorContactInfo.idEOContactInfo";
      System.out.println(sql);
      int rows = numRows("SELECT count(*) FROM EOArrangements_has_EOFacilitatorContactInfo, EOFacilitatorContactInfo WHERE EOArrangements_idEOArrangements = " + Integer.toString(arrangementid) + " AND EOFacilitatorContactInfo_idFacilitatorContactInfo = EOFacilitatorContactInfo.idEOContactInfo");
      if(rows == 0)
      {
         return(null);
      }
      rs = querySql(sql);
      FacilitatorContactInfo[] facilitators = new FacilitatorContactInfo[rows];
      for(int i = 0; i < rows; i++)
      {
         facilitators[i] = null;
      }   

      try
      {
         for(int i = 0; rs.next(); i++)
         {        
               facilitators[i] = new FacilitatorContactInfo(
               rs.getInt("idEOContactInfo"), 
               rs.getString("name"), 
               rs.getString("phone"), 
               rs.getString("email"), 
               rs.getString("info")
            );
         }
      }
      catch(Exception e)
      {
         System.err.println("getFacilitatorContactInfoFromEOArrangement: " +  e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(facilitators); 
   }
     
   public FacilitatorContactInfo[] getFacilitatorContactInfoFromEOEvent(int eventid)
   {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT EOFacilitatorContactInfo.idEOContactInfo, "+ 
             "EOFacilitatorContactInfo.name, " +
             "EOFacilitatorContactInfo.phone, " +
             "EOFacilitatorContactInfo.email, " +
             "EOFacilitatorContactInfo.info " +
             " FROM EOEvents_has_EOFacilitatorContactInfo, EOFacilitatorContactInfo WHERE EOEvents_has_EOFacilitatorContactInfo.EOEvents_idEOEvents = " + Integer.toString(eventid) + " AND EOEvents_has_EOFacilitatorContactInfo.EOFacilitatorContactInfo_idEOFacilitatorContactInfo = EOFacilitatorContactInfo.idEOContactInfo";
      System.out.println(sql);
      int rows = numRows("SELECT count(*) FROM EOEvents_has_EOFacilitatorContactInfo, EOFacilitatorContactInfo WHERE EOEvents_has_EOFacilitatorContactInfo.EOEvents_idEOEvents = " + Integer.toString(eventid) + " AND EOEvents_has_EOFacilitatorContactInfo.EOFacilitatorContactInfo_idEOFacilitatorContactInfo = EOFacilitatorContactInfo.idEOContactInfo");
      if(rows == 0)
      {
         return(null);
      }
      rs = querySql(sql);
      FacilitatorContactInfo[] facilitators = new FacilitatorContactInfo[rows];
      for(int i = 0; i < rows; i++)
      {
         facilitators[i] = null;
      }   

      try
      {
         for(int i = 0; rs.next(); i++)
         {        
            facilitators[i] = new FacilitatorContactInfo(
               rs.getInt("idEOContactInfo"), 
               rs.getString("name"), 
               rs.getString("phone"), 
               rs.getString("email"), 
               rs.getString("info")
               );
         }
      }
      catch(Exception e)
      {
         System.err.println("getFacilitatorContactInfoFromEOEvent: " +  e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }      
      System.out.println("Returning data");
      return(facilitators); 
   }
     
   public ExternalContactInfo getExternalContactInfoFromEOEventType(int eventtypeid)
   {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT EOExternalContactInfo.idEOContactInfo, "+ 
             "EOExternalContactInfo.name, " +
             "EOExternalContactInfo.phone, " +
             "EOExternalContactInfo.email, " +
             "EOExternalContactInfo.info, " +
             "EOExternalContactInfo.company " +             
             " FROM EOEventtypes_has_EOExternalContactInfo, EOExternalContactInfo WHERE EOEventtypes_has_EOExternalContactInfo.EOEventtypes_idEOEventtypes = " + Integer.toString(eventtypeid) + " AND EOEventtypes_has_EOExternalContactInfo.EOExternalContactInfo_idEOExternalContactInfo = EOExternalContactInfo.idEOContactInfo";
      rs = querySql(sql);
      ExternalContactInfo external = null;
      try
      {
         if(rs.next())
         {        
            System.out.println(" WEEEEEEEEEEEEEEEEEEEEEEEF FOUND ONEEEEEEEEEEEEEEEEEE " + rs.getString("name"));
            external = new ExternalContactInfo(
               rs.getInt("idEOContactInfo"), 
               rs.getString("name"), 
               rs.getString("phone"), 
               rs.getString("email"), 
               rs.getString("info"),
               rs.getString("company")
               );
         }
      }
      catch(Exception e)
      {
         System.err.println("getExternalContactInfoFromEOEventType: " +  e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(external); 
   }
   
   public CustomerContactInfo getCustomerContactInfoFromEOArrangement(int arrangementid)
   {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT EOCustomerContactInfo.idEOContactInfo, "+ 
             "EOCustomerContactInfo.name, " +
             "EOCustomerContactInfo.phone, " +
             "EOCustomerContactInfo.email, " +
             "EOCustomerContactInfo.info, " +
             "EOCustomerContactInfo.company " +             
             " FROM EOArrangements_has_EOContactInfo, EOCustomerContactInfo WHERE EOArrangements_has_EOContactInfo.EOArrangements_idEOArrangements = " + Integer.toString(arrangementid) + " AND EOArrangements_has_EOContactInfo.EOCustomerContactInfo_idCustomercontactInfo = EOCustomerContactInfo.idEOContactInfo";
      rs = querySql(sql);
      CustomerContactInfo customers = null;
      try
      {
         if(rs.next())
         {        
            customers = new CustomerContactInfo(
               rs.getInt("idEOContactInfo"), 
               rs.getString("name"), 
               rs.getString("phone"), 
               rs.getString("email"), 
               rs.getString("info"),
               rs.getString("company")
               );
         }
      }
      catch(Exception e)
      {
         System.err.println("getCustomerContactInfoFromEOArrangement" +  e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(customers); 
   }

	/**
	 *  Metode til at hente alle EOFacilitatorContactInfo rows ud som hører til et specifikt arrangement
	 *  og returnere et array af FacilitatorContactInfo objekter
	 */
   public FacilitatorContactInfo[] getFacilitatorsContactInfo(int arrangementid) {
      FacilitatorContactInfo[] facilArr = null;
      int rowCount;

      rowCount 	 = 0;
      rowCount 	 = getNotDeletedRowCountFromTable("EOFacilitatorContactInfo");

      String sql =
         "SELECT EOF.* " +
         "FROM EOArrangements_has_EOFacilitatorContactInfo EOF_h_EOF " +
         "LEFT OUTER JOIN " +
         "EOFacilitatorContactInfo EOF ON EOF.idEOContactInfo = EOF_h_EOF.EOFacilitatorContactInfo_idFacilitatorContactInfo " +
         "WHERE EOF_h_EOF.EOArrangements_idEOArrangements = " + arrangementid;
      ResultSet rs = this.querySql(sql);

      try
      {
      	// Hvis der IKKE returneres 0 rækker (Hvis tabellen ikke er tom)
         if(rowCount != 0){
         	// Initialisere array facilArr på størrelsen defineret i "rowCount"
            facilArr = new FacilitatorContactInfo[rowCount];

            int i = 0;
            while(rs.next()){
               facilArr[i] = new FacilitatorContactInfo(
                  	rs.getInt("idEOContactInfo"),
                  	rs.getString("name"),
                  	rs.getString("phone"),
                  	rs.getString("email"),
                  	rs.getString("info")
                  );
               ++i;
            }
         	// Luk DB forbindelse efter query er kørt færdig
            this.closeConnection(rs);
         }
      	// Hvis facilConInfoArr ikke er null skal det returneres som String i konsollen
      	//if(facilArr != null) {
      	//System.out.println(facilArr[0]);
      	//}
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      return facilArr;
   }

   /**
   * Returns the row that are in the query, the query must have an Count(*).
   */
   /**
   * Returns the row that are in the query, the query must have an Count(*).
   */
   public int numRows(String sql)
   {
      return(numRows(sql, "count(*)"));   
   }
   
   public int numRows(String sql, String cselector)
   {
      if(sql == null)
      {
         return(0);
      }   
      int size = 0;
      ResultSet rs = querySql(sql);
      try
      {
         if(rs.next())
         {
            size = rs.getInt(cselector);
         }   
      }
      catch(Exception e){}
      finally { this.closeConnection(rs);  }
      return(size);
   }

   public EOEvent[] getEOEvents() {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT * FROM EOEvents";
      System.out.println(sql);
      int rows = numRows("SELECT count(*) FROM EOEvents");
      if(rows == 0)
      {
         return(null);
      }
      rs = querySql(sql);
      EOEvent[] events = new EOEvent[rows];
      for(int i = 0; i < rows; i++)
      {
         events[i] = null;
      }   

      try
      {
         for(int i = 0; rs.next(); i++)
         {        
            events[i] = new EOEvent(
               rs.getInt("idEOContactInfo"), 
               rs.getString("description"), 
               sqliteDateTimeConvert(rs.getInt("dateTimeStart")), 
               sqliteDateTimeConvert(rs.getInt("dateTimeEnd")), 
               rs.getDouble("price"),
               getFacilitatorContactInfoFromEOEvent(rs.getInt("idEOContactInfo")),
               getEOEventTypes(rs.getInt("idEOContactInfo"))
               );
         }
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(events);
   }

	/**
	 * 
	 * Returns all Events that are associated with an arrangement
	 */
   public EOEvent[] getEOEvents(int arrangementid) {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT EOEvents.idEOEvents, "+ 
             "EOEvents.description, " +
             "EOEvents.dateTimeStart, " +
             "EOEvents.dateTimeEnd, " +
             "EOEvents.price " +
             " FROM EOArrangements_has_EOEvents, EOEvents WHERE EOArrangements_has_EOEvents.EOArrangements_idEOArrangements = " + Integer.toString(arrangementid) + " AND EOArrangements_has_EOEvents.EOEvents_idEOEvents = EOEvents.idEOEvents";
      System.out.println(sql);
      int rows = numRows("SELECT count(*) FROM EOArrangements_has_EOEvents, EOEvents WHERE EOArrangements_has_EOEvents.EOArrangements_idEOArrangements = " + Integer.toString(arrangementid) + " AND EOArrangements_has_EOEvents.EOEvents_idEOEvents = EOEvents.idEOEvents");
      if(rows == 0)
      {
         return(null);
      }
      rs = querySql(sql);
      EOEvent[] events = new EOEvent[rows];
      for(int i = 0; i < rows; i++)
      {
         events[i] = null;
      }   

      try
      {
         for(int i = 0; rs.next(); i++)
         {        
            events[i] = new EOEvent(
               rs.getInt("idEOContactInfo"), 
               rs.getString("description"), 
               sqliteDateTimeConvert(rs.getInt("dateTimeStart")), 
               sqliteDateTimeConvert(rs.getInt("dateTimeEnd")), 
               rs.getDouble("price"),
               getFacilitatorContactInfoFromEOEvent(rs.getInt("idEOContactInfo")),
               getEOEventTypes(rs.getInt("idEOContactInfo"))
               );
         }
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(events);
   }

	/**
	 * 
	 * Returns all EventTypes that are associated with an Event
	 */
   public EOEventType[] getEOEventTypes(int eventid) {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT EOEventtypes.idEOEventtypes, "+ 
             "EOEventtypes.deletedStatus, " +
             "EOEventtypes.name, " +
             "EOEventtypes.description, " +
             "EOEventtypes.locationStart, " +
             "EOEventtypes.locationEnd, " +
             "EOEventtypes.time, " +
             "EOEventtypes.price " +                                      
             " FROM EOEvents_has_EOEventtypes, EOEventtypes WHERE EOEvents_has_EOEventtypes.EOEvents_idEOEvents = " + Integer.toString(eventid) + " AND EOEvents_has_EOEventtypes.EOEventtypes_idEOEventtypes = EOEventtypes.idEOEventtypes";
      System.out.println(sql);
      int rows = numRows("SELECT count(*) FROM EOEvents_has_EOEventtypes, EOEventtypes WHERE EOEvents_has_EOEventtypes.EOEvents_idEOEvents = " + Integer.toString(eventid) + " AND EOEvents_has_EOEventtypes.EOEventtypes_idEOEventtypes = EOEventtypes.idEOEventtypes");
      System.out.println("Rows: " + rows);
      rs = querySql(sql);
      EOEventType[] eventtypes = new EOEventType[rows];
      for(int i = 0; i < rows; i++)
      {
         eventtypes[i] = null;
      }   

      try
      {
         for(int i = 0; rs.next(); i++)
         {                 
            eventtypes[i] = new EOEventType(
               rs.getInt("idEOEventtypes"), 
               rs.getString("name"), 
               rs.getString("description"), 
               rs.getString("locationstart"), 
               rs.getString("locationend"),
               rs.getInt("time"),
               rs.getDouble("price"),
               getExternalContactInfoFromEOEventType(rs.getInt("idEOEventtypes"))
               );
         }
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(eventtypes);
   }

	/**
	 * 
	 * Returns all events in the database
    */
   public EOEventType[] getEOEventTypes() {
      ResultSet rs = null;
      String sql = null;
      sql = "SELECT * FROM EOEventtypes WHERE deletedStatus = 2";
      System.out.println(sql);
      int rows = numRows("SELECT count(*) FROM EOEventtypes");
      rs = querySql(sql);
      EOEventType[] eventtypes = new EOEventType[rows];
      for(int i = 0; i < rows; i++)
      {
         eventtypes[i] = null;
      }   

      try
      {
         for(int i = 0; rs.next(); i++)
         {                 
            eventtypes[i] = new EOEventType(
               rs.getInt("idEOEventtypes"), 
               rs.getString("name"), 
               rs.getString("description"), 
               rs.getString("locationstart"), 
               rs.getString("locationend"),
               rs.getInt("time"),
               rs.getDouble("price"),
               getExternalContactInfoFromEOEventType(rs.getInt("idEOEventtypes"))
               );
         }
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      finally { this.closeConnection(rs);  }
      System.out.println("Returning data");
      return(eventtypes);
   }

	/**
	 * 
	 * @param aObj
	 *
	 */
	public boolean createEOArrangement(EOArrangement aObj) {

		/* Variabler initialisering start */
			EOArrangement a 		  = aObj;
			boolean  returnvalue 	  = false;

            String 	deletedStatus	  = "2";
            String 	name 			  = a.getName();
            String	desc			  = a.getDescription();
            String	dateStart		  = "" + a.getDateTimeStart();
            String	dateEnd			  = "" + a.getDateTimeEnd();
            String  price 			  = "" + a.getPrice();
            boolean doneStatus		  = a.isDone();
            int 	doneStatusInt	  = 0;
            boolean payedStatus		  = a.isPayed();
            int 	payedStatusInt	  = 0;

			String	 SQLEOArrangements= "";
			boolean  SQLEOArrangementsStatus = false;

			String   SQLEOArrangements_has_EOContactInfo = "";
			boolean  SQLEOArrangements_has_EOContactInfoStatus = false;

			String   SQLEOArrangements_has_EOEvents = "";
			boolean  SQLEOArrangements_has_EOEventsStatus = false;

			String   SQLEOArrangements_has_EOFacilitatorContactInfo = "";
            boolean  SQLEOArrangements_has_EOFacilitatorContactInfoStatus = false;

			if(doneStatus){
				// Får værdien 2 hvis den arrangementet er afholdt
				doneStatusInt = 2;
			}else{
				// Får værdien 3 hvis arrangementet IKKE er afholdt
				doneStatusInt = 3;
			}
			if(payedStatus){
				// Får værdien 2 hvis arrangementet er betalt
				payedStatusInt = 2;
			}else{
				// Får værdien 3 hvis arrangementet IKKE er betalt
				payedStatusInt = 3;
			}
		/* Variable initialisering slut */

		/* Array variables initialisering start */
			EOEvent[] eventsArr		  = aObj.getEvents();

			int eventsArrSize 		  = eventsArr.length;
			// Hvis der er 0 events på arrangementet sættes eventsArr til null
			if(eventsArrSize == 0){
				eventsArr = null;
			}

			FacilitatorContactInfo[] facilArr = aObj.getFacilitators();
			int facilArrSize = facilArr.length;
			// Hvis der er 0 facilitatorer på arrangementet sættes facilArr til null
			if(facilArrSize == 0){
				facilArr = null;
			}
		/* Array variables initialisering slut */

		/* Konsol test facilitatorsarray */
		/*
			for(int i = 0; i < facilArrSize; i++){
				if(facilArr[i] != null){
					System.out.println(facilArr[i].getInfo());
				}
			}
		*/
        // BYG SQL INSERT STATEMENT FOR ARRANGEMENT
        SQLEOArrangements                    = "INSERT INTO EOArrangements ";
        SQLEOArrangements                   += "(name, description, dateTimeStart, dateTimeEnd, price, ispayed, isdone) ";
        SQLEOArrangements                   += "VALUES ('Eksamens Aflevering', 'desc', '16-02-1993 12:00:00', '24-02-1993 12:00:00', 20, 3, 3);";

        // EKSEKVER SQL STATEMENT FOR ARRANGEMENT
        if(executeSql(SQLEOArrangements) == 1) SQLEOArrangementsStatus = true;

        // HENT SENESTE ID TIL VIDERE BYGNING AF SQL STATEMENTS
        int id = this.getLastId("EOArrangements", "idEOArrangements");

        //BYG SQL INSERT STATEMENTS FOR RESTEN SOM ER FORBUNDET TIL ARRANGEMENT
        SQLEOArrangements_has_EOContactInfo  = "INSERT INTO EOArrangements_has_EOContactInfo ";
        SQLEOArrangements_has_EOContactInfo += "(EOArrangements_idEOArrangements, EOCustomerContactInfo_idCustomerContactInfo) ";
        SQLEOArrangements_has_EOContactInfo += "VALUES (" + id + ", " + aObj.getCustomer().getId()  + ");";

        // BYG OG EKSEKVER EVENTS FORBUNDET TIL ARRANGEMENT
        for(int i = 0; i < eventsArrSize; i++){
            if(eventsArr[i] != null){
                //System.out.println(eventsArr[i].getPrice());
                SQLEOArrangements_has_EOEventsStatus = false;

                SQLEOArrangements_has_EOEvents       = "INSERT INTO EOArrangements_has_EOEvents ";
                SQLEOArrangements_has_EOEvents      += "(EOArrangements_idEOArrangements, EOEvents_idEOEvents) ";
                SQLEOArrangements_has_EOEvents      += "VALUES (" + id + ", " + eventsArr[i].getId() + ");";
                if(executeSql(SQLEOArrangements_has_EOEvents) == 1) SQLEOArrangements_has_EOEventsStatus = true;
            }
        }
        // BYG OG EKSEKVER FACILITAORCONTACTINFO FORBUNDET TIL ARRANGEMENT
        for(int i = 0; i < facilArrSize; i++){
            if(facilArr[i] != null){
                //System.out.println(eventsArr[i].getPrice());
                SQLEOArrangements_has_EOFacilitatorContactInfoStatus = false;

                SQLEOArrangements_has_EOFacilitatorContactInfo  = "INSERT INTO EOArrangements_has_EOFacilitatorContactInfo ";
                SQLEOArrangements_has_EOFacilitatorContactInfo += "(EOArrangements_idEOArrangements, EOFacilitatorContactInfo_idFacilitatorContactInfo) ";
                SQLEOArrangements_has_EOFacilitatorContactInfo += "VALUES (" + id + ", " + facilArr[i].getId() + ");";
                if(executeSql(SQLEOArrangements_has_EOFacilitatorContactInfo) == 1) SQLEOArrangements_has_EOFacilitatorContactInfoStatus = true;
            }
        }
        if(executeSql(SQLEOArrangements_has_EOContactInfo) == 1)            SQLEOArrangements_has_EOContactInfoStatus = true;
        if(executeSql(SQLEOArrangements_has_EOFacilitatorContactInfo) == 1) SQLEOArrangements_has_EOFacilitatorContactInfoStatus = true;

        // Sammenlæg alle SQLExecutes boolean værdier og set returnvalue herefter. Oversat: Hvis alle SQLExecutes er korrekt eksekveret sættes den returnvalue til true;
        if(SQLEOArrangementsStatus && SQLEOArrangements_has_EOContactInfoStatus && SQLEOArrangements_has_EOEventsStatus && SQLEOArrangements_has_EOFacilitatorContactInfoStatus){
            returnvalue = true;
        }else{
            returnvalue = false;
        }

        return returnvalue;
	}


	/**
	 * 
	 * @param arrangementid
	 * @param events
	 * @param customer
	 * @param name
	 * @param description
	 * @param datetimestart
	 * @param datetimeend
	 * @param price
	 */
   public void updateEOArrangement(EOArrangement arrangement) {
   	// TODO - implement EODatabaseInterface.updateEOArrangement
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * Sletter et EOArrangement fra databasen
	 */
   public boolean deleteEOArrangement(int arrangementid) {
      EOEvent[] events = getEOEvents(arrangementid);
      if(events != null && events.length > 0)
      {
         for(int i = 0; i < events.length; i++)
         {
            deleteEOEvent(events[i].getId());
         }
      }

      return(executeSql("DELETE FROM EOArrangements WHERE EOArrangements.idEOArrangements = " + arrangementid) == 1 && 
            executeSql("DELETE FROM EOArrangements_has_EOContactInfo WHERE EOArrangements_idEOArrangements = " + arrangementid) == 1 &&
            executeSql("DELETE FROM EOArrangements_has_EOFacilitatorContactInfo WHERE EOArrangements_has_EOFacilitatorContactInfo.EOArrangements_idEOArrangements = " + arrangementid) == 1);
   }

   public boolean deleteEOArrangement(EOArrangement arrangement) {
      return(deleteEOArrangement(arrangement.getId()));
   }

	/**
	 * 
	 * @param eventtypes
	 * @param datetimestart
	 * @param datetimeend
	 * @param price
	 * @param description
	 */
   public void createEOEvent(EOEvent event) {
   	// TODO - implement EODatabaseInterface.createEOEvent
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param eventid
	 * @param eventtypes
	 * @param datetimestart
	 * @param datetimeend
	 * @param price
	 * @param description
	 */
   public void updateEOEvent(EOEvent event) {
      String eventid = Integer.toString(event.getId());
      String description = event.getDescription();
      String dateTimeStart = event.getDateTimeStart().toString();
      String dateTimeEnd = event.getDateTimeEnd().toString();
      String price = Double.toString(event.getPrice());
      
      //Drop any link to EventTypes and Facilitators
      executeSql("DELETE FROM EOEvents_has_EOEventtypes WHERE EOEvents_idEOEvents = " + eventid);
      executeSql("DELETE FROM EOEvents_has_EOFacilitatorContactInfo WHERE EOEvents_idEOEvents = " + eventid);
      //Lets update the EOEvent
      executeSql("UPDATE EOEvents SET description = '"+description+"', dateTimeStart='"+dateTimeStart+"', dateTimeEnd='"+dateTimeEnd+"', price="+price+" WHERE idEOEvents="+eventid);     
      //Lets add links
      if(event.getFacilitators() != null)
      {
         for(int i = 0; i < event.getFacilitators().length; i++)
         {
            createLink("EOEvents_has_EOFacilitatorContactInfo", "EOEvents_idEOEvents", event.getId(), "EOFacilitatorContactInfo_idEOFacilitatorContactInfo", event.getFacilitators()[i].getId());
         }
      }
      if(event.getEventTypes() != null)
      {
         for(int i = 0; i < event.getEventTypes().length; i++)
         {
            createLink("EOEvents_has_EOEventtypes", "EOEvents_idEOEvents", event.getId(), "EOEventtypes_idEOEventtypes", event.getEventTypes()[i].getId());
         }
      }
   }

	/**
	 * 
	 * @param eventid
	 */
   public void deleteEOEvent(int eventid) {
      boolean status = executeSql("DELETE FROM EOEvents WHERE idEOEvents = " + Integer.toString(eventid)) == 1 &&
      executeSql("DELETE FROM EOArrangements_has_EOEvents WHERE EOEvents_idEOEvents = " + Integer.toString(eventid)) == 1 &&
      executeSql("DELETE FROM EOEvents_has_EOEventtypes WHERE EOEvents_idEOEvents = " + Integer.toString(eventid)) == 1 &&
      executeSql("DELETE FROM EOEvents_has_EOFacilitatorContactInfo WHERE EOEvents_idEOEvents = " + Integer.toString(eventid)) == 1;
   }

	/**
	 * 
	 * @param eventtype
	 *
	 */
   public boolean createEOEvenType(EOEventType eventtype) {
      boolean  returnvalue 	= false;

      int      id            = eventtype.getId();
      String 	deletedStatus = "2";
      String 	name 			  = eventtype.getName();
      String	description   = eventtype.getDescription();
      String	locationStart = eventtype.getLocationStart();
      String	locationEnd   = eventtype.getLocationEnd();
      String	time			  = Integer.toString(eventtype.getTime());
      String   price         = Double.toString(eventtype.getPrice());
      String	SQL				= "";
      
      if(id == -1)
      {
         SQL += "INSERT INTO 'EOEventTypes' (deletedStatus, name, description, locationStart, locationEnd, time, price) VALUES (";
         SQL += "'" + deletedStatus 	+ "',";
         SQL += "'" + name 			+ "',";
         SQL += "'" + description 			+ "',";
         SQL += "'" + locationStart 			+ "',";
         SQL += "'" + locationEnd 			+ "',";
         SQL += "'" + time 			+ "',";
         SQL += "'" + price 			+ "')";
      }
      else
      {
         SQL += "INSERT INTO 'EOEventTypes' (idEOEventtypes, deletedStatus, name, description, locationStart, locationEnd, time, price) VALUES (";
         SQL += "'" + Integer.toString(id) 	+ "',";
         SQL += "'" + deletedStatus 	+ "',";
         SQL += "'" + name 			+ "',";
         SQL += "'" + description 			+ "',";
         SQL += "'" + locationStart 			+ "',";
         SQL += "'" + locationEnd 			+ "',";
         SQL += "'" + time 			+ "',";
         SQL += "'" + price 			+ "')";
      }

      
      if(executeSql(SQL) == 1)
      {
         int eventtypeid = getLastId("EOEventTypes", "idEOEventtypes");
         System.out.println("Eventid: " + Integer.toString(eventtypeid));
         if(eventtype.getExternalContactInfo() == null)
         {
            System.out.println("External == null");
         }
         if(eventtypeid > 0 && eventtype.getExternalContactInfo() != null)
         {
            System.out.println("2");
            int externalid = createExternalContactInfo(eventtype.getExternalContactInfo());
            if(externalid > 0)
            {
               System.out.println("3");
               returnvalue = linkEOEventTypeExternalContactInfo(eventtypeid, externalid);
            }
         }
      }

      return returnvalue;
   }
   
   private boolean linkEOEventTypeExternalContactInfo(int eventtypeid, int externalid)
   {
      boolean returnvalue = false;
      String SQL = "INSERT INTO EOEventtypes_has_EOExternalContactInfo (EOEventtypes_idEOEventtypes, EOExternalContactInfo_idEOExternalContactInfo) VALUES (" + Integer.toString(eventtypeid) +", "+Integer.toString(externalid)+")";
      if(executeSql(SQL) == 1){
         returnvalue = true;
      }
      return returnvalue;
   }

	/**
	 * 
	 * @param eventtype
	 *
	 */
   public boolean updateEOEvenType(EOEventType eventtype) {
      boolean  returnvalue 	= false;

      String 	name 			  = eventtype.getName();
      String	description   = eventtype.getDescription();
      String	locationStart = eventtype.getLocationStart();
      String	locationEnd   = eventtype.getLocationEnd();
      String	time			  = Integer.toString(eventtype.getTime());
      String   price         = Double.toString(eventtype.getPrice());
      String   id            = Integer.toString(eventtype.getId());
      String	SQL			  = "";

      SQL = "UPDATE 'EOEventTypes' SET ";
      SQL += "name='" + name 			+ "',";
      SQL += "description='" + description 			+ "',";
      SQL += "locationStart='" + locationStart 			+ "',";
      SQL += "locationEnd='" + locationEnd 			+ "',";
      SQL += "time='" + time 			+ "',";
      SQL += "price='" + price 			+ "' ";
      SQL += "WHERE idEOEventtypes = " + id ;



      if(executeSql(SQL) == 1){
         returnvalue = true;
         if(eventtype.getExternalContactInfo() != null)
         {
            if(eventtype.getExternalContactInfo().getId() == -1)
            {
               int externalid = createExternalContactInfo(eventtype.getExternalContactInfo());
               if(externalid > 0)
               {
                  returnvalue = linkEOEventTypeExternalContactInfo(eventtype.getId(), externalid);
               }
            }
            else
            {
               updateExternalContactInfo(eventtype.getExternalContactInfo());
            }
         }
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }

	/**
	 * 
	 * @param eventtype
	 */
   public boolean deleteEOEvenType(EOEventType eventtype) {
      boolean  returnvalue 	= false;
      System.out.println("deleteEOEvenType"); 
      String SQL = "UPDATE 'EOEventTypes' SET deletedStatus = 3 WHERE idEOEventtypes = " + eventtype.getId();
      if(eventtype.getExternalContactInfo() != null)
      {
         deleteExternalContactInfo(eventtype.getExternalContactInfo());
      }
      if(executeSql(SQL) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }

	/**
	 * 
	 * @param fCIObj
	 */
   public boolean createFacilitatorContactInfo(FacilitatorContactInfo fCIObj) {

      FacilitatorContactInfo f = fCIObj;
      boolean  returnvalue 	= false;

      int      id          = f.getId();
      String 	deletedStatus	= "2";
      String 	name 			= f.getName();
      String	phone			= f.getPhone();
      String	email			= f.getEmail();
      String	info			= f.getInfo();
      String	SQL				= "";

      if(id == -1)
      {
         SQL += "INSERT INTO 'EOFacilitatorContactInfo' (deletedStatus, name, phone, email, info) VALUES (";
         SQL += "'" + deletedStatus + "',";
         SQL += "'" + name 			+ "',";
         SQL += "'" + phone 			+ "',";
         SQL += "'" + email 			+ "',";
         SQL += "'" + info 			+ "')";
      }
      else
      {
         SQL += "INSERT INTO 'EOFacilitatorContactInfo' (idEOContactInfo, deletedStatus, name, phone, email, info) VALUES (";
         SQL += "'" + Integer.toString(id) 	         + "',";
         SQL += "'" + deletedStatus + "',";
         SQL += "'" + name 			+ "',";
         SQL += "'" + phone 			+ "',";
         SQL += "'" + email 			+ "',";
         SQL += "'" + info 			+ "')";
      }



      if(executeSql(SQL) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }

	/**
	 *
	 * @param fCIObj
	 */
   public boolean deleteFacilitatorContactInfo(FacilitatorContactInfo fCIObj) {
      int 	id 			= fCIObj.getId();
      boolean returnvalue = false;
      String 	sql 		= "";

      sql += "UPDATE ";
      sql += "'EOFacilitatorContactInfo' SET deletedStatus = '3'";
      sql += " WHERE idEOContactInfo = " + id;

      if(this.executeSql(sql) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }

	/**
	 * 
	 * @param fCIObj
	 *
	 */
   public boolean updateFacilitatorContactInfo(FacilitatorContactInfo fCIObj) {
      boolean 	returnvalue = false;
      String 	sql 		= "";

      sql +=	"UPDATE 'EOFacilitatorContactInfo' SET ";
      sql +=	"name = '" 	+ fCIObj.getName() + "',";
      sql +=	"phone = '"	+ fCIObj.getPhone() + "',";
      sql +=	"email = '"	+ fCIObj.getEmail() + "' ";
      sql +=	"WHERE idEOContactInfo =" + fCIObj.getId();

      if(this.executeSql(sql) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }

	/**
	 *
	 * @param id
	 */
   public FacilitatorContactInfo getFacilitatorContactInfo(int id) {
      FacilitatorContactInfo fCIObj;
      fCIObj = new FacilitatorContactInfo(
         	id,
         	null,
         	null,
         	null,
         	null
         );

      return this.getFacilitatorContactInfo(fCIObj);
   }

	/**
	 *
	 * @param fCIObj
	 */
   public FacilitatorContactInfo getFacilitatorContactInfo(FacilitatorContactInfo fCIObj) {
      FacilitatorContactInfo contactInfo = null;
      String sql = "";

      sql += "SELECT * FROM EOFacilitatorContactInfo WHERE idEOContactInfo =";
      sql += fCIObj.getId() + " AND deletedStatus = 2";

      ResultSet rs = this.querySql(sql);

      try
      {
      	// Iterate through ResultSet
         while(rs.next())
         {
            contactInfo = new FacilitatorContactInfo(rs.getInt("idEOContactInfo"),rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("info"));
         }
      	// Close connection
         this.closeConnection(rs);
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }

   	// Return CustomerContactInfo
      return contactInfo;
   }
	/**
	 *  Metode til at hente alle EOFacilitatorContactInfo rows ud
	 *  og returnere et array af FacilitatorContactInfo objekter
	 */
   public FacilitatorContactInfo[] getAllFacilitatorContactInfo() {
      FacilitatorContactInfo[] facilArr = null;
      int rowCount;

      rowCount 	 = 0;
      rowCount 	 = getNotDeletedRowCountFromTable("EOFacilitatorContactInfo");

      String sql = "SELECT * FROM EOFacilitatorContactInfo WHERE deletedStatus = 2";
      ResultSet rs = this.querySql(sql);

      try
      {
      	// Hvis der IKKE returneres 0 rækker (Hvis tabellen ikke er tom)
         if(rowCount != 0){
         	// Initialisere array facilArr på størrelsen defineret i "rowCount"
            facilArr = new FacilitatorContactInfo[rowCount];

            int i = 0;
            while(rs.next()){
               facilArr[i] = new FacilitatorContactInfo(rs.getInt("idEOContactInfo"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("info"));
               ++i;
            }
         	// Luk DB forbindelse efter query er kørt færdig
            this.closeConnection(rs);
         }
      	// Hvis facilConInfoArr ikke er null skal det returneres som String i konsollen
      	//if(facilArr != null) {
      	//System.out.println(facilArr[0]);
      	//}
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      return facilArr;
   }

	/**
	 *
	 * @param fCIObj
	 * @param printSqlStatment
	 *
	 * Overwriter metoden updateFacilitatorContactInfo med den
	 * tilføjede parameter printSqlStatment som udskriver SQLstatement
	 * til konsollen
	 *
	 */
   public boolean updateFacilitatorContactInfo(FacilitatorContactInfo fCIObj, boolean printSqlStatment) {
      boolean 	returnvalue = false;
      String 	sql 		= "";

      sql +=	"UPDATE 'EOFacilitatorContactInfo' SET ";
      sql +=	"name = '" 	+ fCIObj.getName() + "',";
      sql +=	"phone = '"	+ fCIObj.getPhone() + "',";
      sql +=	"email = '"	+ fCIObj.getEmail() + "' ";
      sql +=	"WHERE idEOContactInfo =" + fCIObj.getId();

      if(printSqlStatment == true){
         System.out.println(sql);
      }

      if(this.executeSql(sql) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }

	/**
	 * 
	 * @param cECObj
	 *
	 */
   public int createExternalContactInfo(ExternalContactInfo cECObj) {
      ExternalContactInfo e 	= cECObj;
      int  returnvalue 	= -1;

      String 	deletedStatus	= "2";
      int      id          = e.getId();
      String 	name 			= e.getName();
      String	phone			= e.getPhone();
      String	email			= e.getEmail();
      String	info			= e.getInfo();
      String 	company 		= e.getCompany();
      String	SQL				= "";

      if(id == -1)
      {
         SQL += "INSERT INTO 'EOExternalContactInfo' (deletedStatus, name, phone, email, info, company) VALUES (";
         SQL += "'" + deletedStatus 	+ "',";
         SQL += "'" + name 			+ "',";
         SQL += "'" + phone 			+ "',";
         SQL += "'" + email 			+ "',";
         SQL += "'" + info 			+ "',";
         SQL += "'" + company			+ "')";
      }
      else
      {
         SQL += "INSERT INTO 'EOExternalContactInfo' (idEOContactInfo, deletedStatus, name, phone, email, info, company) VALUES (";
         SQL += "'" + Integer.toString(id)   + "',";
         SQL += "'" + deletedStatus + "',";
         SQL += "'" + name 			+ "',";
         SQL += "'" + phone 			+ "',";
         SQL += "'" + email 			+ "',";
         SQL += "'" + info 			+ "',";
         SQL += "'" + company			+ "')";
      }


      if(executeSql(SQL) == 1)
      {
         returnvalue = getLastId("EOExternalContactInfo", "idEOContactInfo");
      }

      return returnvalue;
   }

	/**
	 * 
	 * @param eCIObj
	 */
   public boolean deleteExternalContactInfo(ExternalContactInfo eCIObj) {
      int 		id 			= eCIObj.getId();
      boolean 	returnvalue = false;
      String 	sql 		= "";

      sql += "UPDATE ";
      sql += "'EOExternalContactInfo' SET deletedStatus = '3'";
      sql += " WHERE idEOContactInfo = " + id;

      if(this.executeSql(sql) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }

	/**
	 * 
	 * @param eCIObj
	 *
	 */
   public boolean updateExternalContactInfo(ExternalContactInfo eCIObj) {
      boolean returnvalue = false;
      String sql = "";

      sql +=	"UPDATE 'EOExternalContactInfo' SET ";
      sql +=	"name = '" 	+ eCIObj.getName() + "',";
      sql +=	"phone = '"	+ eCIObj.getPhone() + "',";
      sql +=	"email = '"	+ eCIObj.getEmail() + "' ";
      sql +=	"WHERE idEOContactInfo =" + eCIObj.getId();

      if(this.executeSql(sql) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }
	/**
	 *
	 * @param id
	 */
   public ExternalContactInfo getExternalContactInfo(int id) {
      ExternalContactInfo eCIObj;
      eCIObj = new ExternalContactInfo(
         	id,
         	null,
         	null,
         	null,
         	null,
         	null
         );

      return this.getExternalContactInfo(eCIObj);
   }
	/**
	 *
	 * @param eCIObj
	 */
   public ExternalContactInfo getExternalContactInfo(ExternalContactInfo eCIObj) {
      int id = eCIObj.getId();
      String sql = "";

      sql += "SELECT * FROM 'EOExternalContactInfo'";
      sql += "WHERE idEOContactInfo=" + id + " ";
      sql += "AND deletedStatus=2";
   	//System.out.println(sql);

      ResultSet rs = this.querySql(sql);
      ExternalContactInfo contactInfo = null;
      try
      {
      	// Iterate through ResultSet
         while(rs.next())
         {
            contactInfo = new ExternalContactInfo(rs.getInt("idEOContactInfo"),rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("info"), rs.getString("company"));
         }
      	// Close connection
         this.closeConnection(rs);
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }

   	// Return CustomerContactInfo
      return contactInfo;
   }



	/**
	 *  Metode til at hente alle EOExternalContactInfo rows ud
	 *  og returnere et array af ExternalContactInfo objekter
	 */
   public ExternalContactInfo[] getAllExternalContactInfo() {
      ExternalContactInfo[] extArr = null;
      int rowCount;

      rowCount 	 = 0;
      rowCount 	 = getNotDeletedRowCountFromTable("EOExternalContactInfo");

      String sql = "SELECT * FROM EOExternalContactInfo WHERE deletedStatus = 2";
      ResultSet rs = this.querySql(sql);

      try
      {
      	// Hvis der IKKE returneres 0 rækker (Hvis tabellen ikke er tom)
         if(rowCount != 0){
         	// Initialisere array facilArr på størrelsen defineret i "rowCount"
            extArr = new ExternalContactInfo[rowCount];

            int i = 0;
            while(rs.next()){
               extArr[i] = new ExternalContactInfo(rs.getInt("idEOContactInfo"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("info"), rs.getString("company"));
               ++i;
            }
         	// Luk DB forbindelse efter query er kørt færdig
            this.closeConnection(rs);
         }
      	// Hvis facilConInfoArr ikke er null skal det returneres som String i konsollen
      	//if(facilArr != null) {
      	//System.out.println(facilArr[0]);
      	//}
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      return extArr;
   }

	/**
	 * 
	 * @param cCIObj
     *
	 */
   public boolean createCustomerContactInfo(CustomerContactInfo cCIObj) {
      CustomerContactInfo c 	= cCIObj;
      boolean  returnvalue 	= false;
      String	SQL				= "";

      int 	   id	         = c.getId();
      String 	deletedStatus	= "2";
      String 	name 			= c.getName();
      String	phone			= c.getPhone();
      String	email			= c.getEmail();
      String	company			= c.getCompany();
      String	info			= c.getInfo();

      if(id == -1)
      {
         SQL += "INSERT INTO 'EOCustomerContactInfo' (deletedStatus, name, phone, email, company, info) VALUES (";
         SQL += "'" + deletedStatus 	+ "',";
         SQL += "'" + name 			+ "',";
         SQL += "'" + phone 			+ "',";
         SQL += "'" + email 			+ "',";
         SQL += "'" + company 		+ "',";
         SQL += "'" + info 			+ "')";
      }
      else
      {
         SQL += "INSERT INTO 'EOCustomerContactInfo' (idEOContactInfo, deletedStatus, name, phone, email, company, info) VALUES (";
         SQL += "'" + Integer.toString(id) 	+ "',";
         SQL += "'" + deletedStatus 	+ "',";
         SQL += "'" + name 			+ "',";
         SQL += "'" + phone 			+ "',";
         SQL += "'" + email 			+ "',";
         SQL += "'" + company 		+ "',";
         SQL += "'" + info 			+ "')";
      }


      if(executeSql(SQL) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }

	/**
	 * 
	 * @param cCIObj
	 */
   public boolean deleteCustomerContactInfo(CustomerContactInfo cCIObj) {
      int 		id 			= cCIObj.getId();
      boolean 	returnvalue = false;
      String 	sql 		= "";

      sql += "UPDATE ";
      sql += "'EOCustomerContactInfo' SET deletedStatus = '3'";
      sql += " WHERE idEOContactInfo = " + id;

      if(this.executeSql(sql) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }

	/**
	 * @param cCIObj
     */
   public boolean updateCustomerContactInfo(CustomerContactInfo cCIObj) {
      boolean 	returnvalue = false;
      String 	sql 		= "";

      sql +=	"UPDATE 'EOCustomerContactInfo' SET ";
      sql +=	"name = '" 	+ cCIObj.getName() + "',";
      sql +=	"phone = '"	+ cCIObj.getPhone() + "',";
      sql +=	"email = '"	+ cCIObj.getEmail() + "' ";
      sql +=	"WHERE idEOContactInfo =" + cCIObj.getId();

      if(this.executeSql(sql) == 1){
         returnvalue = true;
      }else{
         returnvalue = false;
      }

      return returnvalue;
   }
	/**
	 *
	 * @param id
	 */
   public CustomerContactInfo getCustomerContactInfo(int id) {
      CustomerContactInfo cCIObj;
      cCIObj = new CustomerContactInfo(
         	id,
         null,
         null,
         null,
         null,
         null
         );

      return this.getCustomerContactInfo(cCIObj);
   }
	/**
	 *
	 * @param cCIObj
	 */
   public CustomerContactInfo getCustomerContactInfo(CustomerContactInfo cCIObj) {
      String sql  = "";
      int id 		= cCIObj.getId();

      sql += "SELECT * FROM 'EOCustomerContactInfo'";
      sql += "WHERE idEOContactInfo=" + id + " ";
      sql += "AND deletedStatus=2";

      ResultSet rs = this.querySql(sql);

      CustomerContactInfo contactInfo = null;
      try
      {

      	// Iterate through ResultSet
         while(rs.next())
         {
            contactInfo = new CustomerContactInfo(rs.getInt("idEOContactInfo"),rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("info"), rs.getString("company"));
         }
      	// Close connection
         this.closeConnection(rs);
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }

   	// Return CustomerContactInfo
      return contactInfo;
   }

	/**
	 *  Metode til at hente alle EOCustomerContactInfo rows ud
	 *  og returnere et array af CustomerContactInfo objekter
	 */
   public CustomerContactInfo[] getAllCustomerContactInfo() {
      CustomerContactInfo[] extArr = null;
      int rowCount;

      rowCount 	 = 0;
      rowCount 	 = getNotDeletedRowCountFromTable("EOCustomerContactInfo");

      String sql = "SELECT * FROM EOCustomerContactInfo WHERE deletedStatus = 2";
      ResultSet rs = this.querySql(sql);

      try
      {
      	// Hvis der IKKE returneres 0 rækker (Hvis tabellen ikke er tom)
         if(rowCount != 0){
         	// Initialisere array facilArr på størrelsen defineret i "rowCount"
            extArr = new CustomerContactInfo[rowCount];

            int i = 0;
            while(rs.next()){
               extArr[i] = new CustomerContactInfo(rs.getInt("idEOContactInfo"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("info"), rs.getString("company"));
               ++i;
            }
         	// Luk DB forbindelse efter query er kørt færdig
            this.closeConnection(rs);
         }
      	// Hvis facilConInfoArr ikke er null skal det returneres som String i konsollen
      	//if(facilArr != null) {
      	//System.out.println(facilArr[0]);
      	//}
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      return extArr;
   }

	/**
	* @param tablename
	* Metode som henter hvor mange antal rækker
	* der er i tabel 'tablename' som ikke er
	* blevet soft deleted.
	*/
   public int getNotDeletedRowCountFromTable(String tablename){
      String  sql = "SELECT Count(*) FROM " + tablename + " WHERE deletedStatus = 2";
      ResultSet rs = this.querySql(sql);
      int		rowCount = 0;
      try {
         while (rs.next()) {
            rowCount = rs.getInt(1);
         }
      }
      catch (Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      	//System.exit(0);
      }
      this.closeConnection(rs);

      return rowCount;
   }

   /**
   * Returns last Id from an insert.
   * Table: The table the insert has been in
   * column: the auto incremental column
   */
   private int getLastId(String table, String column)
   {
      int returnvalue = -1;
      ResultSet rs = null;
      try
      {
         rs = querySql("SELECT * FROM "+table+" ORDER BY "+column+" DESC LIMIT 1");
         if(rs.next())
         {
            if(this.debug) System.out.println("Running id lookup");
            returnvalue = rs.getInt(column);
            if(this.debug) System.out.println("id = " + Integer.toString(returnvalue));
         }
      }
      catch(Exception ee){ System.out.println("getLastId: Error error"); }
      finally{ closeConnection(rs); }
      return(returnvalue);
   }

	private int executeSql(String sql)
	{
	  int returnvalue = -1;
	  PreparedStatement pstmt = null;

	  try
	  {
		 conn = DriverManager.getConnection(this.dbPathAbsolute);
		 if(this.debug) System.out.println("DB CONNECTION OPENED");

		 pstmt = conn.prepareStatement(sql);

		 if(this.debug) System.out.println("EXECUTING SQL ...");
		 returnvalue = pstmt.executeUpdate();

		 if(this.debug) System.out.println("SQL SUCCESS: " + returnvalue);
		 //System.out.println(returnvalue);
		 if(this.debug) System.out.println("CLOSING DB CONNECTION ...");

		  if(conn != null)
		  {
			  conn.close();
			  if(this.debug) System.out.println("DB CONNECTION CLOSED");
		  }

	  }
	  catch (Exception e)
	  {
		 System.err.println( e.getClass().getName() + ": " + e.getMessage() + " Sql: " + sql);

		  returnvalue = -1;
		 //System.exit(0);
	  }
	  return(returnvalue);
	}

    private ResultSet querySql(String sql)
    {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			//conn = DriverManager.getConnection("jdbc:sqlite:database.db");
			conn = DriverManager.getConnection(this.dbPathAbsolute);
			if(this.debug) System.out.println("DB CONNECTION OPENED");

			pstmt = conn.prepareStatement(sql);

			if(this.debug) System.out.println("EXECUTING SQL QUERY ...");
			rs = pstmt.executeQuery();

			return rs;
		}
		catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() + " Sql: " + sql);
			System.exit(0);
		}
		return rs;
    }

   private void closeConnection(ResultSet rs)
   {
      try
      {
         if(conn != null)
         {
            if(this.debug) System.out.println("CLOSING DB CONNECTION");
            rs.close();
            conn.close();
            if(this.debug) System.out.println("DB CONNECTION CLOSED");
         }
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
   }

}