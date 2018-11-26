import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOPanelStartMenu extends EOPanel {

   EOGUI gui = null;
   public EOPanelStartMenu(EOGUI gui)
   {
      this.gui = gui;
      this.setLayout(null);
   
      int fromTop = 50;
      int borderLeft = 10;
      JButton createArrangementButton=new JButton("Opret arrangement");
      createArrangementButton.setBounds(borderLeft,fromTop, 150, 30);
      createArrangementButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               gui.runCommand(EOOperation.CREATEARRANGEMENT);
            }
         });
      this.add(createArrangementButton);

      JButton exportButton=new JButton("Eksporter");
      exportButton.setBounds(borderLeft+150+10,fromTop, 150, 30);
      exportButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    gui.runCommand(EOOperation.EXPORT);
                 }
              });
      this.add(exportButton);

      JButton admFacilitatorButton=new JButton("Administrer Facilitatorer");
      admFacilitatorButton.setBounds(borderLeft+720,fromTop, 200, 30);
      admFacilitatorButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //gui.runCommand(EOOperation.ADMFACILITATOR);
                 }
              });
      this.add(admFacilitatorButton);

      JButton admEventType=new JButton("Administrer begivenhedstyper");
      admEventType.setBounds(borderLeft+600+200+10+120,fromTop, 250, 30);
      admEventType.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //gui.runCommand(EOOperation.ADMEVENTTYPE);
                 }
              });
      this.add(admEventType);

      JLabel arrangementLabel=new JLabel("Arrangement");
      arrangementLabel.setBounds(borderLeft,fromTop, 250, 150);
      arrangementLabel.setFont(gui.getFontbig());
      this.add(arrangementLabel);

      JCheckBox sortCheckBox = new JCheckBox ();
      sortCheckBox.setBounds(borderLeft+750,fromTop+50,50,50);
      sortCheckBox.setMnemonic(KeyEvent.VK_C);
      sortCheckBox.setSelected(true);
      sortCheckBox.addItemListener(
              new ItemListener()
              {
                 public void itemStateChanged(ItemEvent e)
                 {
                    if (e.getStateChange()==ItemEvent.SELECTED)
                    {
                     //gui.runCommand(EOOperation.SHOWONLYPASTEVENT);
                    }
                 }

              });
      this.add(sortCheckBox);

      JLabel showOnlyPastArrangementsLabel = new JLabel("Vis kun afholdte arrangementer");
      showOnlyPastArrangementsLabel.setBounds(borderLeft+800,fromTop,250, 150);
      showOnlyPastArrangementsLabel.setFont (gui.getFontsmall());
      this.add(showOnlyPastArrangementsLabel);

      JButton resetSortingButton = new JButton ("Nustil Sortering");
      resetSortingButton.setBounds(borderLeft+1000,fromTop+60,150,30);
      resetSortingButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //gui.runCommand(EOOperation.RESETSORTING);
                 }
              });
      this.add(resetSortingButton);

      JButton dateStartLabel = new JButton("Dato Start:");
      dateStartLabel.setBounds (borderLeft,fromTop+100,100,50);
      dateStartLabel.setFont (gui.getFontsmall());
      dateStartLabel.setBorderPainted (false);
      dateStartLabel.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //gui.runCommand(EOOperation.SORTSTARTDATE);
                 }
              });

      //this.add(dateStartLabel);

      JButton dateEndLabel = new JButton("Dato Slut");
      dateEndLabel.setBounds (borderLeft+100,fromTop+100,100,50);
      dateEndLabel.setFont (gui.getFontsmall());
      dateEndLabel.setBorderPainted (false);
      dateEndLabel.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //guirunComman(EOOperation.SORTENDDATE);
                 }
              });
      //this.add(dateEndLabel);

      JLabel nameLabel = new JLabel ("Navn:");
      nameLabel.setBounds (borderLeft+260,fromTop+100,100,50);
      dateEndLabel.setFont (gui.getFontsmall());
      //this.add(nameLabel);

      JButton facilitatorLabel = new JButton ("Facilitator:");
      facilitatorLabel.setBounds (borderLeft+375,fromTop+100,100,50);
      facilitatorLabel.setFont (gui.getFontsmall());
      facilitatorLabel.setBorderPainted (false);
      facilitatorLabel.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //guirunComman(EOOperation.SORTBYFACILITATOR);
                 }
              });
      //this.add(facilitatorLabel);

      JLabel hasBeenPaidLabel = new JLabel ("Er Betalt:");
      hasBeenPaidLabel.setBounds (borderLeft+550,fromTop+50,250,150);
      hasBeenPaidLabel.setFont (gui.getFontsmall());
      //this.add(hasBeenPaidLabel);

      JLabel hasBeenHeldLabel = new JLabel ("Er afholdt:");
      hasBeenHeldLabel.setBounds(borderLeft+650,fromTop+50,250,150);
      hasBeenHeldLabel.setFont (gui.getFontsmall());
      //this.add(hasBeenHeldLabel);

      JCheckBox hasBeenPaidCheckBox = new JCheckBox ();
      hasBeenPaidCheckBox.setBounds(borderLeft+750,fromTop+50,50,50);
      hasBeenPaidCheckBox.setMnemonic(KeyEvent.VK_C);
      hasBeenPaidCheckBox.setSelected(true);
      hasBeenPaidCheckBox.addItemListener(
              new ItemListener()
              {
                 public void itemStateChanged(ItemEvent e)
                 {
                    if (e.getStateChange()==ItemEvent.SELECTED)
                    {
                       //gui.runCommand(EOOperation.SHOWONLYPASTEVENT);
                    }
                 }

              });
      //this.add(hasBeenPaidCheckBox);

      JCheckBox hasBeenHeldCheckBox = new JCheckBox ();
      hasBeenHeldCheckBox.setBounds(borderLeft+750,fromTop+50,50,50);
      hasBeenHeldCheckBox.setMnemonic(KeyEvent.VK_C);
      hasBeenHeldCheckBox.setSelected(true);
      hasBeenHeldCheckBox.addItemListener(
              new ItemListener()
              {
                 public void itemStateChanged(ItemEvent e)
                 {
                    if (e.getStateChange()==ItemEvent.SELECTED)
                    {
                       //gui.runCommand(EOOperation.SHOWONLYPASTEVENT);
                    }
                 }

              });
      //this.add(hasBeenHeldCheckBox);

      JButton openButton = new JButton ("Åben");
      openButton.setBounds(borderLeft,fromTop,100,50);
      openButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //guirunComman(EOOperation.OPENEVENTDATA);
                 }
              });
      //this.add(openButton);

      JButton editButton = new JButton ("Rediger");
      editButton.setBounds(borderLeft,fromTop,250,50);
      editButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //guirunComman(EOOperation.EDITEVENTDATA);
                 }
              });
      //this.add(editButton);

      JButton deleteButton = new JButton ("Slet");
      deleteButton.setBounds(borderLeft,fromTop,100,50);
      deleteButton.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    //guirunComman(EOOperation.DELETEEVENTDATA);
                 }
              });
      //this.add(deleteButton);
      String[] columnNames = {"DatoStart", "DatoSlut", "Navn", "Facilitator","Er Betalt","Er Afholdt","Åben","Rediger","Slet"};
      Object[][] data = {{"1/9/2018","2/9/2018","KEA", "Allan Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Brian Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Fred Skou",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "John Mogensen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Kasper Østergaard",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Brian Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Brian Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Brian Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Brian Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Brian Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Brian Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Brian Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"},{"1/9/2018","2/9/2018","KEA", "Brian Nielsen",new Boolean(true),new Boolean(true),"Åben","Rediger","Slet"}};
      JTable eventDataTable = new JTable (data,columnNames);
      JScrollPane scrollPanel = new JScrollPane(eventDataTable);
      eventDataTable.setFillsViewportHeight(true);
      eventDataTable.setAutoCreateRowSorter(true);
      eventDataTable.setBounds(borderLeft,fromTop+150,1000,400);
      eventDataTable.setLayout(new BorderLayout());
      eventDataTable.add(eventDataTable.getTableHeader(), BorderLayout.PAGE_START);
      scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      this.add(eventDataTable);

      //JScrollPane scrollPane = new JScrollPane(eventDataTable);
      //eventDataTable.add(new JScrollPane(eventDataTable));
      //eventDataTable.add(eventDataTable, BorderLayout.CENTER);
      //scrollPanel.setBounds(borderLeft,fromTop+150,1150,400);
      //eventDataTable.setFillsViewportHeight(true);

      //this.add(scrollPanel);


   }
	/**
	 * 
	 * @param visible
	 * @param data
	 */
   public void setVisible(boolean visible, Object data) {
   	// TODO - implement PanelStartMenu.setVisible
      super.setVisible(visible);
   }
}