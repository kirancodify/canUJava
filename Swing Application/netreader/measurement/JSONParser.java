/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netreader.measurement;

/**
 *
 * @author kumark2
 */
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileReader;  
  
//import gsonapplication.Student;  
import com.google.gson.Gson;  
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;




public class JSONParser {


    public JSONParser(String fileName){
    try {
       BufferedReader br = new BufferedReader (new FileReader (fileName));
       Gson gson = new Gson();
   
       JsonParser parser = new JsonParser();
       JsonArray jArray = parser.parse(br).getAsJsonArray();
             //String obj;
   
   // called fromJson() method and passed incoming buffer from json f
       ArrayList<Measurement> master = new ArrayList<Measurement>();
   
         for (JsonElement obj : jArray){
       
            Measurement measureObj = gson.fromJson(obj, Measurement.class);
    
             boolean m = master.add(measureObj);
                    //String Created = measureObj.getCreatedAt();
                    System.out.println(m);
     //abc = measureObj.getCreatedAt();
   // printed student data on console  
   System.out.println("createdAt: "+measureObj.getCreatedAt());  
   System.out.println("TCP Ping: "+measureObj.getTcpPings()); 
   System.out.println("TCP Upload Average: "+measureObj.getTcpUploadAverage().getTime());
   System.out.println("TCP Upload Sample: ");
   for (int i=0;i<=measureObj.getTcpUploadSamples().size()-1;i++){
       System.out.println(measureObj.getTcpUploadSamples().get(i).getValue());
   }
      
   }
    
  } catch (FileNotFoundException e) {  
   e.printStackTrace();
    }  
  }  
        
        //return abc;

    
 
    
}