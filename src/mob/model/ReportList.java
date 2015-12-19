

package mob.model;
public class ReportList {
  
    // Data model used to exctarct combox Items from DB
    
    private String serviceName,serviceCode,reportID;
    
    
    
    public ReportList(String serviceName , String serviceCode ){
    
       this.serviceName = serviceName;
       this.serviceCode = serviceCode;
       this.reportID = reportID;
    
    }

  

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
}
