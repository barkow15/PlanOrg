import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOPanelADMFacilitator extends EOPanel {
   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;
   
   public EOPanelADMFacilitator(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);

      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 400, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);

      JButton exportbutton = new JButton("Gem");
      exportbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      exportbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.START);
                  }
               });
      this.add(exportbutton);
      
      JButton cancelbutton=new JButton("Annuller");
      cancelbutton.setBounds(this.gui.getWidth()-230, 5, 100, 30);
      cancelbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.START);
                  }
               });
      this.add(cancelbutton);
     
   }

   public void setVisible(boolean visible, Object data) {
   	// TODO - implement PanelStartMenu.setVisible
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());      
      super.setVisible(visible);
   }

   public void clearData() {
   	// TODO - implement PanelCreateArrangement.clearData
      throw new UnsupportedOperationException();
   }

   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(00,38, this.getWidth(), 38);
   }     
}