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
}