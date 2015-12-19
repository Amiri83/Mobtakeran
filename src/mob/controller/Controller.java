
package mob.controller;

import mob.model.Database;
import mob.view.MainFrame;
import mob.model.ReportList;
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
    private ArrayList<ReportList> reportListArray;  
    

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
    
    main control function 
    we do it in 3 setps based on Action event object we already add to reports
    (QueryPanel1,QueryPanel0)
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
                   
                      
                  } catch (SQLException ex) {
                      
                      System.out.println("Sql Error "+ ex.getMessage());
                   } catch (IOException ex) {
                      System.out.println("IO Error "+ ex.getMessage());
                  }
                       displayData();
              }
        };           
              
   // here we add action listener to QueryPanels in view           
     mainFrame.getQueryPanel0().getQuerybtn().addActionListener(actionListener);
     mainFrame.getQueryPanel1().getQuerybtn().addActionListener(actionListener);
 
    
    }
    
    /* Determines wich report is selected to do validation based on selected report*/
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
     
      if ((mainFrame.getQueryPanel0().getMsisdn()).length() < 12){
          mainFrame.getQueryPanel0().setError();
          this.msisdn = null;
      } 
      else {
          
          msisdn = mainFrame.getQueryPanel0().getMsisdn();
          mainFrame.getQueryPanel0().setOK();
          info.resetCounter(1);
          infoArrayList.clear();
          mainFrame.getTablePanel().refresh();
          database.queryReport0(msisdn , info,infoArrayList);
          
      }
      
    }
      
      /*second report validation*/
    private void validate1() throws SQLException, IOException{
              
      if ((mainFrame.getQueryPanel1().getMsisdn()).length() < 12){
           mainFrame.getQueryPanel1().setError();
           msisdn = null;
          
      } 
      else { 
          
                   
         
          ReportList[] reportList = new ReportList[reportListArray.size()];
          reportList = reportListArray.toArray(reportList);
          ServiceCode = Integer.parseInt(reportList[mainFrame.getQueryPanel1().getServiceListCombobox().getSelectedIndex()].getServiceCode());
          msisdn = mainFrame.getQueryPanel1().getMsisdn();
          mainFrame.getQueryPanel1().setOK();
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
        ReportList[] reportList = new ReportList[reportListArray.size()];
        reportList = reportListArray.toArray(reportList);
     
            for(ReportList r : reportList){
            
                mainFrame.getQueryPanel1().getServiceListCombobox().addItem(r.getServiceName());
                
                 }
            
                 mainFrame.getQueryPanel1().getServiceListCombobox().setSelectedIndex(0);
          
        
        }
        
        
}