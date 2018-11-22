public class CustomerContactInfo extends ContactInfo {

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
	public CustomerContactInfo(int id, String name, String phone, String email, String info, String company) {
      super(id, name, phone, email, info);
		this.company = company;
	}

}