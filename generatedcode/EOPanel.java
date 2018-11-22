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
   Object data = null;

   public Object getData()
   {
      return(this.data);
   }

	public void setVisible(boolean visible, Object data) {
		this.data = data;
      super.setVisible(visible);
	}
}