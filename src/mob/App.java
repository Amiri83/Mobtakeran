
package mob;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mob.model.Information;
import mob.controller.Controller;
import javax.swing.SwingUtilities;
import mob.view.MainFrame;

///989123154826  989123662860
//R06

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
          MainFrame mainframe = null; 
          
            @Override
            public void run() {
             
                try {
                    mainframe = new  MainFrame();
                } catch (IOException ex) {
                 //   System.out.println("Io Error "+ ex.getMessage());
                  JOptionPane.showMessageDialog(null,ex.getMessage(),
                          "IO ERROR",JOptionPane.ERROR_MESSAGE );
                } catch (SQLException ex) {
                  //  System.out.println("SQL Error "+ ex.getMessage());
                   JOptionPane.showMessageDialog(null,ex.getMessage(),
                           "SQL ERROR",JOptionPane.ERROR_MESSAGE );
              }
                Information info = new Information(); 
                Controller controller = null;
              try {
                  controller = new Controller(info,mainframe);
              } catch (IOException ex) {
                 //  System.out.println("Io Error! "+ ex.getMessage());
                    JOptionPane.showMessageDialog(null,ex.getMessage(),
                          "IO ERROR",JOptionPane.ERROR_MESSAGE );
                 
              } catch (SQLException ex) {
                //  System.out.println("SQL Error! "+ ex.getMessage());
                 JOptionPane.showMessageDialog(null,ex.getMessage(),
                         "SQL ERROR",JOptionPane.ERROR_MESSAGE );
              }
                
              controller.contol();
            
            }
        });
    }
    
}
