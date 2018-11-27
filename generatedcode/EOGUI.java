import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOGUI {
   // JPanel[] screens = null;
   private Map<EODisplayType, EOPanel> screens = null;
   private JFrame frame = null;
   private int screenwidth = 0;
   private int screenheight = 0;
   EOManager eomanager = null;
   public EOGUI()
   {
      eomanager = new EOManager();
      frame = new JFrame("Event Organizer Administration 1.1");
      //frame.setUndecorated(true);
      //GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      //gd.setFullScreenWindow(frame);
      frame.setSize(1200, 900);
      frame.setLayout(null);
      frame.setVisible(true);
      //We will close Java when the screen is exited
      frame.addWindowListener(
                new WindowAdapter()
                {
                   public void windowClosed(WindowEvent e)
                   {
                      System.out.println("windowClosed");
                      Runtime.getRuntime().exit(0);
                   }
                
                   public void windowClosing(WindowEvent e)
                   {
                      System.out.println("windowClosing");
                      Runtime.getRuntime().exit(0);
                   }
                });
   
      screenwidth = frame.getWidth();
      screenheight = frame.getHeight();
   
      //Setting up our different screens
      // screens = new JPanel[8];
      screens = new HashMap<EODisplayType, EOPanel>();
      screens.put(EODisplayType.START, new EOPanelStartMenu(this));      
      screens.put(EODisplayType.CREATEARRANGEMENT, new EOPanelCreateArrangement(this)); 
      screens.put(EODisplayType.UPDATEARRANGEMENT, new EOPanelUpdateArrangement(this)); 
      screens.put(EODisplayType.DELETEARRANGEMENT, new EOPanelDeleteArrangement(this)); 
      //screens.put(EODisplayType.CREATEEVENT, new EOPanelCreateEvent(this)); 
      screens.put(EODisplayType.UPDATEEVENT, new EOPanelUpdateEvent(this)); 
      screens.put(EODisplayType.DELETEEVENT, new EOPanelDeleteEvent(this));  
      screens.put(EODisplayType.ADMEVENTTYPE, new EOPanelADMEventType(this)); 
      screens.put(EODisplayType.ADMFACILITATOR, new EOPanelADMFacilitator(this)); 
      screens.put(EODisplayType.EXPORT, new EOPanelExport(this));
                      
      DisableAllScreen();
      screens.get(EODisplayType.START).setVisible(true);   
   }
   public Font getFontbig (){
      return (new Font ("Arial", Font.PLAIN,40));
   }
   public Font getFontmedium (){
      return (new Font ("Arial", Font.PLAIN,20));
   }
   public Font getFontsmall (){
      return (new Font ("Arial", Font.PLAIN,12));
   }

   void DisableAllScreen()
   {
      for(Map.Entry m:screens.entrySet())
      {
         ((JPanel)m.getValue()).setBounds(0, 0, screenwidth, screenheight);
         ((JPanel)m.getValue()).setVisible(false);
         frame.add((JPanel)m.getValue());
      }
   }
   
   public int getWidth()
   {
      return(screenwidth);
   }
   
   public int getHeight()
   {
      return(screenheight);
   }

	/**
	 * 
	 * @param operation
	 */
   public void runCommand(EOOperation operation) {
      EOOperation coperation = eomanager.runCommand(operation);
      DisableAllScreen();   
      if(coperation.getDisplayType() == null)
      {
         System.out.println("DisplayType == null");
      }
      if(coperation.getData() == null)
      {
         System.out.println("getData == null");
      }      
      System.out.println("Viser: " + coperation.getDisplayType());
      screens.get(coperation.getDisplayType()).setVisible(true, coperation.getData()); 
   }

}