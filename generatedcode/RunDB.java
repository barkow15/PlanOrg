// Import dependencies
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;


public class RunDB {
    public static void main(String[] args){

        EODatabaseInterface db = new EODatabaseInterface();

        /*********************************/
        /** Arrangements DB TESTS START **/
        /*********************************/
        /*
         Lav nye instanser af CCI og FCI og gem i variabler til tests af oprettelse af arrangement
        */
        CustomerContactInfo     aci = new CustomerContactInfo(
                    1,
                    "Customer Test",
                    "67189182",
                    "mathias@test.dk",
                    "Test Firma",
                    "Bla"
        );
        ExternalContactInfo     eci  = new ExternalContactInfo(
                1,
                "Hejsa",
                "12345678",
                "bla bla",
                "Hey",
                "Firma"
        );
        FacilitatorContactInfo  afi  = new FacilitatorContactInfo(
                2,
                "- Name of test -",
                "12345678",
                "test@test.dk",
                "Bla"
        );
        FacilitatorContactInfo[] facilArr = new FacilitatorContactInfo[1];
        facilArr[0] = afi;

        EOEvent[] eventsArr               = new EOEvent[2];
        EOEventType[] eventTypesArr1      = new EOEventType[2];
        EOEventType[] eventTypesArr2      = new EOEventType[2];
        eventTypesArr1[0] = new EOEventType(1, "Bla", "Blabla", "Hørsholm", "Hørsholm", 10,  200, eci);
        eventTypesArr1[1] = new EOEventType(2, "Bla", "Blabla", "Hørsholm", "Hørsholm", 10,  200, eci);
        eventTypesArr1[0] = new EOEventType(3, "Bla", "Blabla", "Hørsholm", "Hørsholm", 10,  200, eci);
        eventTypesArr1[1] = new EOEventType(4, "Bla", "Blabla", "Hørsholm", "Hørsholm", 10,  200, eci);

        // Formatering af dato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTimeStart1 = LocalDateTime.parse( "2018-02-16 06:00:00", formatter);
        LocalDateTime dateTimeEnd1   = LocalDateTime.parse( "2018-02-16 16:00:00", formatter);
        eventsArr[0] = new EOEvent(1,
                "Nøgenbadning",
                dateTimeStart1,
                dateTimeEnd1,
                20.00,
                null,
                eventTypesArr1
        );

        LocalDateTime dateTimeStart2 = LocalDateTime.parse( "2018-02-16 06:00:00", formatter);
        LocalDateTime dateTimeEnd2   = LocalDateTime.parse( "2018-02-16 16:00:00", formatter);
        eventsArr[1] = new EOEvent(
                2,
                "Livet som popstjerne med Erann DD",
                dateTimeStart2,
                dateTimeEnd2,
                20.00,
                null,
                eventTypesArr2
        );

        // Lav en instans af EOArrangement
        LocalDateTime dateTimeStartArrangement  = LocalDateTime.parse( "2018-02-16 06:00:00", formatter);
        LocalDateTime dateTimeEndArrangement    = LocalDateTime.parse( "2018-02-16 06:00:00", formatter);
        EOArrangement           ar  = new EOArrangement(
                1,
                "Nøgenbadning",
                "Blablabla",
                dateTimeStartArrangement,
                dateTimeEndArrangement,
                100,
                false,
                false,
                facilArr,
                eventsArr,
                db.getCustomerContactInfo(1)
        );

        // Kør db test
        db.createEOArrangement(ar);

        /*******************************/
        /** Arrangements DB TESTS END **/
        /*******************************/

        //db.test();
        /*******************************************/
        /** FacilitatorContactInfo DB TESTS START **/
        /*******************************************/
        FacilitatorContactInfo fi = new FacilitatorContactInfo(1,"Test mand 1", "12345678", "test@test.dk","Bla");
        // <-- CREATE -->
        /*
        if(db.createFacilitatorContactInfo(fi)){
            System.out.println("Created facilitator");
        }else{
            System.out.println("Error");
        }
        */
        // <-- GET -->
        FacilitatorContactInfo facilitator = db.getFacilitatorContactInfo(fi);
        if(facilitator != null) {
            System.out.println(facilitator.getName());
        }

        // <-- DELETE -->
        /*
        if(db.deleteFacilitatorContactInfo(fi)){
            System.out.println("Deleted Facilitator Contact Info");
        }else{
            System.out.println("Could not delete facilitator contact info. Try again.");
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
        //FacilitatorContactInfo[] facilAll = db.getAllFacilitatorContactInfo();
        // <-- GET ALL FROM ARRANGEMENT WITH ID -->
        FacilitatorContactInfo[] facilAll = db.getFacilitatorsContactInfo(1);

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



        /*****************************************/
        /** FacilitatorContactInfo DB TESTS END **/
        /*****************************************/


        /****************************************/
        /** CustomerContactInfo DB TESTS START **/
        /****************************************/
        CustomerContactInfo ci = new CustomerContactInfo(1,"Test kunde 3", "67189182", "mathias@test.dk", "Test Firma", "Bla");
        // <-- CREATE -->
        /*
        if(db.createCustomerContactInfo(ci)){
            System.out.println("Created customer");
        }else{
            System.out.println("Error");
        }
        */


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
        ExternalContactInfo ei = new ExternalContactInfo(1,"Test Ekstern kontakt 1", "12345678", "test@test.dk", "Bla","Bla Inc.");
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
