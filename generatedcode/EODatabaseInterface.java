import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class EODatabaseInterface {
   Connection conn = null;

   String dbPathAbsolute = "jdbc:sqlite:/Users/philipbarkow/Library/Mobile Documents/com~apple~CloudDocs/Datamatiker/1. semester/PlanOrg/generatedcode/database.db";
   String dbPathRelative = "jdbc:sqlite:database.db";

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
	 * @param cCIObj
	 */
   public CustomerContactInfo getCustomerContactInfo(CustomerContactInfo cCIObj) {
   		int id = cCIObj.getId();
   		String sql = "";

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
	 * @param fCIObj
	 */
   public boolean createFacilitatorContactInfo(FacilitatorContactInfo fCIObj) {

	   FacilitatorContactInfo f = fCIObj;
	   boolean  returnvalue 	= false;

	   String 	deletedStatus	= "2";
	   String 	name 			= f.getName();
	   String	phone			= f.getPhone();
	   String	email			= f.getEmail();
	   String	info			= f.getInfo();
	   String	SQL				= "";

	   SQL += "INSERT INTO 'EOFacilitatorContactInfo' (deletedStatus, name, phone, email, info) VALUES (";
	   SQL += "'" + deletedStatus 	+ "',";
	   SQL += "'" + name 			+ "',";
	   SQL += "'" + phone 			+ "',";
	   SQL += "'" + email 			+ "',";
	   SQL += "'" + info 			+ "')";

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
	 * @param cECObj
	 *
	 */
   public boolean createExternalContactInfo(ExternalContactInfo cECObj) {
	   ExternalContactInfo e 	= cECObj;
	   boolean  returnvalue 	= false;

	   String 	deletedStatus	= "2";
	   String 	name 			= e.getName();
	   String	phone			= e.getPhone();
	   String	email			= e.getEmail();
	   String	info			= e.getInfo();
	   String 	company 		= e.getCompany();
	   String	SQL				= "";


	   SQL += "INSERT INTO 'EOExternalContactInfo' (deletedStatus, name, phone, email, info, company) VALUES (";
	   SQL += "'" + deletedStatus 	+ "',";
	   SQL += "'" + name 			+ "',";
	   SQL += "'" + phone 			+ "',";
	   SQL += "'" + email 			+ "',";
	   SQL += "'" + info 			+ "',";
	   SQL += "'" + company			+ "')";

	   if(executeSql(SQL) == 1){
		   returnvalue = true;
	   }else{
		   returnvalue = false;
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
	 * @param cCIObj
     *
	 */
   public boolean createCustomerContactInfo(CustomerContactInfo cCIObj) {
   	   CustomerContactInfo c 	= cCIObj;

   	   boolean  returnvalue 	= false;

   	   String 	deletedStatus	= "2";
	   String 	name 			= c.getName();
	   String	phone			= c.getPhone();
	   String	email			= c.getEmail();
	   String	company			= c.getCompany();
	   String	info			= c.getInfo();
	   String	SQL				= "";

	   SQL += "INSERT INTO 'EOCustomerContactInfo' (deletedStatus, name, phone, email, company, info) VALUES (";
	   SQL += "'" + deletedStatus 	+ "',";
	   SQL += "'" + name 			+ "',";
	   SQL += "'" + phone 			+ "',";
	   SQL += "'" + email 			+ "',";
	   SQL += "'" + company 		+ "',";
	   SQL += "'" + info 			+ "')";

	   if(executeSql(SQL) == 1){
	   	returnvalue = true;
	   }else{
	   	returnvalue = false;
	   }

	   return returnvalue;
   }

	/**
	 * 
	 * @param customercontactid
	 */
   public boolean deleteCustomerContactInfo(int customercontactid) {
	   boolean returnvalue = false;
	   String sql = "UPDATE 'EOCustomerContactInfo' SET deletedStatus = '3' WHERE idEOContactInfo = " + customercontactid;

	   if(this.executeSql(sql) == 1){
	   	returnvalue = true;
	   }else{
	   	returnvalue = false;
	   }

	   return returnvalue;
   }

	/**
	 * @param customercontactid
     * @param name
     * @param phone
     * @param email
     * @param company
     */
   public boolean updateCustomerContactInfo(int customercontactid, String name, String phone, String email, String company, String info) {

       boolean returnvalue = false;
       String sql = "UPDATE 'EOCustomerContactInfo' SET name = '" + name + "', phone = '" + phone + "', email = '" + email + "', company = '" + company + "', info = '" + info + "' WHERE idEOContactInfo =" + customercontactid;

       if(this.executeSql(sql) == 1){
           returnvalue = true;
       }else{
           returnvalue = false;
       }

       return returnvalue;

   }

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

   private int executeSql(String sql)
   {
      int returnvalue = -1;
      PreparedStatement pstmt = null;
   
      try
      {
         conn = DriverManager.getConnection(this.dbPathRelative);
         System.out.println("DB CONNECTION OPENED");
      
         pstmt = conn.prepareStatement(sql);

         System.out.println("EXECUTING SQL ...");
         returnvalue = pstmt.executeUpdate();

         System.out.println("SQL SUCCESS: " + returnvalue);
         //System.out.println(returnvalue);
		  System.out.println("CLOSING DB CONNECTION ...");

		  if(conn != null)
		  {
			  conn.close();
			  System.out.println("DB CONNECTION CLOSED");
		  }

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
		  conn = DriverManager.getConnection(this.dbPathRelative);
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

	private void closeConnection(ResultSet rs)
	{
		try
		{
			if(conn != null)
			{
				System.out.println("CLOSING DB CONNECTION");
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

}