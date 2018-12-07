import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;

/**
* The default export panel, the user uses to export data.
*/
public class EOPanelExport extends EOPanel {
   EOGUI gui = null;
   EOGUIDateTimePicker startdatetime;
   EOGUIDateTimePicker enddatetime;
   EOGUIMultiSelect facilitatormultiselect;
   JCheckBox exportprice;
   EOGUIBreadcrumb breadcrumb;
   EOGUIMultiSelect arrangementmultiselect;
   
   public EOPanelExport(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
      
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
      
      JButton backbutton = new JButton("Tilbage");
      backbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      backbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.START);
                  }
               });
      this.add(backbutton);
          
      //Column1//
      JButton exportallbutton = new JButton("Eksporter alt data i programmet");
      exportallbutton.setBounds(10, 43, 300, 30);
      exportallbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     try{
                        File file = null;
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setSelectedFile(new File("EOEksport.CSV"));
                        //Prompt user for where file needs to be saved
                        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                        {
                           String path=fileChooser.getCurrentDirectory().getAbsolutePath();
                           String filename=fileChooser.getSelectedFile().getName();
                           file = new File(path, filename);
                          
                           EOOperation.SAVECSV.setData(new EOCSV(file));
                        
                           //We leave the page by calling SAVECSV
                           gui.runCommand(EOOperation.SAVECSV);
                        }
                     
                     }
                     catch(Exception er)
                     {
                        System.out.println("Der er sket en fejl" + er.getMessage());
                        EOOperation.SAVECSV.setData(null);
                        gui.runCommand(EOOperation.ERROR);
                     }
                  }
               });
      this.add(exportallbutton);
               
      //Column 2//
      JLabel exportfacilitatorarrangementlabel = new JLabel("Facilitatorer:");
      exportfacilitatorarrangementlabel.setBounds(330, 40, 100, 20);
      exportfacilitatorarrangementlabel.setFont(this.gui.getFontsmall());
      this.add(exportfacilitatorarrangementlabel);
      //Example data, here we need to load data from database
   
                        
      facilitatormultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240), ListSelectionModel.SINGLE_SELECTION);
      facilitatormultiselect.setBounds(330, 60, 300, 240);
      this.add(facilitatormultiselect);
   
      exportprice = new JCheckBox("Inkluder priser");
      exportprice.setSelected(false);
      exportprice.setBounds(330, 300, 150, 20);
   //      this.add(exportprice);
   
      JCheckBox exportdone = new JCheckBox("Inkluder afholdte");
      exportdone.setSelected(false);
      exportdone.setBounds(330, 320, 150, 20);
   //      this.add(exportdone);
      
      JButton exportfacilitatorarrangementbutton = new JButton("<html>Eksporter de arangementer som den/de<br/>valgte facilitatorer er tilknyttet</html>");
      exportfacilitatorarrangementbutton.setBounds(330, 300, 300, 60);
      exportfacilitatorarrangementbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     if(facilitatormultiselect.getSelected() != null && facilitatormultiselect.getSelected().length > 0)
                     {
                        Object[] fe = facilitatormultiselect.getSelected();
                        FacilitatorContactInfo[] f = new FacilitatorContactInfo[fe.length];
                        for(int i = 0; i < fe.length; i++)
                        {
                           f[i] = (FacilitatorContactInfo) fe[i];
                        }
                        File file = null;
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setSelectedFile(new File("EOEksport.CSV"));
                           //Prompt user for where file needs to be saved
                        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                        {
                           String path=fileChooser.getCurrentDirectory().getAbsolutePath();
                           String filename=fileChooser.getSelectedFile().getName();
                           file = new File(path, filename);
                             
                           EOOperation.SAVECSV.setData(
                                 new EOCSV(
                                    f, 
                                    exportprice.isSelected(), 
                                    exportdone.isSelected(), 
                                    file
                                 )
                               );
                           
                              //We leave the page by calling SAVECSV
                           gui.runCommand(EOOperation.SAVECSV);
                        }
                     }
                     else
                     {
                        gui.dialogbox("Du skal minimum vælge minimum en facilitator før du kan eksportere.");
                     }
                  }
               });
      this.add(exportfacilitatorarrangementbutton);
   
      //Column 3//   
      JLabel exportarrangementlabel = new JLabel("Arrangementer:");
      exportarrangementlabel.setBounds(650, 40, 100, 20);
      exportarrangementlabel.setFont(this.gui.getFontsmall());
      this.add(exportarrangementlabel);
      
      arrangementmultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240));
      arrangementmultiselect.setBounds(650, 60, 300, 240);
      this.add(arrangementmultiselect);
       
      JButton exportarrangementfacilitatorbutton = new JButton("Eksporter bestemte arrangementer");
      exportarrangementfacilitatorbutton.setBounds(650, 300, 300, 30);
      exportarrangementfacilitatorbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     if(arrangementmultiselect.getSelected() != null && arrangementmultiselect.getSelected().length > 0)
                     {
                        Object[] ae = arrangementmultiselect.getSelected();
                        EOArrangement[] a = new EOArrangement[ae.length];
                        for(int i = 0; i < ae.length; i++)
                        {
                           System.out.println("ADDING AOARRANGEMENT FOR EXPORT");
                           a[i] = (EOArrangement) ae[i];
                        }
                        File file = null;
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setSelectedFile(new File("EOEksport.CSV"));
                           //Prompt user for where file needs to be saved
                        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                        {
                           String path=fileChooser.getCurrentDirectory().getAbsolutePath();
                           String filename=fileChooser.getSelectedFile().getName();
                           file = new File(path, filename);
                             
                           EOOperation.SAVECSV.setData(
                                 new EOCSV(
                                    a, 
                                    file
                                 )
                               );
                           
                              //We leave the page by calling SAVECSV
                           gui.runCommand(EOOperation.SAVECSV);
                        }
                     }
                     else
                     {
                        gui.dialogbox("Du skal minimum vælge minimum et arrangement før du kan eksportere.");
                     }
                  }
               });
      this.add(exportarrangementfacilitatorbutton);      
   
      
      //Column 4//
      JTextArea exportnotetextarea = new JTextArea(
         "Der er 3 forskellige eksport valgmuligheder.\n\n" + 
         "1) eksporter alt data i programmet.\n\n" +
         "2) eksporter arrangementer ud fra tilknyttet facilitator.\nAlle de arrangementer en facilitator er tilknyttet eksporteres.\n\n"+
         "3) eksporter Arrangementer.\nVælg de arrangementer på listen du ønsker at eksportere.\nDu har også mulighed for at eksportere dette hvis du går ind under et arrangement et andet sted i programmet.\n"
         );
      exportnotetextarea.setFont(this.gui.getFontsmall());
      exportnotetextarea.setLineWrap(true);
      exportnotetextarea.setWrapStyleWord(true);
      exportnotetextarea.setBounds(970, 60, 300, 300);
      exportnotetextarea.setBackground(new Color(0, 0, 0, 0));
      exportnotetextarea.setEditable(false);
      this.add(exportnotetextarea);
   }

   public void setVisible(boolean visible, EOOperation currentEOOperation) {
      if(currentEOOperation.getData().getClass().isArray())
      {         
         Object[] data = (Object[]) currentEOOperation.getData();
         if(data[0] instanceof FacilitatorContactInfo[] && data[0] != null)
         {     
            facilitatormultiselect.setList((FacilitatorContactInfo[])data[0]);
         }
         if(data[1] instanceof EOArrangement[] && data[1] != null)
         {
            arrangementmultiselect.setList((EOArrangement[])data[1]);
         }         
      }
   
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());      
      super.setVisible(visible, currentEOOperation);
   }
  
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(0,38, this.getWidth(), 38);
      g.drawLine(320,45, 320, this.getHeight()-50);
      g.drawLine(640,45, 640, this.getHeight()-50);
      g.drawLine(960,45, 960, this.getHeight()-50);      
   }   
}