
package mob.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;





public class Database {
  
    
  private String url , username ,password ,dbdriver ,sql , ServiceName , 
                 MembershipDate , UnSubscribeDate , Msisdn ,SubStatus,
                  reportServiceName , ServiceCode  ;
   
  
  private ComboBoxList comboBoxList;
  private ArrayList<Information> infoArrayList;
  private ArrayList<ComboBoxList> reportListArray;
  private static final String DB_FILEـNAME="mob_db.conf";
  private static final String DB_FILE_LOCATION="./";

  
  
  
  //////////////////////// create instance with Db info ///////////////////////
  public Database() throws IOException {

  getDatabaseInfo();
  
   }
  
 // Reads Db info 
  private void getDatabaseInfo() throws IOException {
        
        Properties props = new Properties();
       
        FileInputStream in = null;
        try {
            in = new FileInputStream(DB_FILE_LOCATION+DB_FILEـNAME);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Database Connection file " 
                    +"'"+DB_FILEـNAME+"'"+ " could not be found at "+"'"
                    +DB_FILE_LOCATION +"'","Error",JOptionPane.ERROR_MESSAGE);
            
        }
  
           props.load(in);
           in.close();

            this.url = props.getProperty("dburl");
            this.username = props.getProperty("dbuser");
            this.password = props.getProperty("dbpassword");
            this.dbdriver = props.getProperty("driver");
          
           
            }
  
  
    // Querys Report1
    public void queryReport1(String  msisdn, int  ServiceCode ,  Information info 
                              ,ArrayList<Information> infoArrayList
                            ) throws SQLException, IOException {
                this.infoArrayList = infoArrayList;
         sql = "select distinct a.MSISDN ,b.ServiceName,a.membershipdate," + 
                " a.unsubscribeDate,a.SubStatus from serviceusers a, services b " + 
                 " where a.ServiceCode = b.ServiceCode " + " and a.MSISDN =" + 
                 "'" + msisdn + "'" 
                 + "and a.ServiceCode = " + ServiceCode;
      
        
        try (final Connection con = DriverManager.getConnection(getUrl(), getUsername(), getPassword())) {
            try (final Statement stmt = con.createStatement();
                 final ResultSet rs = stmt.executeQuery(sql)) {
                if (msisdn != null) {
                    while (rs.next()) {
                         setServiceName(rs.getString("ServiceName"));
                        int SubStatus0 = rs.getInt("SubStatus");
                        if (SubStatus0 == 1) {
                            setSubStatus("Active");                  
                        } else {
                             setSubStatus("Deactive");
                            
                        }
                       
                         setMembershipDate(rs.getString("MembershipDate"));
                         setUnSubscribeDate(rs.getString("UnSubscribeDate"));
                         setServiceName(rs.getString("ServiceName"));
                         setMsisdn(rs.getString("msisdn"));
                         info = new Information(getMsisdn(), getServiceName(), getSubStatus(), getMembershipDate(), getUnSubscribeDate());
                         infoArrayList.add(info);
                    }
                }
                rs.close();
                stmt.close();
                con.close();
            }
        }
    }

    
    // Querys Report0
    public void queryReport0(String  msisdn , Information info ,
                             ArrayList<Information> infoArrayList) throws SQLException, IOException {
        
              this.infoArrayList = infoArrayList;
         sql = "select distinct a.MSISDN ,b.ServiceName,a.membershipdate," + 
               " a.unsubscribeDate,a.SubStatus from serviceusers a, services b " +
               " where a.ServiceCode = b.ServiceCode and a.SubStatus = 1 " + 
               " and a.MSISDN =" + "'" + msisdn + "'";
    
        try (final Connection con = DriverManager.getConnection(getUrl(), getUsername(), getPassword())) {
            try (final Statement stmt = con.createStatement();
                 final ResultSet rs = stmt.executeQuery(sql)) {
                if (msisdn != null) {
                    while (rs.next()) {
                         setSubStatus("Active");
                         setUnSubscribeDate(" Null ");
                         setServiceName(rs.getString("ServiceName"));
                         setMembershipDate(rs.getString("MembershipDate"));
                         setMsisdn(rs.getString("msisdn"));
                         info = new Information(getMsisdn(), getServiceName(), getSubStatus(), getMembershipDate(), getUnSubscribeDate());
                         infoArrayList.add(info);
                     
                    }
                }
                rs.close();
                stmt.close();
                con.close();
            }
        }
    }
    
    //Gets content of Combox for Report1 form view repot_v in DB
    
    public void  getReportList(ArrayList<ComboBoxList> reportListArray) throws SQLException ,IOException{
                       
                      this.reportListArray = reportListArray;
                      
         sql = "select distinct servicename,servicecode from report_v" ;
         
          try (final Connection con = DriverManager.getConnection(getUrl(), getUsername(), getPassword())) {
            try (final Statement stmt = con.createStatement();
                 final ResultSet rs = stmt.executeQuery(sql)) {                
                    while (rs.next()) {
                        
                        setReportServiceName(rs.getString("servicename"));
                        setServiceCode(rs.getString("servicecode"));
                         comboBoxList = new ComboBoxList(getReportServiceName(), getServiceCode());
                         getReportListArray().add(comboBoxList);   
                       //  System.out.println("service name " + getReportServiceName()+ getServiceCode() );
                    }
                
                rs.close();
                stmt.close();
                con.close();
            }
        }
    
    
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDbdriver() {
        return dbdriver;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public String getMembershipDate() {
        return MembershipDate;
    }

    public String getUnSubscribeDate() {
        return UnSubscribeDate;
    }

    public String getMsisdn() {
        return Msisdn;
    }

    private void setSubStatus(String SubStatus) {
        this.SubStatus = SubStatus;
    }

    private void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    private void setMembershipDate(String MembershipDate) {
        this.MembershipDate = MembershipDate;
    }

    private void setUnSubscribeDate(String UnSubscribeDate) {
        this.UnSubscribeDate = UnSubscribeDate;
    }

    private void setMsisdn(String Msisdn) {
        this.Msisdn = Msisdn;
    }

    public String getSubStatus() {
        return SubStatus;
    }

    public ArrayList<Information> getInfoArrayList() {
        return infoArrayList;
    }

    private void setInfoArrayList(ArrayList<Information> infoArrayList) {
        this.infoArrayList = infoArrayList;
    }

    public String getReportServiceName() {
        return reportServiceName;
    }

    public void setReportServiceName(String reportServiceName) {
        this.reportServiceName = reportServiceName;
    }

    public String getServiceCode() {
        return ServiceCode;
    }

    public void setServiceCode(String ServiceCode) {
        this.ServiceCode = ServiceCode;
    }

    public ArrayList<ComboBoxList> getReportListArray() {
        return reportListArray;
    }





}

