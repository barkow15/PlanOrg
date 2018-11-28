import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;

public class EOGUIArrangementTable extends JPanel
{
   String[] columnNames = {"id", "Dato start", "Dato slut", "Navn", "Facilitator(er)", "Er betalt", "Er afholdt", "", "", ""};
   EOArrangement[] arrangements;
   JTable table;
 Object[][] data = {
        {"1", "Kathy1", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"2", "Kathy2", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"3", "Kathy3", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"4", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}, {"5", "Kathy", "Smith",
         "Snowboarding", "", "", "", "Åbn", "Rediger", "Slet"}};
   EOGUI gui;

   public EOGUIArrangementTable(EOGUI gui)
   {
      this(gui, null);
   }
   
   public EOGUIArrangementTable(EOGUI gui, EOArrangement[] arrangements)
   {
      super(new GridLayout(1,0));
      this.gui = gui;
      this.setBackground(java.awt.Color.CYAN);
      
      createTable();
      
      setArrangements(null);      
   }

   private void createTable()
   {
      
      table = new JTable(data, columnNames);
      //Ref: https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
      //Implemented through a anonymous inner class
      table.setModel(new DefaultTableModel(data, columnNames) {
          @Override
          public boolean isCellEditable(int row, int column) {
             //all cells false
             return false;
          }
      });
      table.setPreferredScrollableViewportSize(new Dimension(1000, 800));
      table.setFillsViewportHeight(true);
      table.setAutoCreateRowSorter(true);
      //Disables that users can drag columns around - This is so we know column 0 is always the arrangement id
      table.getTableHeader().setReorderingAllowed(false);
      
      table.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent e)
         {
            int row=table.rowAtPoint(e.getPoint());
            int col=table.columnAtPoint(e.getPoint());
            int id = Integer.parseInt((String)table.getValueAt(row,0));
            //Row 7 = Open
            if(col == 7)
            { 
               EOOperation.OPENARRANGEMENT.setData(getArrangement(id));
               gui.runCommand(EOOperation.OPENARRANGEMENT);
            }
               
            //Row 8 = Edit
            if(col == 8)
            {
               EOOperation.UPDATEARRANGEMENT.setData(getArrangement(id));
               gui.runCommand(EOOperation.UPDATEARRANGEMENT);
            }
            //Row 9 = Delete
            if(col == 9)
            {
               EOOperation.DELETEARRANGEMENT.setData(getArrangement(id));
               gui.runCommand(EOOperation.DELETEARRANGEMENT);
            }            
            try
            {
               System.out.println(" Value in the cell clicked :"+ " " +table.getValueAt(row,0).toString() + " " + row + " " + col);
            }
            catch(Exception err){}
         }
      });    
        
      this.add(table);
      JScrollPane scrollPane = new JScrollPane(table);
      add(scrollPane); 
   }

   public void setArrangements(EOArrangement[] arrangements)
   {
      this.arrangements = arrangements;

      DefaultTableModel dm = (DefaultTableModel)table.getModel();

      dm.getDataVector().removeAllElements();
      dm.fireTableDataChanged();
            
      if(arrangements != null)
      {
         System.out.print("adding to table");
         for(int i = 0; i < arrangements.length && arrangements[i] != null; i++)
         {
            FacilitatorContactInfo[] f = arrangements[i].getFacilitators();
            String facilitators = "";
            if(f != null)
            {
               for(int j = 0; i < f.length;j++)
               {
                  if(j == 0)
                  {
                     facilitators = f[j].getName();
                  }
                  else
                  {
                     facilitators = facilitators + ", " + f[j].getName();
                  }
               }
            }
            else
            {
               facilitators = "";
            }

            String sdate = arrangements[i].getDateTimeStart().getDayOfMonth() + "/" + arrangements[i].getDateTimeStart().getMonthValue() + " " + arrangements[i].getDateTimeStart().getYear();
            String edate = arrangements[i].getDateTimeEnd().getDayOfMonth() + "/" + arrangements[i].getDateTimeEnd().getMonthValue() + " " + arrangements[i].getDateTimeEnd().getYear();
            System.out.println(arrangements[i].getName());
            String[] s = {
               Integer.toString(arrangements[i].getId()), 
               sdate, 
               edate, 
               arrangements[i].getName(), 
               facilitators, 
               Boolean.toString(arrangements[i].isPayed()), 
               Boolean.toString(arrangements[i].isDone()), 
               "Åbn", "Rediger", "Slet"};
            try
            {
               dm.addRow(s);
            }
            catch(Exception e){}      
         }
      }
      dm.fireTableDataChanged();   

   }
   
   private EOArrangement getArrangement(int id)
   {
      for(int i = 0; i < arrangements.length && arrangements[i] != null; i++)
      {
         if(arrangements[i].getId() == id)
         {
            return(arrangements[i]);
         }
      }
      return(null);
   }
}