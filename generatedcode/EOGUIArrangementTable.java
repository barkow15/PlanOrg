import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
The EOGUIArrangementTable is custom made to shows the different EOArrangement, it is used in the EOPanelStartMenu.

If the user is an administrator, Edit, Delete options are shown. Else only the Open option is shown (for both Administrator and none administrator).
*/
public class EOGUIArrangementTable extends JPanel
{
   String[] columnNames;
   EOArrangement[] arrangements;
   JTable table;
   Object[][] data = {};
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
      columnNames = getColumns();      
     
   }
   
   /**
   * Creates the table
   */
   private void createTable()
   {
      columnNames = getColumns();
      table = new JTable(data, columnNames);
      //Ref: https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
      //Implemented through a anonymous inner class
      table.setModel(
         new DefaultTableModel(null, columnNames) {
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
      
      table.addMouseListener(
         new java.awt.event.MouseAdapter()
         {
            public void mouseClicked(java.awt.event.MouseEvent e)
            {
               int row=0;
               int col=0;
               int id=0;
               try
               {
                  row=table.rowAtPoint(e.getPoint());
                  col=table.columnAtPoint(e.getPoint());
                  id = Integer.parseInt((String)table.getValueAt(row,0));
               }catch(Exception epoint){}
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

   /**
   Updates the table witht he new EOArrangements that are parsed to the metode.
   */
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
               for(int j = 0; j < f.length;j++)
               {
                  if(j == 0)
                  {
                     facilitators = f[j].getName();
                  }
                  else
                  {
                     if(f[j] != null && f[j].getName() != null)
                     {
                        facilitators = facilitators + ", " + f[j].getName();
                     }
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
            String ispayed = "Nej";
            if(arrangements[i].isPayed())
            {
               ispayed = "Ja";
            }
            String isdone = "Nej";
            if(arrangements[i].isDone())
            {
               isdone = "Ja";
            }            
            if(gui.isAdministrator())
            {
               String[] s = {
                  Integer.toString(arrangements[i].getId()), 
                  sdate, 
                  edate, 
                  arrangements[i].getName(), 
                  facilitators, 
                  ispayed, 
                  isdone, 
                  "Åben", "Rediger", "Slet"};
               try
               {
                  dm.addRow(s);
               }
               catch(Exception e){}  
            }
            else
            {
               
               String[] s = {
                  Integer.toString(arrangements[i].getId()), 
                  sdate, 
                  edate, 
                  arrangements[i].getName(), 
                  facilitators, 
                  ispayed, 
                  isdone, 
                  "Åben"};
               try
               {
                  dm.addRow(s);
               }
               catch(Exception e){}  
            }
         
         }
      }
      dm.fireTableDataChanged();   
   
   }
   /**
   * Gets the EOArrangement, that the row id is showing data from
   */
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
   
   /**
   * Gets  the different colimuns, since these are different if its either an administrator or none administrator.
   */
   public String[] getColumns()
   {
      String[] sarray;
      if(gui.isAdministrator())
      {
         sarray = new String[10];
      }
      else
      {
         sarray = new String[8];
      }
      sarray[0] = "id";
      sarray[1] = "Dato start";
      sarray[2] = "Dato slut";
      sarray[3] = "Navn";
      sarray[4] = "Facilitator(er)";
      sarray[5] = "Er betalt";
      sarray[6] = "Er afholdt";     
      sarray[7] = "";
      if(gui.isAdministrator())
      {
         sarray[8] = "";
         sarray[9] = "";   
      }
      return(sarray);
   }
}