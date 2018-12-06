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
      return(EOCSV.formatField(type) + ", " + EOCSV.formatField(identifier1) + ", " + EOCSV.formatField(value1) + ", " + EOCSV.formatField(identifier2) + ", " + EOCSV.formatField(value2) + "\n");
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
}