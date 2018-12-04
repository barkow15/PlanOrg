import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOPanelOpenEventType extends EOPanel {
   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;   
   EOGUIMultiSelect eventtypemultiselect;
   
   JTextField namejtextfield = null;
   JTextField startadrjtextfield = null;
   JTextField endadrjtextfield = null;
   JTextField timejtextfield = null;
   JTextField pricejtextfield = null;
   JTextArea notejtextarea = null;
   JTextField externalcontactnamejtextfield = null;
   JTextField externalcontactphonejtextfield = null;
   JTextField externalcontactemailjtextfield = null;
   JTextArea externalcontactenotejtextarea = null;
   JTextField externalcontactcompanyjtextfield = null;

   
   public EOPanelOpenEventType(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);

      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);

      JButton exportbutton = new JButton("Tilbage");
      exportbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      exportbutton.addActionListener(
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
      this.add(exportbutton); 
                 
     //Column2//
      JLabel namelabel = new JLabel("Navn:");
      namelabel.setBounds(330, 40, 300, 20);
      namelabel.setFont(this.gui.getFontsmall());
      this.add(namelabel);
      
      namejtextfield = new JTextField();
      namejtextfield.setBounds(330, 60, 300, 20);
      namejtextfield.setFont(this.gui.getFontsmall());
      this.add(namejtextfield);  

      JLabel startadrlabel = new JLabel("Start adresse:");
      startadrlabel.setBounds(330, 80, 300, 20);
      startadrlabel.setFont(this.gui.getFontsmall());
      this.add(startadrlabel);
      
      startadrjtextfield = new JTextField();
      startadrjtextfield.setBounds(330, 100, 300, 20);
      startadrjtextfield.setFont(this.gui.getFontsmall());
      this.add(startadrjtextfield);

      JLabel endadrlabel = new JLabel("Slut adresse:");
      endadrlabel.setBounds(330, 120, 300, 20);
      endadrlabel.setFont(this.gui.getFontsmall());
      this.add(endadrlabel);
      
      endadrjtextfield = new JTextField();
      endadrjtextfield.setBounds(330, 140, 300, 20);
      endadrjtextfield.setFont(this.gui.getFontsmall());
      this.add(endadrjtextfield);  

      JLabel timelabel = new JLabel("Varidhed i timer:");
      timelabel.setBounds(330, 160, 300, 20);
      timelabel.setFont(this.gui.getFontsmall());
      this.add(timelabel);
      
      timejtextfield = new JTextField();
      timejtextfield.setBounds(330, 180, 300, 20);
      timejtextfield.setFont(this.gui.getFontsmall());
      this.add(timejtextfield);

      JLabel pricelabel = new JLabel("Pris:");
      pricelabel.setBounds(330, 200, 300, 20);
      pricelabel.setFont(this.gui.getFontsmall());
      this.add(pricelabel);
      
      pricejtextfield = new JTextField();
      pricejtextfield.setBounds(330, 220, 300, 20);
      pricejtextfield.setFont(this.gui.getFontsmall());
      this.add(pricejtextfield);

      JLabel notelabel = new JLabel("Beskrivelse/noter:");
      notelabel.setBounds(330, 240, 300, 20);
      notelabel.setFont(this.gui.getFontsmall());
      this.add(notelabel);      


      notejtextarea = new JTextArea();
      notejtextarea.setBounds(330, 260, 300, 200);
      notejtextarea.setBorder(gui.getDefaultBorder());           
      notejtextarea.setFont(this.gui.getFontsmall());
      this.add(notejtextarea);   
                         
     //Column3//
      JLabel externalcontactlabel = new JLabel("Ekstern kontakt");
      externalcontactlabel.setBounds(650, 50, 300, 20);
      externalcontactlabel.setFont(this.gui.getFontmedium());
      this.add(externalcontactlabel);
      
      JLabel externalcontactnamelabel = new JLabel("navn");
      externalcontactnamelabel.setBounds(650, 80, 300, 20);
      externalcontactnamelabel.setFont(this.gui.getFontsmall());
      this.add(externalcontactnamelabel);

      externalcontactnamejtextfield = new JTextField();
      externalcontactnamejtextfield.setBounds(650, 100, 300, 20);
      externalcontactnamejtextfield.setFont(this.gui.getFontsmall());
      this.add(externalcontactnamejtextfield);

      JLabel externalcontactphonelabel = new JLabel("Telefon");
      externalcontactphonelabel.setBounds(650, 120, 300, 20);
      externalcontactphonelabel.setFont(this.gui.getFontsmall());
      this.add(externalcontactphonelabel);

      externalcontactphonejtextfield = new JTextField();
      externalcontactphonejtextfield.setBounds(650, 140, 300, 20);
      externalcontactphonejtextfield.setFont(this.gui.getFontsmall());
      this.add(externalcontactphonejtextfield);

      JLabel externalcontactemaillabel = new JLabel("Email");
      externalcontactemaillabel.setBounds(650, 160, 300, 20);
      externalcontactemaillabel.setFont(this.gui.getFontsmall());
      this.add(externalcontactemaillabel);

      externalcontactemailjtextfield = new JTextField();
      externalcontactemailjtextfield.setBounds(650, 180, 300, 20);
      externalcontactemailjtextfield.setFont(this.gui.getFontsmall());
      this.add(externalcontactemailjtextfield);
      
      JLabel externalcontactenotelabel = new JLabel("Beskrivelse/noter");
      externalcontactenotelabel.setBounds(650, 200, 300, 20);
      externalcontactenotelabel.setFont(this.gui.getFontsmall());
      this.add(externalcontactenotelabel);


      externalcontactenotejtextarea = new JTextArea();
      externalcontactenotejtextarea.setBounds(650, 220, 300, 200);
      externalcontactenotejtextarea.setFont(this.gui.getFontsmall());
      externalcontactenotejtextarea.setBorder(gui.getDefaultBorder());
      this.add(externalcontactenotejtextarea);   

      JLabel externalcontactcompanylabel = new JLabel("Firma");
      externalcontactcompanylabel.setBounds(650, 420, 300, 20);
      externalcontactcompanylabel.setFont(this.gui.getFontsmall());
      this.add(externalcontactcompanylabel);  
      
      externalcontactcompanyjtextfield = new JTextField();
      externalcontactcompanyjtextfield.setBounds(650, 440, 300, 20);
      externalcontactcompanyjtextfield.setFont(this.gui.getFontsmall());
      this.add(externalcontactcompanyjtextfield);                                
   }

   public void setVisible(boolean visible, EOOperation currentEOOperation) {
      //Shows the facilitator list
   	if(currentEOOperation == EOOperation.OPENEVENTTYPE)
      {
         if(currentEOOperation == EOOperation.OPENEVENTTYPE)
         {
            
            if(currentEOOperation.getData() instanceof EOEventType)
            {
               EOEventType eoeventtype = (EOEventType)currentEOOperation.getData(); 
               namejtextfield.setText(eoeventtype.getName()); 
               notejtextarea.setText(eoeventtype.getDescription()); 
               startadrjtextfield.setText(eoeventtype.getLocationStart()); 
               endadrjtextfield.setText(eoeventtype.getLocationEnd()); 
               timejtextfield.setText(Integer.toString(eoeventtype.getTime())); 
               pricejtextfield.setText(Double.toString(eoeventtype.getPrice())); 
               if(eoeventtype.getExternalContactInfo() != null)
               {  
                  ExternalContactInfo external = eoeventtype.getExternalContactInfo();
                  externalcontactnamejtextfield.setText(external.getName()); 
                  externalcontactphonejtextfield.setText(external.getPhone()); 
                  externalcontactemailjtextfield.setText(external.getEmail()); 
                  externalcontactenotejtextarea.setText(external.getInfo()); 
                  externalcontactcompanyjtextfield.setText(external.getCompany());
               }
            }
         }
      }
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());      
      super.setVisible(visible, currentEOOperation);
   }

   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(00,38, this.getWidth(), 38);
      g.drawLine(320,45, 320, this.getHeight()-50); 
      g.drawLine(640,45, 640, this.getHeight()-250);
      g.drawLine(960,45, 960, this.getHeight()-50);          
   }      
}