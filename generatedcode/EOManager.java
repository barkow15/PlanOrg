import java.time.LocalDateTime;

public class EOManager {
   EOGUI gui;
   
   public EOManager()
   {
      gui = new EOGUI(this);
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
         case EXPORT:
            FacilitatorContactInfo[] f = new FacilitatorContactInfo[5];
            f[0] = new FacilitatorContactInfo(1, "Martin Nikolajsen" + Integer.toString((int) Math.floor(Math.random() * 101)), "35323232", "mkn@soc.ku.dk", "This is info");
            f[1] = new FacilitatorContactInfo(2, "Rasmus Neo Lassen" + Integer.toString((int) Math.floor(Math.random() * 101)), "35323232", "mkn@soc.ku.dk", "This is info");
            f[2] = new FacilitatorContactInfo(3, "Postmand Per" + Integer.toString((int) Math.floor(Math.random() * 101)), "35323232", "mkn@soc.ku.dk", "This is info");
            f[3] = new FacilitatorContactInfo(4, "Svend Agner" + Integer.toString((int) Math.floor(Math.random() * 101)), "35323232", "mkn@soc.ku.dk", "This is info");
            f[4] = new FacilitatorContactInfo(5, "Hans Kristen Torbensen" + Integer.toString((int) Math.floor(Math.random() * 101)), "35323232", "mkn@soc.ku.dk", "This is info");
            EOOperation.EXPORT.setData(f);
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
            gui.getBreadcrumb().push(EOOperation.CREATEARRANGEMENT);
            break;
         case UPDATEARRANGEMENT:
            gui.getBreadcrumb().push(EOOperation.UPDATEARRANGEMENT);
            break;                    
         case DELETEARRANGEMENT:
            gui.getBreadcrumb().push(EOOperation.DELETEARRANGEMENT);
            break;
         case ADMFACILITATOR:
            gui.getBreadcrumb().push(EOOperation.ADMFACILITATOR);
            break;
         case ADMEVENTTYPE:
            gui.getBreadcrumb().push(EOOperation.ADMEVENTTYPE);
            break;
         default:
            break;
      }
      System.out.println("Current: "+operation);
      return(operation);
   }
}