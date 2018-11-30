/**
Viser en flere linier's select box.

Data objecter skal implementere EOGUIMultiSelectInterface
*/
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.Border;

public class EOGUIMultiSelect extends JPanel
{
   JList<Object> list = null;
   DefaultListModel<Object> model = null;
   
   public EOGUIMultiSelect(EOGUIMultiSelectInterface[] options)
   {
      this(options, new Dimension(300, 200));
   }
   
   public EOGUIMultiSelect(EOGUIMultiSelectInterface[] options, Dimension size)
   {
      this(options, new Dimension(300, 200), ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
   }

   public EOGUIMultiSelect(EOGUIMultiSelectInterface[] options, Dimension size, int selectionmode)
   {
      this(options, new Dimension(300, 200), ListSelectionModel.MULTIPLE_INTERVAL_SELECTION, BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
   }

   public EOGUIMultiSelect(EOGUIMultiSelectInterface[] options, Dimension size, int selectionmode, Border border)
   {   
      super(new GridLayout(1,0));
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
   
   public void setList(EOGUIMultiSelectInterface[] options)
   {
      model = new DefaultListModel<>();
      if(options != null)
      {
         for(int i = 0; i < options.length; i++)
         {
            model.addElement(options[i]);
         }
      }
      list.setModel(model);
   }
}