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
//Expected width: 300
//Expected Height: 400
class EOGUIDateTimePicker extends JPanel
{
	//The current datetime
	private LocalDateTime datetime;
	private LocalDate selecteddate=null;
   private LocalTime selectedtime=null;
   
   private String[] monthnames = {"Januar", "Februar", "Marts", "April", "Maj", "Juni", "Juli", "August", "September", "Oktober", "November", "December"};
	private JLabel cmonth = null;
   private Font font = null;
   private JTextField datetext;
   private JTextField timetext;

   //The different days in a month
   private JButton[] mdaysbutton = new JButton[38];
   
   public EOGUIDateTimePicker()
	{
		this(LocalDateTime.now());
	}
   
   public EOGUIDateTimePicker(LocalDateTime datetime)
   {
      this(datetime, new Font("TimesRoman", Font.PLAIN, 12));
   }
	
	public EOGUIDateTimePicker(LocalDateTime datetime, Font font)
	{
      this.font = font;
      this.setVisible(true);
      this.setLayout(null);
		this.datetime = datetime;
      if(datetime != null)
      {
         this.selecteddate = datetime.toLocalDate();
         this.selectedtime = datetime.toLocalTime();      
      }

      this.setBackground(Color.WHITE);
		//1 month back button 
      BasicArrowButton lastmonth = new BasicArrowButton(SwingConstants.WEST);     
      lastmonth.setBounds(40, 10, 25, 25);
      lastmonth.addActionListener(
                new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                   {
                      System.out.println("Last month");
                      LastMonth();                   }
                });
             
      this.add(lastmonth);

		//Month label
		cmonth = new JLabel(monthnames[this.datetime.getMonthValue()-1] + " " + this.datetime.getYear());
      cmonth.setFont(this.font);         
      cmonth.setBounds(70, 10, 160, 25);
      cmonth.setHorizontalAlignment(SwingConstants.CENTER);
      cmonth.setVerticalAlignment(SwingConstants.CENTER);

      this.add(cmonth);

