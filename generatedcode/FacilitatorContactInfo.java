/**
* Contains facilitator contact info
*/
public class FacilitatorContactInfo extends ContactInfo {

	/**
	 * 
	 * @param id
	 * @param name
	 * @param phone
	 * @param email
	 * @param info
	 */
   public FacilitatorContactInfo(int id, String name, String phone, String email, String info) {
      super(id, name, phone, email, info);
   }

   @Override   
   public String exportCSV()
   {
      System.out.println("FacilitatorContactInfo.exportCSV()");
      return(super.exportCSV("FacilitatorContactInfo") + "\n");
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
      return(getId()+750000001);
   }      
}