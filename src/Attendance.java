import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

public class Attendance extends JFrame 
{
   public static void main(String[] args) 
   {
      EventQueue.invokeLater //post Runnable(interface) in the AWT thread's event queue
         (
         new Runnable() 
         {
            public void run() 
            {
               Attendance form = new Attendance();
               form.setVisible(true);
            }
         });
   }

   public Attendance() 
   {
   
   	// Create Frame
      super("Attendance Program");
      setSize(668, 345);
      setLocation(500, 280);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().setLayout(null);
   
   	// Label Result
      final JLabel lblResult = new JLabel("Access Roster", JLabel.CENTER);
      lblResult.setBounds(150, 22, 370, 14);
      getContentPane().add(lblResult);
   
   	// Data Source
      final CustomModel model = new CustomModel();
   	
   	// Table
      JTable table = new JTable(model);
      getContentPane().add(table);
   	
   	// ScrollPane
      JScrollPane scroll = new JScrollPane(table);
      scroll.setBounds(84, 98, 506, 140); //84, 98, 506, 79 (120)
      getContentPane().add(scroll);
   
   
      JFileChooser fileopen = new JFileChooser(); //allow "Take Attendance" button to access fileopen
      
   	// Create Button "Open File"
      JButton btnButton = new JButton("Open File");
      btnButton.setBounds(268, 47, 135, 23);
      btnButton.addActionListener(
         new ActionListener() 
         {
            public void actionPerformed(ActionEvent e) 
            {
               
               FileFilter filter = new FileNameExtensionFilter("Text/CSV file", "txt", "csv");
               fileopen.addChoosableFileFilter(filter);
            
               int ret = fileopen.showDialog(null, "Choose file");
            
               if (ret == JFileChooser.APPROVE_OPTION) 
               {
               
               // Read Text file
                  File file = fileopen.getSelectedFile();
                  
                  try 
                  {
                     BufferedReader br = new BufferedReader(new FileReader(file));
                     String line;
                     while ((line = br.readLine()) != null) 
                     {
                        String[] arr = line.split(",");
                        model.addRow(arr[0],arr[1],arr[2],arr[3]);
                     }
                     br.close();
                  } 
                  catch (IOException ex) 
                  {
                  // TODO Auto-generated catch block
                  // Help traces the exception
                     ex.printStackTrace();
                  }
               
                  lblResult.setText(fileopen.getSelectedFile().toString());
               }
            
            }
         });
      getContentPane().add(btnButton);
      
      //Create button for taking attendance and picking 3 random people
      JButton attend = new JButton("Take Attendance");
      attend.setBounds(268, 250, 135, 23);
      attend.addActionListener(
         new ActionListener() 
         {
            public void actionPerformed(ActionEvent e) 
            {
               try
               {
                  //boolean for the 3 random students
                  boolean stuAbs1, stuAbs2, stuAbs3;
                  stuAbs1 = false;
                  stuAbs2 = false;
                  stuAbs3 = false;
                  int student1, student2, student3;
                  String Stu1, Stu2, Stu3;
                  ArrayList<ArrayList<Student>> listList = new ArrayList<ArrayList<Student>>();
                  listList = FileProcessor.fileProcessor(fileopen.getSelectedFile().toString());//"C:/Users/joeyt/Documents/NetBeansProjects/Attendance/src/Roster.csv"
                  
                  Stu1 = (listList.get(1).get(0)).getFirstName();
                  Stu2 = (listList.get(1).get(1)).getFirstName();
                  Stu3 = (listList.get(1).get(2)).getFirstName();
               
                  JOptionPane.showMessageDialog(null, "Students picked: \n" + Stu1 + "\n" + Stu2 + "\n" + Stu3, "Pick", JOptionPane.INFORMATION_MESSAGE);
               
                  student1 = JOptionPane.showConfirmDialog(null, "Is " + Stu1 + " here?", "Available?", JOptionPane.YES_NO_OPTION);
                  if(student1 == JOptionPane.NO_OPTION)
                  {
                     stuAbs1 = true;
                  }
                  else
                     stuAbs1 = false;
               
                  student2 = JOptionPane.showConfirmDialog(null, "Is " + Stu2 + " here?", "Available?", JOptionPane.YES_NO_OPTION);
                  if(student2 == JOptionPane.NO_OPTION)
                  {
                     stuAbs2 = true;
                  }
                  else
                     stuAbs2 = false;
               
                  student3 = JOptionPane.showConfirmDialog(null, "Is " + Stu3 + " here?", "Available?", JOptionPane.YES_NO_OPTION);
                  if(student3 == JOptionPane.NO_OPTION)
                  {
                     stuAbs3 = true;
                  }
                  else
                     stuAbs3 = false;
                  
                  FileProcessor.dateAddition(listList, stuAbs1, stuAbs2, stuAbs3,fileopen.getSelectedFile().toString());
               }
               catch (NullPointerException a) //prevent taking attendance without a chosen roster
               {
                  JOptionPane.showMessageDialog(null, "Please choose a file to take attendance", "Pick a Roster", JOptionPane.INFORMATION_MESSAGE);
               }
               
            }
         });
      getContentPane().add(attend);
   
   }

	// Movable Columns (from what I can tell)
   class CustomModel extends AbstractTableModel 
   {
   
      List<Students> dataRow;
      String[] columnHeader = { "Last Name", "First Name"," K-number", "Absence"};
      
   
      public CustomModel() 
      {
         dataRow = new ArrayList<Students>();
      }
   
      @Override
      public String getColumnName(int column) 
      {
         return columnHeader[column];
      }
   
      @Override
      public int getColumnCount() 
      {
         return columnHeader.length;
      }
      
      @Override
      public int getRowCount() 
      {
         return dataRow.size();
      }
   
      @Override
      public Object getValueAt(int rowIndex, int columnIndex) 
      {
         Students student = dataRow.get(rowIndex);
         switch (columnIndex) 
         {
            case 0:
               return student.getLName();
            case 1:
               return student.getFName();
            case 2:
               return student.getKNumber();
            case 3:
               return student.getAbsences();
            default:
               return null;
         }
      }
   
      public void addRow(String LName, String FName, String KNumber, String Absences) 
      {
         dataRow.add(new Students(LName, FName, KNumber, Absences));
         int rowCount = getRowCount();
         fireTableRowsInserted(rowCount, rowCount);
      }
   
   }

}

//Student Class
class Students
{
   private String LName;
   private String FName;
   private String KNumber;
   private String Absences;

   public Students(String sLName, String sFName, String sKNumber, String sAbsences) 
   {
      this.LName = sLName;
      this.FName = sFName;
      this.KNumber = sKNumber;
      this.Absences = sAbsences;
   }

	// Last Name
   public String getLName() 
   {
      return LName;
   }
   public void setLName(String tLName) 
   {
      this.LName = tLName;
   }

	// First Name
   public String getFName() 
   {
      return FName;
   }
   public void setName(String tName) 
   {
      this.FName = tName;
   }

	// K-Number
   public String getKNumber() 
   {
      return KNumber;
   }
   public void setKNumber(String tKNumber) 
   {
      this.KNumber = tKNumber;
   }
   
   // Absences
   public String getAbsences() 
   {
      return Absences;
   }
   public void setAbsences(String tAbsences) 
   {
      this.Absences = tAbsences;
   }
   
}

//                      Works Cited                             //
//https://www.thaicreate.com/java/java-gui-swing-jfilechooser.html