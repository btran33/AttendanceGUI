
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joeyt
 */
public class FileProcessor {//begin class

   public static ArrayList<String> lines = new ArrayList<String>();
   public static void main(String[] args)//this is a main method to test the method
   {
     //  String file="", str;
//    //       file = "C:\\\\Users\\\\joeyt\\\\Documents\\\\NetBeansProjects\\\\Attendance\\\\src\\\\TestRoster.csv";
       ArrayList<ArrayList<Student>> listList = new ArrayList<ArrayList<Student>>();
//    //       ArrayList<Student> chosenStuList = new ArrayList<Student>();
//    //       ArrayList<Student> stuList = new ArrayList<Student>();
//    //       listList = fileProcessor(file);
//    //       fileWriter(file, listList.get(0));
//       //listList = fileProcessor(file);
//       //dateAddition(listList, true,true,true, file);
//       //System.out.println(chosenStuList);
String fLocation = "C:\\\\Users\\\\joeyt\\\\Documents\\\\NetBeansProjects\\\\Attendance\\\\src\\\\TestRoster.csv";
//       ArrayList<ArrayList<Student>> listList = new ArrayList<ArrayList<Student>>();
//    //       ArrayList<Student> stuList = new ArrayList<Student>();
//    //       ArrayList<Student> stuList2 = new ArrayList<Student>();
//    //       ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
//    //       dates.add(LocalDateTime.now());
//    //       
//    //       stuList.add(new Student("k000000", "fname","lname",0,dates));
//    //       dates.add(LocalDateTime.now());
//    //       stuList.add(new Student("k100000", "fname","lname",1,dates));
//    //       dates.add(LocalDateTime.now());
//    //       stuList.add(new Student("k200000", "fname","lname",2,dates));
//    //       
//    //       stuList2.add(stuList.get(0));
//    //       
//    //       stuList2.add(stuList.get(1));
//    //       
//    //       stuList2.add(stuList.get(2));
//    //       
//    //       listList.add(stuList);
//    //       listList.add(stuList2);
      
       listList = fileProcessor(fLocation);
       System.out.println(listList.get(1).get(0).getLastName());
       System.out.println(listList.get(1).get(1).getLastName());
       System.out.println(listList.get(1).get(2).getLastName());
       
//       
//       dateAddition(listList,false,false,false,fLocation);
      
      fileWriter(fLocation);
   }
    
    
    
    
   public static ArrayList<Student> stuList = new ArrayList<Student>();
    
