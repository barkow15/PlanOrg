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
      customernamelabel.setBounds(400, 50, 120, 30);
      customernamelabel.setFont(this.gui.getFontsmall());
      this.add(customernamelabel);

      JTextField customertextfield=new JTextField();
      customertextfield.setBounds(400, 75, 300, 30);
      customertextfield.setFont(this.gui.getFontsmall());
      this.add(customertextfield);
   
      JLabel customeremaillabel=new JLabel("Kundens e-mail:");
      customeremaillabel.setBounds(400, 100, 100, 30);
      customeremaillabel.setFont(this.gui.getFontsmall());
      this.add(customeremaillabel);

      JTextField customeremailtextfield=new JTextField();
      customeremailtextfield.setBounds(400, 125, 300, 30);
      customeremailtextfield.setFont(this.gui.getFontsmall());
      this.add(customeremailtextfield);
   
      JLabel customerphonenumberlabel=new JLabel("Kundens tlf.:");
      customerphonenumberlabel.setBounds(400, 150, 100, 30);
      customerphonenumberlabel.setFont(this.gui.getFontsmall());
      this.add(customerphonenumberlabel);

      JTextField customerphonenumertextfield=new JTextField();
      customerphonenumertextfield.setBounds(400, 175, 300, 30);
      customerphonenumertextfield.setFont(this.gui.getFontsmall());
      this.add(customerphonenumertextfield);
   
      JLabel facilitatorlabel=new JLabel("Facilitator(er):");
      facilitatorlabel.setBounds(400, 200, 100, 30);
      facilitatorlabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorlabel);
   
   
      JLabel descriptionlabel=new JLabel("Beskrivelse/noter:");
      descriptionlabel.setBounds(400, 400, 100, 30);
      descriptionlabel.setFont(this.gui.getFontsmall());
      this.add(descriptionlabel);

      JTextField descriptiontextfield=new JTextField();
      descriptiontextfield.setBounds(400, 425, 300, 250);
      descriptiontextfield.setFont(this.gui.getFontsmall());
      this.add(descriptiontextfield);

      //Column 3 
      JButton createbutton=new JButton("Åben");
      System.out.println(this.gui.getWidth()-160);
      createbutton.setBounds(this.gui.getWidth()-460, 300, 100, 30);
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

         arrangementtextfield.setText(arrangement.getName());
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