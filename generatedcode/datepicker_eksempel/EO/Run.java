import javax.swing.*; 
import java.awt.Color;
import java.awt.Font;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.Graphics;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.*;

public class Run
{
	public static void main(String[] args)
	{
		new M();
	}
	
}

class M
{
	public M()
	{
      JFrame frame = new JFrame("Event Organizer Administration 1.1");
      //frame.setUndecorated(true);
      //GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      //gd.setFullScreenWindow(frame);
      frame.setSize(1200, 900);
      frame.setLayout(null);

	  EOGUIDateTimePicker eodtp = new EOGUIDateTimePicker();
	  eodtp.setBounds(100, 100, 300, 400);

     //eodtp.setBorder(BorderFactory.createLineBorder(Color.black));
	  frame.add(eodtp);

     eodtp.setVisible(true);
     frame.setVisible(true);

      JButton getdatetime = new JButton("Get datetime");
      getdatetime.addActionListener(
                new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                   {
                     try
                     {
                        System.out.println(eodtp.getDateTime().toString());
                     }
                     catch(Exception ee)
                     {
                        System.out.println("Oh noes" + ee.getMessage());
                     }
                    }
                });
       getdatetime.setBounds(0, 0, 200, 30);
       frame.add(getdatetime);

	}
}

