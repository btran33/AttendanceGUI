import java.time.LocalDateTime;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Damien Love
 */
public class Student 
{//start class Student
   private String kNum;
   private String fName, lName;
   private int totalAbs;
   private ArrayList<LocalDateTime> dateOfAbs = new ArrayList<LocalDateTime>();
   private LocalDateTime current;
    /*
    constructors
    */

    /**
     *
     * @param kNum       Represents the student's K number.
     * @param name          Represents the student's name.
     * @param totalAbs      Represents the number of times the student was absent.
     * @param dates         Lists all days the student was absent.
     */

   public Student(String kNum, String fName, String lName, int totalAbs, 
            ArrayList<LocalDateTime> dates) 
   {
      this.kNum = kNum;
      this.fName = fName;
      this.lName = lName;
      this.totalAbs = totalAbs;
      int year, month, dayOfMonth, hour, minute, second, nanosecond;
      for(int i = 0; i < dates.size(); i++)
      {
         year = dates.get(i).getYear();
         month = dates.get(i).getMonthValue();
         dayOfMonth = dates.get(i).getDayOfMonth();
         hour = dates.get(i).getHour();
         minute = dates.get(i).getMinute();
         second = dates.get(i).getSecond();
         nanosecond = dates.get(i).getNano();
         this.dateOfAbs.add(LocalDateTime.of(year, month, dayOfMonth, hour, 
               minute, second, nanosecond));
      }
   }
   public Student(String kNum, String fName, String lName) 
   {
      this.kNum = kNum;
      this.fName = fName;
      this.lName = lName;
      this.totalAbs = 0;
      dateOfAbs = new ArrayList<LocalDateTime>();
   }
    /*
    accessors
    */

    /**
     *
     * @return  Returns the student's K number.
     */

   public String getkNum() 
   {
      return kNum;
   }

    /**
     *
     * @return  Returns the student's first name.
     */
   public String getFirstName() 
   {
      return fName;
   }
    /**
     *
     * @return  Returns the student's last name.
     */
   public String getLastName() 
   {
      return lName;
   }

    /**
     *
     * @return  Returns the total number of absences for the student.
     */
   public int getTotalAbs() 
   {
      return totalAbs;
   }

    /**
     *
     * @return  Returns the dates during which the student was absent.
     */
   public ArrayList<LocalDateTime> getDateOfAbs() 
   {
      return dateOfAbs;
   }
   public void addDateOfAbs()
   {
      
      

      this.dateOfAbs.add(LocalDateTime.now());
   }    
   public void addAbsence()
   {
      this.totalAbs = totalAbs + 1;
   }
    /*
    toString method
    */
   @Override
    public String toString() {
      String strOut;
      strOut = "kNumber = " + kNum + ", First Name = " + fName + ", Last Name = " + lName + ", Absences = " + totalAbs + ", Date(s) of Absences = ";
      for (int i = 0; i < dateOfAbs.size(); i++)
      {
         if (i != totalAbs - 1)
            strOut = strOut + dateOfAbs.get(i).toString() + ", ";
         else
            strOut = strOut + dateOfAbs.get(i).toString();
      }
      if (dateOfAbs.size() == 0)
      {
         strOut = strOut + "No days absent.";
      }
      return strOut;
   }
}//end class Student