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
      return(super.exportCSV("CustomerContactInfo") + ", " + EOCSV.formatField(getCompany()) + "\n");
   }   
}