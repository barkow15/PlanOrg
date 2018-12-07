import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Used for when a user needs to create an arrangement
*/
public class EOPanelCreateArrangement extends EOPanel {
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

   public EOPanelCreateArrangement(EOGUI gui) {
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
                     //If any events that are associated with the arrangement has been created we delete em
                     gui.runCommand(EOOperation.START);
                  }
               });
      this.add(cancelbutton);
   
   
      JButton createbutton=new JButton("Gem");
      createbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      createbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     updateArrangementObj();
                     EOOperation.SAVECREATEARRANGMENT.setData(getCurrentArrangement());
                     gui.runCommand(EOOperation.SAVECREATEARRANGMENT);
                  }
               });
      this.add(createbutton);
   
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
      JLabel arrangementnamelabel=new JLabel("Arrangement navn:");
      arrangementnamelabel.setBounds(650, 40, 120, 20);
      arrangementnamelabel.setFont(this.gui.getFontsmall());
      this.add(arrangementnamelabel);
   
      arrangementtextfield=new JTextField();
      arrangementtextfield.setBounds(650, 60, 300, 20);
      arrangementtextfield.setFont(this.gui.getFontsmall());
      this.add(arrangementtextfield);
   //Her
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
   //Til       
      JLabel descriptionlabel=new JLabel("Beskrivelse/noter:");
      descriptionlabel.setBounds(650, 160, 100, 20);
      descriptionlabel.setFont(this.gui.getFontsmall());
      this.add(descriptionlabel);
   
      descriptionjtextarea=new JTextArea();
      descriptionjtextarea.setBounds(650, 180, 300, 170);
      descriptionjtextarea.setBorder(gui.getDefaultBorder());
      descriptionjtextarea.setFont(this.gui.getFontsmall());
      this.add(descriptionjtextarea);
   
      JLabel facilitatorlabel=new JLabel("Facilitator(er):");
      facilitatorlabel.setBounds(650, 360, 100, 20);
      facilitatorlabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorlabel);
   
      facilitatormultiselect = new EOGUIMultiSelect(null, new Dimension(300, 160));
      facilitatormultiselect.addMouseListener(gui, EOOperation.OPENFACILITATOR);
      facilitatormultiselect.setBounds(650, 380, 300, 160);
      this.add(facilitatormultiselect);
   
       //Column 4
      JLabel eventtypelabel=new JLabel("Begivenheder:");
      eventtypelabel.setBounds(970, 40, 200, 20);
      eventtypelabel.setFont(this.gui.getFontsmall());
      this.add(eventtypelabel);
   
      eventmultiselect = new EOGUIMultiSelect(null, new Dimension(300, 240), ListSelectionModel.SINGLE_SELECTION);
      eventmultiselect.addMouseListener(gui, EOOperation.OPENEVENT);       
      eventmultiselect.setBounds(970, 60, 300, 240);
      this.add(eventmultiselect);
   
      JButton deletearrangementbutton=new JButton("Slet");
      deletearrangementbutton.setBounds(970, 300, 85, 20);
      deletearrangementbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     if(eventmultiselect.getSelected() != null && eventmultiselect.getSelected().length > 0)
                     {
                        updateArrangementObj();
                        getCurrentArrangement().deleteEvent((EOEvent)eventmultiselect.getSelected()[0]);
                        //Refresh page
                        gui.getBreadcrumb().pop();
                        gui.runCommand(EOOperation.CREATEARRANGEMENT);
                     }
                     else
                     {
                        gui.dialogbox("Du skal v�lge en begivenhed for at kunne slette den.");
                     }
                  }
               });
      this.add(deletearrangementbutton);
       
      JButton updatearrangementbutton=new JButton("Rediger");
      updatearrangementbutton.setBounds(1055, 300, 115, 20);
      updatearrangementbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     if(eventmultiselect.getSelected() != null && eventmultiselect.getSelected().length > 0)
                     {
                        updateArrangementObj();
                        EOOperation.UPDATEEVENT.setData((EOEvent)eventmultiselect.getSelected()[0]);
                        gui.runCommand(EOOperation.UPDATEEVENT);
                     }
                     else
                     {
                        gui.dialogbox("Du skal v�lge en begivenhed for at kunne opdatere den.");
                     }
                  }
               });
      this.add(updatearrangementbutton);
   
      JButton createarrangementbutton=new JButton("Opret");
      System.out.println(this.gui.getWidth()-160);
      createarrangementbutton.setBounds(1170, 300, 100, 20);
      createarrangementbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                       //EOOperation.CREATEEVENT.setData();
                     updateArrangementObj();
                     EOOperation.CREATEEVENT.setData(getCurrentArrangement());
                     gui.runCommand(EOOperation.CREATEEVENT);
                  }
               });
      this.add(createarrangementbutton);              
   
      JLabel customernamelabel=new JLabel("Kundenavn:");
      customernamelabel.setBounds(970, 330, 120, 20);
      customernamelabel.setFont(this.gui.getFontsmall());
      this.add(customernamelabel);
   
      customertextfield=new JTextField();
      customertextfield.setBounds(970, 350, 300, 20);
      customertextfield.setFont(this.gui.getFontsmall());
      this.add(customertextfield);
   
      JLabel customeremaillabel=new JLabel("Kundens e-mail:");
      customeremaillabel.setBounds(970, 370, 100, 20);
      customeremaillabel.setFont(this.gui.getFontsmall());
      this.add(customeremaillabel);
   
      customeremailtextfield=new JTextField();
      customeremailtextfield.setBounds(970, 390, 300, 20);
      customeremailtextfield.setFont(this.gui.getFontsmall());
      this.add(customeremailtextfield);
   
      JLabel customerphonenumberlabel=new JLabel("Kundens tlf.:");
      customerphonenumberlabel.setBounds(970, 410, 100, 20);
      customerphonenumberlabel.setFont(this.gui.getFontsmall());
      this.add(customerphonenumberlabel);
   
      customerphonenumertextfield=new JTextField();
      customerphonenumertextfield.setBounds(970, 430, 300, 20);
      customerphonenumertextfield.setFont(this.gui.getFontsmall());
      this.add(customerphonenumertextfield);
   
      JLabel customerfirmlabel=new JLabel("Kundens Firma:");
      customerfirmlabel.setBounds(970, 450, 100, 20);
      customerfirmlabel.setFont(this.gui.getFontsmall());
      this.add(customerfirmlabel);
   
      customerfirmtextfield=new JTextField();     
      customerfirmtextfield.setBounds(970, 470, 300, 20);
      customerfirmtextfield.setFont(this.gui.getFontsmall());
      this.add(customerfirmtextfield);
   
      JLabel customerinfolabel=new JLabel("Kunde note:");
      customerinfolabel.setBounds(970, 490, 100, 20);
      customerinfolabel.setFont(this.gui.getFontsmall());
      this.add(customerinfolabel);
      
      customerinfojtextarea=new JTextArea();
      customerinfojtextarea.setBounds(970, 510, 300, 130);
      customerinfojtextarea.setBorder(gui.getDefaultBorder());
      customerinfojtextarea.setFont(this.gui.getFontsmall());
      this.add(customerinfojtextarea);     
   }
	/**
	 * 
	 * @param visible
	 * @param data
	 */
   public void setVisible(boolean visible, EOOperation currentEOOperation) {
      if(EOOperation.CREATEARRANGEMENT.getData().getClass().isArray())
      {
         Object[] obj = (Object[])EOOperation.CREATEARRANGEMENT.getData();
         EOArrangement arrangement = null;
         if(obj.length == 2)
         {
            if(obj[1] instanceof EOArrangement && obj[1] != null)
            {
               arrangement = (EOArrangement) obj[1];
               eventmultiselect.setList(arrangement.getEvents());
            
               arrangementtextfield.setText(arrangement.getName());
               descriptionjtextarea.setText(arrangement.getDescription());
               startdatetime.setDateTime(arrangement.getDateTimeStart());
               enddatetime.setDateTime(arrangement.getDateTimeEnd());
               pricetextfield.setText(Double.toString(arrangement.getPrice()));
               ispayed.setSelected(arrangement.isPayed());
               isdone.setSelected(arrangement.isDone());
               //Column2
               if(arrangement.getCustomer() != null)
               {
                  customertextfield.setText(arrangement.getCustomer().getName());
                  customeremailtextfield.setText(arrangement.getCustomer().getEmail());
                  customerphonenumertextfield.setText(arrangement.getCustomer().getEmail());
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
            }  
            if(obj[0] instanceof FacilitatorContactInfo[] && obj[0] != null)
            {
               if(arrangement != null)
               {
                  facilitatormultiselect.setList((FacilitatorContactInfo[])obj[0], arrangement.getFacilitators());
               }
               else
               {
                  facilitatormultiselect.setList((FacilitatorContactInfo[])obj[0]);
               }
            }
         }
       
      }
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());
      super.setVisible(visible, currentEOOperation);
   }

   private EOArrangement getCurrentArrangement()
   {
      if(EOOperation.CREATEARRANGEMENT.getData().getClass().isArray())
      {
         Object[] obj = (Object[])EOOperation.CREATEARRANGEMENT.getData();
         if(obj.length == 2)
         {
            if(obj[1] instanceof EOArrangement && obj[1] != null)
            {
               return((EOArrangement)obj[1]);
            }
         }
      }
      return(null);
   }

   /**
   * If we leave the panel, we save the state of the arrangement object. The changes are not saved in the database before the user selects to save the data
   */
   private void updateArrangementObj()
   {
      if(EOOperation.CREATEARRANGEMENT.getData().getClass().isArray())
      {
         Object[] obj = (Object[])EOOperation.CREATEARRANGEMENT.getData();
         if(obj.length == 2)
         {
            if(obj[1] instanceof EOArrangement && obj[1] != null)
            {
               EOArrangement arrangement = (EOArrangement) obj[1];
               if(eventmultiselect.getList() != null && eventmultiselect.getList().length > 0)
               {
                     //This could be optimized by doing a cast
                  EOEvent[] e = new EOEvent[eventmultiselect.getList().length];
                  for(int i = 0; i < eventmultiselect.getList().length; i++)
                  {
                     e[i] = (EOEvent)(eventmultiselect.getList()[i]);
                  }
                  arrangement.setEvents(e);
               }
               else
               {
                  arrangement.setEvents(null);
               }
               if(facilitatormultiselect.getSelected() != null && facilitatormultiselect.getSelected().length > 0)
               {
               
                     //This could be optimized by doing a cast
                  FacilitatorContactInfo[] f = new FacilitatorContactInfo[facilitatormultiselect.getSelected().length];
                  for(int i = 0; i < facilitatormultiselect.getSelected().length; i++)
                  {
                     f[i] = (FacilitatorContactInfo)(facilitatormultiselect.getSelected()[i]);
                  }
                  arrangement.setFacilitators(f);
               
               }
               else
               {
                  arrangement.setFacilitators(null);
               }
               arrangement.setName(arrangementtextfield.getText());
               arrangement.setDescription(descriptionjtextarea.getText());
               arrangement.isDone(isdone.isSelected());
               arrangement.isPayed(ispayed.isSelected());
               try
               {
                  pricetextfield.setText(Double.toString(arrangement.getPrice()));
               }catch(Exception sdt){}
               try
               {
                  arrangement.setDateTimeStart(startdatetime.getDateTime());
               }catch(Exception sdt){}
               try
               {
                  arrangement.setDateTimeEnd(enddatetime.getDateTime()); 
               }catch(Exception edt){}
               if(arrangement.getCustomer() != null)
               {
                  arrangement.getCustomer().setName(customertextfield.getText());
                  arrangement.getCustomer().setEmail(customeremailtextfield.getText());
                  arrangement.getCustomer().setPhone(customerphonenumertextfield.getText());
                  arrangement.getCustomer().setCompany(customerfirmtextfield.getText());
                  arrangement.getCustomer().setInfo(customerinfojtextarea.getText());
                                                 
               }
               else
               {
                  arrangement.setCustomer(
                     new CustomerContactInfo(
                        -1,
                        customertextfield.getText(),
                        customerphonenumertextfield.getText(),
                        customeremailtextfield.getText(),
                        customerinfojtextarea.getText(),
                        customerfirmtextfield.getText()
                     )
                     );
               }        
            }
         }
      }       
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