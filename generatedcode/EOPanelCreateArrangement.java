import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EOPanelCreateArrangement extends EOPanel {
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

   public EOPanelCreateArrangement(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);

       //Header
       breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
       breadcrumb.setBounds(5, 5, 800, 30);
       breadcrumb.setVisible(true);
       this.add(breadcrumb);

       JButton cancelbutton=new JButton("Annuller");
       cancelbutton.setBounds(this.gui.getWidth()-225, 5, 100, 30);
       cancelbutton.addActionListener(
               new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       gui.runCommand(EOOperation.START);
                   }
               });
       this.add(cancelbutton);


       JButton createbutton=new JButton("Opret");
       createbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
       createbutton.addActionListener(
               new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       gui.runCommand(EOOperation.START);
                   }
               });
       this.add(createbutton);

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
       arrangementtextfield.setBounds(650, 60, 300, 20);
       arrangementtextfield.setFont(this.gui.getFontsmall());
       this.add(arrangementtextfield);

       JLabel descriptionlabel=new JLabel("Beskrivelse/noter:");
       descriptionlabel.setBounds(650, 80, 100, 20);
       descriptionlabel.setFont(this.gui.getFontsmall());
       this.add(descriptionlabel);

       descriptionjtextarea=new JTextArea();
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

       JButton facilitatorbutton=new JButton("Åben");
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
       JLabel eventtypelabel=new JLabel("Begivenhedstype(r):");
       eventtypelabel.setBounds(970, 40, 200, 20);
       eventtypelabel.setFont(this.gui.getFontsmall());
       this.add(eventtypelabel);

       eventtypemultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240));
       eventtypemultiselect.setBounds(970, 60, 300, 240);
       this.add(eventtypemultiselect);

       JButton createarrangementbutton=new JButton("?ben");
       System.out.println(this.gui.getWidth()-160);
       createarrangementbutton.setBounds(1170, 310, 100, 20);
       createarrangementbutton.addActionListener(
               new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       gui.runCommand(EOOperation.CREATEEVENT);
                   }
               });
       this.add(createarrangementbutton);

       JLabel customernamelabel=new JLabel("Kundenavn:");
       customernamelabel.setBounds(970, 330, 120, 20);
       customernamelabel.setFont(this.gui.getFontsmall());
       this.add(customernamelabel);

       customertextfield=new JTextField();
       customertextfield.setBounds(970, 350, 300, 20);
       customertextfield.setFont(this.gui.getFontsmall());
       this.add(customertextfield);

       JLabel customeremaillabel=new JLabel("Kundens e-mail:");
       customeremaillabel.setBounds(970, 370, 100, 20);
       customeremaillabel.setFont(this.gui.getFontsmall());
       this.add(customeremaillabel);

       customeremailtextfield=new JTextField();
       customeremailtextfield.setBounds(970, 390, 300, 20);
       customeremailtextfield.setFont(this.gui.getFontsmall());
       this.add(customeremailtextfield);

       JLabel customerphonenumberlabel=new JLabel("Kundens tlf.:");
       customerphonenumberlabel.setBounds(970, 410, 100, 20);
       customerphonenumberlabel.setFont(this.gui.getFontsmall());
       this.add(customerphonenumberlabel);

       customerphonenumertextfield=new JTextField();
       customerphonenumertextfield.setBounds(970, 430, 300, 20);
       customerphonenumertextfield.setFont(this.gui.getFontsmall());
       this.add(customerphonenumertextfield);
   
   
   }
	/**
	 * 
	 * @param visible
	 * @param data
	 */
   public void setVisible(boolean visible, EOOperation currentEOOperation) {
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