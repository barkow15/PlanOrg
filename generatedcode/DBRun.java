public class DBRun {
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
        }*/

        // <-- DELETE -->
        //db.deleteCustomerContactInfo(1);

        // <-- GET -->
        CustomerContactInfo customer = db.getCustomerContactInfo(1);
        System.out.println(customer);

        // <-- UPDATE -->
        //db.updateCustomerContactInfo(1, "Test", "28282828", "test@phil.dk", "testfirma", "Ny info test");

        /**************************************/
        /** CustomerContactInfo DB TESTS END **/
        /**************************************/
    }
}
