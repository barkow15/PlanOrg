import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class EODatabaseInterface {
   Connection conn = null;

   String dbPathAbsolute = "jdbc:sqlite:/Users/philipbarkow/Library/Mobile Documents/com~apple~CloudDocs/Datamatiker/1. semester/PlanOrg/generatedcode/database.db";
   String dbPathRelative = "jdbc:sqlite:database.db";

   private void closeConnection(ResultSet rs)
   {
      try
      {
         if(conn != null)
         {
            rs.close();
            conn.close();
            System.out.println("DB CONNECTION CLOSED");
         }
      }
      catch(Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
   }

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
	/**
	 * 
	 * @param arrangementid
	 */
   public EOArrangement getEOArrangement(int arrangementid) {
   	// TODO - implement EODatabaseInterface.getEOArrangement
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param eventid
	 */
   public EOEvent getEOEvent(int eventid) {
   	// TODO - implement EODatabaseInterface.getEOEvent
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param eventtypeid
	 */
   public EOEventType getEOEventType(int eventtypeid) {
   	// TODO - implement EODatabaseInterface.getEOEventType
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param customercontactid
	 */
   public CustomerContactInfo getCustomerContactInfo(int customercontactid) {
   	// TODO - implement EODatabaseInterface.getCustomerContactInfo
	   String sql = "SELECT * FROM EOCustomerContactInfo WHERE idEOContactInfo =" + customercontactid + " AND deletedStatus = 2";
	   ResultSet rs = this.querySql(sql);
	   CustomerContactInfo contactInfo = null;

	   try
	   {
	   		//Iterate through ResultSet
		   while(rs.next())
		   {
			   System.out.println(rs);
			   contactInfo = new CustomerContactInfo(rs.getInt("idEOContactInfo"),rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("info"), rs.getString("company"));
		   }
	   }
	   catch(Exception e)
	   {
		   System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		   System.exit(0);
	   }

	   // Luk DB forbindelse
	   this.closeConnection(rs);

	   // Return CustomerContactInfo
	   return contactInfo;
	   //throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param externalcontactid
	 */
   public ExternalContactInfo getExternalContactInfo(int externalcontactid) {
   	// TODO - implement EODatabaseInterface.getExternalContantInfo
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param facilitatorcontactid
	 */
   public FacilitatorContactInfo getFacilitatorContactInfo(int facilitatorcontactid) {
   	// TODO - implement EODatabaseInterface.getFacilitatorContactInfo
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param sortby
	 * @param pagenumber
	 */
   public void getEOArrangements(String sortby, int pagenumber) {
   	// TODO - implement EODatabaseInterface.getEOArrangements
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param arrangementid
	 */
   public void getEOEvents(int arrangementid) {
   	// TODO - implement EODatabaseInterface.getEOEvents
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param eventid
	 */
   public void getEOEventTypes(int eventid) {
   	// TODO - implement EODatabaseInterface.getEOEventTypes
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param events
	 * @param customer
	 * @param name
	 * @param description
	 * @param datetimestart
	 * @param datetimeend
	 * @param price
	 */
   public void createEOArrangement(EOEvent[] events, CustomerContactInfo customer, String name, String description, LocalDateTime datetimestart, LocalDateTime datetimeend, double price) {
   	// TODO - implement EODatabaseInterface.createEOArrangement
      throw new UnsupportedOperationException();
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
   public void updateEOArrangement(int arrangementid, EOEvent[] events, CustomerContactInfo customer, String name, String description, LocalDateTime datetimestart, LocalDateTime datetimeend, double price) {
   	// TODO - implement EODatabaseInterface.updateEOArrangement
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param arrangementid
	 */
   public void deleteEOArrangement(int arrangementid) {
   	// TODO - implement EODatabaseInterface.deleteEOArrangement
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param eventtypes
	 * @param datetimestart
	 * @param datetimeend
	 * @param price
	 * @param description
	 */
   public void createEOEvent(EOEventType[] eventtypes, LocalDateTime datetimestart, LocalDateTime datetimeend, double price, String description) {
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
   public void updateEOEvent(int eventid, EOEventType[] eventtypes, LocalDateTime datetimestart, LocalDateTime datetimeend, double price, String description) {
   	// TODO - implement EODatabaseInterface.updateEOEvent
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param eventid
	 */
   public void deleteEOEvent(int eventid) {
   	// TODO - implement EODatabaseInterface.deleteEOEvent
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param locationstart
	 * @param locationend
	 * @param time
	 * @param name
	 * @param externalcontact
	 * @param description
	 * @param price
	 */
   public void createEOEvenType(String locationstart, String locationend, int time, String name, ExternalContactInfo externalcontact, String description, double price) {
   	// TODO - implement EODatabaseInterface.createEOEvenType
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param eventtypeid
	 * @param locationstart
	 * @param locationend
	 * @param time
	 * @param name
	 * @param externalcontact
	 * @param description
	 * @param price
	 */
   public void updateEOEvenType(int eventtypeid, String locationstart, String locationend, int time, String name, ExternalContactInfo externalcontact, String description, double price) {
   	// TODO - implement EODatabaseInterface.updateEOEvenType
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param eventtypeid
	 */
   public void deleteEOEvenType(int eventtypeid) {
   	// TODO - implement EODatabaseInterface.deleteEOEvenType
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 */
   public void createFacilitatorContactInfo(String name, String phone, String email) {
   	// TODO - implement EODatabaseInterface.createFacilitatorContactInfo
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param facilitatorid
	 */
   public void deleteFacilitatorContactInfo(int facilitatorid) {
   	// TODO - implement EODatabaseInterface.deleteFacilitatorContactInfo
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param facilitatorid
	 * @param name
	 * @param phone
	 * @param email
	 */
   public void updateFacilitatorContactInfo(int facilitatorid, String name, String phone, String email) {
   	// TODO - implement EODatabaseInterface.updateFacilitatorContactInfo
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param company
	 */
   public void createExternalContactInfo(String name, String phone, String email, String company) {
   	// TODO - implement EODatabaseInterface.createExternalContactInfo
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param externalcontactid
	 */
   public void deleteExternalContactInfo(int externalcontactid) {
   	// TODO - implement EODatabaseInterface.deleteExternalContactInfo
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param externalcontactid
	 * @param name
	 * @param phone
	 * @param email
	 * @param company
	 */
   public void updateExternalContactInfo(int externalcontactid, String name, String phone, String email, String company) {
   	// TODO - implement EODatabaseInterface.updateExternalContactInfo
      throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param company
     * @param info
	 */
   public boolean createCustomerContactInfo(String name, String phone, String email, String company, String info) {
   	// TODO - implement EODatabaseInterface.createCustomerContactInfo
	   boolean returnvalue = false;
	   int deletedStatus = 2;
	   //executeSql("INSERT INTO 'EOCustomerContactInfo' (name, phone, email, company) VALUES ('"+ name + "','"+ phone + "','"+ email +"','"+company+"')");

	   if(executeSql("INSERT INTO 'EOCustomerContactInfo' (deletedStatus, name, phone, email, company, info) VALUES ('" + deletedStatus + "','" + name + "','" + phone + "','" + email + "','" + company + "', '" + info + "')") == 1){
	   	returnvalue = true;
	   }else{
	   	returnvalue = false;
	   }

	   return returnvalue;
      //throw new UnsupportedOperationException();
   }

	/**
	 * 
	 * @param customercontactid
	 */
   public boolean deleteCustomerContactInfo(int customercontactid) {
   	// TODO - implement EODatabaseInterface.deleteCustomerContactInfo
	   boolean returnvalue = false;
	   String sql = "UPDATE 'EOCustomerContactInfo' SET deletedStatus = '3' WHERE idEOContactInfo = " + customercontactid;

	   if(this.executeSql(sql) == 1){
	   	returnvalue = true;
	   }else{
	   	returnvalue = false;
	   }

	   return returnvalue;
       //throw new UnsupportedOperationException();
   }

	/**
	 * @param customercontactid
     * @param name
     * @param phone
     * @param email
     * @param company
     */
   public boolean updateCustomerContactInfo(int customercontactid, String name, String phone, String email, String company, String info) {
   	// TODO - implement EODatabaseInterface.updateCustomerContactInfo
       boolean returnvalue = false;
       String sql = "UPDATE 'EOCustomerContactInfo' SET name = '" + name + "', phone = '" + phone + "', email = '" + email + "', company = '" + company + "', info = '" + info + "'";

       if(this.executeSql(sql) == 4){
           returnvalue = true;
       }else{
           returnvalue = false;
       }

       return returnvalue;

      //throw new UnsupportedOperationException();
   }

   private int executeSql(String sql)
   {
      int returnvalue = -1;
      PreparedStatement pstmt = null;
   
      try
      {
         conn = DriverManager.getConnection(this.dbPathAbsolute);
         System.out.println("DB CONNECTION OPENED");
      
         pstmt = conn.prepareStatement(sql);

         System.out.println("EXECUTING SQL ...");
         returnvalue = pstmt.executeUpdate();

         System.out.println("SQL SUCCESS: " + returnvalue);
         //System.out.println(returnvalue);
      }
      catch (Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

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
         System.out.println("DB CONNECTION OPENED");
      
         pstmt = conn.prepareStatement(sql);

         System.out.println("EXECUTING SQL QUERY ...");
         rs = pstmt.executeQuery();
      
         return rs;
      }
      catch (Exception e)
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      return rs;
   }

}