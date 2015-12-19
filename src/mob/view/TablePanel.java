

package mob.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/* creats empty Jtabale to displaye data*/

public class TablePanel extends JPanel{
    
    private TableModel tableModel;
    private JTable table;

   
 
        public TablePanel(){
        tableModel = new TableModel();
        table = new JTable(tableModel);    
        setLayout(new BorderLayout());
        add(new JScrollPane(table),BorderLayout.CENTER);
    
    }
    
 
       
    public void refresh(){
        getTableModel().fireTableDataChanged();
    
    }

   
    public TableModel getTableModel() {
        return tableModel;
        
    }

 

}
