public enum EOOperation {
   START (null, EODisplayType.START),
   CREATEARRANGEMENT (null, EODisplayType.CREATEARRANGEMENT),
    EXPORT (null,EODisplayType.START),
    ADMFACILITATOR (null, EODisplayType.ADMFACILITATOR),
    ADMEVENTTYPE (null, EODisplayType.ADMEVENTTYPE),
    SHOWONLYPASTEVENT (null, EODisplayType.SHOWONLYPASTEVENT),
    RESETSORTING (null, EODisplayType.RESETSORTING)

   private Object data = null;
   private EODisplayType displaytype;

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

	/**
	 * 
	 * @param data
	 * @param displaytype
	 */
   EOOperation(Object data, EODisplayType displaytype) {
      this.data = data;
      this.displaytype = displaytype;
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
