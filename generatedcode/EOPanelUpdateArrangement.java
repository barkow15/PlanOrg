import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Gives the option for updating an arrangement
*/
public class EOPanelUpdateArrangement extends EOPanel {
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
   EOGUIMultiSelect facilitatormultiselect;
   //Column3
   EOGUIMultiSelect eventmultiselect;
   JTextField pricetextfield;
   JCheckBox ispayed;
   JCheckBox isdone;
   
   public EOPanelUpdateArrangement(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
   
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
      
      JButton savebutton=new JButton("Opdater");
      System.out.println(this.gui.getWidth()-110);
      savebutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      savebutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.START);
                  }
               });
      this.add(savebutton);
   
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
   
   
      JButton createbutton=new JButton("Opret");
      createbutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      createbutton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    gui.runCommand(EOOperation.START);
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
      descriptionjtextarea.setBounds(650, 180, 300, 170);
      descriptionjtextarea.setBorder(gui.getDefaultBorder());
      descriptionjtextarea.setFont(this.gui.getFontsmall());
      this.add(descriptionjtextarea);
   
      JLabel facilitatorlabel=new JLabel("Facilitator(er):");
      facilitatorlabel.setBounds(650, 360, 100, 20);
      facilitatorlabel.setFont(this.gui.getFontsmall());
      this.add(facilitatorlabel);
   
      facilitatormultiselect = new EOGUIMultiSelect(null, new Dimension(300, 160), ListSelectionModel.SINGLE_SELECTION);
      facilitatormultiselect.addMouseListener(gui, EOOperation.OPENFACILITATOR);
      facilitatormultiselect.setBounds(650, 380, 300, 160);
      this.add(facilitatormultiselect);
   
      //Column 4
      JLabel eventlabel=new JLabel("Begivenheder:");
      eventlabel.setBounds(970, 40, 200, 20);
      eventlabel.setFont(this.gui.getFontsmall());
      this.add(eventlabel);
   
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
                        gui.runCommand(EOOperation.UPDATEARRANGEMENT);
                     }
                     else
                     {
                        gui.dialogbox("Du skal vælge en begivenhed for at kunne slette den.");
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
                        gui.dialogbox("Du skal vælge en begivenhed for at kunne opdatere den.");
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
      if(currentEOOperation == EOOperation.UPDATEARRANGEMENT)
      {
         if(currentEOOperation.getData().getClass().isArray())
         {
            Object[] edata = (Object[])currentEOOperation.getData();
            if(edata.length == 2)
            {
               //We expect 
               //1 = EOArrangement
               EOArrangement arrangement = null;
               if(edata[1] instanceof EOArrangement)
               {
                  arrangement = (EOArrangement)edata[1];
                  eventmultiselect.setList(arrangement.getEvents());
                  startdatetime.setDateTime(arrangement.getDateTimeStart());
                  enddatetime.setDateTime(arrangement.getDateTimeEnd());
                  arrangementtextfield.setText(arrangement.getName());
                  pricetextfield.setText(Double.toString(arrangement.getPrice()));
                  ispayed.setSelected(arrangement.isPayed());
                  isdone.setSelected(arrangement.isDone());
                  if(arrangement.getCustomer() != null)
                  {
                     customertextfield.setText(arrangement.getCustomer().getName());
                     customeremailtextfield.setText(arrangement.getCustomer().getEmail());
                     customerphonenumertextfield.setText(arrangement.getCustomer().getPhone());
                     customerfirmtextfield.setText(arrangement.getCustomer().getCompany());
                     customerinfojtextarea.setText(arrangement.getCustomer().getInfo());
                  }
                  descriptionjtextarea.setText(arrangement.getDescription());
               }
               //0 = FacilitatorContactInfo[]
               if(edata[0] instanceof FacilitatorContactInfo[] && edata[0] != null)
               {
                  if(arrangement == null)
                  {
                     facilitatormultiselect.setList((FacilitatorContactInfo[])edata[0]);
                  }
                  else
                  {
                     facilitatormultiselect.setList((FacilitatorContactInfo[])edata[0], arrangement.getFacilitators());
                  }
               }
            }
         }
      }
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());
      super.setVisible(visible, currentEOOperation);
   }

   /**
   * If we leave the panel, we save the state of the arrangement object. The changes are not saved in the database before the user selects to save the data
   */
   private void updateArrangementObj()
   {
      if(EOOperation.UPDATEARRANGEMENT.getData().getClass().isArray())
      {
         Object[] obj = (Object[])EOOperation.UPDATEARRANGEMENT.getData();
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

   private EOArrangement getCurrentArrangement()
   {
      if(EOOperation.UPDATEARRANGEMENT.getData().getClass().isArray())
      {
         Object[] obj = (Object[])EOOperation.UPDATEARRANGEMENT.getData();
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

   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(10,38, this.getWidth(), 38);
      g.drawLine(320,45, 320, this.getHeight()-50);
      g.drawLine(640,45, 640, this.getHeight()-50);
      g.drawLine(960,45, 960, this.getHeight()-50);
   }
}