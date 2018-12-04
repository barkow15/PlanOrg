public class EOBreadcrumb
{
   private EOOperation[] stack;
   private int stackcounter = 0;
   private int maxsize = 10;
          
   public EOBreadcrumb()
   {      
      stack = new EOOperation[maxsize];
      
      for(int i = 0; i < maxsize; i++)
      {
         stack[i] = null;
      }
      reset();
   }
   
   public int getStackCounter()
   {
      return(this.stackcounter);
   }
   
   /**
   * Index starting at 0
   */
   public EOOperation getIndex(int i)
   {
      if(i>stackcounter)
      {
         return(null);
      }
      //System.out.println("getIndex returned: " + this.stack[i]);
      return(this.stack[i]);
   }
   
   public void reset()
   {
      //System.out.println("RESET");
      for(int i = 0; i < maxsize; i++)
      {
         stack[i] = null;
      }   
      this.stack[0] = EOOperation.START;
      this.stackcounter = 1;
   }
   
   public void setStack(EOOperation[] stack)
   {
      this.stack = stack;
   }
   
   public void push(EOOperation o)
   {
      if(stackcounter < maxsize)
      {
         this.stack[stackcounter] = o;
         this.stackcounter++;
      }
   }
   
   public EOOperation pop()
   {
      return(pop(1));
      /*
      EOOperation e = stack[stackcounter-1];
      this.stack[stackcounter-1] = null;
      this.stackcounter--;
      return(e);*/
   }

   public EOOperation pop(int num)
   {
      System.out.println("Stackcounter prior: " + num + " " + this.stackcounter);   
      EOOperation eoop = null;
      for(int i = 0; i < num; i++)
      {
         eoop = stack[stackcounter-1]; 
         this.stack[stackcounter-1] = null;
         this.stackcounter--;
   
      }
      System.out.println("Stackcounter after:" + num + " " + this.stackcounter);
      //EOOperation e = stack[stackcounter-num];
      //this.stackcounter= this.stackcounter - num;
      return(eoop);
   }
   
}