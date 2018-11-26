import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EOPanelCreateArrangement extends EOPanel {
   EOGUI gui = null;

   public EOPanelCreateArrangement(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);


       JButton savebutton=new JButton("Gem");
       System.out.println(this.gui.getWidth()-110);
       savebutton.setBounds(this.gui.getWidth()-110, 10, 100, 30);
       savebutton.addActionListener(
               new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       gui.runCommand(EOOperation.START);
                   }
               });
       this.add(savebutton);


       JButton cancelbutton=new JButton("Annuller");
       System.out.println(this.gui.getWidth()-210);
       cancelbutton.setBounds(this.gui.getWidth()-210, 10, 100, 30);
       cancelbutton.addActionListener(
               new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       gui.runCommand(EOOperation.START);
                   }
               });
       this.add(cancelbutton);


       JButton deletebutton=new JButton("Slet");
       System.out.println(this.gui.getWidth()-160);
       deletebutton.setBounds(this.gui.getWidth()-160, 300, 100, 30);
       deletebutton.addActionListener(
               new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       gui.runCommand(EOOperation.START);
                   }
               });
       this.add(deletebutton);


       JButton editbutton=new JButton("Rediger");
       System.out.println(this.gui.getWidth()-160);
       editbutton.setBounds(this.gui.getWidth()-310, 300, 100, 30);
       editbutton.addActionListener(
               new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       gui.runCommand(EOOperation.START);
                   }
               });
       this.add(editbutton);


       JButton createbutton=new JButton("Opret");
       System.out.println(this.gui.getWidth()-160);
       createbutton.setBounds(this.gui.getWidth()-460, 300, 100, 30);
       createbutton.addActionListener(
               new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       gui.runCommand(EOOperation.START);
                   }
               });
       this.add(createbutton);


       JLabel arrangementnamelabel=new JLabel("Arrangement navn:");
       arrangementnamelabel.setBounds(10, 50, 120, 30);
       arrangementnamelabel.setFont(this.gui.getFontsmall());

       this.add(arrangementnamelabel);


       JLabel customernamelabel=new JLabel("Kundenavn:");
       customernamelabel.setBounds(400, 50, 120, 30);
       customernamelabel.setFont(this.gui.getFontsmall());

       this.add(customernamelabel);


       JLabel administrateeventlabel=new JLabel("Administrer begivenhed(er):");
       administrateeventlabel.setBounds(750, 50, 155, 30);
       administrateeventlabel.setFont(this.gui.getFontsmall());

       this.add(administrateeventlabel);


       JLabel customeremaillabel=new JLabel("Kundens e-mail:");
       customeremaillabel.setBounds(400, 100, 100, 30);
       customeremaillabel.setFont(this.gui.getFontsmall());

       this.add(customeremaillabel);


       JLabel customerphonenumberlabel=new JLabel("Kundens tlf.:");
       customerphonenumberlabel.setBounds(400, 150, 100, 30);
       customerphonenumberlabel.setFont(this.gui.getFontsmall());

       this.add(customerphonenumberlabel);


       JLabel startdateandtimelabel=new JLabel("Startdato/tid:");
       startdateandtimelabel.setBounds(10, 120, 120, 30);
       startdateandtimelabel.setFont(this.gui.getFontsmall());

       this.add(startdateandtimelabel);


       JLabel enddateandtimelabel=new JLabel("Slutdato/tid:");
       enddateandtimelabel.setBounds(10, 420, 120, 30);
       enddateandtimelabel.setFont(this.gui.getFontsmall());

       this.add(enddateandtimelabel);


       JLabel startdatelabel=new JLabel("Dato:");
       startdatelabel.setBounds(80, 320, 120, 30);
       startdatelabel.setFont(this.gui.getFontsmall());

       this.add(startdatelabel);


       JLabel starttimelabel=new JLabel("Tid:");
       starttimelabel.setBounds(80, 350, 120, 30);
       starttimelabel.setFont(this.gui.getFontsmall());

       this.add(starttimelabel);


       JLabel enddatelabel=new JLabel("Dato:");
       enddatelabel.setBounds(80, 620, 120, 30);
       enddatelabel.setFont(this.gui.getFontsmall());

       this.add(enddatelabel);


       JLabel endtimelabel=new JLabel("Tid:");
       endtimelabel.setBounds(80, 650, 120, 30);
       endtimelabel.setFont(this.gui.getFontsmall());

       this.add(endtimelabel);


       JLabel facilitatorlabel=new JLabel("Facilitator(er):");
       facilitatorlabel.setBounds(400, 200, 100, 30);
       facilitatorlabel.setFont(this.gui.getFontsmall());

       this.add(facilitatorlabel);


       JLabel descriptionlabel=new JLabel("Beskrivelse/noter:");
       descriptionlabel.setBounds(400, 400, 100, 30);
       descriptionlabel.setFont(this.gui.getFontsmall());

       this.add(descriptionlabel);


       JTextField arrangementtextfield=new JTextField();
       arrangementtextfield.setBounds(10, 75, 300, 30);
       arrangementtextfield.setFont(this.gui.getFontsmall());

       this.add(arrangementtextfield);


       JTextField customertextfield=new JTextField();
       customertextfield.setBounds(400, 75, 300, 30);
       customertextfield.setFont(this.gui.getFontsmall());

       this.add(customertextfield);


       JTextField customeremailtextfield=new JTextField();
       customeremailtextfield.setBounds(400, 125, 300, 30);
       customeremailtextfield.setFont(this.gui.getFontsmall());

       this.add(customeremailtextfield);


       JTextField customerphonenumertextfield=new JTextField();
       customerphonenumertextfield.setBounds(400, 175, 300, 30);
       customerphonenumertextfield.setFont(this.gui.getFontsmall());

       this.add(customerphonenumertextfield);


       JTextField descriptiontextfield=new JTextField();
       descriptiontextfield.setBounds(400, 425, 300, 250);
       descriptiontextfield.setFont(this.gui.getFontsmall());

       this.add(descriptiontextfield);


   }
	/**
	 * 
	 * @param visible
	 * @param data
	 */
	public void setVisible(boolean visible, Object data) {
		// TODO - implement PanelStartMenu.setVisible
		super.setVisible(visible);
	}

	public void clearData() {
		// TODO - implement PanelCreateArrangement.clearData
		throw new UnsupportedOperationException();
	}
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawLine(10,38, this.getWidth(), 38);
        g.drawLine(380,45, 380, this.getHeight()-30);
        g.drawLine(730,45, 730, this.getHeight()-30);
    }
}