import java.time.LocalDateTime;

public class EOManager {
   EOGUI               gui;
   EODatabaseInterface db;
   
   public EOManager()
   {

      this.gui = new EOGUI(this);
      this.db = new EODatabaseInterface();

   }
	/**
	 * 
	 * @param operation
	 */
   public EOOperation runCommand(EOOperation operation) {
      System.out.println("EOManager.runCommand(" + operation + ")");
      // TODO - implement EOManager.runCommand
      switch(operation)
      {
         case START:
            EOArrangement[] arrangements = new EOArrangement[5];
            arrangements[0] = new EOArrangement(1, "START 1", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, true, null, null, null);
            arrangements[1] = new EOArrangement(2, "START 2", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, true, null, null, null);
            arrangements[2] = new EOArrangement(3, "START 3", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, true, null, null, null);
            arrangements[3] = new EOArrangement(4, "START 4", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, true, null, null, null);                        
            EOOperation.START.setData(arrangements);         
            if(gui != null)
            {
               gui.getBreadcrumb().reset();
            }
            break;
         case STARTSHOWALL:
            EOArrangement[] ssarrangements = new EOArrangement[5];
            ssarrangements[0] = new EOArrangement(1, "STARTSHOWALL 1", "description", LocalDateTime.now(), LocalDateTime.now(), 100, false, true, null, null, null);
            ssarrangements[1] = new EOArrangement(2, "STARTSHOWALL 2", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, false, null, null, null);
            ssarrangements[2] = new EOArrangement(3, "STARTSHOWALL 3", "description", LocalDateTime.now(), LocalDateTime.now(), 100, false, true, null, null, null);
            ssarrangements[3] = new EOArrangement(4, "STARTSHOWALL 4", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, false, null, null, null);                        
            EOOperation.STARTSHOWALL.setData(ssarrangements);         
            if(gui != null)
            {
               gui.getBreadcrumb().reset();
            }
            break;
         case IMPORT:
            gui.getBreadcrumb().push(EOOperation.IMPORT);
            break; 
         case IMPORTCSV:
            gui.getBreadcrumb().pop();
            break;             
         case EXPORT:
            // Udkommenteret da Philip anvender SQLite driveren med en absolut sti

            FacilitatorContactInfo[] allFacilConInfo = db.getAllFacilitatorContactInfo();
         
            if(allFacilConInfo != null){
               EOOperation.EXPORT.setData(allFacilConInfo);
            }else{
               //EOOperation.EXPORT.setData(allFacilConInfo)
            }

         
            gui.getBreadcrumb().push(EOOperation.EXPORT);
            break;
         case SAVECSV:
            EOOperation.EXPORT.setData(null);
            EOCSV eocsv = (EOCSV)EOOperation.SAVECSV.getData();
            try
            {
               eocsv.createCSV();
               operation = EOOperation.START;
            }
            catch(Exception e)
            {
               System.out.println("CSV file failed to be created: " + e.getMessage());
               operation = EOOperation.ERROR;
            }
            gui.getBreadcrumb().pop();
            break;
         case OPENARRANGEMENT:
            gui.getBreadcrumb().push(EOOperation.OPENARRANGEMENT);
            break;               
         case CREATEARRANGEMENT:
            gui.getBreadcrumb().reset();
            gui.getBreadcrumb().push(EOOperation.CREATEARRANGEMENT);
            break;
         case UPDATEARRANGEMENT:
            gui.getBreadcrumb().push(EOOperation.UPDATEARRANGEMENT);
            break;                    
         case DELETEARRANGEMENT:
            gui.getBreadcrumb().push(EOOperation.DELETEARRANGEMENT);
         case SAVEDELETEARRANGEMENT:
            EOArrangement savedeletearrangement = (EOArrangement) EOOperation.SAVEDELETEARRANGEMENT.getData();
            //Databasekald
            gui.getBreadcrumb().reset();
            break;
         case ADMFACILITATOR:
            FacilitatorContactInfo[] allFacilConInfo1 = db.getAllFacilitatorContactInfo();

            if(allFacilConInfo1 != null){
               EOOperation.ADMFACILITATOR.setData(allFacilConInfo1);
            }else{
               //EOOperation.EXPORT.setData(allFacilConInfo)
            }
            gui.getBreadcrumb().push(EOOperation.ADMFACILITATOR);
            break;
         case UPDATEFACILITATOR:
            gui.getBreadcrumb().push(EOOperation.UPDATEFACILITATOR);
            db.getFacilitatorContactInfo((FacilitatorContactInfo) EOOperation.UPDATEFACILITATOR.getData());
            break;
         case ADMEVENTTYPE:
            gui.getBreadcrumb().push(EOOperation.ADMEVENTTYPE);
            break;
         case CREATEEVENT:
            gui.getBreadcrumb().push(EOOperation.CREATEEVENT);
            break;
         case CREATEFACILITATOR:
            db.createFacilitatorContactInfo((FacilitatorContactInfo) EOOperation.CREATEFACILITATOR.getData());
            break;
         case DELETEFACILITATOR:
            db.deleteFacilitatorContactInfo((FacilitatorContactInfo) EOOperation.DELETEFACILITATOR.getData());
            break;
         case SAVEEDITFACILITATOR:
            db.updateFacilitatorContactInfo((FacilitatorContactInfo) EOOperation.SAVEEDITFACILITATOR.getData());
            System.out.println(EOOperation.SAVEEDITFACILITATOR);
            break;
         default:
            break;
      }
      System.out.println("Current: "+operation);
      return(operation);
   }
}