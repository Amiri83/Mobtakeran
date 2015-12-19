
package mob.controller;

import mob.model.Database;
import mob.view.MainFrame;
import mob.model.ComboBoxList;
import mob.model.Information;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class Controller {

    private Information info;          
    private ArrayList<Information> infoArrayList; 
    private MainFrame mainFrame;
    private String msisdn;
    private int reportNumber,ServiceCode;
    private ActionListener actionListener;
    private String SubStatus;
    private Database database;
    private ArrayList<ComboBoxList> reportListArray;  
    

    /* 
    
       Controller method Constructor , in addintion to get view(mainFarme) and 
       Model (Information) objects it will create Database instance 
       used to connect and query Db  and an ArrayList of model get and store
       model info .
    
    */
    public Controller(Information info,MainFrame mainFrame ) throws IOException, SQLException{
        
      
        this.info = info;
        this.mainFrame = mainFrame;
        infoArrayList = new ArrayList<>();
        database  = new Database();
        fillComboBox();
      
    }
    
    /*
    
    main control is done in 3 steps 
    1- check validtiy of data based on selected report
    2- set data in ArrayList
    3- Dispaly Array List in Jtable 
    */
   
    public void contol ()  {
        
           

     
              actionListener = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent actionEvent) {
            
                  try {
                    
                      validateData();
                      setData();
                      displayData();
                      
                  } catch (SQLException  | IOException ex) {
                      
                      System.out.println(" Error "+ ex.getMessage());
                   }
                      
              }
        };           
     
     // here we add action listener to Reports in view              
     mainFrame.getReport0().getQuerybtn().addActionListener(actionListener);
     mainFrame.getReport1().getQuerybtn().addActionListener(actionListener);
 
    
    }
    
    /* Determines which report is selected to do validation */
    private void validateData() throws SQLException, IOException{
       reportNumber =mainFrame.getComboxIndex();
       switch (reportNumber){
           case 1 :  validate0();
               break;
           case 2:  validate1();
               break;
           case 3:
               break;            
       }
    }
    
    
    /*first report validation*/
    private void validate0() throws SQLException, IOException{
     
      if ((mainFrame.getReport0().getMsisdn()).length() < 12){
          mainFrame.getReport0().setError();
          this.msisdn = null;
      } 
      else {
          
          msisdn = mainFrame.getReport0().getMsisdn();
          mainFrame.getReport0().setOK();
          info.resetCounter(1);
          infoArrayList.clear();
          mainFrame.getTablePanel().refresh();
          database.queryReport0(msisdn , info,infoArrayList);
          
      }
      
    }
      
      /*second report validation*/
    private void validate1() throws SQLException, IOException{
              
      if ((mainFrame.getReport1().getMsisdn()).length() < 12){
           mainFrame.getReport1().setError();
           msisdn = null;
          
      } 
      else { 
          
                   
         
          ComboBoxList[] reportList = new ComboBoxList[reportListArray.size()];
          reportList = reportListArray.toArray(reportList);
          ServiceCode = Integer.parseInt(reportList[mainFrame.getReport1().getServiceListCombobox().getSelectedIndex()].getServiceCode());
          msisdn = mainFrame.getReport1().getMsisdn();
          mainFrame.getReport1().setOK();
          info.resetCounter(1);
          infoArrayList.clear();
          mainFrame.getTablePanel().refresh();
          database.queryReport1(msisdn,ServiceCode,info,infoArrayList);
         
            
      }
      
    } 
  
       /* Fill array list*/
        private void setData() throws SQLException{
           if (msisdn != null) {
               infoArrayList = database.getInfoArrayList();
               
           }
        }

        /*
         Displays data in Jtabale
         
        */
        
        private void displayData(){
          mainFrame.getTablePanel().getTableModel().setData(infoArrayList);
          mainFrame.getTablePanel().refresh();
        
        }

       
        
        /*
        
        This will fill combo box valus used in QueryPanel1 
        
        */

        private void fillComboBox() throws SQLException, IOException{
    
        reportListArray = new ArrayList<>();
        database.getReportList( reportListArray );
        ComboBoxList[] reportList = new ComboBoxList[reportListArray.size()];
        reportList = reportListArray.toArray(reportList);
     
            for(ComboBoxList r : reportList){
            
                mainFrame.getReport1().getServiceListCombobox().addItem(r.getServiceName());
                
                 }
            
                 mainFrame.getReport1().getServiceListCombobox().setSelectedIndex(0);
          
        
        }
        
        
}