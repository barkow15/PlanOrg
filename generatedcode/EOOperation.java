public enum EOOperation {
   START (null, "Start menu", EODisplayType.START),
   CREATEARRANGEMENT (null, "Opret Arrangement", EODisplayType.CREATEARRANGEMENT),
   UPDATEARRANGEMENT (null, "Opdater Arrangement", EODisplayType.UPDATEARRANGEMENT),
   DELETEARRANGEMENT (null, "Slet Arrangement", EODisplayType.DELETEARRANGEMENT),
   CREATEEVENT (null, "Opret Begivenhed", EODisplayType.CREATEEVENT),
   UPDATEEVENT (null, "Opdater Begivenhed", EODisplayType.UPDATEEVENT),
   DELETEEVENT (null, "Slet Begivenhed", EODisplayType.DELETEEVENT),   
   EXPORT (null, "Exporter", EODisplayType.EXPORT),
   ADMFACILITATOR (null, "Administrer Facilitatorer", EODisplayType.ADMFACILITATOR),
   ADMEVENTTYPE (null, "Administrer Begivenhedstyper", EODisplayType.ADMEVENTTYPE),
   ERROR (null, "Fejl sk�rm", EODisplayType.ERROR),
   SAVECSV (null, "Gem CSV", EODisplayType.START);
   
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
