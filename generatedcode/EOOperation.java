/**
* All operations in the Event Organizer program is tied to an EOOperation, the EOOperation ENUM tells the program what it must do and it contains the data it must do it with. The DisplayType is used to tell the EOGUI which panel it must show. while the data the EOOperation has through the getData() or setData is mainly used in the EOPanels and EOManager.
*/
public enum EOOperation {
   START (null, "Start menu", EODisplayType.START), //Martin - Mangler DB
   STARTSHOWALL (null, "Start menu", EODisplayType.START), //Martin - Mangler DB
   
   CREATEARRANGEMENT (null, "Opret Arrangement", EODisplayType.CREATEARRANGEMENT), //Marcus
   UPDATEARRANGEMENT (null, "Opdater Arrangement", EODisplayType.UPDATEARRANGEMENT), //Marcus
   SAVECREATEARRANGMENT(null, "Opret Arrangement", EODisplayType.START), // Philip
   SAVEUPDATEARRANGEMENT (null, "Opdater Arrangement", EODisplayType.START), //Marcus
   DELETEARRANGEMENT (null, "Slet Arrangement", EODisplayType.DELETEARRANGEMENT), //Marcus
   SAVEDELETEARRANGEMENT (null, "Slet Arrangement", EODisplayType.DELETEARRANGEMENT), //Marcus
   OPENARRANGEMENT (null, "Vis Arrangement", EODisplayType.OPENARRANGEMENT), //Martin - Mangler DB
   
   CREATEEVENT (null, "Opret Begivenhed", EODisplayType.CREATEEVENT),  //Rasmus
   UPDATEEVENT (null, "Opdater Begivenhed", EODisplayType.UPDATEEVENT),  //Rasmus
   DELETEEVENT (null, "Slet Begivenhed", EODisplayType.DELETEEVENT), //Rasmus
   SAVECREATEEVENT (null, "Gem Event", EODisplayType.START), //Rasmus
   OPENEVENT (null, "Vis Begivenhed", EODisplayType.OPENEVENT),  //Rasmus
      
   IMPORT (null, "Importer", EODisplayType.IMPORT), //Martin - DONE
   IMPORTCSV (null, "Importer", EODisplayType.START), //Faelles  
   EXPORT (null, "Exporter", EODisplayType.EXPORT), //Martin - DONE
   SAVECSV (null, "Gem CSV", EODisplayType.START), //Faelles 
   
   ADMFACILITATOR (null, "Administrer Facilitatorer", EODisplayType.ADMFACILITATOR), //Frederik
   UPDATEFACILITATOR (null,"Rediger Facilitator",EODisplayType.ADMFACILITATOR), //Frederik
   CREATEFACILITATOR (null,"Opret Facilitator",EODisplayType.ADMFACILITATOR), //Frederik
   DELETEFACILITATOR (null,"Slet facilitator",EODisplayType.ADMFACILITATOR), //Frederik
   SAVEEDITFACILITATOR(null,"Gem Opdatering",EODisplayType.ADMFACILITATOR),//Frederik
   OPENFACILITATOR (null,"Vis Facilitator",EODisplayType.OPENFACILITATOR),
  
   ADMEVENTTYPE (null, "Administrer Begivenhedstyper", EODisplayType.ADMEVENTTYPE), //Martin
   CREATEEVENTTYPE (null,"Opret Begivenhedstype",EODisplayType.ADMEVENTTYPE), //Frederik
   UPDATEEVENTTYPE (null,"Rediger Begivenhedstype",EODisplayType.ADMEVENTTYPE), //Frederik
   SAVEEDITEVENTTYPE(null,"Gem Opdatering",EODisplayType.ADMEVENTTYPE),//Frederik
   DELETEEVENTTYPE(null,"Slet Begivenhedstype",EODisplayType.ADMEVENTTYPE), //Frederik
   OPENEVENTTYPE (null,"Vis Begivenhedstype",EODisplayType.OPENEVENTTYPE), //Frederik

   ERROR (null, "Fejl Skaerm", EODisplayType.ERROR); //Martin
 
   private Object data = null;
   private EODisplayType displaytype;
   private String displayname;
	/**
	 * Sets the specified data
    */
   public void setData(Object data) {
      this.data = data;
   }
	/**
	 * gets the specified data
    */
   public Object getData() {
      return(this.data);
   }
	/**
	 * gets the displaynane for the EOOperation. This is mainly used in the breadcrumb
    */
   public String getDisplayName()
   {
      return(this.displayname);
   }

	/**
	 * 
	 */
   EOOperation(Object data, String displayname, EODisplayType displaytype) {
      this.data = data;
      this.displaytype = displaytype;
      this.displayname = displayname;
   }

   /**
   * Gets the displaytime for the EOOperation
   */
   public EODisplayType getDisplayType() {
      return(displaytype);
   }
}
