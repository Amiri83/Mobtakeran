package mob.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Report0 extends JPanel {

    private  JLabel msisdnLable;
    private  JFormattedTextField msisdnTextFeild;
    private  JButton querybtn;
    private  String  msisdn;

  

    public Report0(){
        initComponents();
    }
          
     
   
   public void initComponents(){
       
        setLayout(new GridLayout(18, 0,15,5));
        msisdnLable = new JLabel("MSISDN");
        querybtn    = new JButton("Query");
        
  
       
        ////// Formatted text //////
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.setGroupingUsed(false);
        msisdnTextFeild = new JFormattedTextField(decimalFormat);
        msisdnTextFeild.setColumns(12); 
        msisdnTextFeild.setToolTipText("lenght Should be "
                                  + "at least 12 like 989209202207");
         ///////////////////////////////
        
        Border innerBorder = BorderFactory.createTitledBorder("Query Items");
        Border outterBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outterBorder));
        
        ///////////////////////////////////////////
        
        add(msisdnLable);
        add(msisdnTextFeild);
        add (new JLabel(""));
        add(querybtn);
       
        
   }
   
          public JButton getQuerybtn(){
                return querybtn;
          }

            public void setError(){
               msisdnTextFeild.setBackground(Color.red);
               msisdnTextFeild.setText("");

           }

            public void setOK(){
               msisdnTextFeild.setBackground(Color.white);
               msisdnTextFeild.setText("");

            }

            public String getMsisdn() {
                return (msisdn = msisdnTextFeild.getText());
            }

           
                
            
}