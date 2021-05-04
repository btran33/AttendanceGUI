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
public class Test1 {
   public ArrayList<LocalDateTime> dateOfAbs = new ArrayList<LocalDateTime>();
   
   public static void main(String args[]){
      ArrayList<Student> stuList = new ArrayList<Student>();
      ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
      stuList.add(new Student("k000000", "fname","lname",0,dates));
      dates.add(LocalDateTime.now());
      stuList.add(new Student("k100000", "fname","lname",1,dates));
      dates.add(LocalDateTime.now());
      stuList.add(new Student("k200000", "fname","lname",2,dates));
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
      for(int counter = 0; counter<2; counter++)
      {
         Random random = new Random();
         int rand = random.nextInt(stuList.size());
         stuList2.add(0,stuList.get(rand));
         stuList.remove(rand);
         
      }
      System.out.println(stuList2.get(0).getkNum());
      System.out.println(stuList2.get(1).getkNum());
      //System.out.println(stuList2.get(2).getkNum());
   }
}
