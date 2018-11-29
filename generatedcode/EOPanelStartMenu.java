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
   EOGUIBreadcrumb breadcrumb;
   EOGUIArrangementTable arrangementtable;
   
   public EOPanelStartMenu(EOGUI gui)
   {
      this.gui = gui;
      this.setLayout(null);
   
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 400, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
   
      int fromTop = 5;
      int borderRight = this.gui.getWidth();
      
      JButton createArrangementButton=new JButton("Opret Arrangement");
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
   
      JButton admEventType=new JButton("Administrer Begivenhedstyper");
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
   
      JLabel arrangementLabel=new JLabel("Arrangementer");
      arrangementLabel.setBounds(10, 0, 350, 150);
      arrangementLabel.setFont(gui.getFontbig());
      this.add(arrangementLabel);
   
      JCheckBox showallcheckbox = new JCheckBox("Vis kun ikke afholdte");
      showallcheckbox.setBounds(this.gui.getWidth()-170, 70, 150, 30);
      showallcheckbox.setSelected(true);
      showallcheckbox.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    if(showallcheckbox.isSelected())
                    {
                       gui.runCommand(EOOperation.START);
                    }
                    else
                    {
                       gui.runCommand(EOOperation.STARTSHOWALL);
                    }
                 }
              });
      this.add(showallcheckbox);
   
      
      arrangementtable = new EOGUIArrangementTable(gui);
      arrangementtable.setBounds(10, 100, gui.getWidth()-35, gui.getHeight()-150);
      this.add(arrangementtable);
   }
	/**
	 * 
	 * @param visible
	 * @param data
	 */
   public void setVisible(boolean visible, Object data) {
   	// TODO - implement PanelStartMenu.setVisible
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());
      if(((EOOperation)data).getData() instanceof EOArrangement[])
      {
         arrangementtable.setArrangements((EOArrangement[])((EOOperation)data).getData());
      }
      super.setVisible(visible);
   }
   
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(00,38, this.getWidth(), 38);
   }      
}

