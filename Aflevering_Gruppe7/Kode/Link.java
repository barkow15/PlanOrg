/**
* Contains information for a database link. The link is made in the table: table between column with name identifier1 and value1 and column with name identifier2 with value2.
*/
public class Link implements EOCSVInterface
{
   String table;
   String type;
   String identifier1;
   int    value1;
   String identifier2;
   int    value2;
   
   public Link(String table, String identifer1, int value1, String identifier2, int value2)
   {
      this.table = table;
      this.type = new String("Link");
      this.identifier1 = identifer1;
      this.value1 = value1;
      this.identifier2 = identifier2;
      this.value2 = value2;
   }
   
   public String exportCSV()
   {
      System.out.println("Link.exportCSV()");
      return(EOCSV.formatField(type) + ", " + EOCSV.formatField(table) + ", " + EOCSV.formatField(identifier1) + ", " + EOCSV.formatField(value1) + ", " + EOCSV.formatField(identifier2) + ", " + EOCSV.formatField(value2) + "\n");
   }
   
   public String getTable(){
      return(this.table);
   }
   
   public String getIdentifier1(){
      return(this.identifier1);
   }
   
   public int getValue1(){
      return(this.value1);
   }
   
   public String getIdentifier2(){
      return(this.identifier2);
   }
   
   public int getValue2(){
      return(this.value2);
   }
   
   
   public boolean equals(Object obj)
   {
      if(obj instanceof Link)
      {
         Link c = (Link)obj;
         return(
            (c.getTable().equals(this.table) && 
            c.getIdentifier1().equals(this.identifier1) && 
            c.getValue1() == this.getValue1() && 
            c.getIdentifier2().equals(this.identifier2) && 
            c.getValue2() == this.getValue2()) || super.equals(obj));
      }
      return(false);
   }
   
   @Override      
   public int hashCode()
   {
      String[] tables = new String[12];
      tables[0] = "EOArrangements";
      tables[1] = "EOArrangements_has_EOContactInfo";
      tables[2] = "EOArrangements_has_EOEvents";
      tables[3] = "EOArrangements_has_EOFacilitatorContactInfo";
      tables[4] = "EOCustomerContactInfo";
      tables[5] = "EOEvents";
      tables[6] = "EOEvents_has_EOEventtypes";
      tables[7] = "EOEvents_has_EOFacilitatorContactInfo";
      tables[8] = "EOEventtypes";
      tables[9] = "EOEventtypes_has_EOExternalContactInfo";
      tables[10] = "EOExternalContactInfo";
      tables[11] = "EOFacilitatorContactInfo";
      
      int[] tablesvalue = new int[12];
      tablesvalue[0] = 1550000001;
      tablesvalue[1] = 1600000001;
      tablesvalue[2] = 1650000001;
      tablesvalue[3] = 1700000001;
      tablesvalue[4] = 1750000001;
      tablesvalue[5] = 1800000001;
      tablesvalue[6] = 1850000001;
      tablesvalue[7] = 1900000001;
      tablesvalue[8] = 1950000001;
      tablesvalue[9] = 2000000001;
      tablesvalue[10] = 2050000001;
      tablesvalue[11] = 2100000001;
      
      int value = 1550000001;
      for(int i = 0; i < 12; i++)
      {
         if(table.equals(tables[i]))
         {
            value = tablesvalue[1] + value1 * 1000 + value2;
         }
      }         
      return(value);
   }    
}