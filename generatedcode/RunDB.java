public class RunDB {
    public static void main(String[] args){

        EODatabaseInterface db = new EODatabaseInterface();
        //db.test();

        /****************************************/
        /** CustomerContactInfo DB TESTS START **/
        /****************************************/
        // <-- CREATE -->
        /*
        if(db.createCustomerContactInfo("Phil", "23232949", "philip@test.dk", "Test Firma", "Test info lorem ipsum ")){
            System.out.println("Created customer");
        }else{
            System.out.println("Error");
        }
        */

        // <-- DELETE -->
        /*
        if(db.deleteCustomerContactInfo(1)){
            System.out.println("Deleted Customer Contact Info");
        }else{
            System.out.println("Could not delete customer contact info. Try again.");
        }
        */

        // <-- GET -->
        //CustomerContactInfo customer = db.getCustomerContactInfo(2);
        //System.out.println(customer.getName());

        // <-- UPDATE -->
        /*
        if(db.updateCustomerContactInfo(1, "Johnny Madsen", "12345678", "mads@madsen.dk", "madsens", "Mads")){
            System.out.println("Updated Customer Contact Info");
        }else{
            System.out.println("Could not update Customer Contact Info. Try again");
        }
        */
        /**************************************/
        /** CustomerContactInfo DB TESTS END **/
        /**************************************/
    }
}
