import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;

public class EOPanelExport extends EOPanel {
   EOGUI gui = null;
   EOGUIDateTimePicker startdatetime;
   EOGUIDateTimePicker enddatetime;
   EOGUIMultiSelect facilitatormultiselect;
   JCheckBox exportprice;
   FacilitatorContactInfo[] f;
   EOGUIBreadcrumb breadcrumb;
   
   public EOPanelExport(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
      
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
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
      startdatetimelabel.setBounds(10, 40, 400, 20);
      startdatetimelabel.setFont(this.gui.getFontsmall());
      this.add(startdatetimelabel);
      
      startdatetime = new EOGUIDateTimePicker(LocalDateTime.now());
      startdatetime.setBounds(10, 60, 300, 400);
      this.add(startdatetime);
      //Column 2// 
      JLabel enddatetimelabel = new JLabel("Slut dato/tid");
      enddatetimelabel.setBounds(330, 40, 100, 20);
      enddatetimelabel.setFont(this.gui.getFontsmall());
      this.add(enddatetimelabel);
      
      enddatetime = new EOGUIDateTimePicker(LocalDateTime.now().plusDays(7));
      enddatetime.setBounds(330, 60, 300, 400);
      this.add(enddatetime);
   
      //Column 3//   
      JLabel facilitatorslabel = new JLabel("Facilitator(er)");
      facilitatorslabel.setBounds(650, 40, 100, 20);
      facilitatorslabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorslabel);       
      
      //Example data, here we need to load data from database
   
                        
      facilitatormultiselect = new EOGUIMultiSelect(f, new Dimension(300, 240));
      facilitatormultiselect.setBounds(650, 60, 300, 240);
      this.add(facilitatormultiselect);
      
      exportprice = new JCheckBox("Vis priser");
      exportprice.setSelected(false);
      exportprice.setBounds(650, 310, 100, 20);
      this.add(exportprice);
      
      //Column 4//
      JTextArea exportnotetextarea = new JTextArea(
         "Vaelg den tidsperiode du oensker at eksportere og de facilitatorer som der er med i arrangementerne.\n\n" +
         "Efter du har eksporteret data, kan du sende CSV filen til den/de facilitatorer som skal afholde arrangementet. Som kan aabne filen i deres Event Organizer program.\n\n" +
         "Som standard er prisen for de forskellige dele af arrangementet ikke synlige."
         );
      exportnotetextarea.setFont(this.gui.getFontsmall());
      exportnotetextarea.setLineWrap(true);
      exportnotetextarea.setWrapStyleWord(true);
      exportnotetextarea.setBounds(970, 60, 300, 300);
      exportnotetextarea.setBackground(new Color(0, 0, 0, 0));
      this.add(exportnotetextarea);
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
      g.drawLine(320,45, 320, this.getHeight()-50);
      g.drawLine(640,45, 640, this.getHeight()-50);
      g.drawLine(960,45, 960, this.getHeight()-50);      
   }   
}