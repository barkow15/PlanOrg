import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.Border;
import java.awt.event.*;
/**
   * Shows one or more lines of options.
   * In the constructor it can be set, if the user has the option to select one or more options.
* Objects that are parsed to this class, must implement EOGUIMultiSelectInterface, they are then listed showing their name through calling getDisplayName() on the object.
*/
public class EOGUIMultiSelect extends JPanel
{
   JList<Object> list = null;
   DefaultListModel<Object> model = null;
   EOGUIMultiSelectInterface[] options;
   
   public EOGUIMultiSelect(EOGUIMultiSelectInterface[] options)
   {
      this(options, new Dimension(300, 200));
   }


   public EOGUIMultiSelect(EOGUIMultiSelectInterface[] options, Dimension size)
   {
      this(options, size, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
   }

   
   public EOGUIMultiSelect(EOGUIMultiSelectInterface[] options, Dimension size, int selectionmode)
   {
      this(options, new Dimension(300, 200), ListSelectionModel.MULTIPLE_INTERVAL_SELECTION, BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
   }

   
   public EOGUIMultiSelect(EOGUIMultiSelectInterface[] options, Dimension size, int selectionmode, Border border) 
   {
      super(new GridLayout(1,0));
      this.options = options;
      this.setBackground(Color.WHITE);
      this.setBorder(border);
      //size.setSize(size.getWidth()-2, size.getHeight()-7);  
      model = new DefaultListModel<>();
      if(options != null)
      {
         for(int i = 0; i < options.length; i++)
         {
            model.addElement(options[i]);
         }
      }
   
      list = new JList<>(model);        
      //list.setPreferredSize(size);
       
      list.setCellRenderer(
         new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
               Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
               if (renderer instanceof JLabel && value instanceof EOGUIMultiSelectInterface) {
                  ((JLabel) renderer).setText(((EOGUIMultiSelectInterface) value).getDisplayName());
               }
               return renderer;
            }
         });
      JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
      scrollPane.setSize(size);  
   
      this.add(scrollPane);
      //this.add(list);
   }
   
   /**
   * Adds a specific mouseadapter to the list
   */
   public void addMouseListener(MouseAdapter mouseadapter)
   {
      list.addMouseListener(mouseadapter);
   }
  
   /**
   * Adds our generic EO mouse adapter to the list, this will set the data of the list in the EOOperation defined and do a runCommand with the EOOperation. If the user right clicks the cell
   */
   public void addMouseListener(EOGUI gui, EOOperation operation)
   {
      MouseAdapter mouseadapter = 
         new java.awt.event.MouseAdapter()
         {
            public void mouseClicked(java.awt.event.MouseEvent e)
            {
               if(e.getButton() == MouseEvent.BUTTON3) {
                  System.out.println("Right Click! " + ((EOGUIMultiSelectInterface)list.getModel().getElementAt(list.locationToIndex(e.getPoint()))).getDisplayName());
                  operation.setData(((EOGUIMultiSelectInterface)list.getModel().getElementAt(list.locationToIndex(e.getPoint()))));
                  gui.runCommand(operation);
               }
            }
         };   
      list.addMouseListener(mouseadapter);
   }   
   
   /**
   * Returns selected objects
   */
   public Object[] getSelected()
   {
      java.util.List<Object> r = list.getSelectedValuesList();
      if(r == null)
      {
         return(null);
      }
      else
      {
         return(r.toArray());
      }
   }

   /**
   * Marks the selected options in the list
   */
   public void setList(EOGUIMultiSelectInterface[] options)
   {
      this.options = options;
      setList(options, null);
   }
   
   /**
   * Get all options
   */
   public EOGUIMultiSelectInterface[] getList()
   {
      return(this.options);
   }
   
   /**
   * Sets the options and selects the options that are in the selected array.
   */
   public void setList(EOGUIMultiSelectInterface[] options, EOGUIMultiSelectInterface[] selected)
   {
      model = new DefaultListModel<>();
      int selectedindex = 0;
      int[] iselected = null;
      if(selected != null)
      {
         iselected = new int[selected.length];
      }
      
      if(options != null)
      {
         for(int i = 0; i < options.length; i++)
         {
            for(int j = 0; selected != null && j < selected.length; j++)
            {
               if(selected[j].equals(options[i]))
               {
                  iselected[selectedindex] = i;
                  selectedindex++;
               }
            }
            model.addElement(options[i]);
         }
      }
      list.setModel(model);
      for(int j = 0; selected != null && j < iselected.length; j++)
      {
         list.addSelectionInterval(iselected[j], iselected[j]);
      }
   }
}