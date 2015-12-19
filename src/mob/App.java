
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
          MainFrame mainframe = null; 
          
            @Override
            public void run() {
             
                try {
                    mainframe = new  MainFrame(); // <<-- View  Object
                } catch (IOException ex) {
              
                  JOptionPane.showMessageDialog(null,ex.getMessage(),
                          "IO ERROR",JOptionPane.ERROR_MESSAGE );
                } catch (SQLException ex) {
          
                   JOptionPane.showMessageDialog(null,ex.getMessage(),
                           "SQL ERROR",JOptionPane.ERROR_MESSAGE );
              }
                Information info = new Information();  // <<-- Model Object
                Controller controller = null;         //  <<-- Controller Object
              try {
                  controller = new Controller(info,mainframe);     // <<-- passing object
              } catch (IOException ex) {
               
                    JOptionPane.showMessageDialog(null,ex.getMessage(),
                          "IO ERROR",JOptionPane.ERROR_MESSAGE );
                 
              } catch (SQLException ex) {
             
                 JOptionPane.showMessageDialog(null,ex.getMessage(),
                         "SQL ERROR",JOptionPane.ERROR_MESSAGE );
              }
                
              controller.contol();  // <<-- Controller perfomes controll

            }
        });
    }
    
}
