import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import java.time.LocalDateTime;


public class EOPanelOpenFacilitator extends EOPanel {
   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;
   JTextField nameText;
   JTextField emailText;
   JTextField phoneNumberText;
   JTextArea notesText;
   JOptionPane besked;
   EOGUIMultiSelect admFacilitatorMultiselect;
   
   public EOPanelOpenFacilitator(EOGUI gui) {
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
                     EOOperation backoperation = gui.getBreadcrumb().getIndex(gui.getBreadcrumb().getStackCounter()-2);
                     gui.getBreadcrumb().pop();
                     gui.getBreadcrumb().pop();
                     gui.runCommand(backoperation);
                  }
               });
      this.add(backbutton);

     
      JLabel nameOfFacilitator = new JLabel("Navn:");
      nameOfFacilitator.setBounds(450,75,100,50);
      this.add(nameOfFacilitator);
   
      nameText = new JTextField();
      nameText.setEditable(false);
      nameText.setBounds(450,125,150,20);
      this.add(nameText);
   
      JLabel email = new JLabel("Email:");
      email.setBounds(450,150,100,50);
      this.add(email);
   
      emailText = new JTextField();
      emailText.setEditable(false);      
      emailText.setBounds(450,200,150,20);
      this.add(emailText);
   
      JLabel phoneNumber = new JLabel("Telefonnummer:");
      phoneNumber.setBounds(450,225,100,50);
      this.add(phoneNumber);

      phoneNumberText = new JTextField();
      phoneNumberText.setEditable(false);        
      phoneNumberText.setBounds (450,275,150,20);
      this.add(phoneNumberText);
   
      JLabel notes = new JLabel("Noter:");
      notes.setBounds(450,300,150,50);
      this.add(notes);


      notesText = new JTextArea();
      notesText.setEditable(false);          
      notesText.setBounds(450,350,300,150);
      this.add(notesText);
   

   }

   public void setVisible(boolean visible, EOOperation currentEOOperation) {
      if (EOOperation.OPENFACILITATOR.getData() instanceof FacilitatorContactInfo)
      {
         FacilitatorContactInfo facilitator = (FacilitatorContactInfo) EOOperation.OPENFACILITATOR.getData();
         nameText.setText(facilitator.getName());
         phoneNumberText.setText(facilitator.getPhone());
         emailText.setText(facilitator.getEmail());
         notesText.setText(facilitator.getInfo());
      }
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());      
      super.setVisible(visible, currentEOOperation);
   }

   public void clearData() {
      //Clear input Fields.
      EOOperation facilitatorEnum=currentEOOperation;
      if (EOOperation.ADMFACILITATOR == facilitatorEnum){
         nameText.setText("");
         phoneNumberText.setText("");
         emailText.setText("");
         notesText.setText("");
      }
   }

   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(00,38, this.getWidth(), 38);
      g.drawLine(433,45, 433, this.getHeight()-50);
      g.drawLine(866,45, 866, this.getHeight()-50);
   }     
}