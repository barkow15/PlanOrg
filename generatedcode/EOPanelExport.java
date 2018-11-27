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

public class EOPanelExport extends EOPanel {
   EOGUI gui = null;
   EOGUIDateTimePicker startdatetime;
   EOGUIDateTimePicker enddatetime;
   EOGUIMultiSelect facilitatormultiselect;
   JCheckBox exportprice;
   FacilitatorContactInfo[] f;
   
   public EOPanelExport(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
      
      //Header
      EOGUIBreadcrumb breadcrumb = this.gui.getBreadcrumb();
      breadcrumb.setBounds(5, 5, 800, 30);
      this.add(breadcrumb);
      
      JButton exportbutton = new JButton("Export");
      exportbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      exportbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     //We take all data from the panel
                     try{
                        LocalDateTime sdatetime = startdatetime.getDateTime();
                        LocalDateTime edatetime = enddatetime.getDateTime();
                        exportprice.isSelected();
                        Object[] selecteditems = facilitatormultiselect.getSelected();
                        File file = null;
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setSelectedFile(new File("EOEksport.CSV"));
                        //Prompt user for where file needs to be saved
                        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                        {
                           String path=fileChooser.getCurrentDirectory().getAbsolutePath();
                           String filename=fileChooser.getSelectedFile().getName();
                           file = new File(path, filename);
                          
                           FacilitatorContactInfo[] fci = null;
                           if(selecteditems != null)
                           {
                              int len = selecteditems.length;
                              fci = new FacilitatorContactInfo[len];
                              for(int i = 0; i < selecteditems.length; i++)
                              {
                                 fci[i] = (FacilitatorContactInfo)selecteditems[i];
                              }
                           }
                           EOOperation.SAVECSV.setData(new EOCSV(sdatetime, edatetime, fci, exportprice.isSelected(), file));
                        
                           exportprice.setSelected(false);
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
      
      //Column1//
      JLabel startdatetimelabel = new JLabel("Start dato/tid");
      startdatetimelabel.setBounds(65, 40, 400, 20);
      startdatetimelabel.setFont(this.gui.getFontsmall());
      this.add(startdatetimelabel);
      
      startdatetime = new EOGUIDateTimePicker(LocalDateTime.now());
      startdatetime.setBounds(65, 60, 300, 400);
      this.add(startdatetime);
   
      JLabel enddatetimelabel = new JLabel("Slut dato/tid");
      enddatetimelabel.setBounds(65, 460, 100, 20);
      enddatetimelabel.setFont(this.gui.getFontsmall());
      this.add(enddatetimelabel);
      
      enddatetime = new EOGUIDateTimePicker(LocalDateTime.now().plusDays(7));
      enddatetime.setBounds(65, 480, 300, 400);
      this.add(enddatetime);
   
      //Column 2//   
      JLabel facilitatorslabel = new JLabel("Facilitator(er)");
      facilitatorslabel.setBounds(500, 40, 100, 20);
      facilitatorslabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorslabel);       
      
      //Example data, here we need to load data from database
   
                        
      facilitatormultiselect = new EOGUIMultiSelect(f, new Dimension(300, 240));
      facilitatormultiselect.setBounds(500, 60, 300, 240);
      this.add(facilitatormultiselect);
      
      exportprice = new JCheckBox("Vis priser");
      exportprice.setSelected(false);
      exportprice.setBounds(500, 310, 100, 20);
      this.add(exportprice);
      
      //Column 3//
      JTextArea exportnotetextarea = new JTextArea(
         "Vælg den tidsperiode du ønsker at eksportere og de facilitatorer som der er med i arrangementerne.\n\n" +
         "Efter du har eksporteret data, kan du sende CSV filen til den/de facilitatorer som skal afholde arrangementet. Som kan åbne filen i deres Event Organizer program.\n\n" +
         "Som standard er prisen for de forskellige dele af arrangementet ikke synlige."
         );
      exportnotetextarea.setFont(this.gui.getFontsmall());
      exportnotetextarea.setLineWrap(true);
      exportnotetextarea.setWrapStyleWord(true);
      exportnotetextarea.setBounds(910, 60, 300, 300);
      exportnotetextarea.setBackground(new Color(0, 0, 0, 0));
      this.add(exportnotetextarea);
   }

   public void setVisible(boolean visible, Object data) {
      if(data instanceof FacilitatorContactInfo[])
      {
         facilitatormultiselect.setList((FacilitatorContactInfo[])data);
      }
      // TODO - implement PanelStartMenu.setVisible
      super.setVisible(visible);
   }
  
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(0,38, this.getWidth(), 38);
      g.drawLine(433,45, 433, this.getHeight()-50);
      g.drawLine(866,45, 866, this.getHeight()-50);
   }   
}