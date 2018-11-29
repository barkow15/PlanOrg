import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOPanelADMEventType extends EOPanel {
   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;   
   EOGUIMultiSelect eventtypemultiselect;

   public EOPanelADMEventType(EOGUI gui) {
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
     
     //Column1//
      JLabel facilitatorslabel = new JLabel("Begivenhedstyper:");
      facilitatorslabel.setBounds(10, 40, 300, 20);
      facilitatorslabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorslabel);       
                        
      eventtypemultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240), ListSelectionModel.SINGLE_SELECTION);
      eventtypemultiselect.setBounds(10, 60, 300, 240);
      eventtypemultiselect.setBorder(gui.getDefaultBorder());
      this.add(eventtypemultiselect);
      
      JButton deleteeventtypebutton=new JButton("Slet");
      deleteeventtypebutton.setBounds(10, 310, 100, 30);
      deleteeventtypebutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.START);
                  }
               });
      this.add(deleteeventtypebutton);

      JButton editeventtypebutton=new JButton("Rediger");
      editeventtypebutton.setBounds(210, 310, 100, 30);
      editeventtypebutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.START);
                  }
               });
      this.add(editeventtypebutton);   
                 
     //Column2//
      JLabel namelabel = new JLabel("Navn:");
      namelabel.setBounds(330, 40, 300, 20);
      namelabel.setFont(this.gui.getFontsmall());
      this.add(namelabel);
      
      JTextField namejtextfield = new JTextField();
      namejtextfield.setBounds(330, 60, 300, 20);
      namejtextfield.setFont(this.gui.getFontsmall());
      this.add(namejtextfield);  

      JLabel startadrlabel = new JLabel("Start adresse:");
      startadrlabel.setBounds(330, 80, 300, 20);
      startadrlabel.setFont(this.gui.getFontsmall());
      this.add(startadrlabel);
      
      JTextField startadrjtextfield = new JTextField();
      startadrjtextfield.setBounds(330, 100, 300, 20);
      startadrjtextfield.setFont(this.gui.getFontsmall());
      this.add(startadrjtextfield);

      JLabel endadrlabel = new JLabel("Slut adresse:");
      endadrlabel.setBounds(330, 120, 300, 20);
      endadrlabel.setFont(this.gui.getFontsmall());
      this.add(endadrlabel);
      
      JTextField endadrjtextfield = new JTextField();
      endadrjtextfield.setBounds(330, 140, 300, 20);
      endadrjtextfield.setFont(this.gui.getFontsmall());
      this.add(endadrjtextfield);  

      JLabel timelabel = new JLabel("Varidhed i timer:");
      timelabel.setBounds(330, 160, 300, 20);
      timelabel.setFont(this.gui.getFontsmall());
      this.add(timelabel);
      
      JTextField timejtextfield = new JTextField();
      timejtextfield.setBounds(330, 180, 300, 20);
      timejtextfield.setFont(this.gui.getFontsmall());
      this.add(timejtextfield);

      JLabel pricelabel = new JLabel("Pris:");
      pricelabel.setBounds(330, 200, 300, 20);
      pricelabel.setFont(this.gui.getFontsmall());
      this.add(pricelabel);
      
      JTextField pricejtextfield = new JTextField();
      pricejtextfield.setBounds(330, 220, 300, 20);
      pricejtextfield.setFont(this.gui.getFontsmall());
      this.add(pricejtextfield);

      JLabel notelabel = new JLabel("Beskrivelse/noter:");
      notelabel.setBounds(330, 240, 300, 20);
      notelabel.setFont(this.gui.getFontsmall());
      this.add(notelabel);      


      JTextArea notejtextarea = new JTextArea();
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

      JTextField externalcontactnamejtextfield = new JTextField();
      externalcontactnamejtextfield.setBounds(650, 100, 300, 20);
      externalcontactnamejtextfield.setFont(this.gui.getFontsmall());
      this.add(externalcontactnamejtextfield);

      JLabel externalcontactphonelabel = new JLabel("Telefon");
      externalcontactphonelabel.setBounds(650, 120, 300, 20);
      externalcontactphonelabel.setFont(this.gui.getFontsmall());
      this.add(externalcontactphonelabel);

      JTextField externalcontactphonejtextfield = new JTextField();
      externalcontactphonejtextfield.setBounds(650, 140, 300, 20);
      externalcontactphonejtextfield.setFont(this.gui.getFontsmall());
      this.add(externalcontactphonejtextfield);

      JLabel externalcontactemaillabel = new JLabel("Email");
      externalcontactemaillabel.setBounds(650, 160, 300, 20);
      externalcontactemaillabel.setFont(this.gui.getFontsmall());
      this.add(externalcontactemaillabel);

      JTextField externalcontactemailjtextfield = new JTextField();
      externalcontactemailjtextfield.setBounds(650, 180, 300, 20);
      externalcontactemailjtextfield.setFont(this.gui.getFontsmall());
      this.add(externalcontactemailjtextfield);
      
      JLabel externalcontactenotelabel = new JLabel("Beskrivelse/noter");
      externalcontactenotelabel.setBounds(650, 200, 300, 20);
      externalcontactenotelabel.setFont(this.gui.getFontsmall());
      this.add(externalcontactenotelabel);


      JTextArea externalcontactenotejtextarea = new JTextArea();
      externalcontactenotejtextarea.setBounds(650, 220, 300, 200);
      externalcontactenotejtextarea.setFont(this.gui.getFontsmall());
      externalcontactenotejtextarea.setBorder(gui.getDefaultBorder());
      this.add(externalcontactenotejtextarea);   

      JLabel externalcontactcompanylabel = new JLabel("Firma");
      externalcontactcompanylabel.setBounds(650, 420, 300, 20);
      externalcontactcompanylabel.setFont(this.gui.getFontsmall());
      this.add(externalcontactcompanylabel);  
      
      JTextField externalcontactcompanyjtextfield = new JTextField();
      externalcontactcompanyjtextfield.setBounds(650, 440, 300, 20);
      externalcontactcompanyjtextfield.setFont(this.gui.getFontsmall());
      this.add(externalcontactcompanyjtextfield);                         
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
      g.drawLine(320,45, 320, this.getHeight()-50); 
      g.drawLine(640,45, 640, this.getHeight()-50);
      g.drawLine(960,45, 960, this.getHeight()-50);          
   }      
}