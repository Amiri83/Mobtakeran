

package mob.view;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class InfoFileFilter extends FileFilter  {

    @Override
    public boolean accept(File file) {
        String name = file.getName();
        
        String extension = Utiles.getFileExtension(name);
        
        if (extension == null) {
        return false;
        }
        if (extension.equals("csv")){
        return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Comma Separated Values file (*.csv)";
    }
    

}