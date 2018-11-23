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
                    //gui.runCommand(EOOperation.EXPORT);
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
      sortCheckBox.setBounds(borderLeft+750,fromTop+60,50,50);
      sortCheckBox.setMnemonic(KeyEvent.VK_C);
      sortCheckBox.setSelected(true);
      sortCheckBox.addItemListener(
              new ItemListener()
              {
                 public void itemStateChanged(ItemEvent e)
                 {
                    if (e.getStateChange()==ItemEvent.SELECTED)
                    {
                     //gui.runCommand(EOOperation.SORT);
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