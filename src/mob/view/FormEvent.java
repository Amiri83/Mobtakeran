package mob.view;

import java.util.EventObject;

public class FormEvent extends EventObject{
    
   private int comboBoxIndex,serviceList;
   private String msisdn;
  
   
    public FormEvent(Object source) {
        super(source);
    }

    
    public FormEvent(Object source,String msisdn) {
        super(source);
        this.msisdn = msisdn;
       
    }
    
    
    
    public FormEvent(Object source,int comboBoxIndex ) {
        super(source);
                this.comboBoxIndex=comboBoxIndex;
    }


    public FormEvent(Object source,String msisdn,int comboBoxIndex , int  serviceList) {
        super(source);
                this.comboBoxIndex=comboBoxIndex;
                this.serviceList = serviceList;
                this.msisdn = msisdn;
    }

    public int getComboBoxIndex() {
        return comboBoxIndex;
    }

  
    public void setComboBoxIndex(int comboBoxIndex) {
        this.comboBoxIndex = comboBoxIndex;
    }

 
    public String getMsisdn() {
        return msisdn;
    }

   
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the serviceList
     */
    public int getServiceList() {
        return serviceList;
    }

    /**
     * @param serviceList the serviceList to set
     */
    public void setServiceList(int serviceList) {
        this.serviceList = serviceList;
    }

}
