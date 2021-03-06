import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* The user can open an arrangement, and read the specific properties from the arrangement through this panel
*/
public class EOPanelOpenArrangement extends EOPanel {
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
   JTextField customerfirmtextfield;
   JTextArea customerinfojtextarea;  
   JTextArea descriptionjtextarea;
   JTextField pricetextfield;
   JCheckBox ispayed;
   JCheckBox isdone;
   
   EOGUIMultiSelect facilitatormultiselect;
   //Column3
   EOGUIMultiSelect eventmultiselect;
   
   public EOPanelOpenArrangement(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
   
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
            
      JButton backbutton=new JButton("Tilbage");
      backbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      backbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.START);
                  }
               });
      this.add(backbutton); 
   
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
      JLabel arrangementnamelabel=new JLabel("Navn:");
      arrangementnamelabel.setBounds(650, 40, 120, 20);
      arrangementnamelabel.setFont(this.gui.getFontsmall());
      this.add(arrangementnamelabel);
   
      arrangementtextfield=new JTextField();
      arrangementtextfield.setEditable(false);
      arrangementtextfield.setBounds(650, 60, 300, 20);
      arrangementtextfield.setFont(this.gui.getFontsmall());
      this.add(arrangementtextfield);
   
   
      JLabel pricelabel=new JLabel("Pris:");
      pricelabel.setBounds(650, 80, 120, 20);
      pricelabel.setFont(this.gui.getFontsmall());
      this.add(pricelabel);
   
      pricetextfield=new JTextField();
      pricetextfield.setBounds(650, 100, 300, 20);
      pricetextfield.setFont(this.gui.getFontsmall());
      this.add(pricetextfield);
   
      ispayed = new JCheckBox("Er betalt");
      ispayed.setBounds(650, 120, 300, 20);
      ispayed.setFont(this.gui.getFontsmall());
      this.add(ispayed);
       
      isdone = new JCheckBox("Er afholdt");
      isdone.setBounds(650, 140, 300, 20);
      isdone.setFont(this.gui.getFontsmall());
      this.add(isdone);       
   
   
      JLabel descriptionlabel=new JLabel("Beskrivelse/noter:");
      descriptionlabel.setBounds(650, 160, 100, 20);
      descriptionlabel.setFont(this.gui.getFontsmall());
      this.add(descriptionlabel);
      
      descriptionjtextarea=new JTextArea();
      descriptionjtextarea.setEditable(false);  
      descriptionjtextarea.setLineWrap(true);
      descriptionjtextarea.setWrapStyleWord(true);
      descriptionjtextarea.setBounds(650, 180, 300, 170);
      descriptionjtextarea.setBorder(gui.getDefaultBorder());
      descriptionjtextarea.setFont(this.gui.getFontsmall());
      this.add(descriptionjtextarea); 
             
      JLabel facilitatorlabel=new JLabel("Facilitator(er):");
      facilitatorlabel.setBounds(650, 360, 100, 20);
      facilitatorlabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorlabel);
       
      facilitatormultiselect = new EOGUIMultiSelect(null, new Dimension(300, 160), ListSelectionModel.SINGLE_SELECTION);
      facilitatormultiselect.setBounds(650, 380, 300, 160);
      facilitatormultiselect.addMouseListener(gui, EOOperation.OPENFACILITATOR);
      this.add(facilitatormultiselect);
   
      JLabel facilitatorhelplabel=new JLabel("* H�jre klik for at se info");
      facilitatorhelplabel.setBounds(650, 540, 200, 20);
      facilitatorhelplabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorhelplabel);
     
      //Column 4      
      JLabel eventlabel=new JLabel("Begivenheder:");
      eventlabel.setBounds(970, 40, 200, 20);
      eventlabel.setFont(this.gui.getFontsmall());
      this.add(eventlabel);
      
      eventmultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240), ListSelectionModel.SINGLE_SELECTION);
      eventmultiselect.setBounds(970, 60, 300, 240);
      eventmultiselect.addMouseListener(gui, EOOperation.OPENEVENT);      
      this.add(eventmultiselect);
      
      JLabel eventhelplabel=new JLabel("* H�jre klik for at se info");
      eventhelplabel.setBounds(970, 300, 200, 20);
      eventhelplabel.setFont(this.gui.getFontsmall());
      this.add(eventhelplabel);
      
      JLabel customernamelabel=new JLabel("Kundenavn:");
      customernamelabel.setBounds(970, 330, 120, 20);
      customernamelabel.setFont(this.gui.getFontsmall());
      this.add(customernamelabel);
   
      customertextfield=new JTextField();
      customertextfield.setEditable(false);      
      customertextfield.setBounds(970, 350, 300, 20);
      customertextfield.setFont(this.gui.getFontsmall());
      this.add(customertextfield);
   
      JLabel customeremaillabel=new JLabel("Kundens e-mail:");
      customeremaillabel.setBounds(970, 370, 100, 20);
      customeremaillabel.setFont(this.gui.getFontsmall());
      this.add(customeremaillabel);
   
      customeremailtextfield=new JTextField();
      customeremailtextfield.setEditable(false);         
      customeremailtextfield.setBounds(970, 390, 300, 20);
      customeremailtextfield.setFont(this.gui.getFontsmall());
      this.add(customeremailtextfield);
   
      JLabel customerphonenumberlabel=new JLabel("Kundens tlf.:");
      customerphonenumberlabel.setBounds(970, 410, 100, 20);
      customerphonenumberlabel.setFont(this.gui.getFontsmall());
      this.add(customerphonenumberlabel);
   
      customerphonenumertextfield=new JTextField();
      customerphonenumertextfield.setEditable(false);         
      customerphonenumertextfield.setBounds(970, 430, 300, 20);
      customerphonenumertextfield.setFont(this.gui.getFontsmall());
      this.add(customerphonenumertextfield);
   
      JLabel customerfirmlabel=new JLabel("Kundens Firma:");
      customerfirmlabel.setBounds(970, 450, 100, 20);
      customerfirmlabel.setFont(this.gui.getFontsmall());
      this.add(customerfirmlabel);
   
      customerfirmtextfield=new JTextField();
      customerfirmtextfield.setEditable(false);         
      customerfirmtextfield.setBounds(970, 470, 300, 20);
      customerfirmtextfield.setFont(this.gui.getFontsmall());
      this.add(customerfirmtextfield);
   
      JLabel customerinfolabel=new JLabel("Kunde note:");
      customerinfolabel.setBounds(970, 490, 100, 20);
      customerinfolabel.setFont(this.gui.getFontsmall());
      this.add(customerinfolabel);
      
      customerinfojtextarea=new JTextArea();
      customerinfojtextarea.setEditable(false);
      customerinfojtextarea.setLineWrap(true);
      customerinfojtextarea.setWrapStyleWord(true);
      customerinfojtextarea.setBounds(970, 510, 300, 130);
      customerinfojtextarea.setBorder(gui.getDefaultBorder());
      customerinfojtextarea.setFont(this.gui.getFontsmall());
      this.add(customerinfojtextarea);       
   
   }

   public void setVisible(boolean visible, EOOperation currentEOOperation) {     
      if(currentEOOperation == EOOperation.OPENARRANGEMENT)
      {
               //We expect 
               //0 = EOArrangement
         EOArrangement arrangement = null;
         if(EOOperation.OPENARRANGEMENT.getData() instanceof EOArrangement)
         {
            arrangement = (EOArrangement)EOOperation.OPENARRANGEMENT.getData();
                  //column1
            startdatetime.setDateTime(arrangement.getDateTimeStart());
            enddatetime.setDateTime(arrangement.getDateTimeEnd());
            arrangementtextfield.setText(arrangement.getName());
            pricetextfield.setText(Double.toString(arrangement.getPrice()));
            ispayed.setSelected(arrangement.isPayed());
            isdone.setSelected(arrangement.isDone());
                  //Column2
            if(arrangement.getCustomer() != null)
            {
               customertextfield.setText(arrangement.getCustomer().getName());
               customeremailtextfield.setText(arrangement.getCustomer().getEmail());
               customerphonenumertextfield.setText(arrangement.getCustomer().getPhone());
               customerfirmtextfield.setText(arrangement.getCustomer().getCompany());
               customerinfojtextarea.setText(arrangement.getCustomer().getInfo());
            }
            else
            {
               customertextfield.setText("");
               customeremailtextfield.setText("");
               customerphonenumertextfield.setText("");
               customerfirmtextfield.setText("");
               customerinfojtextarea.setText("");
            }
            descriptionjtextarea.setText(arrangement.getDescription());
            if(arrangement.getFacilitators() != null)
            {
               facilitatormultiselect.setList(arrangement.getFacilitators());
            }
            else
            {
               facilitatormultiselect.setList(null);
            }
            if(arrangement.getEvents() != null)
            {
               eventmultiselect.setList(arrangement.getEvents());
            }
            else
            {
               eventmultiselect.setList(null);
            }
         
         }
      }
   
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