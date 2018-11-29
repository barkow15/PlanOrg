import java.util.ArrayList;

public class RunDB {
    public static void main(String[] args){

        EODatabaseInterface db = new EODatabaseInterface();
        //db.test();
        /*******************************************/
        /** FacilitatorContactInfo DB TESTS START **/
        /*******************************************/

        FacilitatorContactInfo fi = new FacilitatorContactInfo(1,"Test Testesen", "12345678", "test@test.dk","Bla");
        // <-- CREATE -->
        /*
        if(db.createFacilitatorContactInfo(fi)){
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

        // <-- DELETE -->
        /*
        if(db.deleteFacilitatorContactInfo(1)){
            System.out.println("Deleted Customer Contact Info");
        }else{
            System.out.println("Could not delete customer contact info. Try again.");
        }
        */

        // <-- UPDATE -->
        /*
        if(db.updateFacilitatorContactInfo(new FacilitatorContactInfo(2,"John", "70807080", "marcus@test.dk", "blablabla"))){
            System.out.println("Updated Customer Contact Info");
        }else{
            System.out.println("Could not update Customer Contact Info. Try again");
        }
        */


        // <-- GET ALL -->
        /*
        FacilitatorContactInfo[] facilAll = db.getAllFacilitatorContactInfo();
        if(facilAll != null) {
            for (int i = 0; i < facilAll.length; i++) {
                //System.out.println(facilAll[0].getName());
                if(facilAll[i] != null) {

                    System.out.println("FacilConInfo id: " + facilAll[i].getId());

                    System.out.println(facilAll[i].getName());
                    System.out.println(facilAll[i].getPhone());
                    System.out.println(facilAll[i].getEmail());
                    System.out.println(facilAll[i].getInfo() + "\n");

                }
            }
        }else{
            System.out.println("No data or error.");
        }
        */



        /*****************************************/
        /** FacilitatorContactInfo DB TESTS END **/
        /*****************************************/


        /****************************************/
        /** CustomerContactInfo DB TESTS START **/
        /****************************************/

        CustomerContactInfo ci = new CustomerContactInfo(1,"Mathias Johnson", "67189182", "mathias@test.dk", "Test Firma", "Bla");
        // <-- CREATE -->
        /*
        if(db.createCustomerContactInfo(ci)){
            System.out.println("Created customer");
        }else{
            System.out.println("Error");
        }*/


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

        /****************************************/
        /** ExternalContactInfo DB TESTS START **/
        /****************************************/
        ExternalContactInfo ei = new ExternalContactInfo(1,"Test Testesen", "12345678", "test@test.dk", "","Bla");
        // <-- CREATE -->
        /*
        if(db.createExternalContactInfo(ei)){
            System.out.println("Created external contact");
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

        // <-- DELETE -->
        /*
        if(db.deleteFacilitatorContactInfo(1)){
            System.out.println("Deleted Customer Contact Info");
        }else{
            System.out.println("Could not delete customer contact info. Try again.");
        }
        */

        // <-- UPDATE -->
        /*
        if(db.updateFacilitatorContactInfo(new FacilitatorContactInfo(2,"John", "70807080", "marcus@test.dk", "blablabla"))){
            System.out.println("Updated Customer Contact Info");
        }else{
            System.out.println("Could not update Customer Contact Info. Try again");
        }
        */


        // <-- GET ALL -->
        /*
        FacilitatorContactInfo[] facilAll = db.getAllFacilitatorContactInfo();
        if(facilAll != null) {
            for (int i = 0; i < facilAll.length; i++) {
                //System.out.println(facilAll[0].getName());
                if(facilAll[i] != null) {

                    System.out.println("FacilConInfo id: " + facilAll[i].getId());

                    System.out.println(facilAll[i].getName());
                    System.out.println(facilAll[i].getPhone());
                    System.out.println(facilAll[i].getEmail());
                    System.out.println(facilAll[i].getInfo() + "\n");

                }
            }
        }else{
            System.out.println("No data or error.");
        }
        */

        /**************************************/
        /** ExternalContactInfo DB TESTS END **/
        /**************************************/
    }
}
