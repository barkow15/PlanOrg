import java.util.ArrayList;

public class RunDB {
    public static void main(String[] args){

        EODatabaseInterface db = new EODatabaseInterface();
        //db.test();
        /*******************************************/
        /** FacilitatorContactInfo DB TESTS START **/
        /*******************************************/
        FacilitatorContactInfo fi = new FacilitatorContactInfo(2,"- Name of test -", "12345678", "test@test.dk","Bla");
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
        if(db.deleteFacilitatorContactInfo(fi)){
            System.out.println("Deleted Customer Contact Info");
        }else{
            System.out.println("Could not delete customer contact info. Try again.");
        }
        */


        // <-- UPDATE -->
        /*
        if(db.updateFacilitatorContactInfo(fi)){
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

        CustomerContactInfo ci = new CustomerContactInfo(1,"Customer Test", "67189182", "mathias@test.dk", "Test Firma", "Bla");
        // <-- CREATE -->
        /*
        if(db.createCustomerContactInfo(ci)){
            System.out.println("Created customer");
        }else{
            System.out.println("Error");
        }*/


        // <-- DELETE -->
        /*
        if(db.deleteCustomerContactInfo(ci)){
            System.out.println("Deleted Customer Contact Info");
        }else{
            System.out.println("Could not delete customer contact info. Try again.");
        }
        */

        // <-- GET -->
        /*
        CustomerContactInfo customer = db.getCustomerContactInfo(ci);
        if(customer != null) {
            System.out.println(customer.getName());
        }
        */

        // <-- UPDATE -->
        /*
        if(db.updateCustomerContactInfo(ci)){
            System.out.println("Updated Customer Contact Info");
        }else{
            System.out.println("Could not update Customer Contact Info. Try again");
        }*/

        // <-- GET ALL -->
        /*
        CustomerContactInfo[] customAll = db.getAllCustomerContactInfo();
        if(customAll != null) {
            for (int i = 0; i < customAll.length; i++) {
                //System.out.println(facilAll[0].getName());
                if(customAll[i] != null) {

                    System.out.println("CustomConInfo id: " + customAll[i].getId());

                    System.out.println(customAll[i].getName());
                    System.out.println(customAll[i].getPhone());
                    System.out.println(customAll[i].getEmail());
                    System.out.println(customAll[i].getInfo() + "\n");

                }
            }
        }else{
            System.out.println("No data or error.");
        }
        */

        /**************************************/
        /** CustomerContactInfo DB TESTS END **/
        /**************************************/

        /****************************************/
        /** ExternalContactInfo DB TESTS START **/
        /****************************************/
        ExternalContactInfo ei = new ExternalContactInfo(1,"Test Testesen", "12345678", "test@test.dk", "Bla","Bla Inc.");
        // <-- CREATE -->
        /*
        if(db.createExternalContactInfo(ei)){
            System.out.println("Created external contact");
        }else{
            System.out.println("Error");
        }*/


        // <-- GET -->
        /*
        ExternalContactInfo external = db.getExternalContactInfo(ei);
        if(external != null) {
            System.out.println(external.getName());
        }
        */


        // <-- DELETE -->
        /*
        if(db.deleteExternalContactInfo(ei)){
            System.out.println("Deleted Customer Contact Info");
        }else{
            System.out.println("Could not delete customer contact info. Try again.");
        }
        */


        // <-- UPDATE -->
        /*
        if(db.updateExternalContactInfo(ei))){
            System.out.println("Updated Customer Contact Info");
        }else{
            System.out.println("Could not update Customer Contact Info. Try again");
        }
        */


        // <-- GET ALL -->
        /*
        ExternalContactInfo[] extAll = db.getAllExternalContactInfo();
        if(extAll != null) {
            for (int i = 0; i < extAll.length; i++) {
                //System.out.println(facilAll[0].getName());
                if(extAll[i] != null) {

                    System.out.println("ExtConInfo id: " + extAll[i].getId());

                    System.out.println(extAll[i].getName());
                    System.out.println(extAll[i].getPhone());
                    System.out.println(extAll[i].getEmail());
                    System.out.println(extAll[i].getInfo() + "\n");

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
