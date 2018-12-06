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

/**
* Administrates the Facilitators, Create, Delete and UPDATE.
*/
public class EOPanelADMFacilitator extends EOPanel {
   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;
   JTextField nameText;
   JTextField emailText;
   JTextField phoneNumberText;
   JTextArea notesText;
   JOptionPane besked;
   EOGUIMultiSelect admFacilitatorMultiselect;
   
   public EOPanelADMFacilitator(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
   
      
   
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
       
      JButton cancelbutton=new JButton("Tilbage");
      cancelbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
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
      editCreateFacilitatorLabel.setBounds(450,50,200,50);
      this.add(editCreateFacilitatorLabel);
   
      JLabel nameOfFacilitator = new JLabel("Navn:");
      nameOfFacilitator.setBounds(450,75,100,50);
      this.add(nameOfFacilitator);
   
      nameText = new JTextField();
      nameText.setBounds(450,125,150,20);
      this.add(nameText);
   
      JLabel email = new JLabel("Email:");
      email.setBounds(450,150,100,50);
      this.add(email);
   
      emailText = new JTextField();
      emailText.setBounds(450,200,150,20);
      this.add(emailText);
   
      JLabel phoneNumber = new JLabel("Telefonnummer:");
      phoneNumber.setBounds(450,225,100,50);
      this.add(phoneNumber);
   
      phoneNumberText = new JTextField();
      phoneNumberText.setBounds (450,275,150,20);
      this.add(phoneNumberText);
   
      JLabel notes = new JLabel("Noter:");
      notes.setBounds(450,300,150,50);
      this.add(notes);
   
   
      notesText = new JTextArea();
      notesText.setBounds(450,350,300,150);
      this.add(notesText);
   
      //MultiSelect
      admFacilitatorMultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240), ListSelectionModel.SINGLE_SELECTION);
      admFacilitatorMultiselect.setBounds(50, 100, 300, 240);
   
      this.add(admFacilitatorMultiselect);
   
      //Edit Facilitator
      JButton editFacilitatorButton = new JButton("Rediger Facilitator");
      editFacilitatorButton.setBounds (50,400,150,50);
      editFacilitatorButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    Object[] facilitatorArray = admFacilitatorMultiselect.getSelected();
                    EOOperation.UPDATEFACILITATOR.setData(facilitatorArray[0]);
                 
                    gui.runCommand(EOOperation.UPDATEFACILITATOR);
                 }
              });
      this.add(editFacilitatorButton);
   
      //Create Facilitator
      JButton createFacilitatorButton = new JButton("Opret Ny Facilitator");
      createFacilitatorButton.setBounds (650,550,150,50);
      createFacilitatorButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    FacilitatorContactInfo facilitatorcontactinfo = new FacilitatorContactInfo
                            (
                            -1,
                            nameText.getText(),
                            phoneNumberText.getText(),
                            emailText.getText(),
                            notesText.getText()
                            );
                    EOOperation.CREATEFACILITATOR.setData(facilitatorcontactinfo);
                    gui.runCommand(EOOperation.CREATEFACILITATOR);
                 }
              });
      this.add(createFacilitatorButton);
   
      //Delete Button
      JButton deleteFacilitatorButton = new JButton("Slet");
      deleteFacilitatorButton.setBounds(200,400,150,50);
      deleteFacilitatorButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    Object[] facilitatorArray = admFacilitatorMultiselect.getSelected();
                    if (facilitatorArray!=null) {
                       EOOperation.DELETEFACILITATOR.setData(facilitatorArray[0]);
                       gui.runCommand(EOOperation.DELETEFACILITATOR);
                    }
                 }
              });
      this.add(deleteFacilitatorButton);
   
      //ADM Facilitator text
      JTextArea admFacilitatorTextArea = new JTextArea(
              "Tryk paa en Facalitator fra listen for at redigere eller slette oprettede facilitatorer.\n\n"+
                      "Tryk paa Gem Redigering, naar du er slut med at redigereen facilitator for at gemme updateringen.\n\n"+
                      "Ellers kan kan man oprette en ny facilitater ved at udfylde felterne, og trykke paa Opret Ny Facilitator.");
      admFacilitatorTextArea.setFont(this.gui.getFontsmall());
      admFacilitatorTextArea.setLineWrap(true);
      admFacilitatorTextArea.setWrapStyleWord(true);
      admFacilitatorTextArea.setBounds(910, 60, 300, 300);
      admFacilitatorTextArea.setBackground(new Color(0, 0, 0, 0));
      this.add(admFacilitatorTextArea);
   
      //Gem redigering af Facilitator
      JButton saveEditFacilitator = new JButton("Gem Redigering");
      saveEditFacilitator.setBounds(450,550,150,50);
      saveEditFacilitator.addActionListener(
              new ActionListener() {
                 public void actionPerformed(ActionEvent e)
                 {
                    int selectedID=((FacilitatorContactInfo)EOOperation.UPDATEFACILITATOR.getData()).getId();
                    FacilitatorContactInfo updatefacilitatorcontactinfo = new FacilitatorContactInfo
                            (
                                    selectedID,
                                    nameText.getText(),
                                    phoneNumberText.getText(),
                                    emailText.getText(),
                                    notesText.getText()
                            );
                    EOOperation.SAVEEDITFACILITATOR.setData(updatefacilitatorcontactinfo);
                    gui.runCommand(EOOperation.SAVEEDITFACILITATOR);
                 }
              });
      this.add(saveEditFacilitator);
   
   }

   public void setVisible(boolean visible, EOOperation currentEOOperation) {
   	// TODO - implement PanelStartMenu.setVisible
      if (currentEOOperation instanceof EOOperation)
      {
         EOOperation facilitatorEnum=currentEOOperation;
         //Clear input Fields.
         if (EOOperation.ADMFACILITATOR == facilitatorEnum){
            nameText.setText("");
            phoneNumberText.setText("");
            emailText.setText("");
            notesText.setText("");
         }
      
         if (EOOperation.UPDATEFACILITATOR == facilitatorEnum)
         {
         
            if (EOOperation.UPDATEFACILITATOR.getData()==null)
            {
            
            }
            if (EOOperation.UPDATEFACILITATOR.getData() instanceof FacilitatorContactInfo)
            {
               FacilitatorContactInfo facilitator = (FacilitatorContactInfo) EOOperation.UPDATEFACILITATOR.getData();
               nameText.setText(facilitator.getName());
               phoneNumberText.setText(facilitator.getPhone());
               emailText.setText(facilitator.getEmail());
               notesText.setText(facilitator.getInfo());
            
            
            }
         
         }
      
      
         System.out.println("1");
         if(facilitatorEnum == EOOperation.ADMFACILITATOR)
         {
            System.out.println("2"+EOOperation.ADMFACILITATOR.getData());
            if(EOOperation.ADMFACILITATOR.getData() instanceof FacilitatorContactInfo[])
            {
               System.out.println("3");
               admFacilitatorMultiselect.setList((FacilitatorContactInfo[])EOOperation.ADMFACILITATOR.getData());
            }
         }
         
      }
      EOOperation facilitatorEnum=currentEOOperation;
      if (EOOperation.SAVEEDITFACILITATOR == facilitatorEnum){
         nameText.setText("");
         phoneNumberText.setText("");
         emailText.setText("");
         notesText.setText("");
         admFacilitatorMultiselect.setList((EOGUIMultiSelectInterface[])EOOperation.SAVEEDITFACILITATOR.getData());
         gui.dialogbox("Update Gemt!");
      }
      if (EOOperation.CREATEFACILITATOR == facilitatorEnum) {
         nameText.setText("");
         phoneNumberText.setText("");
         emailText.setText("");
         notesText.setText("");
         admFacilitatorMultiselect.setList((EOGUIMultiSelectInterface[])EOOperation.CREATEFACILITATOR.getData());
         gui.dialogbox("Facilitator Oprettet!");
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