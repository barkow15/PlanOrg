import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

/**
* Our main class for all EOPanel's
*/
public abstract class EOPanel extends JPanel
{
   //Defines the current operation, in the panel
   EOOperation currentEOOperation = null;

   /**
   * Returns the current EOOperation 
   */
   public EOOperation getcurrentEOOperation()
   {
      return(this.currentEOOperation);
   }
   
   /**
   * Sets visibility of the panel, with an EOOperation.
   * In many cases the EOOperation includes data for the EOPanel. This is the default metode to call when you want to show a EOPanel.
   */
   public void setVisible(boolean visible, EOOperation currentEOOperation) {
      this.currentEOOperation = currentEOOperation;
      super.setVisible(visible);
   }
}