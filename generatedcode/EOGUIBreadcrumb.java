import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
Is the visuable breadcrumb class, this is used on all Panels in the program to show the user where they are currently located, and giving them an option to jump to another location in the path.
*/
public class EOGUIBreadcrumb extends JPanel
{
   EOBreadcrumb breadcrumb = null;
   private JLabel[] labellink;
   private JLabel[] seperator;
   private int maxsize = 10;
   EOGUI gui;
   
   public EOGUIBreadcrumb(EOGUI gui, EOBreadcrumb breadcrumb)
   {
      this.gui = gui;
      this.breadcrumb = breadcrumb;
      
      this.setLayout(null);
      
      labellink = new JLabel[maxsize];
      seperator = new JLabel[maxsize];
      
      for(int i = 0; i < maxsize; i++)
      {
         labellink[i] = null;
         seperator[i] = null;
      }
      reset();
      drawCrumbs();
   }
   
   /**
   Sets the breadcrumb to be shown
   */
   public void setBreadcrumb(EOBreadcrumb b)
   {
      this.breadcrumb = breadcrumb;  
      reset();
      drawCrumbs();
   }
   
   private void reset()
   {
      for(int i = 0; i < maxsize; i++)
      {
         if(labellink[i] != null)
         {
            labellink[i].setVisible(false);
         }
         labellink[i] = null;
         if(seperator[i] != null)
         {
            seperator[i].setVisible(false);
         }         
         seperator[i] = null;
      }   
   }
     
   public void drawCrumbs()
   {
      //System.out.println("Start: drawCrumbs()");
      Font medium = gui.getFontmedium();
      Dimension size;
      final String sepstr = ">";
      int x = 5;
      for(int i = 0; i < breadcrumb.getStackCounter(); i++)
      {
         if(labellink[i] != null)
         {
            labellink[i].setVisible(false);
         }
         if(i+1 == breadcrumb.getStackCounter())
         {
            //System.out.println("Breakcrumb current: " + breadcrumb.getIndex(i).getDisplayName());         
            labellink[i] = new JLabel(breadcrumb.getIndex(i).getDisplayName());
            labellink[i].setForeground(Color.MAGENTA);
            labellink[i].setFont(medium);
            size = getTextDimensions(labellink[i], medium, breadcrumb.getIndex(i).getDisplayName());        
            labellink[i].setBounds(x, 0, (int)size.getWidth(), (int)size.getHeight());
            this.add(labellink[i]);
            x += size.getWidth() + 5;
         }
         else
         {
            //System.out.println("Breakcrumb link: "+breadcrumb.getIndex(i).getDisplayName());
            labellink[i] = new JLabel(breadcrumb.getIndex(i).getDisplayName());
            labellink[i].setForeground(Color.BLUE);            
            labellink[i].setFont(medium);
            size = getTextDimensions(labellink[i], medium, breadcrumb.getIndex(i).getDisplayName());
            labellink[i].setBounds(x, 0, (int)size.getWidth(), (int)size.getHeight());
            final EOOperation eoope = breadcrumb.getIndex(i);
            final int ipos = breadcrumb.getStackCounter() - i;
            labellink[i].addMouseListener(
               new MouseAdapter() {
                  public void mouseClicked(MouseEvent e) {
                     gui.runCommand(gui.getBreadcrumb().pop(ipos));
                  }
                
                  public void mouseEntered(MouseEvent e) {
                     Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
                     setCursor(cursor);
                  }
                 
                  public void mouseExited(MouseEvent e) {
                     Cursor cursor = Cursor.getDefaultCursor();
                     setCursor(cursor);
                  }  
               
               });
            labellink[i].setVisible(true);
            this.add(labellink[i]);
            x += size.getWidth() + 5;
            seperator[i] = new JLabel(sepstr);
            seperator[i].setForeground(Color.BLACK);            
            seperator[i].setFont(medium);
            size = getTextDimensions(seperator[i], medium, sepstr);
            seperator[i].setBounds(x, 0, (int)size.getWidth(), (int)size.getHeight());
            this.add(seperator[i]);
            x += size.getWidth() + 5;
         }
         labellink[i].setVisible(true);
      }
      //System.out.println("End: drawCrumbs()");      
      this.revalidate();
      repaint();
   }
   
   public static Dimension getTextDimensions(Component c, Font font, String text)
   {
      FontMetrics metrics = c.getFontMetrics(font);
      // get the height of a line of text in this
      // font and render context
      int hgt = metrics.getHeight();
      // get the advance of my text in this font
      // and render context
      int adv = metrics.stringWidth(text);
      // calculate the size of a box to hold the
      // text with some padding.
      return(new Dimension(adv+2, hgt+2));
   }   
}