package mob.view;

import java.util.EventObject;

/* custom Event lsitenet to let Mainfram know which Report should be put 
    to ReportPanel
*/
public class ReportPanelEvent extends EventObject{

    private static final long serialVersionUID = 1L;
    
   private int comboBoxIndex; 
  
   
    public ReportPanelEvent(Object source) {
        super(source);
    }

    
    
    public ReportPanelEvent(Object source,int comboBoxIndex ) {
        super(source);
                this.comboBoxIndex=comboBoxIndex;
    }


    public int getComboBoxIndex() {
        return comboBoxIndex;
    }

  
    public void setComboBoxIndex(int comboBoxIndex) {
        this.comboBoxIndex = comboBoxIndex;
    }



}
