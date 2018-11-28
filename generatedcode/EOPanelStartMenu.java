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
   
      //Header
      EOGUIBreadcrumb breadcrumb = this.gui.getBreadcrumb();
      breadcrumb.setBounds(5, 5, 800, 30);
      this.add(breadcrumb);
   
      int fromTop = 5;
      int borderRight = this.gui.getWidth();
      
      JButton createArrangementButton=new JButton("Opret arrangement");
      createArrangementButton.setBounds(borderRight-175,fromTop, 150, 30);
      createArrangementButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               gui.runCommand(EOOperation.CREATEARRANGEMENT);
            }
         });
      this.add(createArrangementButton);
   
      JButton exportButton=new JButton("Eksporter");
      exportButton.setBounds(borderRight-330,fromTop, 150, 30);
      exportButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    gui.runCommand(EOOperation.EXPORT);
                 }
              });
      this.add(exportButton);
   
      JButton admFacilitatorButton=new JButton("Administrer Facilitatorer");
      admFacilitatorButton.setBounds(borderRight-535,fromTop, 200, 30);
      admFacilitatorButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    gui.runCommand(EOOperation.ADMFACILITATOR);
                 }
              });
      this.add(admFacilitatorButton);
   
      JButton admEventType=new JButton("Administrer begivenhedstyper");
      admEventType.setBounds(borderRight-790,fromTop, 250, 30);
      admEventType.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    gui.runCommand(EOOperation.ADMEVENTTYPE);
                 }
              });
      this.add(admEventType);
   
      JLabel arrangementLabel=new JLabel("Arrangement");
      arrangementLabel.setBounds(50, 40, 250, 150);
      arrangementLabel.setFont(gui.getFontbig());
      this.add(arrangementLabel);
            
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
   
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(00,38, this.getWidth(), 38);
   }      
}

