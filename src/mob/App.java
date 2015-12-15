
package mob;

import java.io.IOException;
import java.sql.SQLException;
import mob.model.Information;
import mob.controller.Controller;
import javax.swing.SwingUtilities;
import mob.view.MainFrame;

///989123154826  989123662860


public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
          MainFrame mainframe = null; 
          
            @Override
            public void run() {
             
                try {
                    mainframe = new  MainFrame();
                } catch (IOException ex) {
                    System.out.println("Io Error "+ ex.getMessage());
                } catch (SQLException ex) {
                    System.out.println("SQL Error "+ ex.getMessage());
              }
                Information info = new Information(); 
                Controller controller = null;
              try {
                  controller = new Controller(info,mainframe);
              } catch (IOException ex) {
                   System.out.println("Io Error "+ ex.getMessage());
              } catch (SQLException ex) {
                  System.out.println("SQL Error "+ ex.getMessage());
              }
                
              controller.contol();

            }
        });
    }
    
}
