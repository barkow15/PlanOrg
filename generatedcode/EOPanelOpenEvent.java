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

/**
* A user can open 1 event and look at its properties through this panel.
*/
public class EOPanelOpenEvent extends EOPanel {

   EOGUIDateTimePicker startdatetime;
   EOGUIDateTimePicker enddatetime;
   EOGUI gui = null;
   EOGUIMultiSelect facilitatormultiselect;
   EOGUIMultiSelect eventtypemultiselect;
   EOGUIBreadcrumb breadcrumb;
   JTextField begivenhedsprisTextField;
   JTextArea beskrivelsenoterTextArea;
   
   public EOPanelOpenEvent(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
   
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
       
      JButton backbutton=new JButton("Tilbage");
      backbutton.setBounds(this.gui.getWidth()-120, 5, 100, 30);
      backbutton.addActionListener(
                new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                   {
                      EOOperation backoperation = gui.getBreadcrumb().getIndex(gui.getBreadcrumb().getStackCounter()-2);
                      gui.getBreadcrumb().pop();
                      gui.getBreadcrumb().pop();
                      gui.runCommand(backoperation);
                   }
                });
      this.add(backbutton);
   
      int fromTop = 50;
      int borderLeft = 10;
   
      //Column1//
      //dato/tid start
      JLabel startdatetimelabel = new JLabel("Start dato/tid");
      startdatetimelabel.setBounds(10, 40, 400, 20);
      startdatetimelabel.setFont(this.gui.getFontsmall());
      this.add(startdatetimelabel);
   
      startdatetime = new EOGUIDateTimePicker(LocalDateTime.now());
      startdatetime.setBounds(10, 60, 300, 400);
      this.add(startdatetime);
   
      //Column2//
      //dato/tid slut
      JLabel enddatetimelabel = new JLabel("Slut dato/tid");
      enddatetimelabel.setBounds(330, 40, 100, 20);
      enddatetimelabel.setFont(this.gui.getFontsmall());
      this.add(enddatetimelabel);
   
      enddatetime = new EOGUIDateTimePicker(LocalDateTime.now().plusDays(7));
      enddatetime.setBounds(330, 60, 300, 400);
      this.add(enddatetime);
   
      //Column3// 
      //Begivenhedstype
      JLabel eventtypeLabel = new JLabel("Begivenhedstyper:");
      eventtypeLabel.setBounds(650, 40, 250, 20);
      eventtypeLabel.setFont(gui.getFontsmall());
      this.add(eventtypeLabel);
         
      eventtypemultiselect  = new EOGUIMultiSelect(null, new Dimension(300, 240));
      eventtypemultiselect.addMouseListener(gui, EOOperation.OPENEVENTTYPE);
      eventtypemultiselect.setBounds(650, 60, 300, 240);
      this.add(eventtypemultiselect);
   
      JLabel eventtypehelpLabel = new JLabel("* Hoejre klik for at se info.");
      eventtypehelpLabel.setBounds(650, 300, 250, 20);
      eventtypehelpLabel.setFont(gui.getFontsmall());
      this.add(eventtypehelpLabel);
   
      //begivenhedpris
      JLabel begivenhedsprisLabel = new JLabel("Begivenhedspris");
      begivenhedsprisLabel.setBounds(650, 320, 250, 20);
      begivenhedsprisLabel.setFont(gui.getFontsmall());
      this.add(begivenhedsprisLabel);
   
      begivenhedsprisTextField = new JTextField();
      begivenhedsprisTextField.setBounds(650, 340, 100, 20);
      begivenhedsprisTextField.setFont(gui.getFontsmall());
      this.add(begivenhedsprisTextField);
   
      //beskrivelse/noter
      JLabel beskrivelsenoterLabel = new JLabel("Beskrivelse/noter:");
      beskrivelsenoterLabel.setBounds(650, 360, 250, 20);
      beskrivelsenoterLabel.setFont(gui.getFontsmall());
      this.add(beskrivelsenoterLabel);
   
      beskrivelsenoterTextArea = new JTextArea();
      beskrivelsenoterTextArea.setBounds(650, 380, 300, 240);
      beskrivelsenoterTextArea.setFont(gui.getFontsmall());
      this.add(beskrivelsenoterTextArea);
     
      //Column4
      JLabel addfacilitatorLabel = new JLabel("Facilitatorer:");
      addfacilitatorLabel.setBounds(970, 40, 350, 20);
      addfacilitatorLabel.setFont(gui.getFontsmall());
      this.add(addfacilitatorLabel);
   
      facilitatormultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240));
      facilitatormultiselect.addMouseListener(gui, EOOperation.OPENFACILITATOR);
      facilitatormultiselect.setBounds(970, 60, 300, 240);
      this.add(facilitatormultiselect);
   
      JLabel addfacilitatorhelpLabel = new JLabel("* Hoejreklik for at se info");
      addfacilitatorhelpLabel.setBounds(970, 300, 350, 20);
      addfacilitatorhelpLabel.setFont(gui.getFontsmall());
      this.add(addfacilitatorhelpLabel);
   }
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(10,38, this.getWidth(), 38);
      g.drawLine(320,45, 320, this.getHeight()-50);
      g.drawLine(640,45, 640, this.getHeight()-50);
      g.drawLine(960,45, 960, this.getHeight()-50);
   }

   public void setVisible(boolean visible, EOOperation currentEOOperation) {
      if(currentEOOperation == EOOperation.OPENEVENT)
      {
      
               //We expect 
               //0 = EOEvent
         EOEvent event = null;
         if(EOOperation.OPENEVENT.getData() instanceof EOEvent)
         {
            event = (EOEvent)EOOperation.OPENEVENT.getData();
            startdatetime.setDateTime(event.getDateTimeStart());
            enddatetime.setDateTime(event.getDateTimeEnd());
            begivenhedsprisTextField.setText(Double.toString(event.getPrice()));
            beskrivelsenoterTextArea.setText(event.getDescription());
            if(event.getFacilitators() != null)
            {
               facilitatormultiselect.setList(event.getFacilitators());
            }
            if(event.getEventTypes() != null)
            {
               eventtypemultiselect.setList(event.getEventTypes());
            }
         
         }
      }
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());
      super.setVisible(visible, currentEOOperation);
   }



}