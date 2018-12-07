/**
The interface is used in conjunction with EOCSV, classes that implements EOCSVInterface are can be converted to CSV lines.

Note: This only applies to classes that are part of the EO project. Other classes will need implementation in the EOCSV class.
*/
public interface EOCSVInterface {
   /**
   Returns a CSV line with the different fields in the object.
   */
   String exportCSV();
   int hashCode();
   boolean equals(Object obj);

}