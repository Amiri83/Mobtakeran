package mob.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;





public class MainFrame extends JFrame  {
    

    private  FormPanel formPanel;
    private  int comboxIndex ;
    private  JFileChooser fileChooser;
    private  QueryPanel0 queryPanel0;
    private  QueryPanel1 queryPanel1 ;
    private  QueryPanel2 queryPanel2 ;
    private  TablePanel  tablePanel;
    private  InfoFileFilter filter;
  

            
    public MainFrame() throws IOException, SQLException{
      super ("Mobatkeran Reporting System Version R06");
      initComponents();

    }
    
  /////////////////// Menu Bar ////////////////////////////////////////////////
    
        private JMenuBar createMenuBar (){
        
     
        JMenuBar menuBar = new JMenuBar();
        Dimension dim = getPreferredSize();
        dim.height = 25;
        menuBar.setPreferredSize(dim);
        JMenu fileMenu = new JMenu("File");
        JMenu viewMenu = new JMenu("View");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem exportDataItem = new JMenuItem("Export Data..");
        JCheckBoxMenuItem hideForm = new JCheckBoxMenuItem ("Hide form");
        fileMenu.add(exportDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        viewMenu.add(hideForm);
        menuBar.add(fileMenu);
        menuBar.add (viewMenu);
 
        fileMenu.setMnemonic(KeyEvent.VK_F);
        viewMenu.setMnemonic(KeyEvent.VK_V);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exportDataItem.setMnemonic(KeyEvent.VK_E);
        hideForm.setMnemonic(KeyEvent.VK_S);
        
       
        //Set Exit Accelator ////// you can set accleator only to Jmenu
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                                ActionEvent.CTRL_MASK));
        hideForm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                ActionEvent.CTRL_MASK));
        
        ///// action listener for reprotList ///////////////////
        ///////// exit /////////////////////////
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int action= JOptionPane.showConfirmDialog(MainFrame.this, 
                                            "Are You Sure to Close Application? ", 
                                            "Exit Confirmation", 
                                            JOptionPane.YES_NO_OPTION);
                
                if (action == JOptionPane.YES_OPTION){
                System.exit(0);
                }
            }
        });
        
 
     /////////////////////////////// Hide Form ////////////////////////////////
        
            hideForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              JCheckBoxMenuItem showHide = (JCheckBoxMenuItem)  ev.getSource();
                formPanel.setVisible(!(showHide.isSelected()));
            
            }
        });
          
  
   //////////////////////////////////////// Export File ///////////////////////
            
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.setFileFilter(filter);
            exportDataItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               if( fileChooser.showSaveDialog(MainFrame.this) == 
                   JFileChooser.APPROVE_OPTION ) {
                  
                   tablePanel.getTableModel().savetoFile(fileChooser
                             .getSelectedFile().toString());
                  
               
               }
            }
        });
        return menuBar;
         }
       
      
     //////////////////// window listener to set exit button //////////////////  
        
    WindowListener exitListener = new WindowAdapter() {

    @Override
    public void windowClosing(WindowEvent ew) {
        int action=    JOptionPane.showConfirmDialog(MainFrame.this, 
                                         "Are You Sure to Close Application? ", 
                                         "Exit Confirmation", 
                                         JOptionPane.YES_NO_OPTION);
        if (action == JOptionPane.YES_OPTION) {
           System.exit(0);
        }
    }
    };
        
    
     ///////////////////////////////////////////////////////////////////////
    
    
     ///////////////////// init componets /////////////////////////////////// 
        
    public void initComponents() throws IOException, SQLException{ 
        
       setLayout    (new BorderLayout());
   
       formPanel   = new FormPanel();
       queryPanel0 = new QueryPanel0();
       fileChooser = new JFileChooser();
       queryPanel0 = new QueryPanel0();
       queryPanel1 = new QueryPanel1();
       queryPanel2 = new QueryPanel2();
       tablePanel  = new TablePanel() ;
       filter      = new InfoFileFilter();
    
     
        setJMenuBar(createMenuBar());
        
    /////////////////////////// Form panel Listener ///////////////////////////    
      
          formPanel.setFormListener(new FormListener(){
          @Override
          public void fromEventoccurred(FormEvent e){
                setComboxIndex(e.getComboBoxIndex());
            switch(getComboxIndex()){
                case 1: formPanel.setFramePanelLayout(getQueryPanel0());
                    break;
                case 2: formPanel.setFramePanelLayout(getQueryPanel1());
                    break;
                case 3: formPanel.setFramePanelLayout(getQueryPanel2());
                    break;
           
                default: formPanel.setFramePanelLayout(getQueryPanel0());
            }
          }
      });
   
         
    /////////////////////// Windows listener /////////////////////////////////
          
      addWindowListener(exitListener);     
      setDefaultCloseOperation(MainFrame.DO_NOTHING_ON_CLOSE);
      setSize(1200,650);
      setLocation(100, 100);
      add(tablePanel,BorderLayout.CENTER);
      add(formPanel,BorderLayout.WEST);
      setVisible(true);
        
        }
    
  /////////////////////// Setter And getters /////////////////////////////////  
    
    public QueryPanel0 getQueryPanel0() {
        return queryPanel0;
    }

    public QueryPanel1 getQueryPanel1() {
        return queryPanel1;
    }

    public QueryPanel2 getQueryPanel2() {
        return queryPanel2;
    }

    public TablePanel getTablePanel (){
        return tablePanel;
    
    } 
     
    public int getComboxIndex() {
        return comboxIndex;
    }

    private void setComboxIndex(int comboxIndex) {
        this.comboxIndex = comboxIndex;
    }

    

}

