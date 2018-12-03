public class ExternalContactInfo extends ContactInfo {

	private String company;

	public String getCompany() {
		return this.company;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param phone
	 * @param email
	 * @param info
	 * @param company
	 */
	public ExternalContactInfo(int id, String name, String phone, String email, String info, String company) {
      super(id, name, phone, email, info);
		this.company = company;
	}

   @Override   
   public String exportCSV()
   {
      return(super.exportCSV("ExternalContactInfo") + ", " + EOCSV.formatField(getCompany()) + "\n");
   }   
}