import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOPanelError extends EOPanel {
   EOGUI gui = null;

   public EOPanelError(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);

      JButton backbutton = new JButton("Tilbage");
      backbutton.setBounds(this.gui.getWidth()-120, 5, 100, 30);
      backbutton.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     gui.runCommand(EOOperation.START);
                  }
               });
      this.add(backbutton);

      JLabel errorlabel = new JLabel("Der er sket en fejl.");
      errorlabel.setBounds(450, 450, 400, 50);
      errorlabel.setFont(this.gui.getFontbig());
      this.add(errorlabel);
     
   }

   public void setVisible(boolean visible, Object data) {
   	// TODO - implement PanelStartMenu.setVisible
      super.setVisible(visible);
   }

   public void clearData() {
   	// TODO - implement PanelCreateArrangement.clearData
      throw new UnsupportedOperationException();
   }
   
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(0,38, this.getWidth(), 38);
   }      
}