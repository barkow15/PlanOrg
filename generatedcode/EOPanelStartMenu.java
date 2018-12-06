import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* The start menu shows all arrangements, and provides the user options to CRUD arrangements the classes they contains.
*/
public class EOPanelStartMenu extends EOPanel {

   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;
   EOGUIArrangementTable arrangementtable;
   
   public EOPanelStartMenu(EOGUI gui)
   {
      this.gui = gui;
      this.setLayout(null);
   
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 400, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
   
      int fromTop = 5;
      int borderRight = this.gui.getWidth();
      if(gui.isAdministrator())
      {
         JButton createArrangementButton=new JButton("Opret Arrangement");
         createArrangementButton.setBounds(borderRight-175,fromTop, 150, 30);
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
         exportButton.setBounds(borderRight-330,fromTop, 150, 30);
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
         admFacilitatorButton.setBounds(borderRight-535,fromTop, 200, 30);
         admFacilitatorButton.addActionListener(
                 new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                    {
                       gui.runCommand(EOOperation.ADMFACILITATOR);
                    }
                 });
         this.add(admFacilitatorButton);
      
         JButton admEventType=new JButton("Administrer Begivenhedstyper");
         admEventType.setBounds(borderRight-790,fromTop, 250, 30);
         admEventType.addActionListener(
                 new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                    {
                       gui.runCommand(EOOperation.ADMEVENTTYPE);
                    }
                 });
         this.add(admEventType);
      }
      else
      {
         JButton createArrangementButton=new JButton("Importer Arrangementer");
         createArrangementButton.setBounds(borderRight-215,fromTop, 190, 30);
         createArrangementButton.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
               {
                  gui.runCommand(EOOperation.IMPORT);
               }
            });
         this.add(createArrangementButton);
      }
   
      JLabel arrangementLabel=new JLabel("Arrangementer");
      arrangementLabel.setBounds(10, 0, 350, 150);
      arrangementLabel.setFont(gui.getFontbig());
      this.add(arrangementLabel);
   
      JCheckBox showallcheckbox = new JCheckBox("Vis kun ikke afholdte");
      showallcheckbox.setBounds(this.gui.getWidth()-170, 70, 150, 30);
      showallcheckbox.setSelected(true);
      showallcheckbox.addActionListener(
              new ActionListener()
              {
                 public void actionPerformed(ActionEvent e)
                 {
                    if(showallcheckbox.isSelected())
                    {
                       gui.runCommand(EOOperation.START);
                    }
                    else
                    {
                       gui.runCommand(EOOperation.STARTSHOWALL);
                    }
                 }
              });
      this.add(showallcheckbox);
   
      
      arrangementtable = new EOGUIArrangementTable(gui);
      arrangementtable.setBounds(10, 100, gui.getWidth()-35, gui.getHeight()-150);
      this.add(arrangementtable);
   }
	/**
	 * 
	 * @param visible
	 * @param data
	 */
   public void setVisible(boolean visible, EOOperation currentEOOperation ) {
   	// TODO - implement PanelStartMenu.setVisible
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());
      if(currentEOOperation.getData() instanceof EOArrangement[])
      {
         arrangementtable.setArrangements((EOArrangement[])currentEOOperation.getData());
      }
      else
      {
         arrangementtable.setArrangements(null);
      }
      super.setVisible(visible, currentEOOperation);
   }
   
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(00,38, this.getWidth(), 38);
   }      
}

