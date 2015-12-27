
package mob;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mob.model.Information;
import mob.controller.Controller;
import javax.swing.SwingUtilities;
import mob.view.MainFrame;


/*
      Main App program  Based on MVC pattern 
      to test program you can use this numbers : 
      989123154826  989123662860
*/

public class App {

    public static void main(String[] args)  throws Exception{
       
        SwingUtilities.invokeLater(new Runnable() {
          
//           inner Class local variables 
           MainFrame  mainframe   = null; 
           Controller controller  = null;   
          
            @Override
            public void run() {
                  
                try {
                    
                  
                    Information info = new Information();
                    mainframe = new  MainFrame();
                    controller = new Controller(info,mainframe);   
                    controller.contol(); 

                    
                } catch (IOException | SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),
                                "ERROR",JOptionPane.ERROR_MESSAGE );
                   
                }

            }
        });
    }
    
}
