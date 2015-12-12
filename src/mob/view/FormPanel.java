package mob.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FormPanel extends JPanel{
    
    private  JComboBox comboBox;
    private  JLabel    reportlable;
    private int comboBoxIndex ;
    private FormListener formListener;
            
    public FormPanel(){
        initComponent();
     
    }
    
    
 public void setFramePanelLayout(JPanel comp){
            
            removeAll();
            revalidate();
            repaint();      
    
            GridBagConstraints gc = new GridBagConstraints();
      
             /// first row
             gc.weightx = 0;
             gc.weighty = 0.1;
             gc.gridx = 0;
             gc.gridy = 0;
             gc.fill = GridBagConstraints.NONE;
         
             gc.insets = new Insets (5,5,5,5);
             add (reportlable,gc);
        
             gc.gridx = 1;
             gc.gridy = 0;
             add (comboBox,gc);
     
        /// second row
     
    
             gc.weightx = 0;
             gc.weighty = 3;
             gc.gridx = 1;
             gc.gridy = 3;
             gc.anchor = GridBagConstraints.FIRST_LINE_START;
             gc.fill = GridBagConstraints.BOTH;
             add (comp,gc);
        
        }
 
    
 public void setEmptyFramePanelLayout(){
            
            removeAll();
            revalidate();
            repaint();      
    
            GridBagConstraints gc = new GridBagConstraints();
      
             /// first row
             gc.weightx = 0;
             gc.weighty = 0.1;
             gc.gridx = 0;
             gc.gridy = 0;
             gc.fill = GridBagConstraints.NONE;
             gc.anchor = GridBagConstraints.FIRST_LINE_START;
             gc.insets = new Insets (5,5,5,5);
             add (reportlable,gc);
        
             gc.gridx = 1;
             gc.gridy = 0;
             add (comboBox,gc);
        }
        
 
 
            public void initComponent(){
            
        comboBox = new JComboBox();
        reportlable = new JLabel("Select Report:");
 
  
        Dimension dim = getPreferredSize();
        dim.width = 300;
        setPreferredSize(dim);
        Border innerBorder = BorderFactory.createTitledBorder("Report List");
        Border outterBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outterBorder));
        
        setLayout(new GridBagLayout());
        // comboo box
       
        DefaultComboBoxModel reportList = new DefaultComboBoxModel();
        reportList.addElement("...");
        reportList.addElement("Service List");
        reportList.addElement("Service Hsitory");
        reportList.addElement("Service SubHistory");
  /*    reportList.addElement("Report3");
        reportList.addElement("Report4");  */
        comboBox.setModel(reportList);
        comboBox.setSelectedIndex(0);
         comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
              comboBoxIndex =  comboBox.getSelectedIndex();

            //  querySelector(comboBoxIndex =  comboBox.getSelectedIndex()); 
               
               FormEvent ev = new FormEvent(this,comboBoxIndex);
               if (formListener != null){
                   formListener.fromEventoccurred(ev);
               }
               
            }
        });
        
                      setEmptyFramePanelLayout();
 
            }

              public void setFormListener (FormListener listener){
              this.formListener = listener;  
               
            }
    
            
}
