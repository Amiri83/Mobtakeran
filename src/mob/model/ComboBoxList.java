

package mob.model;
public class ComboBoxList {
  
 // Data model used to exctarct combox Items from DB
    
    private String serviceName,serviceCode;
    
    
    
    public ComboBoxList(String serviceName , String serviceCode ){
    
       this.serviceName = serviceName;
       this.serviceCode = serviceCode;
      
    
    }

  
    //get ServiceName for combo
    public String getServiceName() {
        return serviceName;
    }
   //set ServiceName  for combo
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    //get ServiceCode for combo
    public String getServiceCode() {
        return serviceCode;
    }
    //set ServiceCode for combo 
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
}