		//1 month forward button 
      BasicArrowButton nextmonth = new BasicArrowButton(SwingConstants.EAST);             
      nextmonth.setBounds(235, 10, 25, 25);
      nextmonth.addActionListener(
                new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                   {
                      System.out.println("Next month");
                      NextMonth();                   }
                });
      this.add(nextmonth);
      
      //Lets make out table
      int fwidth = 35;
      int fheight = 35;
      int fspacing = 8;
      int topborder = 40;   
      //Header
      System.out.println("paint" + 0*(fspacing+fwidth) + " " +  0*(fspacing+fheight)+topborder);
      JLabel monday = new JLabel("Man");
      monday.setBounds(0*(fspacing+fwidth), 0*(fspacing+fheight)+topborder, fwidth, fheight);
      monday.setFont(this.font);
      monday.setHorizontalAlignment(SwingConstants.CENTER);
      monday.setVerticalAlignment(SwingConstants.CENTER);  
      this.add(monday);
           
      JLabel tuesday = new JLabel("Tir");
      tuesday.setBounds(1*(fspacing+fwidth), 0*(fspacing+fheight)+topborder, fwidth, fheight);
      tuesday.setFont(this.font);
      tuesday.setHorizontalAlignment(SwingConstants.CENTER);
      tuesday.setVerticalAlignment(SwingConstants.CENTER);        
      this.add(tuesday);
      
      JLabel wednesday = new JLabel("Ons");
      wednesday.setBounds(2*(fspacing+fwidth), 0*(fspacing+fheight)+topborder, fwidth, fheight);
      wednesday.setFont(this.font);
      wednesday.setHorizontalAlignment(SwingConstants.CENTER);
      wednesday.setVerticalAlignment(SwingConstants.CENTER);     
      this.add(wednesday);
            
      JLabel thuesday = new JLabel("Tor");
      thuesday.setBounds(3*(fspacing+fwidth), 0*(fspacing+fheight)+topborder, fwidth, fheight);
      thuesday.setFont(this.font);
      thuesday.setHorizontalAlignment(SwingConstants.CENTER);
      thuesday.setVerticalAlignment(SwingConstants.CENTER);    
      this.add(thuesday);

      JLabel friday = new JLabel("Fre");
      friday.setBounds(4*(fspacing+fwidth), 0*(fspacing+fheight)+topborder, fwidth, fheight);
      friday.setFont(this.font);
      friday.setHorizontalAlignment(SwingConstants.CENTER);
      friday.setVerticalAlignment(SwingConstants.CENTER);    
      this.add(friday);
      
      JLabel saturday = new JLabel("Lør");
      saturday.setBounds(5*(fspacing+fwidth), 0*(fspacing+fheight)+topborder, fwidth, fheight);
      saturday.setFont(this.font);
      saturday.setHorizontalAlignment(SwingConstants.CENTER);
      saturday.setVerticalAlignment(SwingConstants.CENTER);    
      this.add(saturday);

      JLabel sunday = new JLabel("Søn");
      sunday.setBounds(6*(fspacing+fwidth), 0*(fspacing+fheight)+topborder, fwidth, fheight);
      sunday.setFont(this.font);
      sunday.setHorizontalAlignment(SwingConstants.CENTER);
      sunday.setVerticalAlignment(SwingConstants.CENTER);    
      this.add(sunday);
      
      //Lets add alot of buttons. The max amount of days in a month is 31, the max amount of rows are 1 + 4 * 7 + 3 so 6 rows.
      //So we need 5*7 + 3 buttons = 38 buttons
      topborder += 40;
      int maxbutton = 38;
      int buttonnr = 0;
      for(int i = 0; i < 7; i++)
      {
         for(int j = 0; j < 7 && buttonnr < maxbutton; j++)
         {

            mdaysbutton[buttonnr] = new JButton(Integer.toString(buttonnr));
            mdaysbutton[buttonnr].setBackground(Color.WHITE);
            mdaysbutton[buttonnr].setBounds(j*(fspacing+fwidth)+2, i*(fspacing+fheight)+topborder, fwidth, fheight);
            mdaysbutton[buttonnr].setFont(this.font);
            mdaysbutton[buttonnr].setBorder(null);
            mdaysbutton[buttonnr].setFocusable(false);
            //Because we are using a inner class we are making the int variable final.
            final int bnum = buttonnr;
            mdaysbutton[buttonnr].addActionListener(
                new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                   {
                     setDate(bnum);
                   }
                });
            this.add(mdaysbutton[buttonnr]);
            buttonnr++;
         }
      }
      paint(this.datetime);
      
      JLabel datelabel = new JLabel("Dato:");
      datelabel.setBounds(80, 345, 60, 20);
      datelabel.setFont(this.font);
      this.add(datelabel);
      
      datetext = new JTextField();
      datetext.setBounds(120, 345, 100, 20);
      if(selecteddate != null)
      {
         datetext.setText(selecteddate.getDayOfMonth() + "/" + selecteddate.getMonthValue() + " " + selecteddate.getYear());
      }
      datetext.setFont(this.font);
      datetext.setEditable(false);
      this.add(datetext);
      
      JLabel timelabel = new JLabel("Tid:");
      timelabel.setBounds(80, 370, 60, 20);
      timelabel.setFont(this.font);
      this.add(timelabel);  

      timetext = new JTextField();
      timetext.setBounds(120, 370, 100, 20);
      if(selectedtime != null)
      {
         timetext.setText(selectedtime.format(DateTimeFormatter.ofPattern("k:mm")));      
      }
      timetext.setFont(this.font);
      this.add(timetext);          
	}

   private void setDate(int buttonnr)
   {
      System.out.println(buttonnr);
      this.selecteddate = LocalDate.of(datetime.getYear(), datetime.getMonth(), Integer.parseInt(mdaysbutton[buttonnr].getText()));
      mdaysbutton[buttonnr].setFocusable(false);
      mdaysbutton[buttonnr].setSelected(false);
      datetext.setText(selecteddate.getDayOfMonth() + "/" + selecteddate.getMonthValue() + " " + selecteddate.getYear());
      paint(this.datetime);
   }
	
   public void setDateTime(LocalDateTime datetime)
   {
   	this.datetime = datetime;
   	selecteddate = datetime.toLocalDate();
      selectedtime = datetime.toLocalTime();
      paint(this.datetime);      
   }
   
   private void hideAllDayButtons()
   {
      for(int i = 0; i < mdaysbutton.length; i++)
      {
         mdaysbutton[i].setVisible(false);
      }
   }
   
	private void paint(LocalDateTime datetime)
	{

		//Next month label
		cmonth.setText(monthnames[datetime.getMonthValue()-1] + " " + datetime.getYear());



		//Lets see how many buttons we wanna add for datetime
		//current day of the month
      int currentdayinmonth = datetime.getDayOfMonth();
      //current weekday
      int weekday = (datetime.getDayOfWeek()).getValue();
      //Get days in month (We have to check for it, if its a leapyear or not)
      int daysinmonth = 0;
      if(datetime.toLocalDate().isLeapYear())
      {
         daysinmonth = (datetime.getMonth()).maxLength();

      }
      else
      {
         daysinmonth = (datetime.getMonth()).minLength();
      }
      //Lets find out what the first day of the month is
      //Monday = 0 .. Sunday = 6
      int firstdayinmonth = (7 - (((currentdayinmonth - (weekday-1)) % 7)-1)) % 7;
      int daynum = 1;
      for(int i = 0; i < mdaysbutton.length; i++)
      {
         if(i < firstdayinmonth)
         {
            mdaysbutton[i].setVisible(false);
         }
         else
         {
            if(daysinmonth < daynum)
            {
               mdaysbutton[i].setVisible(false);
            }
            else
            {
               mdaysbutton[i].setText(Integer.toString(daynum));
               mdaysbutton[i].setVisible(true);
               if(datetime.getMonthValue() == selecteddate.getMonthValue() && datetime.getYear() == selecteddate.getYear() && selecteddate.getDayOfMonth() == daynum)
               {
                  mdaysbutton[i].setBackground(Color.GREEN);
               } 
               else
               {
                  mdaysbutton[i].setBackground(Color.WHITE);
               }
               daynum++;
            }
         }
      }
      //Had some issues with the JPanel not refreshing paint, so repainting all.
      this.revalidate();
      this.repaint();      
	}
	  
   private void LastMonth()
   {
      this.datetime = datetime.plusMonths(-1);
      paint(this.datetime);
   }
   
   private void NextMonth()
   {
      this.datetime = datetime.plusMonths(1);
      paint(this.datetime);      
   }
   
	public LocalDateTime getDateTime() throws Exception
	{
      LocalTime t = null;
      try
      {
         t = LocalTime.parse(timetext.getText(), DateTimeFormatter.ofPattern("k:mm")); 
      }
      catch(Exception e)
      {
         throw new IllegalArgumentException("Tidsformat er ikke genkendt " + e.getMessage());
      }
		return(LocalDateTime.of(selecteddate,  t));
	}
   
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawLine(0,38, this.getWidth(), 38);
      g.drawLine(0,332, this.getWidth(), 332);     
      g.drawLine(0,0, this.getWidth(), 0);
      g.drawLine(0,0, 0, this.getHeight());              
      g.drawLine(0,this.getHeight()-1, this.getWidth(), this.getHeight()-1);            
      g.drawLine(this.getWidth()-1,0, this.getWidth()-1, this.getHeight());            
   }
}