import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOPanelADMFacilitator extends EOPanel {
   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;
   
   public EOPanelADMFacilitator(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);

      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 400, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);

      JButton exportbutton = new JButton("Gem");
      exportbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      exportbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.START);
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

      JLabel facilitatorLabel = new JLabel ("Facilitator:");
      facilitatorLabel.setBounds (50,50,150,50);
      this.add(facilitatorLabel);

      JLabel editCreateFacilitatorLabel = new JLabel("Rediger/Opret Facilitator:");
      editCreateFacilitatorLabel.setBounds(200,50,200,50);
      this.add(editCreateFacilitatorLabel);

      JLabel nameOfFacilitator = new JLabel("Navn:");
      nameOfFacilitator.setBounds(200,75,100,50);
      this.add(nameOfFacilitator);

      JTextField nameText = new JTextField();
      nameText.setBounds(200,100,100,50);
      this.add(nameText);

      JLabel email = new JLabel("Email:");
      email.setBounds(200,200,100,50);
      this.add(email);

      JTextField emailText = new JTextField();
      emailText.setBounds(200,250,100,50);
      this.add(emailText);

      JLabel phoneNumber = new JLabel("Telefonnummer:");
      phoneNumber.setBounds(200,300,100,50);
      this.add(phoneNumber);

      JTextField phoneNumberText = new JTextField();
      phoneNumberText.setBounds (200,450,100,50);
      this.add(phoneNumberText);

      JLabel notes = new JLabel("Noter:");
      notes.setBounds(200,500,150,50);
      this.add(notes);

      JTextField notesText = new JTextField();
      notesText.setBounds(200,550,400,400);
      this.add(notesText);

      JButton editFacilitatorButton = new JButton("Rediger");
      editFacilitatorButton.setBounds (100,550,100,100);
      editFacilitatorButtonbutton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //gui.runCommand(EOOperation.EDITFACILITATOR);
                 }
              });
      this.add(editFacilitatorButton);




     
   }

   public void setVisible(boolean visible, Object data) {
   	// TODO - implement PanelStartMenu.setVisible
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());      
      super.setVisible(visible);
   }

   public void clearData() {
   	// TODO - implement PanelCreateArrangement.clearData
      throw new UnsupportedOperationException();
   }

   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(00,38, this.getWidth(), 38);
   }     
}