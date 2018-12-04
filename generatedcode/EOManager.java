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
      //Test data
            ExternalContactInfo extc = new ExternalContactInfo(-1, "Skipper Jack", "99990000", "skipper@hvalfangeren.dk", "Jack er normalt at traeffe mellem 9 - 10. Hvis du ringer foer kl 8 saa foer du verbale taesk af skipperen", "Den Gule Skipper");   
            CustomerContactInfo c = new CustomerContactInfo(-1, "Kunde 1", "22224444", "kunde@kunde.dk", "Er fra en stor virksomhed", "Coca cola");
  
            FacilitatorContactInfo[] facilitator = new FacilitatorContactInfo[3];
            facilitator[0] = new FacilitatorContactInfo(1, "Martin Nikolajsen", "22224444", "kunde@kunde.dk", "Arbejder bedre derhjemme :)");
            facilitator[1] = new FacilitatorContactInfo(2, "Rasmus Neo Lassen", "22224444", "kunde@kunde.dk", "Her er der en tekst om facilitatoren");
            facilitator[2] = new FacilitatorContactInfo(3, "Frederik Nyborg", "22224444", "kunde@kunde.dk", "Her er der endnu en facilitator beskrivelse");
            FacilitatorContactInfo[] facilitatorselected = new FacilitatorContactInfo[1];
            facilitatorselected[0] = facilitator[1];
            EOEventType[] eventtypes = new EOEventType[2];
            eventtypes[0] = new EOEventType(1, "Kano tur", "Kunderne er glade for den her", "holstebro rasteplads", "Ringsted", 38, 500, extc);
            eventtypes[1] = new EOEventType(2, "Vandski", "Ekstrem!", "Roskildefjor", "Roskilde", 38, 500, extc);
            EOEvent[] events = new EOEvent[2];
            events[0] = new EOEvent(1, "description", LocalDateTime.now(), LocalDateTime.now(), 100, facilitatorselected, eventtypes);
            events[1] = new EOEvent(1, "Mere description", LocalDateTime.now(), LocalDateTime.now(), 100, facilitator, eventtypes);      
            EOArrangement[] arrangements = new EOArrangement[5];
            arrangements[0] = new EOArrangement(1, "START 1", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, true, facilitator, events, c);
            arrangements[1] = new EOArrangement(2, "START 2", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, true, null, null, c);
            arrangements[2] = new EOArrangement(3, "START 3", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, true, facilitator, null, null);
            arrangements[3] = new EOArrangement(4, "START 4", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, true, null, events, null); 
            
            EOArrangement[] ssarrangements = new EOArrangement[5];
            ssarrangements[0] = new EOArrangement(1, "STARTSHOWALL 1", "description", LocalDateTime.now(), LocalDateTime.now(), 100, false, true, null, null, null);
            ssarrangements[1] = new EOArrangement(2, "STARTSHOWALL 2", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, false, null, null, null);
            ssarrangements[2] = new EOArrangement(3, "STARTSHOWALL 3", "description", LocalDateTime.now(), LocalDateTime.now(), 100, false, true, null, null, null);
            ssarrangements[3] = new EOArrangement(4, "STARTSHOWALL 4", "description", LocalDateTime.now(), LocalDateTime.now(), 100, true, false, null, null, null);                
      System.out.println("EOManager.runCommand(" + operation + ")");
      // TODO - implement EOManager.runCommand
      switch(operation)
      {
         case START:
                      
            EOOperation.START.setData(arrangements);         
            if(gui != null)
            {
               gui.getBreadcrumb().reset();
            }
            break;
         case STARTSHOWALL:
                    
            EOOperation.STARTSHOWALL.setData(ssarrangements);         
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
            gui.getBreadcrumb().pop();
            break;             
//Export         
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
            if(EOOperation.SAVECSV.getData() instanceof EOCSV)
            {
               try
               {
                  EOCSV eocsv = (EOCSV)EOOperation.SAVECSV.getData();
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
            gui.getBreadcrumb().reset();
            gui.getBreadcrumb().push(EOOperation.CREATEARRANGEMENT);
            break;
         case UPDATEARRANGEMENT:
            Object[] updatearrangement_data = {
               EOOperation.UPDATEARRANGEMENT.getData(),
               db.getAllFacilitatorContactInfo(),
               null
            };
            EOOperation.UPDATEARRANGEMENT.setData(updatearrangement_data);
            gui.getBreadcrumb().push(EOOperation.UPDATEARRANGEMENT);
            break;
         case SAVEDELETEARRANGEMENT:
            if(EOOperation.SAVEDELETEARRANGEMENT.getData() instanceof EOArrangement)
            {
               //db.deleteEOArrangement((EOArrangement)EOOperation.DELETEARRANGEMENT.getData());
            }
            //Databasekald
            gui.getBreadcrumb().reset();
            operation = EOOperation.START;
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
               db.createEOEvenType((EOEventType) EOOperation.CREATEEVENTTYPE.getData());
               EOOperation.CREATEEVENTTYPE.setData(db.getEOEventTypes());
            }
            break;
         case UPDATEEVENTTYPE:
               EOOperation.UPDATEEVENTTYPE.setData(db.getEOEventTypes());
            break;
         case SAVEEDITEVENTTYPE:
            if(EOOperation.SAVEEDITEVENTTYPE.getData() instanceof EOEventType)
            {
               db.updateEOEvenType((EOEventType) EOOperation.SAVEEDITEVENTTYPE.getData());
               EOOperation.SAVEEDITEVENTTYPE.setData(db.getEOEventTypes());
            }
            break;
         case DELETEEVENTTYPE:
            if(EOOperation.DELETEEVENTTYPE.getData() instanceof EOEventType)
            {
               db.deleteEOEvenType((EOEventType) EOOperation.DELETEEVENTTYPE.getData());
               EOOperation.DELETEEVENTTYPE.setData(db.getEOEventTypes());
            }
            break;
         case OPENEVENTTYPE:
            gui.getBreadcrumb().push(EOOperation.OPENEVENTTYPE);
            break;
//Event
         case CREATEEVENT:
            gui.getBreadcrumb().push(EOOperation.CREATEEVENT);
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