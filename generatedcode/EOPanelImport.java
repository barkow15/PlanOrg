import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import java.awt.Graphics;
import java.time.LocalDateTime;
import java.io.File;

public class EOPanelImport extends EOPanel {
   EOGUI gui = null;
   EOGUIDateTimePicker startdatetime;
   EOGUIDateTimePicker enddatetime;
   EOGUIMultiSelect facilitatormultiselect;
   JCheckBox exportprice;
   FacilitatorContactInfo[] f;
   EOGUIBreadcrumb breadcrumb;
   
   public EOPanelImport(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
      
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
      
      JButton importbutton = new JButton("Importer");
      importbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      importbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     File file = null;
                     JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setSelectedFile(new File("EOEksport.CSV"));
                     //Prompt user for where CSV file is
                     if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                     {
                        String path=fileChooser.getCurrentDirectory().getAbsolutePath();
                        String filename=fileChooser.getSelectedFile().getName();
                        file = new File(path, filename);
                        EOOperation.IMPORTCSV.setData(file);
                        gui.runCommand(EOOperation.IMPORTCSV);                       
                     }
                   }
                });
      this.add(importbutton);
      
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
      
      //Page text
      JTextArea importnotetextarea = new JTextArea(
         "Ved at importere data, så bliver alt andet data i databasen slettet. Dvs. du har kun adgang til det data som du importere.\n\n" +
         "Vælg importer i højre top hjørne og derefter find den CSV fil du har modtaget med data eller vælg annuller for at afbryde import af data.\n\n"
         );
      importnotetextarea.setFont(this.gui.getFontmedium());
      importnotetextarea.setLineWrap(true);
      importnotetextarea.setWrapStyleWord(true);
      importnotetextarea.setBounds(10, 40, 1200, 500);
      importnotetextarea.setBackground(new Color(0, 0, 0, 0));
      this.add(importnotetextarea);
   }

   public void setVisible(boolean visible, Object data) {
      if(((EOOperation)data).getData() instanceof FacilitatorContactInfo[])
      {
         facilitatormultiselect.setList((FacilitatorContactInfo[])((EOOperation)data).getData());
      }
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());      
      super.setVisible(visible);
   }
  
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(0,38, this.getWidth(), 38);
  
   }   
}