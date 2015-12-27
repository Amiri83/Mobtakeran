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

// Main View controller , 
// All componets in view will be commuincate together from here

public final class MainFrame extends JFrame  {

    private static final long serialVersionUID = 1L;
    

    private  ReportPanel reportPanel;
    private  int comboxIndex ;
    private  JFileChooser fileChooser;
    private  Report0 report0;
    private  Report1 report1 ;
    private  Report2 report2 ;
    private  TablePanel  tablePanel;
    private  CSVFileFilter filter;
  

            
    public MainFrame() throws IOException, SQLException{
      super ("Mobatkeran Reporting System Version R06");
      initComponents();

    }
    

// JMenuBar Creation    
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
        
        //Set Mnominics
        fileMenu.setMnemonic(KeyEvent.VK_F);
        viewMenu.setMnemonic(KeyEvent.VK_V);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exportDataItem.setMnemonic(KeyEvent.VK_E);
        hideForm.setMnemonic(KeyEvent.VK_S);
        
       
        //Set Exit Accelator  
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                                ActionEvent.CTRL_MASK));
        //Set hide Form Accelator  
        hideForm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                ActionEvent.CTRL_MASK));
        
      
     // Exit Item is selected  
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
        
 
     // Hide Form 
        
            hideForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              JCheckBoxMenuItem showHide = (JCheckBoxMenuItem)  ev.getSource();
                reportPanel.setVisible(!(showHide.isSelected()));
            
            }
        });
          
  
   // Export File Gui
            
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
       
      
   // window listener to set exit button 
        
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
        

     // init componets 
    public void initComponents() throws IOException, SQLException{ 
        
       setLayout    (new BorderLayout());
       reportPanel = new ReportPanel();
       report0     = new Report0();
       fileChooser = new JFileChooser();
       report0     = new Report0();
       report1     = new Report1();
       report2     = new Report2();
       tablePanel  = new TablePanel() ;
       filter      = new CSVFileFilter();
    
     
        setJMenuBar(createMenuBar());
        
  // reportPanel Listener used to find which report should be send to ReportPanel
      
          reportPanel.setFormListener(new ReportPanelListener(){
          @Override
          public void reportEventoccurred(ReportPanelEvent e){
                setComboxIndex(e.getComboBoxIndex());
            switch(getComboxIndex()){
                case 1: reportPanel.setFramePanelLayout(getReport0());
                    break;
                case 2: reportPanel.setFramePanelLayout(getReport1());
                    break;
                case 3: reportPanel.setFramePanelLayout(getReport2());
                    break;
           
                default: reportPanel.setFramePanelLayout(getReport0());
            }
          }
      });
   
         
 // Setting main componet on Main Fram
          
      addWindowListener(exitListener);     
      setDefaultCloseOperation(MainFrame.DO_NOTHING_ON_CLOSE);
      setSize(1200,650);
      setLocation(100, 100);
      add(tablePanel,BorderLayout.CENTER);
      add(reportPanel,BorderLayout.WEST);
      setVisible(true);
        
        }

    
    public Report0 getReport0() {
        return report0;
    }

    public Report1 getReport1() {
        return report1;
    }

    public Report2 getReport2() {
        return report2;
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

