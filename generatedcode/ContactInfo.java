public class ContactInfo implements EOGUIMultiSelectInterface, EOCSVInterface {

	private String name;
	private String phone;
	private String email;
	private String info;
	private int id;

	/**
	 * 
	 * @param id        
	 * @param name
	 * @param phone
	 * @param email
	 * @param info
	 */
	public ContactInfo(int id, String name, String phone, String email, String info) {
		this.id = id;
      this.name = name;
      this.phone = phone;
      this.email = email;
      this.info = info;
	}

	public String getName() {
		return this.name;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getEmail() {
		return this.email;
	}

	public String getInfo() {
		return this.info;
	}

	public int getId() {
		return this.id;
	}

   public String getDisplayName()
   {
      return(this.name);
   }   
   
   public String exportCSV()
   {
      return(exportCSV("ContactInfo") + "\n");
   }
   
   public String exportCSV(String type)
   {
      String str = 
         type + "; " +
         id + "; " +
         name + "; " +
         phone + "; " + 
         email + "; " +
         "info";
      return(str);
   }   

}