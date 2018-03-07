/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.WorkingSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author Parastoo
 */
public class SummerizeFile {
    
    public static void summerize(String inputName, String OutputName ,WorkingSet ws ) throws IOException{
        
        HashSet<String> hs = new HashSet<>();
        HashSet<String> hsresult = new HashSet<>();
        for(String key : ws.getEntityTitle().keySet()){
            for (String value: ws.getEntityTitle().get(key)) {
                hs.add(value);
                hsresult.add(value);
            }
        }
        System.out.println("number of All entity is : "+ ws.entityTitle.size());
        
        File inputFile = new File("./data/Total/" + inputName);
        File outputFile = new File("./data/Total/" + OutputName);
        inputFile.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(outputFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile));
        String line = null;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] lineSplit = line.split(">");
            if(lineSplit.length>0){
                String key = lineSplit[0].substring(29,lineSplit[0].length());
                if(hs.contains(key)){
                    bw.write(String.format("%s%n",line));
                    if(hsresult.contains(key))
                        hsresult.remove(key);
                }
            }
        }
        
        br.close();
        bw.close();
        
        System.out.println("number of All entity in file: " + inputName+": " + (hs.size()-hsresult.size()));
        System.out.println("number of remaining titles: "+hsresult.size());
        for (String s :hsresult) {
            System.out.println(s);
        }
        
    }
}
