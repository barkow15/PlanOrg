public enum EOOperation {
   START (null, "Start menu", EODisplayType.START), //Martin - Mangler DB
   STARTSHOWALL (null, "Start menu", EODisplayType.START), //Martin - Mangler DB
   CREATEARRANGEMENT (null, "Opret Arrangement", EODisplayType.CREATEARRANGEMENT), //Marcus
   UPDATEARRANGEMENT (null, "Opdater Arrangement", EODisplayType.UPDATEARRANGEMENT), //Marcus
   DELETEARRANGEMENT (null, "Slet Arrangement", EODisplayType.DELETEARRANGEMENT), //Marcus
   OPENARRANGEMENT (null, "Vis Arrangement", EODisplayType.OPENARRANGEMENT), //Martin - Mangler DB
   CREATEEVENT (null, "Opret Begivenhed", EODisplayType.CREATEEVENT),  //Rasmus
   UPDATEEVENT (null, "Opdater Begivenhed", EODisplayType.UPDATEEVENT),  //Rasmus
   DELETEEVENT (null, "Slet Begivenhed", EODisplayType.DELETEEVENT), //Rasmus
   SAVECREATEEVENT (null, "Gem Event", EODisplayType.START), //Rasmus
   IMPORT (null, "Importer", EODisplayType.IMPORT), //Martin - DONE
   IMPORTCSV (null, "Importer", EODisplayType.START), //Fælles  
   EXPORT (null, "Exporter", EODisplayType.EXPORT), //Martin - DONE
   SAVECSV (null, "Gem CSV", EODisplayType.START), //Fælles
   ADMFACILITATOR (null, "Administrer Facilitatorer", EODisplayType.ADMFACILITATOR), //Frederik
   UPDATEFACILITATOR (null,"Rediger Facilitator",EODisplayType.ADMFACILITATOR), //Frederik
   CREATEFACILITATOR (null,"Opret Facilitator",EODisplayType.ADMFACILITATOR), //Frederik
   DELETEFACILITATOR(null,"Slet facilitator",EODisplayType.ADMFACILITATOR), //Frederik
   ADMEVENTTYPE (null, "Administrer Begivenhedstyper", EODisplayType.ADMEVENTTYPE), //Martin
   ERROR (null, "Fejl skærm", EODisplayType.ERROR); //Martin
 
   private Object data = null;
   private EODisplayType displaytype;
   private String displayname;
	/**
	 * 
	 * @param data
	 */
   public void setData(Object data) {
      this.data = data;
   }

   public Object getData() {
      return(this.data);
   }
   
   public String getDisplayName()
   {
      return(this.displayname);
   }

	/**
	 * 
	 * @param data
	 * @param displaytype
	 */
   EOOperation(Object data, String displayname, EODisplayType displaytype) {
      this.data = data;
      this.displaytype = displaytype;
      this.displayname = displayname;
   }

   public void reset() {
   	// TODO - implement EOOPERATION.reset
      throw new UnsupportedOperationException();
   }

   public EODisplayType getDisplayType() {
      return(displaytype);
   }

	/**
	 * 
	 * @param displaytype
	 */
   public void setDisplayType(EODisplayType displaytype) {
   	// TODO - implement EOOPERATION.setDisplayType
      throw new UnsupportedOperationException();
   }

}
