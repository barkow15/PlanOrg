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


public class EOPanelADMFacilitator extends EOPanel {
   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;
   JTextField nameText;
   JTextField emailText;
   JTextField phoneNumberText;
   JTextField notesText;
   EOGUIMultiSelect admFacilitatorMultiselect;
   
   public EOPanelADMFacilitator(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
   
      
   
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
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
   
      notesText = new JTextField();
      notesText.setBounds(450,350,300,150);
      this.add(notesText);
   
      //MultiSelect
      admFacilitatorMultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240), ListSelectionModel.SINGLE_SELECTION);
      admFacilitatorMultiselect.setBounds(50, 100, 300, 240);
   
      this.add(admFacilitatorMultiselect);
   
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

      JButton createFacilitatorButton = new JButton("Opret Facilitator");
      createFacilitatorButton.setBounds (450,550,150,50);
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

      JTextArea admFacilitatorTextArea = new JTextArea(
              "Tryk paa en Facalitator fra listen for at redigere eller slette oprettede facilitatorer.\n"+
                      "Ellers kan kan man oprette en ny facilitater ved at udfylde de valgte felter.");
      admFacilitatorTextArea.setFont(this.gui.getFontsmall());
      admFacilitatorTextArea.setLineWrap(true);
      admFacilitatorTextArea.setWrapStyleWord(true);
      admFacilitatorTextArea.setBounds(910, 60, 300, 300);
      admFacilitatorTextArea.setBackground(new Color(0, 0, 0, 0));
      this.add(admFacilitatorTextArea);

   }

   public void setVisible(boolean visible, Object data) {
   	// TODO - implement PanelStartMenu.setVisible
      if (data instanceof EOOperation)
      {
         EOOperation facilitatorEnum=(EOOperation)data;
         if (EOOperation.UPDATEFACILITATOR == facilitatorEnum)
         {

            if (EOOperation.UPDATEFACILITATOR.getData()==null)
            {

            }
            if (EOOperation.UPDATEFACILITATOR.getData() instanceof FacilitatorContactInfo)
            {
               FacilitatorContactInfo facilitator = (FacilitatorContactInfo) EOOperation.UPDATEFACILITATOR.getData();
               nameText.setText(facilitator.getName());

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
      g.drawLine(433,45, 433, this.getHeight()-50);
      g.drawLine(866,45, 866, this.getHeight()-50);
   }     
}