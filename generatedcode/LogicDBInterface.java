import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface LogicDBInterface{
    public void truncateDB();
    public boolean createLink(Link c);
    public boolean createLink(String table, String identifier1, int value1, String identifier2, int value2);
    public EOArrangement[] getAllEOArrangementsFromFacilitator(FacilitatorContactInfo facilitator);
    public EOArrangement[] getEOArrangementsFromEventsFacilitator(FacilitatorContactInfo facilitator);
    public EOArrangement[] getEOArrangementsFromFacilitator(FacilitatorContactInfo facilitator);
    public EOArrangement[] getEOArrangements(boolean includeIsDone);

    public FacilitatorContactInfo[] getFacilitatorContactInfoFromEOArrangement(int arrangementid);
    public FacilitatorContactInfo[] getFacilitatorContactInfoFromEOEvent(int eventid);
    public ExternalContactInfo getExternalContactInfoFromEOEventType(int eventtypeid);
    public CustomerContactInfo getCustomerContactInfoFromEOArrangement(int arrangementid);

    public FacilitatorContactInfo[] getFacilitatorsContactInfo(int arrangementid);
    public int numRows(String sql);
    public int numRows(String sql, String cselector);

    public EOEvent[] getEOEvents();
    public EOEvent[] getEOEvents(int arrangementid);
    public EOEventType[] getEOEventTypes(int eventid);
    public EOEventType[] getEOEventTypes();
    public boolean createEOArrangement(EOArrangement aObj);
    public void updateEOArrangement(EOArrangement arrangement);
    public boolean deleteEOArrangement(EOArrangement arrangement);
    public boolean createEOEvent(EOEvent event);

    public void updateEOEvent(EOEvent event);
    public void deleteEOEvent(int eventid);
    public boolean createEOEvenType(EOEventType eventtype);
    public boolean linkEOEventTypeExternalContactInfo(int eventtypeid, int externalid);
    public boolean updateEOEvenType(EOEventType eventtype);
    public boolean deleteEOEvenType(EOEventType eventtype);

    public boolean createFacilitatorContactInfo(FacilitatorContactInfo fCIObj);
    public boolean deleteFacilitatorContactInfo(FacilitatorContactInfo fCIObj);
    public boolean updateFacilitatorContactInfo(FacilitatorContactInfo fCIObj);
    public FacilitatorContactInfo getFacilitatorContactInfo(int id);
    public FacilitatorContactInfo getFacilitatorContactInfo(FacilitatorContactInfo fCIObj);
    public FacilitatorContactInfo[] getAllFacilitatorContactInfo();
    public boolean  updateFacilitatorContactInfo(FacilitatorContactInfo fCIObj, boolean printSqlStatment);
    public int      createExternalContactInfo(ExternalContactInfo cECObj);
    public boolean  deleteExternalContactInfo(ExternalContactInfo eCIObj);
    public boolean  updateExternalContactInfo(ExternalContactInfo eCIObj);

    public ExternalContactInfo getExternalContactInfo(int id);
    public ExternalContactInfo getExternalContactInfo(ExternalContactInfo eCIObj) ;
    public ExternalContactInfo[] getAllExternalContactInfo();

    public boolean createCustomerContactInfo(CustomerContactInfo cCIObj);
    public boolean deleteCustomerContactInfo(CustomerContactInfo cCIObj);
    public boolean updateCustomerContactInfo(CustomerContactInfo cCIObj);
    public CustomerContactInfo getCustomerContactInfo(int id);

    public CustomerContactInfo getCustomerContactInfo(CustomerContactInfo cCIObj);
    public CustomerContactInfo[] getAllCustomerContactInfo();

    public int executeSql(String sql);
    public int getNotDeletedRowCountFromTable(String tablename);
    public int getLastId(String table, String column);
    public ResultSet querySql(String sql);
    public void closeConnection(ResultSet rs);
}
