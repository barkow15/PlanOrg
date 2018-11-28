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
   
   public EOOperation getIndex(int i)
   {
      if(i>stackcounter)
      {
         return(null);
      }
      return(this.stack[i]);
   }
   
   public void reset()
   {
      System.out.println("RESET");
      for(int i = 0; i < maxsize; i++)
      {
         stack[i] = null;
      }   
      this.stack[0] = EOOperation.START;
      this.stackcounter = 1;
   }
   
   public void push(EOOperation o)
   {
      if(stackcounter < maxsize)
      {
         this.stack[stackcounter] = o;
         this.stackcounter++;
      }
   }
   
   public void pop()
   {
      this.stack[stackcounter-1] = null;
      this.stackcounter--;
   }
}