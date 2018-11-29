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
   EOGUIMultiSelect eventtypemultiselect;
   
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
      JLabel arrangementnamelabel=new JLabel("Arrangement navn:");
      arrangementnamelabel.setBounds(10, 40, 120, 20);
      arrangementnamelabel.setFont(this.gui.getFontsmall());
      this.add(arrangementnamelabel);

      arrangementtextfield=new JTextField();
      arrangementtextfield.setEditable(false);
      arrangementtextfield.setBounds(10, 60, 300, 20);
      arrangementtextfield.setFont(this.gui.getFontsmall());
      this.add(arrangementtextfield);

      JLabel startdateandtimelabel=new JLabel("Startdato/tid:");
      startdateandtimelabel.setBounds(10, 80, 120, 20);
      startdateandtimelabel.setFont(this.gui.getFontsmall());
      this.add(startdateandtimelabel);

      startdatetime = new EOGUIDateTimePicker();
      startdatetime.setBounds(10, 100, 300, 400);
      add(startdatetime);

      JLabel enddateandtimelabel=new JLabel("Startdato/tid:");
      enddateandtimelabel.setBounds(10, 500, 120, 20);
      enddateandtimelabel.setFont(this.gui.getFontsmall());
      this.add(enddateandtimelabel);

      enddatetime = new EOGUIDateTimePicker();
      enddatetime.setBounds(10, 520, 300, 400);
      add(enddatetime);

      //Column 2           
      JLabel customernamelabel=new JLabel("Kundenavn:");
      customernamelabel.setBounds(400, 40, 120, 20);
      customernamelabel.setFont(this.gui.getFontsmall());
      this.add(customernamelabel);

      customertextfield=new JTextField();
      customertextfield.setEditable(false);      
      customertextfield.setBounds(400, 60, 300, 20);
      customertextfield.setFont(this.gui.getFontsmall());
      this.add(customertextfield);
   
      JLabel customeremaillabel=new JLabel("Kundens e-mail:");
      customeremaillabel.setBounds(400, 80, 100, 20);
      customeremaillabel.setFont(this.gui.getFontsmall());
      this.add(customeremaillabel);

      customeremailtextfield=new JTextField();
      customeremailtextfield.setEditable(false);         
      customeremailtextfield.setBounds(400, 100, 300, 20);
      customeremailtextfield.setFont(this.gui.getFontsmall());
      this.add(customeremailtextfield);
   
      JLabel customerphonenumberlabel=new JLabel("Kundens tlf.:");
      customerphonenumberlabel.setBounds(400, 120, 100, 20);
      customerphonenumberlabel.setFont(this.gui.getFontsmall());
      this.add(customerphonenumberlabel);

      customerphonenumertextfield=new JTextField();
      customerphonenumertextfield.setEditable(false);         
      customerphonenumertextfield.setBounds(400, 140, 300, 20);
      customerphonenumertextfield.setFont(this.gui.getFontsmall());
      this.add(customerphonenumertextfield);
   
      JLabel facilitatorlabel=new JLabel("Facilitator(er):");
      facilitatorlabel.setBounds(400, 160, 100, 20);
      facilitatorlabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorlabel);
      
      facilitatormultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240));
      facilitatormultiselect.setBounds(400, 180, 300, 240);
      this.add(facilitatormultiselect);
      
      JLabel descriptionlabel=new JLabel("Beskrivelse/noter:");
      descriptionlabel.setBounds(400, 420, 100, 20);
      descriptionlabel.setFont(this.gui.getFontsmall());
      this.add(descriptionlabel);

      descriptionjtextarea=new JTextArea();
      descriptionjtextarea.setEditable(false);  
      descriptionjtextarea.setBounds(400, 440, 300, 250);
      descriptionjtextarea.setBorder(gui.getDefaultBorder());
      descriptionjtextarea.setFont(this.gui.getFontsmall());
      this.add(descriptionjtextarea);

      //Column 3 
      JLabel eventtypelabel=new JLabel("Begivenhedstype(r):");
      eventtypelabel.setBounds(740, 40, 200, 30);
      eventtypelabel.setFont(this.gui.getFontsmall());
      this.add(eventtypelabel);
      
      eventtypemultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240));
      eventtypemultiselect.setBounds(740, 60, 300, 240);
      this.add(eventtypemultiselect);
      
      JButton createbutton=new JButton("Åben");
      System.out.println(this.gui.getWidth()-160);
      createbutton.setBounds(940, 360, 100, 20);
      createbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.CREATEEVENT);
                  }
               });
      this.add(createbutton);
   }
	/**
	 * 
	 * @param visible
	 * @param data
	 */
   public void setVisible(boolean visible, Object data) {
      if(((EOOperation)data).getData() instanceof EOArrangement)
      {
         EOArrangement arrangement = (EOArrangement)(((EOOperation)data).getData());
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

      breadcrumb.setBreadcrumb(gui.getBreadcrumb()); 
      super.setVisible(visible);
   }


   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(10,38, this.getWidth(), 38);
      g.drawLine(380,45, 380, this.getHeight()-30);
      g.drawLine(730,45, 730, this.getHeight()-30);
   }
}