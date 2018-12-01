import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EOPanelOpenArrangement extends EOPanel {
   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;
   //column1
   JTextField arrangementtextfield;
   EOGUIDateTimePicker startdatetime;
   EOGUIDateTimePicker enddatetime;
   //Column2
   JTextField customertextfield;
   JTextField customeremailtextfield;
   JTextField customerphonenumertextfield;
   JTextArea descriptionjtextarea;
   EOGUIMultiSelect facilitatormultiselect;
   //Column3
   EOGUIMultiSelect eventmultiselect;
   
   public EOPanelOpenArrangement(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
   
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
            
      JButton backbutton=new JButton("Tilbage");
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
   
      //Column 1
      JLabel startdateandtimelabel=new JLabel("Start dato/tid:");
      startdateandtimelabel.setBounds(10, 40, 150, 20);
      startdateandtimelabel.setFont(this.gui.getFontsmall());
      this.add(startdateandtimelabel);

      startdatetime = new EOGUIDateTimePicker();
      startdatetime.setBounds(10, 60, 300, 400);
      add(startdatetime);

      //Column 2
      JLabel enddateandtimelabel=new JLabel("Slut dato/tid:");
      enddateandtimelabel.setBounds(330, 40, 150, 20);
      enddateandtimelabel.setFont(this.gui.getFontsmall());
      this.add(enddateandtimelabel);

      enddatetime = new EOGUIDateTimePicker();
      enddatetime.setBounds(330, 60, 300, 400);
      add(enddatetime);

      //Column 3
      JLabel arrangementnamelabel=new JLabel("Arrangement navn:");
      arrangementnamelabel.setBounds(650, 40, 120, 20);
      arrangementnamelabel.setFont(this.gui.getFontsmall());
      this.add(arrangementnamelabel);

      arrangementtextfield=new JTextField();
      arrangementtextfield.setEditable(false);
      arrangementtextfield.setBounds(650, 60, 300, 20);
      arrangementtextfield.setFont(this.gui.getFontsmall());
      this.add(arrangementtextfield);

      JLabel descriptionlabel=new JLabel("Beskrivelse/noter:");
      descriptionlabel.setBounds(650, 80, 100, 20);
      descriptionlabel.setFont(this.gui.getFontsmall());
      this.add(descriptionlabel);
      
      descriptionjtextarea=new JTextArea();
      descriptionjtextarea.setEditable(false);  
      descriptionjtextarea.setBounds(650, 100, 300, 170);
      descriptionjtextarea.setBorder(gui.getDefaultBorder());
      descriptionjtextarea.setFont(this.gui.getFontsmall());
      this.add(descriptionjtextarea); 
             
      JLabel facilitatorlabel=new JLabel("Facilitator(er):");
      facilitatorlabel.setBounds(650, 280, 100, 20);
      facilitatorlabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorlabel);
      
      facilitatormultiselect = new EOGUIMultiSelect(null, new Dimension(300, 160));
      facilitatormultiselect.setBounds(650, 300, 300, 160);
      this.add(facilitatormultiselect);

      JButton facilitatorbutton=new JButton("Aaben");
      facilitatorbutton.setBounds(850, 470, 100, 20);
      facilitatorbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.CREATEEVENT);
                  }
               });
      this.add(facilitatorbutton);
      
      //Column 4      
      JLabel eventlabel=new JLabel("Begivenheder:");
      eventlabel.setBounds(970, 40, 200, 20);
      eventlabel.setFont(this.gui.getFontsmall());
      this.add(eventlabel);
      
      eventmultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240));
      eventmultiselect.setBounds(970, 60, 300, 240);
      this.add(eventmultiselect);
      
      JButton createbutton=new JButton("?ben");
      System.out.println(this.gui.getWidth()-160);
      createbutton.setBounds(1170, 310, 100, 20);
      createbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.CREATEEVENT);
                  }
               });
      this.add(createbutton);
      
      JLabel customernamelabel=new JLabel("Kundenavn:");
      customernamelabel.setBounds(970, 330, 120, 20);
      customernamelabel.setFont(this.gui.getFontsmall());
      this.add(customernamelabel);

      customertextfield=new JTextField();
      customertextfield.setEditable(false);      
      customertextfield.setBounds(970, 350, 300, 20);
      customertextfield.setFont(this.gui.getFontsmall());
      this.add(customertextfield);
   
      JLabel customeremaillabel=new JLabel("Kundens e-mail:");
      customeremaillabel.setBounds(970, 370, 100, 20);
      customeremaillabel.setFont(this.gui.getFontsmall());
      this.add(customeremaillabel);

      customeremailtextfield=new JTextField();
      customeremailtextfield.setEditable(false);         
      customeremailtextfield.setBounds(970, 390, 300, 20);
      customeremailtextfield.setFont(this.gui.getFontsmall());
      this.add(customeremailtextfield);
   
      JLabel customerphonenumberlabel=new JLabel("Kundens tlf.:");
      customerphonenumberlabel.setBounds(970, 410, 100, 20);
      customerphonenumberlabel.setFont(this.gui.getFontsmall());
      this.add(customerphonenumberlabel);

      customerphonenumertextfield=new JTextField();
      customerphonenumertextfield.setEditable(false);         
      customerphonenumertextfield.setBounds(970, 430, 300, 20);
      customerphonenumertextfield.setFont(this.gui.getFontsmall());
      this.add(customerphonenumertextfield);

   }

   public void setVisible(boolean visible, EOOperation currentEOOperation) {
      if(currentEOOperation == EOOperation.OPENARRANGEMENT)
      {
         if(currentEOOperation.getData().getClass().isArray())
         {
            Object[] edata = (Object[])currentEOOperation.getData();
            if(edata.length == 3)
            {
               //We expect 
               //0 = EOArrangement
               EOArrangement arrangement = null;
               if(edata[0] instanceof EOArrangement)
               {
                  arrangement = (EOArrangement)edata[0];
                  //column1
                  startdatetime.setDateTime(arrangement.getDateTimeStart());
                  enddatetime.setDateTime(arrangement.getDateTimeEnd());
                  arrangementtextfield.setText(arrangement.getName());
                  //Column2
                  if(arrangement.getCustomer() != null)
                  {
                     customertextfield.setText(arrangement.getCustomer().getName());
                     customeremailtextfield.setText(arrangement.getCustomer().getEmail());
                     customerphonenumertextfield.setText(arrangement.getCustomer().getPhone());
                  }
                  descriptionjtextarea.setText(arrangement.getDescription());
               }
               //1 = FacilitatorContactInfo[]
               if(edata[1] instanceof FacilitatorContactInfo[])
               {
                  facilitatormultiselect.setList((FacilitatorContactInfo[])edata[1], arrangement.getFacilitators());
               }
               //2 = EOEvent[]
               if(edata[2] instanceof EOEvent[])
               {
                  eventmultiselect.setList((EOEvent[])edata[2], arrangement.getEvents());
               }
            }
         }
      }

      breadcrumb.setBreadcrumb(gui.getBreadcrumb()); 
      super.setVisible(visible, currentEOOperation);
   }


   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(10,38, this.getWidth(), 38);
      g.drawLine(320,45, 320, this.getHeight()-50);
      g.drawLine(640,45, 640, this.getHeight()-50);
      g.drawLine(960,45, 960, this.getHeight()-50);      
   }
}