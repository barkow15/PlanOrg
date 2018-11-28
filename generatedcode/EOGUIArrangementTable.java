import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;

public class EOGUIArrangementTable extends JPanel
{
   String[] columnNames = {"id", "Dato start", "Dato slut", "Navn", "Facilitator(er)", "Er betalt", "Er afholdt", "", "", ""};
 
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
      super(new GridLayout(1,0));
      this.gui = gui;
      this.setBackground(java.awt.Color.CYAN);
      JTable table = new JTable(data, columnNames);
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
      
      table.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent e)
         {
            int row=table.rowAtPoint(e.getPoint());
            int col=table.columnAtPoint(e.getPoint());
            //Row 7 = Open
            if(col == 7)
            {
               EOOperation.OPENARRANGEMENT.setData(table.getValueAt(row,col));             
               gui.runCommand(EOOperation.OPENARRANGEMENT);
            }
               
            //Row 8 = Edit
            if(col == 8)
            {
               EOOperation.UPDATEARRANGEMENT.setData(table.getValueAt(row,col));            
               gui.runCommand(EOOperation.UPDATEARRANGEMENT);
            }
            //Row 9 = Delete
            if(col == 9)
            {
               EOOperation.DELETEARRANGEMENT.setData(table.getValueAt(row,col));
               gui.runCommand(EOOperation.DELETEARRANGEMENT);
            }            
            try
            {
               System.out.println(" Value in the cell clicked :"+ " " +table.getValueAt(row,col).toString() + " " + row + " " + col);
            }
            catch(Exception err){}
         }
      });    
        
      this.add(table);
      JScrollPane scrollPane = new JScrollPane(table);
      add(scrollPane);        
   }
}