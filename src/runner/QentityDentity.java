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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amin
 * THIS CLASS IS DEDICATED FOR CREATING ENTITY VS ENTITY
 * EVERY ENTITES OF QUERY WILL BE PAIRED AGAINST EVERY SINGLE ENTITY OF DOCUMENT'S ENTITIES
 */
public class QentityDentity {
    private WorkingSet workingSet;

    public QentityDentity(WorkingSet workingSet) {
        this.workingSet = workingSet;
    }
    
    public void createEntityEntityPairFile() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException{
        File inputFile = new File( "./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        run(inputFile);
    }
    
    private void run(File fin) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        String saveAddress = "./output/entity_entity/" + workingSet.getWorkingSetName() + "/EE"+ workingSet.getSFileNumber()+ ".txt";
        String output = "";
        writeOutputToFile(output, saveAddress);
        
        BufferedReader br = new BufferedReader(new FileReader(fin));
        String line = null;
        int i=0;
        while ((line = br.readLine()) != null) {
            i++;
            String qId = line.split(" ")[1].substring(4);
            String docId = line.split(" ")[50];
            String resultLine = "qid:" + qId + " _ docid:" + docId;
            
            for(String qEnt : workingSet.getQueryEntities(qId)){
                
                for(String dEnt : workingSet.getDocumentEntities(docId)){
                    
                    String qEntName = "not available"; //core.CoreServices.getTitle(Integer.parseInt(qEnt));
                    String dEntName = "not available"; //core.CoreServices.getTitle(Integer.parseInt(dEnt));
                    resultLine +=  "\n\t" + qEnt + " ("+ qEntName + ") _ " + dEnt + " ("+dEntName + ")";
                    
                }
            }
            resultLine +="\n";
            System.out.println(i);
            fileWriterContinue(resultLine, saveAddress);
            
        }
        br.close();
        System.out.println(workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
    }
      
    
    
    private void writeOutputToFile(String content, String fileName) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateOutput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(CreateOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void fileWriterContinue(String content, String fileName) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateOutput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(CreateOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
