package mob.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


import javax.swing.table.AbstractTableModel;
import mob.model.Information;


/* Displays data on TablePanel also will used to export data */

public class TableModel extends AbstractTableModel{

  	//Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
  

	//CSV file header
    private static final String FILE_HEADER = "ID,Msisdn,Sevice Name,State,"+
                                    " Membership_Date,Unsubscribe_Date";
        
     private final String[] colNames ={"ID","Msisdn" , "Sevice Name", "State" 
                                ,"Membership Date", "Unsubscribe Date"};
     private ArrayList <Information> db;
  

     // number of arrayList rows
     private int dbSize;
  
       public void setData(ArrayList<Information> db){
        this.db = db;
           dbSize = db.size(); 
         
         
    }
   
     
    @Override
    public int getRowCount() {
        return dbSize;
       
    }

    @Override
    public String getColumnName (int column){
        return colNames[column];
    }
    
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    // displayes data after event fireTableEvent occures
   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
               Information info = db.get(rowIndex);
      
               switch(columnIndex){
             case 0:
                return info.getId();
             case 1:
                return info.getMsisdn();
             case 2:
                return info.getServiceName();
             case 3:
                return info.setSubState();
             case 4:
                return info.getMembershipDate();
             case 5:
                return info.getUnsubscribeDate();
          
         }
         return null;

    }

    
     public void savetoFile(String fileName) {
     
     
         FileWriter fileWriter = null;
				
		try {
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
			for (Information info : db) {
                            
				fileWriter.append(String.valueOf(info.getId()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(info.getMsisdn());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(info.getServiceName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(info.setSubState());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(info.getMembershipDate());
                                fileWriter.append(COMMA_DELIMITER);
                                fileWriter.append(info.getUnsubscribeDate());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			
			
			      JOptionPane.showMessageDialog(null, 
                              "File Suscessfully Saved to "+ fileName , 
                              "Save Sucssess", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!! "+e.getMessage() );
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!! "+
                                        e.getMessage());
                                e.printStackTrace();
			}
			
		}
	}

     
 }
    

