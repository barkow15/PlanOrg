/**
* This class contains information, for a specific customer.
*/
public class CustomerContactInfo extends ContactInfo  {

   private String company;

   public String getCompany() {
      return this.company;
   }

   public void setCompany(String company)
   {
      this.company = company;
   }

   public CustomerContactInfo(int id, String name, String phone, String email, String info, String company) {
      super(id, name, phone, email, info);
      this.company = company;
   }

   @Override   
   public String exportCSV()
   {
      System.out.println("CustomerContactInfo.exportCSV()");
      return(super.exportCSV("CustomerContactInfo") + ", " + EOCSV.formatField(getCompany()) + "\n");
   }

   @Override   
   public boolean equals(Object obj)
   {
      boolean returnvar = false;
      //Ref: https://stackoverflow.com/questions/6304056/does-instanceof-return-true-if-instance-of-a-parent
      if(obj instanceof CustomerContactInfo)
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
      return(getId()+250000001);
   }   
}