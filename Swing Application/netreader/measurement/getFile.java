/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netreader.measurement;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author kumark2
 */
public class getFile {
public FileReader getFile (String fileName){
   FileReader file = null;
   try {
   file = new FileReader (fileName);
   
   }catch (FileNotFoundException e){
       e.printStackTrace();
   }
   
   return file;
   }
}
