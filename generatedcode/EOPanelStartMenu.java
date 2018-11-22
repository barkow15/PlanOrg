import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOPanelStartMenu extends EOPanel {

   EOGUI gui = null;
   public EOPanelStartMenu(EOGUI gui)
   {
      this.gui = gui;
      this.setLayout(null);

      String simbuttonlabel = "CreateArrangement";
      JButton simbutton=new JButton(simbuttonlabel);
      simbutton.setBounds(100, 100, 300, 30);
      simbutton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               gui.runCommand(EOOperation.CREATEARRANGEMENT);
            }
         });
      this.add(simbutton);      
   }
	/**
	 * 
	 * @param visible
	 * @param data
	 */
	public void setVisible(boolean visible, Object data) {
		// TODO - implement PanelStartMenu.setVisible
		super.setVisible(visible);
	}
}