   public static ArrayList<ArrayList<Student>> fileProcessor(String fileLocation) {//begin method
      
      //Declare variables
      String csvLocation;//C:/Users/joeyt/Documents/NetBeansProjects/Attendance/src/TEXTCSV.csv
      csvLocation = fileLocation;
      BufferedReader reader;
      String line = "";
      String cvsSplitBy = ",";
      int classSize=0, doneReading=0;
      boolean done=false;
      FileReader file;
   
      
     
      //filereader code based on www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
      try 
      {//begin try/catch
      //         file = new FileReader(fileLocation);
         reader = new BufferedReader(new FileReader(fileLocation));
         
         // while(done==false)
      //          {//while to build list based on student objects
         int n=0;
         String kNum=" ", fName=" ", lName=" ", numOfAbsStr=" ";
         int numOfAbs = 0; 
         
      //             //check if file reader has reached the end of the file
      //             doneReading=file.read();
      //             if(doneReading==-1)
      //                done=true;
         while ((line = reader.readLine()) != null)
         {//begin while to read line
            lines.add(line);
            // use comma as separator
            String[] part = line.split(cvsSplitBy);
            
           // System.out.println("Part 1 is " + part[0]);
               
            //Set each part[x} to the parts of a student
            kNum=part[2];
            fName=part[1];
            lName=part[0];
            //numOfAbs = Integer.parseInt(numOfAbsStr);
            numOfAbs = Integer.parseInt(part[3]);
            int year, month, dayOfMonth, hour, minute, second, nanosecond;
            //ArrayList<String> dateStr = new ArrayList<String>();
            ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
            
            if(numOfAbs!=0)
            {
               for(int i=0; i < dates.size();i++)
               {
                                 
                  int x=4;
                  String[] dateTime = part[x].split("T");
                  String[] splitDate = dateTime[0].split("-");//could change depending on writing format
                  String[] splitTime = dateTime[1].split(":");
                  String[] splitSec = splitTime[2].split(".");
                  
                  year = Integer.parseInt(splitDate[0]);
                  month = Integer.parseInt(splitDate[1]);
                  dayOfMonth = Integer.parseInt(splitDate[2]);
                  
                  hour = Integer.parseInt(splitTime[0]);
                  minute = Integer.parseInt(splitTime[1]);
                  
                  second = Integer.parseInt(splitSec[0]);
                  nanosecond = Integer.parseInt(splitSec[1]);
                  dates.add(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanosecond));
                  x++;
                  
               }
            }
            
            stuList.add(n, new Student(kNum, fName, lName, numOfAbs, dates));
         }//end while
      //             
      //             
      //             
      //          }//end while to build student list
      } 
      
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      } 
      
      catch (IOException e) 
      {
         e.printStackTrace();
      } 
      
      //randomize into stuList2
      ArrayList<Student> stuList2 = new ArrayList<Student>(); 
      int n = stuList.size();
      while(stuList.size() != 0)
      {
         
         Random random = new Random();
         //needs to be from 1 and stuList.size()
         int rand = random.nextInt(stuList.size());
         stuList2.add(0, stuList.get(rand));
         stuList.remove(rand);
      }
      
      //Randomize back into stuList
      
      while(stuList2.size() != 0)
      {
         
         Random random = new Random();
         //needs to be from 1 and stuList.size()
         int rand = random.nextInt(stuList2.size());
         stuList.add(0, stuList2.get(rand));
         stuList2.remove(rand);
      }
      
      
      //Pick 3 students at random
      for(int counter = 0; counter<3; counter++)
      {
         Random random = new Random();
         int rand = random.nextInt(stuList.size());
         stuList2.add(0,stuList.get(rand));
         stuList.remove(rand);
         
      }

      ArrayList<ArrayList<Student>> listList = new ArrayList<ArrayList<Student>>();
      listList.add(stuList);
      listList.add(stuList2);
      
      
      
      return listList;
   }//end method
   
   public static void dateAddition(ArrayList<ArrayList<Student>> listList, boolean isStu1Abs, boolean isStu2Abs, boolean isStu3Abs, String fLocation)
   {
       
      int a=0;
      int b=0;
      int c=0;
      String kNum1=listList.get(1).get(0).getkNum(),kNum2=listList.get(1).get(1).getkNum(),kNum3=listList.get(1).get(2).getkNum();
       
      for(int j=0;j<lines.size();j++)
      {
         String[] parts = lines.get(j).split(",");
           
         if(parts[2].equals(kNum1))
            a = j;
         if(parts[2].equals(kNum2))
            b=j;
         if(parts[2].equals(kNum3))
            c=j;
      }
      
      if(isStu1Abs==true){
         //listList.get(1).get(0).addDateOfAbs();
         //listList.get(1).get(0).addAbsence();
         String newLine =lines.get(a) + "," + LocalDateTime.now();
         lines.remove(a);
         String[] parts = newLine.split(",");
         int abs = Integer.parseInt(parts[3])+1;
         parts[3] = String.format("%d" ,abs);
         String line=parts[0];
         for(int i=1;i<parts.length;i++){
            line = line + "," + parts[i];
         }
         lines.add(a, line);
         
      }
      if(isStu2Abs==true){
         //listList.get(1).get(1).addDateOfAbs();
         //listList.get(1).get(1).addAbsence();
         String newLine =lines.get(b) + "," + LocalDateTime.now();
         lines.remove(b);
         String[] parts = newLine.split(",");
         int abs = Integer.parseInt(parts[3])+1;
         parts[3] = String.format("%d" ,abs);
         String line=parts[0];
         for(int i=1;i<parts.length;i++){
            line = line + "," + parts[i];
         }
         lines.add(b, line);
      }
      if(isStu3Abs==true){
         //listList.get(1).get(2).addDateOfAbs();
         //listList.get(1).get(2).addAbsence();
         String newLine =lines.get(c) + "," + LocalDateTime.now();
         lines.remove(c);
         String[] parts = newLine.split(",");
         int abs = Integer.parseInt(parts[3])+1;
         parts[3] = String.format("%d" ,abs);
         String line=parts[0];
         for(int i=1;i<parts.length;i++){
            line = line + "," + parts[i];
         }
         lines.add(c, line);
      }
      
      //listList.get(0).add(listList.get(1).get(0));
      //listList.get(0).add(listList.get(1).get(1));
      //listList.get(0).add(listList.get(1).get(2));
      
      fileWriter(fLocation);
   }
   
   public static boolean fileWriter(String fLocation){
        //String fileName = "TestWrite.csv";
        //String fLocation = "C:/Users/joeyt/Documents/NetBeansProjects/Attendance/src/TestWrite.csv";
      
      boolean validWrite = true;
      String string = String.format("\\\\");
      String[] parts = fLocation.split(string);
      fLocation = parts[0];
      for(int i=1;i<parts.length;i++)
      {
         fLocation = fLocation + "/" + parts[i];
      }
      
      
      
      File fNew = new File(fLocation);
      //System.out.print(stuList.size());
      try{
         FileWriter writer = new FileWriter(fNew, false);
         BufferedWriter bw = new BufferedWriter(writer);
         String str = "";
         bw.write(str);
         bw.close();
      }catch(IOException exptn){
         exptn.printStackTrace();
         validWrite = false;
            
      }
      
      int j=0;
      for(int h=0;h<lines.size();h++){
      
         try{
//            int i = 0;
//            System.out.print(stuList.get(i).getDateOfAbs().size());
            FileWriter writer = new FileWriter(fNew, true);
            BufferedWriter bw = new BufferedWriter(writer);
            // String str = stuList.get(i).getkNum() + "," + stuList.get(i).getFirstName() + "," + stuList.get(i).getLastName() + "," + stuList.get(i).getTotalAbs(); 
         //             for(int j=0; j<stuList.get(i).getDateOfAbs().size();j++)
         //             {
         //                str = str + "," + stuList.get(i).getDateOfAbs().get(j).toString();
         //             }
         //             stuList.remove(i);
            
            
            String str = lines.get(j);
            j++;
            bw.write(str);
            bw.write("\n");
            bw.close();
            
            
            //System.out.print("Write done");
         }catch(IOException exptn){
            exptn.printStackTrace();
            validWrite = false;
            
         }
         
      }
      
      return validWrite;
   }
   
}//end class
