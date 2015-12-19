package mob.view;


import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;




public class ReportPanel1 extends JPanel {

    private  JLabel msisdnLable, serviceLable;
    private  JFormattedTextField msisdnTextFeild;
    private  JButton querybtn;
    private  JComboBox serviceListCombobox;


    


 
    public ReportPanel1() throws IOException, SQLException{
      
        setLayout(new GridLayout(18, 0,15,5));
        msisdnLable = new JLabel("MSISDN");
        serviceLable = new JLabel("Service");
        querybtn    = new JButton("Query");
        serviceListCombobox =  new JComboBox();
  
        
      
        ////// Formatted text //////
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.setGroupingUsed(false);
        msisdnTextFeild = new JFormattedTextField(decimalFormat);
        msisdnTextFeild.setColumns(12); 
        msisdnTextFeild.setToolTipText("lenght Should be "
                                  + "at least 12 like 989209202207");
        
         //////////////////////////////
        
        Border innerBorder = BorderFactory.createTitledBorder("Query Items");
        Border outterBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outterBorder));
        querybtn.setName("qbtn1");
        //////////////////////////////////////////////
        add(msisdnLable);
        add(msisdnTextFeild);
        add(serviceLable);
        add(serviceListCombobox); // <<====== adding combo box 
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
                return (msisdnTextFeild.getText());
            }
         
            

            public JComboBox getServiceListCombobox() {
                return serviceListCombobox;
            }
       
            
 }

