package mob.model;

import java.io.Serializable;


// Databse model for information to be displayed 
public class Information implements Serializable{  
    public static int count = 0;
    
    private String msisdn,membershipDate,unsubscribeDate,serviceName, subState;
 
    private int serviceCode,id;

    
    public Information(String msisdn , String serviceName ,String subState,
                        String membershipDate , String unsubscribeDate){
    
        this.serviceName = serviceName;
        this.subState = subState;
        this.msisdn = msisdn;
        this.membershipDate = membershipDate;
        this.unsubscribeDate = unsubscribeDate;
        
        this.id = count;
        count ++;
    
    }
    
        public Information(int id,String msisdn , String serviceName ,String subState,
                        String membershipDate , String unsubscribeDate){
    
        this(msisdn,serviceName,subState,membershipDate,unsubscribeDate);
        
        this.id = id;
       
    
    }

    public Information() {
    }

 
   
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getUnsubscribeDate() {
        return unsubscribeDate;
    }

    public void setUnsubscribeDate(String unsubscribeDate) {
        this.unsubscribeDate = unsubscribeDate;
    }

    public String setSubState() {
        return subState;
    }

    public void setSubState(String subState) {
        this.subState = subState;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

  
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public void resetCounter(int count){
    
        this.count = count;
    }
   
    
    // this is used for CSV output to convert All numbers to String 
     public String toString() {

	        return " id: " + id + ", Msisdn= " + msisdn
	                + ", ServiceName: " + serviceName + ", Status: " 
                        + subState 
                        + ", MembrShip Date: "+ membershipDate 
                        + ", UnSubscribe Date: "+ unsubscribeDate;

	    }
}