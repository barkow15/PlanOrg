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


public class EOPanelCreateEvent extends EOPanel {

   EOGUIDateTimePicker startdatetime;
   EOGUIDateTimePicker enddatetime;
   EOGUI gui = null;
   EOGUIMultiSelect facilitatormultiselect;
   EOGUIMultiSelect eventtypemultiselect;
   EOGUIBreadcrumb breadcrumb;
   
   public EOPanelCreateEvent(EOGUI gui) {
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
   
      JButton savebutton=new JButton("Gem");
      savebutton.setBounds(this.gui.getWidth()-120, 5, 100, 30);
      savebutton.addActionListener(
                new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                   {
                      gui.runCommand(EOOperation.SAVECREATEEVENT);
                   }
                });
      this.add(savebutton);
   
      int fromTop = 50;
      int borderLeft = 10;
   
      //Column1//
      //begivenheds navn
      JLabel eventnameLabel = new JLabel("Begivenheds navn");
      eventnameLabel.setBounds(borderLeft, 40, 250, 20);
      eventnameLabel.setFont(gui.getFontsmall());
      this.add(eventnameLabel);
   
      JTextField eventnameTextField = new JTextField();
      eventnameTextField.setBounds(borderLeft, 60, 250, 20);
      eventnameTextField.setFont(gui.getFontsmall());
      this.add(eventnameTextField);
   
      //Begivenhedstype
      JLabel eventtypeLabel = new JLabel("Begivenhedstype");
      eventtypeLabel.setBounds(borderLeft, 100, 250, 20);
      eventtypeLabel.setFont(gui.getFontsmall());
      this.add(eventtypeLabel);
   
      eventtypemultiselect  = new EOGUIMultiSelect(null, new Dimension(300, 240));
      eventtypemultiselect.setBounds(borderLeft, 120, 300, 240);
      this.add(eventtypemultiselect);
   
      //begivenhedpris
      JLabel begivenhedsprisLabel = new JLabel("Begivenhedspris");
      begivenhedsprisLabel.setBounds(borderLeft, 380, 250, 20);
      begivenhedsprisLabel.setFont(gui.getFontsmall());
      this.add(begivenhedsprisLabel);
   
      JTextField begivenhedsprisTextField = new JTextField();
      begivenhedsprisTextField.setBounds(borderLeft, 400, 100, 20);
      begivenhedsprisTextField.setFont(gui.getFontsmall());
      this.add(begivenhedsprisTextField);
   
      //beskrivelse/noter
      JLabel beskrivelsenoterLabel = new JLabel("Beskrivelse/noter:");
      beskrivelsenoterLabel.setBounds(borderLeft, 440, 250, 20);
      beskrivelsenoterLabel.setFont(gui.getFontsmall());
      this.add(beskrivelsenoterLabel);
   
      JTextField beskrivelsenoterTextField = new JTextField();
      beskrivelsenoterTextField.setBounds(borderLeft, 460, 300, 240);
      beskrivelsenoterTextField.setFont(gui.getFontsmall());
      this.add(beskrivelsenoterTextField);
   
   
      //Column2//
      //dato/tid start
      JLabel startdatetimelabel = new JLabel("Start dato/tid");
      startdatetimelabel.setBounds(443, 40, 400, 20);
      startdatetimelabel.setFont(this.gui.getFontsmall());
      this.add(startdatetimelabel);
   
      startdatetime = new EOGUIDateTimePicker(LocalDateTime.now());
      startdatetime.setBounds(443, 60, 300, 400);
      this.add(startdatetime);
   
      //dato/tid slut
      JLabel enddatetimelabel = new JLabel("Slut dato/tid");
      enddatetimelabel.setBounds(443, 460, 100, 20);
      enddatetimelabel.setFont(this.gui.getFontsmall());
      this.add(enddatetimelabel);
   
      enddatetime = new EOGUIDateTimePicker(LocalDateTime.now().plusDays(7));
      enddatetime.setBounds(443, 480, 300, 400);
      this.add(enddatetime);
   
   
      //Column3
      JLabel addfacilitatorLabel = new JLabel("Tilfoej facilitator(er) til begivenhed ");
      addfacilitatorLabel.setBounds(876, 40, 350, 20);
      addfacilitatorLabel.setFont(gui.getFontsmall());
      this.add(addfacilitatorLabel);
   
      facilitatormultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240));
      facilitatormultiselect.setBounds(876, 60, 300, 240);
      this.add(facilitatormultiselect);
   
   
   }
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(0,38, this.getWidth(), 38);
      g.drawLine(433,45, 433, this.getHeight()-50);
      g.drawLine(866,45, 866, this.getHeight()-50);
   }

   public void setVisible(boolean visible, Object data) {
   	// TODO - implement PanelStartMenu.setVisible
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());
   
      super.setVisible(visible);
   }



}