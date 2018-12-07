import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

/**
Administrates the GUI, what panels are shown and communicates with EOManager, with data the user creates, reads, updates and deletes.
*/
public class EOGUI {
   // JPanel[] screens = null;
   private Map<EODisplayType, EOPanel> screens = null;
   private JFrame frame = null;
   private int screenwidth = 0;
   private int screenheight = 0;
   EOManager eomanager = null;
   EOBreadcrumb breadcrumb = null;
   //usertype = 1: Secretarian
   //usertype = 2: Facilitator   
   int usertype = 2;
   public EOGUI(EOManager eomanager)
   {      
      this.breadcrumb = new EOBreadcrumb();
      this.eomanager = eomanager;
      if(usertype == 1)
      {
         frame = new JFrame("Event Organizer Administration 1.1");
      }
      else
      {
         frame = new JFrame("Event Organizer 1.1");
      }
      
      //frame.setUndecorated(true);
      //GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      //gd.setFullScreenWindow(frame);
      frame.setResizable(false);
      frame.setSize(1300, 700);
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
      screens.put(EODisplayType.OPENARRANGEMENT, new EOPanelOpenArrangement(this));       
      
      screens.put(EODisplayType.CREATEEVENT, new EOPanelCreateEvent(this)); 
      screens.put(EODisplayType.UPDATEEVENT, new EOPanelUpdateEvent(this));  
      screens.put(EODisplayType.OPENEVENT, new EOPanelOpenEvent(this)); 
      
      screens.put(EODisplayType.ADMEVENTTYPE, new EOPanelADMEventType(this)); 
      screens.put(EODisplayType.OPENEVENTTYPE, new EOPanelOpenEventType(this));       
      screens.put(EODisplayType.ADMFACILITATOR, new EOPanelADMFacilitator(this)); 
      screens.put(EODisplayType.OPENFACILITATOR, new EOPanelOpenFacilitator(this));       
      screens.put(EODisplayType.EXPORT, new EOPanelExport(this));
      screens.put(EODisplayType.IMPORT, new EOPanelImport(this));      
      screens.put(EODisplayType.ERROR, new EOPanelError(this));
                            
      DisableAllScreen();
      runCommand(EOOperation.START);
   }

   public javax.swing.border.Border getDefaultBorder()
   {
      return(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
   }
   
   /**
   Returns a que that shows where the user is in the system, so the user can keep track of current location.
   */
   public EOBreadcrumb getBreadcrumb()
   {
      return(this.breadcrumb);
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

   /**
   Disable all panels.
   */
   void DisableAllScreen()
   {
      for(Map.Entry m:screens.entrySet())
      {
         ((JPanel)m.getValue()).setBounds(0, 0, screenwidth, screenheight);
         ((JPanel)m.getValue()).setVisible(false);
         frame.add((JPanel)m.getValue());
      }
   }
   
   /**
   Get width of the application
   */
   public int getWidth()
   {
      return(screenwidth);
   }

   /**
   Get height of the application
   */
   public int getHeight()
   {
      return(screenheight);
   }

	/**
	 * 
	 * Main metode that is called from the panels, this metode controls which panel that is shown through the EOOperation.getDisplayType.
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
      screens.get(coperation.getDisplayType()).setVisible(true, coperation); 
   }
   
   
   /**
   * <pre>
   * Returns if the user is administrator or not.
   * If the user is not administrator, they only have read options availab.e
   * </pre>
   */
   public boolean isAdministrator()
   {
      return(usertype == 1);
   }

   /**
   * <pre>
   * Shows a dialogbox to the user
   * </pre>
   */
   public void dialogbox(String msg)
   {
      JOptionPane.showMessageDialog(frame, msg);
   }

}