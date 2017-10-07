/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.WorkingSet;
import features.F105;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import features.*;
import java.text.ParseException;
/**
 *
 * @author amin
 */
public class LetorOutput {

    private WorkingSet workingSet;
    private double[] featureValues;
    private int baseFeatureNumber = 95;

    public LetorOutput(WorkingSet workingSet) {
        this.workingSet = workingSet;
        featureValues = new double[14];
    }
    
    public void createLetorFileWithNewFeatures(int baseFeatureNumber) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException{
//        -- addrese file vooroodio inja besaz ba tavajoe be workingSet.getSFileNumber() va workingSet.getname()
//             -- call run()
        File inputFile = new File( "./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        run(inputFile, baseFeatureNumber);
    }

    private void runFeatures(String qid, String docid) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        //--inja tamame feature haro ejra kon k too araye featureValues bnvis
        int index = 0 ;
        F095 f95  = new F095(workingSet);
        F096 f96  = new F096(workingSet);
        F097 f97  = new F097(workingSet);
        F098 f98  = new F098(workingSet);
        F099 f99  = new F099(workingSet);
        F100 f100 = new F100(workingSet);
        F101 f101 = new F101(workingSet);
        F102 f102 = new F102(workingSet);
        F103 f103 = new F103(workingSet);
        F104 f104 = new F104(workingSet);
        F105 f105 = new F105(workingSet);
        F106 f106 = new F106(workingSet);
        F107 f107 = new F107(workingSet);
        F108 f108 = new F108(workingSet);
        
        featureValues[index++] = f95 .execute(qid, docid) ;
        featureValues[index++] = f96 .execute(qid, docid) ;
        featureValues[index++] = f97 .execute(qid, docid) ;
        featureValues[index++] = f98 .execute(qid, docid) ;
        featureValues[index++] = f99 .execute(qid, docid) ;
        featureValues[index++] = f100.execute(qid, docid) ;
        featureValues[index++] = f101.execute(     docid) ;
        featureValues[index++] = f102.execute(     docid) ;
        featureValues[index++] = f103.execute(     docid) ;
        featureValues[index++] = f104.execute(     docid) ;
        featureValues[index++] = f105.execute(qid, docid) ;
        featureValues[index++] = f106.execute(qid, docid) ;
        featureValues[index++] = f107.execute(qid, docid) ;
        featureValues[index++] = f108.execute(qid, docid) ;
        
    }
    /*
    CREATES A HASHMAP OF THE QUERY-DOCUMENTS ENTITIES FROM THE GIVEN FILE
    EACH QUERY IS RELATED TO SOME DOCUMENTS
    QUERY IS THE KEY AND ARRAY LIST OF DOCUMENTS IS THE VALUE OF THE HASHMAP
     */
    private void run(File fin, int baseFeatureNumber) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        String saveAddress = "./output/letor/" + workingSet.getWorkingSetName() + "/s"+ workingSet.getSFileNumber()+ ".txt";
        String output = "";
        writeOutputToFile(output, saveAddress);
        
        BufferedReader br = new BufferedReader(new FileReader(fin));
        String line = null;
        
        while ((line = br.readLine()) != null) {
            int index = baseFeatureNumber;
            String qId = line.split(" ")[1].substring(4);
            String docId = line.split(" ")[50];
            
            runFeatures(qId, docId);
            String resultLine = line.split("#")[0];
            for (int i = 0; i < featureValues.length; i++) {
                
                resultLine +=  " " + index++ + ":" + featureValues[i];
            }
            resultLine += " "+line.split("#")[1];
            resultLine += " \n";
//            -- line inja mishe daghighan y pair az queryid va docid k feature ha vasatesh gharar migiran
//            -- y nemoone vase ink substringo inaro bkhay bash kar koni :::  "1 qid:10 1:0.031310 2:0.666667 3:0.500000 4:0.166667 5:0.033206 6:0.000000 7:0.000000 8:0.000000 9:0.000000 10:0.000000 11:0.023327 12:0.641157 13:0.498951 14:0.323153 15:0.026674 16:0.029246 17:0.500000 18:0.222222 19:0.111111 20:0.029398 21:0.689128 22:0.636228 23:0.869764 24:0.716400 25:0.725186 26:0.554961 27:0.695985 28:0.504060 29:0.602946 30:0.679534 31:0.730286 32:0.687414 33:0.529688 34:0.436996 35:0.643739 36:0.372337 37:0.646890 38:0.686107 39:0.823908 40:0.750092 41:0.385426 42:0.923077 43:0.086207 44:0.333333 45:0.448276 46:0.000000 #docid = GX000-24-12369390 inc = 0.600318836372593 prob = 0.416367"
//            -- inja b line feature haro ezafe kon (az int baseFeatureNumber estefade kon y joori ++ esh kon k age khastim az 95
//            -- shooroo beshe bere bala ok bashe age am goft na baayad az 46 shooroo she chon file ta 46 dare
//            -- k moshkeli pish nayad dg) feature haro k gozashti dakhele line, line jadido hala write mikoni ba methode zir
//            
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
