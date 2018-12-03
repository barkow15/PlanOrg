import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EOPanelDeleteArrangement extends EOPanel {
   EOGUI gui = null;
   EOGUIBreadcrumb breadcrumb;
   JLabel deletenamevaluejlabel;
   EOArrangement deletearrangement;

   public EOPanelDeleteArrangement(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
   
      //Header
      breadcrumb = new EOGUIBreadcrumb(gui, gui.getBreadcrumb());
      breadcrumb.setBounds(5, 5, 800, 30);
      breadcrumb.setVisible(true);
      this.add(breadcrumb);
      
      JButton savebutton=new JButton("Slet");
      System.out.println(this.gui.getWidth()-110);
      savebutton.setBounds(this.gui.getWidth()-125, 5, 100, 30);
      savebutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     EOOperation.SAVEDELETEARRANGEMENT.setData(deletearrangement);
                     gui.runCommand(EOOperation.SAVEDELETEARRANGEMENT);
                  }
               });
      this.add(savebutton);
   
   
      JButton cancelbutton=new JButton("Annuller");
      System.out.println(this.gui.getWidth()-210);
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


      JLabel verifylabel=new JLabel("Er du sikker paa, at du vil slette arrangementet?");
      verifylabel.setBounds(425, 200, 450, 60);
      verifylabel.setFont(this.gui.getFontmedium());

      this.add(verifylabel);

      //Name
      JLabel deletenamelabel=new JLabel("Navn:");
      deletenamelabel.setBounds(550, 275, 70, 30);
      deletenamelabel.setFont(this.gui.getFontsmall());

      this.add(deletenamelabel);

      deletenamevaluejlabel=new JLabel();
      deletenamevaluejlabel.setBounds(600, 275, 650, 30);
      deletenamevaluejlabel.setFont(this.gui.getFontsmall());

      this.add(deletenamevaluejlabel);


   
   }
	/**
	 * 
	 * @param visible
	 * @param data
	 */
   public void setVisible(boolean visible, EOOperation currentEOOperation) {
      EOOperation deletearrangement = currentEOOperation;
      if(deletearrangement.getData() instanceof EOArrangement)
      {
         EOArrangement arrangement = (EOArrangement) deletearrangement.getData();
         deletenamevaluejlabel.setText(arrangement.getName());
         this.deletearrangement = arrangement;
      }
      breadcrumb.setBreadcrumb(gui.getBreadcrumb());     
      super.setVisible(visible, currentEOOperation);
   }

   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(10,38, this.getWidth(), 38);
   }
}