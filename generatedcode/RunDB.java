public class RunDB {
    public static void main(String[] args){

        EODatabaseInterface db = new EODatabaseInterface();
        //db.test();
        /*******************************************/
        /** FacilitatorContactInfo DB TESTS START **/
        /*******************************************/

        // <-- CREATE -->
        /*

        if(db.createFacilitatorContactInfo(new FacilitatorContactInfo(1,"Martin", "70807080", "johnny@hej.dk", "blablabla"))){
            System.out.println("Created facilitator");
        }else{
            System.out.println("Error");
        }

        */

        // <-- GET -->
        /*
        FacilitatorContactInfo facilitator = db.getFacilitatorContactInfo(1);
        if(facilitator != null) {
            System.out.println(facilitator.getName());
        }
        */

        // <-- GET ALL -->
        db.getAllFacilitatorContactInfo();

        /*****************************************/
        /** FacilitatorContactInfo DB TESTS END **/
        /*****************************************/


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
        /*
        CustomerContactInfo customer = db.getCustomerContactInfo(1);
        if(customer != null) {
            System.out.println(customer.getName());
        }
        */

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
