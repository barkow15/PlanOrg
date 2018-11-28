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
   	// TODO - implement EOManager.runCommand
      switch(operation)
      {
         case START:
            gui.getBreadcrumb().reset();
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