import java.time.LocalDateTime;
import java.io.File;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.FileWriter;

public class EOCSV
{
   LocalDateTime sdatetime;
   LocalDateTime edatetime;
   FacilitatorContactInfo[] facilitators;
   boolean showprice;
   String savepath;
   File outfile;
   
   public EOCSV(LocalDateTime sdatetime, LocalDateTime edatetime, FacilitatorContactInfo[] facilitators, boolean showprice, File outfile)
   {
      this.sdatetime = sdatetime;
      this.edatetime = edatetime;
      this.facilitators = facilitators;
      this.showprice = showprice;
      this.outfile = outfile;
   }
   
   public void setFileLocation(String savepath)
   {
      this.savepath = savepath;
   }
   
   public void createCSV() throws Exception
   {
      //This is where the magic happens
      try
      {
         Writer output = null;
         output = new BufferedWriter(new FileWriter(outfile));
         output.write("data!");
         output.close();
      }
      catch(Exception e)
      {
         System.out.println("Could not create file: " + e.getMessage());
      }      
   }
}