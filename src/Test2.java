/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joeyt
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.util.regex.Pattern;


public class Test2 {

   public static void main(String[] args) {
      String fLocation = "C:\\\\Users\\\\joeyt\\\\Documents\\\\NetBeansProjects\\\\Attendance\\\\src\\\\TestWrite2.csv";
      ArrayList<Student> stuList = new ArrayList<Student>();
      ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
      dates.add(LocalDateTime.now());
      
      stuList.add(new Student("k000000", "fname","lname",1,dates));
      
      fileWriterTest(fLocation,stuList);
   }
   
   
   public static boolean fileWriterTest(String fLocation, ArrayList<Student> stuList){
        //String fileName = "TestWrite.csv";
        //String fLocation = "C:/Users/joeyt/Documents/NetBeansProjects/Attendance/src/TestWrite.csv";
      
      boolean validWrite = true;
      String string = String.format("\\\\");
      String[] parts = fLocation.split(string);
      fLocation = parts[0];
      for(int i=1;i<parts.length-1;i++)
      {
         fLocation = fLocation + "/" + parts[i];
      }
      
      File old = new File(fLocation);
      old.delete();
      String fNewLocation = fLocation + "/New.csv";
      File fNew = new File(fNewLocation);
      System.out.print(stuList.size());
      while(stuList.size()!=0){
      
         try{
            int i = 0;
            FileWriter writer = new FileWriter(fNew, true);
            BufferedWriter bw = new BufferedWriter(writer);
            String str = stuList.get(i).getkNum() + "," + stuList.get(i).getFirstName() + "," + stuList.get(i).getLastName() + "," + stuList.get(i).getTotalAbs(); 
            for(int j=0; j<stuList.get(i).getTotalAbs();j++)
            {
               str = str + "," + stuList.get(i).getDateOfAbs().get(j).toString();
            }
            stuList.remove(i);
            bw.write(str);
            bw.write("\n");
            bw.close();
            
            
            System.out.print("Write done");
         }catch(IOException exptn){
            exptn.printStackTrace();
            validWrite = false;
            
         }
         
      }
      
      return validWrite;
   }

}
