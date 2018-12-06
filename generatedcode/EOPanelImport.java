import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                        EOOperation.IMPORTCSV.setData(new EOCSV(file));
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
         "Ved at importere data, saa bliver alt andet data i databasen slettet. Dvs. du har kun adgang til det data som du importere.\n\n" +
         "Vaelg importer i hoejre top hjoerne og derefter find den CSV fil du har modtaget med data eller vaelg annuller for at afbryde import af data.\n\n"
         );
      importnotetextarea.setFont(this.gui.getFontmedium());
      importnotetextarea.setLineWrap(true);
      importnotetextarea.setWrapStyleWord(true);
      importnotetextarea.setBounds(10, 40, 1200, 500);
      importnotetextarea.setBackground(new Color(0, 0, 0, 0));
      this.add(importnotetextarea);
   }

   public void setVisible(boolean visible, EOOperation currentEOOperation) {
      if(currentEOOperation.getData() instanceof FacilitatorContactInfo[])
      {
         facilitatormultiselect.setList((FacilitatorContactInfo[])(currentEOOperation.getData()));
      }
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());      
      super.setVisible(visible, currentEOOperation);
   }
  
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(0,38, this.getWidth(), 38);
  
   }   
}