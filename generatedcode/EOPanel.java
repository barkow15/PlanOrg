import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOPanel extends JPanel
{
   EOOperation currentEOOperation = null;

   public EOOperation getcurrentEOOperation()
   {
      return(this.currentEOOperation);
   }

	public void setVisible(boolean visible, EOOperation currentEOOperation) {
		this.currentEOOperation = currentEOOperation;
      super.setVisible(visible);
	}
}