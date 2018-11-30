/**
This is the base class for all ContactInfo classes. These classes contains contact information, for a specific group of people.
*/
public abstract class ContactInfo implements EOGUIMultiSelectInterface, EOCSVInterface {

	private String name;
	private String phone;
	private String email;
	private String info;
	private int id;

	public ContactInfo(int id, String name, String phone, String email, String info) {
		this.id = id;
      this.name = name;
      this.phone = phone;
      this.email = email;
      this.info = info;
	}

   
   /**
   Returns the name of the contact.
   */
	public String getName() {
		return this.name;
	}

   /**
   Returns the name of the contact.
   */
	public String getPhone() {
		return this.phone;
	}

   /**
   Returns the email of the contact.
   */
	public String getEmail() {
		return this.email;
	}

   /**
   Returns the any additional information about the contact.
   */
	public String getInfo() {
		return this.info;
	}

   /**
   Returns the database identifier for the contact, this is a unique id for the contact.
   */
	public int getId() {
		return this.id;
	}

   /**
   Returns the name of the contact.
   */
   public String getDisplayName()
   {
      return(this.name);
   }   

   /**
   * <pre>
   * Returns a semicolon seperated line, with all the information for the contact.
   * 
   * If you need to export the information from a subclass, please see exportCSV(String type)
   * </pre>
   */   
   public String exportCSV()
   {
      return(exportCSV("ContactInfo") + "\n");
   }

   /**
   * <pre>
   * Returns a semicolon seperated line, with all the information for the contact.
   * 
   * The type parameter String is set to the Object name of the classes, for example exportCSV("CustomerContactInfo") if you are exporting contact info from the CustomerContactInfo class.
   * </pre>
   */     
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