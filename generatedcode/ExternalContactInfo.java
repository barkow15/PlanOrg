/**
* Contains contact information for an external contact.
*/
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
      System.out.println("ExternalContactInfo.exportCSV()");
      return(super.exportCSV("ExternalContactInfo") + ", " + EOCSV.formatField(getCompany()) + "\n");
   }   
   
   @Override   
   public boolean equals(Object obj)
   {
      boolean returnvar = false;
      //Ref: https://stackoverflow.com/questions/6304056/does-instanceof-return-true-if-instance-of-a-parent
      if(obj instanceof ExternalContactInfo)
      {
         ContactInfo f = (ContactInfo) obj;
         if(this.getId() != -1 && f.getId() == this.getId())
         {
            returnvar = true;
         }
      }
      return(returnvar || super.equals(obj));
   }

   @Override      
   public int hashCode()
   {
      return(getId()+500000001);
   }     
}