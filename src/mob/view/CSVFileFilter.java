

package mob.view;

import java.io.File;
import javax.swing.filechooser.FileFilter;

// extion file filter for CSV file 

public class CSVFileFilter extends FileFilter  {

    @Override
    public boolean accept(File file) {
        String name = file.getName();
        
        String extension = CVSFileUtiles.getFileExtension(name);
        
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
