import java.time.LocalDateTime;
/**
* The EOManager class is the class that talks with the GUI (EOGUI) and the Database (EODatabaseInterface).
* It gets the data from the user interface, and translate this into data that is handed over to the database.
*/
public class EOManager {
   EOGUI               gui;
   EODatabaseInterface db;
   
   public EOManager()
   {
      //Database connections needs to be established before GUI!
      this.db = new EODatabaseInterface();
      this.gui = new EOGUI(this);
   
   
   }
	/**
	 * 
	 * The main metode in EOManager, all operations from the GUI is ran through this metode. The metode return the operation that it wishes the GUI to execute (which panel it needs to show, with what data).
	 */
   public EOOperation runCommand(EOOperation operation) {
      //Test data
         
      System.out.println("EOManager.runCommand(" + operation + ")");
      // TODO - implement EOManager.runCommand
      switch(operation)
      {
         case START:
            EOOperation.START.setData(db.getEOArrangements(false));
            //EOOperation.START.setData();         
            if(gui != null)
            {
               gui.getBreadcrumb().reset();
            }
            //We reset our EOOperations, that we want to have active as we are running in that content
            EOOperation.CREATEARRANGEMENT.setData(null);
            EOOperation.UPDATEARRANGEMENT.setData(null);            
            break;
         case STARTSHOWALL:
                    
            EOOperation.STARTSHOWALL.setData(db.getEOArrangements(true));
            if(gui != null)
            {
               gui.getBreadcrumb().reset();
            }
            break;
      //Import            
         case IMPORT:
            gui.getBreadcrumb().push(EOOperation.IMPORT);
            break; 
         case IMPORTCSV:
            try
            {
               EOCSV eocsv = (EOCSV)EOOperation.IMPORTCSV.getData();
               eocsv.setDB(db);
               eocsv.importCSV();
               operation = EOOperation.START;
            }
            catch(Exception e)
            {
               System.out.println("import CSV file failed: " + e.getMessage());
               operation = EOOperation.ERROR;
            }
            gui.getBreadcrumb().pop();
            break;             
      //Export         
         case EXPORT:
            Object[] export_date = {
                db.getAllFacilitatorContactInfo(),
               db.getEOArrangements(false)
               };
            EOOperation.EXPORT.setData(export_date);
            gui.getBreadcrumb().push(EOOperation.EXPORT);
            break;
         case SAVECSV:
            EOOperation.EXPORT.setData(null);
            if(EOOperation.SAVECSV.getData() instanceof EOCSV)
            {
               try
               {
                  EOCSV eocsv = (EOCSV)EOOperation.SAVECSV.getData();
                  eocsv.setDB(db);
                  eocsv.createCSV();
                  operation = EOOperation.START;
               }
               catch(Exception e)
               {
                  System.out.println("CSV file failed to be created: " + e.getMessage());
                  operation = EOOperation.ERROR;
               }
            }
            gui.getBreadcrumb().pop();
            break;
      //Arrangement
         case OPENARRANGEMENT:
            gui.getBreadcrumb().push(EOOperation.OPENARRANGEMENT);
            break;               
         case CREATEARRANGEMENT:
            if(EOOperation.CREATEARRANGEMENT.getData() == null)
            {
               Object[] createarrangement_data = {
                  db.getAllFacilitatorContactInfo(),
                  new EOArrangement()
                  };
               EOOperation.CREATEARRANGEMENT.setData(createarrangement_data);
            }
            else
            {
               if(EOOperation.CREATEARRANGEMENT.getData().getClass().isArray())
               {
                  Object[] createarrangement_data = (Object[]) EOOperation.CREATEARRANGEMENT.getData();
                  if(createarrangement_data[0] instanceof FacilitatorContactInfo[])
                  {
                     //We update the facilitatorcontactinfo list, since its not specific to the arrangement
                     createarrangement_data[0] = db.getAllFacilitatorContactInfo();
                  }
               }
               
            }
            gui.getBreadcrumb().reset();
            gui.getBreadcrumb().push(EOOperation.CREATEARRANGEMENT);
            break;
         case UPDATEARRANGEMENT:
            //New call to update
            if(EOOperation.UPDATEARRANGEMENT.getData() instanceof EOArrangement)
            {
               System.out.println("New call to UPDATEARRANGEMENT");
               Object[] updatearrangement_data = {
                  db.getAllFacilitatorContactInfo(),
                  EOOperation.UPDATEARRANGEMENT.getData()
                  };
               EOOperation.UPDATEARRANGEMENT.setData(updatearrangement_data);
            }
            else //Refresh call
            {
               System.out.println("REFRESH call to UPDATEARRANGEMENT");
               if(EOOperation.UPDATEARRANGEMENT.getData().getClass().isArray())
               {
                  Object[] updatearrangement_data = (Object[]) EOOperation.UPDATEARRANGEMENT.getData();
                  if(updatearrangement_data[0] instanceof FacilitatorContactInfo[])
                  {
                     //We update the facilitatorcontactinfo list, since its not specific to the arrangement
                     updatearrangement_data[0] = db.getAllFacilitatorContactInfo();
                  }
               }
            }
         
            
            gui.getBreadcrumb().push(EOOperation.UPDATEARRANGEMENT);
            break;
         case SAVEDELETEARRANGEMENT:
            if(EOOperation.SAVEDELETEARRANGEMENT.getData() instanceof EOArrangement)
            {
               System.out.println("Slet arrangement");
               db.deleteEOArrangement((EOArrangement)EOOperation.DELETEARRANGEMENT.getData());
               EOOperation.START.setData(db.getEOArrangements(false));
               operation = EOOperation.START;
            }
            else
            {
               operation = EOOperation.ERROR;
            }
            //Databasekald
            gui.getBreadcrumb().reset();
         
            break;
         case DELETEARRANGEMENT:
            gui.getBreadcrumb().push(EOOperation.DELETEARRANGEMENT);
         //ADM Facilitators            
         case ADMFACILITATOR:
            EOOperation.ADMFACILITATOR.setData(db.getAllFacilitatorContactInfo());
            gui.getBreadcrumb().push(EOOperation.ADMFACILITATOR);
            break;
         case CREATEFACILITATOR:
            db.createFacilitatorContactInfo((FacilitatorContactInfo) EOOperation.CREATEFACILITATOR.getData());
            FacilitatorContactInfo[] allFacilConInfoCreate = db.getAllFacilitatorContactInfo();
            if(allFacilConInfoCreate != null){
               EOOperation.CREATEFACILITATOR.setData(allFacilConInfoCreate);
            }
            break;
         case DELETEFACILITATOR:
            db.deleteFacilitatorContactInfo((FacilitatorContactInfo) EOOperation.DELETEFACILITATOR.getData());
            break;
         case UPDATEFACILITATOR:
            db.getFacilitatorContactInfo((FacilitatorContactInfo) EOOperation.UPDATEFACILITATOR.getData());
            break;
         case SAVEEDITFACILITATOR: //This metode is used when UPDATEFACILITATOR saves its data
            db.updateFacilitatorContactInfo((FacilitatorContactInfo) EOOperation.SAVEEDITFACILITATOR.getData());
            FacilitatorContactInfo[] allFacilConInfoSave = db.getAllFacilitatorContactInfo();
            if(allFacilConInfoSave != null){
               EOOperation.SAVEEDITFACILITATOR.setData(allFacilConInfoSave);
            }
            System.out.println(EOOperation.SAVEEDITFACILITATOR);
            break;
         case OPENFACILITATOR:
            gui.getBreadcrumb().push(EOOperation.OPENFACILITATOR);
            break;
      //ADM EventTypes            
         case ADMEVENTTYPE:
            EOOperation.ADMEVENTTYPE.setData(db.getEOEventTypes());
            gui.getBreadcrumb().push(EOOperation.ADMEVENTTYPE);
            break;
         case CREATEEVENTTYPE:
            if(EOOperation.CREATEEVENTTYPE.getData() instanceof EOEventType)
            {
               if(db.createEOEvenType((EOEventType) EOOperation.CREATEEVENTTYPE.getData()))
               {
                  EOOperation.CREATEEVENTTYPE.setData(db.getEOEventTypes());
               }
               else
               {
                  operation = EOOperation.ERROR;
               }
            }
            break;
         case UPDATEEVENTTYPE:
            Object[] updateeventtype_data = {
                  EOOperation.UPDATEEVENTTYPE.getData(),
                  db.getEOEventTypes()
               };
            EOOperation.UPDATEEVENTTYPE.setData(updateeventtype_data);
            break;
         case SAVEEDITEVENTTYPE:
            if(EOOperation.SAVEEDITEVENTTYPE.getData() instanceof EOEventType)
            {
               if(db.updateEOEvenType((EOEventType) EOOperation.SAVEEDITEVENTTYPE.getData()))
               {
                  EOOperation.SAVEEDITEVENTTYPE.setData(db.getEOEventTypes());
               }
               else
               {
                  operation = EOOperation.ERROR;
               }
            }
            break;
         case DELETEEVENTTYPE:
            if(EOOperation.DELETEEVENTTYPE.getData() instanceof EOEventType)
            {
               if(db.deleteEOEvenType((EOEventType) EOOperation.DELETEEVENTTYPE.getData()))
               {
                  EOOperation.DELETEEVENTTYPE.setData(db.getEOEventTypes());
               }
               else
               {
                  operation = EOOperation.ERROR;
               }
            }
            break;
         case OPENEVENTTYPE:
            gui.getBreadcrumb().push(EOOperation.OPENEVENTTYPE);
            break;
      //Event
         case CREATEEVENT:
            //We get a EOArrangement object from either UPDATEARRANGEMENT or CREATEARRANGEMENT, that object we want to take with us, so we can save the EOEvent in it
            if(EOOperation.CREATEEVENT.getData() instanceof EOArrangement)
            {
               Object[] createevent_data = {
                  db.getAllFacilitatorContactInfo(),
                  db.getEOEventTypes(),
                  EOOperation.CREATEEVENT.getData()
                  };
               EOOperation.CREATEEVENT.setData(createevent_data);
            }
            else  //If we dont get an EOArrangement Object, its a call back to the metode, in this case we want to update our facilitatorcontactinfo and eventtypes - But only if we got an EOArrangement object
            {
               if(EOOperation.CREATEEVENT.getData() != null && EOOperation.CREATEEVENT.getData().getClass().isArray())
               {
                  Object[] createevent_data = (Object[]) EOOperation.CREATEEVENT.getData();
                  if(createevent_data.length == 3 && createevent_data[2] instanceof EOArrangement)
                  {
                     Object[] createevent_data1 = {
                        db.getAllFacilitatorContactInfo(),
                        db.getEOEventTypes(),
                        createevent_data[2]
                        };
                     EOOperation.CREATEEVENT.setData(createevent_data1);
                  }
               }
            }
         
            gui.getBreadcrumb().push(EOOperation.CREATEEVENT);
            break;
         case SAVECREATEEVENT:
            break;
         case UPDATEEVENT:
            Object[] updateevent_data = {
               db.getAllFacilitatorContactInfo(),
               db.getEOEventTypes(),
               EOOperation.UPDATEEVENT.getData()
               };
            EOOperation.UPDATEEVENT.setData(updateevent_data);
         
            gui.getBreadcrumb().push(EOOperation.UPDATEEVENT);
            break;     
         case OPENEVENT:
            gui.getBreadcrumb().push(EOOperation.OPENEVENT);
            break;            
      
      
         default:
            break;
      }
      System.out.println("Current: "+operation);
      return(operation);
   }
}