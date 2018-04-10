/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import static com.google.common.util.concurrent.Striped.lock;
import core.WorkingSet;
import core.sqlConnection.SqlConnection;
import features.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amin
 */
public class CreateOutput {

    private WorkingSet workingSet;
    public int WaitingRequest = 0;
    private final Object lock = new Object();
    
    public CreateOutput(WorkingSet workingSet) {
        this.workingSet = workingSet;
    }
    
    public void outputF001(){
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F01"+ workingSet.getSFileNumber()+ ".txt";
        String saveExceptionAddress = "./output/exception/" + workingSet.getWorkingSetName() + "/F01"+ workingSet.getSFileNumber()+ ".txt";
        F001 f01 = new F001(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        writeOutputToFile(output, saveExceptionAddress);
        for (String qid : workingSet.getEntityTitle().keySet()) {
            output = f01.execute(qid);
            if(output.contains("exception")){
                fileWriterContinue(output, saveExceptionAddress);
                
            }
            else
                fileWriterContinue(output, saveAddress);
            System.out.println(output);
        }
        System.out.println("F01.txt" + " done");
    }
    
    public void outputF003() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F03"+ workingSet.getSFileNumber()+ ".txt";
        F003 f03 = new F003(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                fileWriterContinue(f03.print(qid, docid), saveAddress);
//                output += f95.print(qid, docid);

            }
        }
//        writeOutputToFile(output,saveAddress);
        System.out.println("F03.txt" + " done");
    }
    public void outputF021() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F021"+ workingSet.getSFileNumber()+ ".txt";
        String saveExceptionAddress = "./output/exception/" + workingSet.getWorkingSetName() + "/F021"+ workingSet.getSFileNumber()+ ".txt";
        F021 f21 = new F021(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        writeOutputToFile(output, saveExceptionAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
                ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
                ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
                for(String q : qEntities){
                    for(String d : dEntities ){
                        output = f21.execute(q,d);
                        if(output.contains("exception")){
                            fileWriterContinue(output, saveExceptionAddress);

                        }
                        else
                        {
                            fileWriterContinue(output, saveAddress);
                        }
                        System.out.println(output);
                    }
                }
                

            }
        }
        System.out.println("F021.txt" + " done");
    }
    
    public void outputF021sql() {
        String saveExceptionAddress = "./output/exception/" + workingSet.getWorkingSetName() + "/F021"+ workingSet.getSFileNumber()+ ".txt";
        F021 f21 = new F021(workingSet);
        String output = "";
        writeOutputToFile(output, saveExceptionAddress);
        for (int i= 344501; i<4857282 ; i++) {
            String pair = SqlConnection.selectPair(i);
            String[] cells = pair.split("-");
            if(cells.length ==2){
                
                request(cells[0],cells[1],saveExceptionAddress,i,new ResponseHandler() {
                    @Override
                    public void onResponse(String result) {
                        System.out.println("RESULT:\t"+result);
                        synchronized (lock) {
                            WaitingRequest-- ; 
                        }
                        
                    }
                });
                System.out.println("request "+i+" sent!");
                try{
                    if(WaitingRequest <10000)
                        Thread.sleep(5);
                    else if (WaitingRequest >60000){
                         while(WaitingRequest >60000)
                             Thread.sleep(100);
                    }
                    else  {
                        int time = WaitingRequest/100;
                        Thread.sleep(time);
                    }
                        
                }
                catch(InterruptedException ex){
                    Logger.getLogger(CreateOutput.class.getName()).log(Level.SEVERE , null , ex);
                }
//                output = f21.execute(cells[0],cells[1]);
//                if(output.contains("exception")){
//                    fileWriterContinue(output, saveExceptionAddress);
//                    SqlConnection.updateFeature("F21", i, -1);
//                }
//                else
//                {
//                    String result = output.split("\\s+")[1];
//                    SqlConnection.updateFeature("F21", i, Double.parseDouble(result));
//                }
            }
        }
        System.out.println("F021.txt" + " done");
    }
    
    public void request(String s1 , String s2 , String address , int i , ResponseHandler responseHandler){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("new thread");
                F021 f21 = new F021(workingSet);
                String output = "";
                output = f21.execute(s1,s2);
                if(output.contains("exception")){
                    fileWriterContinue(output, address);
                    SqlConnection.updateFeature("F21", i, -1);
                }
                else
                {
                    String result = output.split("\\s+")[1];
                    SqlConnection.updateFeature("F21", i, Double.parseDouble(result));
                }
                System.out.println(output);
                
            }
        };
        synchronized (lock) {
            WaitingRequest++ ; 
        }
        Thread t=new Thread(r);
        t.start();
    }
    public void outputF092() throws Exception {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F92"+ workingSet.getSFileNumber()+ ".txt";
        F092 f92 = new F092(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        fileWriterContinue(f92.print(), saveAddress);

        System.out.println("F92.txt" + " done");
    }
    
    public void outputF069() throws IOException {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F69"+ workingSet.getSFileNumber()+ ".txt";
        F069 f69 = new F069(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
//        fileWriterContinue(f69.print(), saveAddress);

        System.out.println("F69.txt" + " done");
    }
    
    public void outputF111() throws Exception {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F111"+ workingSet.getSFileNumber()+ ".txt";
        F111 f111 = new F111(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
            f111.execute(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                fileWriterContinue(f111.print(qid, docid), saveAddress);
//                output += f95.print(qid, docid);

            }
        }
//        writeOutputToFile(output,saveAddress);
        System.out.println("F111.txt" + " done");
    }
    
    public void outputF112() throws Exception {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F112"+ workingSet.getSFileNumber()+ ".txt";
        F112 f112 = new F112(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {

            f112.execute(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                fileWriterContinue(f112.print(qid, docid), saveAddress);
//                output += f95.print(qid, docid);

            }
        }
//        writeOutputToFile(output,saveAddress);
        System.out.println("F112.txt" + " done");
    }
    
    public void outputF113() throws Exception {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F113"+ workingSet.getSFileNumber()+ ".txt";
        F113 f113 = new F113(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {

            f113.execute(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                fileWriterContinue(f113.print(qid, docid), saveAddress);
//                output += f95.print(qid, docid);

            }
        }
//        writeOutputToFile(output,saveAddress);
        System.out.println("F113.txt" + " done");
    }
    
    public void outputF114() throws Exception {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F114"+ workingSet.getSFileNumber()+ ".txt";
        F114 f114 = new F114(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {

            f114.execute(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                fileWriterContinue(f114.print(qid, docid), saveAddress);
//                output += f95.print(qid, docid);

            }
        }
//        writeOutputToFile(output,saveAddress);
        System.out.println("F114.txt" + " done");
    }
    
    
    public void outputF078() throws Exception {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F78"+ workingSet.getSFileNumber() +".txt";
        F078 f78 = new F078(workingSet);
        String output = "";
        int index =1;
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
            System.out.println(index++);
            System.out.println(qid);
            if(index ==40){
                System.out.println("this");
            }
//            System.out.println(qid);
//                System.out.println(docid);
//                System.out.println(f95.print(qid, docid));
            fileWriterContinue(f78.print(qid), saveAddress);
//                output += f95.print(qid, docid);

            
        }
//        writeOutputToFile(output,saveAddress);
        System.out.println("F78.txt" + " done");
    }
    
//    public void outputF092() throws Exception {
//        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F92"+ workingSet.getSFileNumber() +".txt";
//        F092 f92 = new F092(workingSet);
//        String output = "";
//        int index =1;
//        writeOutputToFile(output, saveAddress);
//        for (String qid : workingSet.getQuery().keySet()) {
//            System.out.println(index++);
//            System.out.println(qid);
//            
//            fileWriterContinue(f92.print(qid), saveAddress);   
//        }
//        System.out.println("F92.txt" + " done");
//    }
    
    public void outputF094() throws Exception {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F94"+ workingSet.getSFileNumber() +".txt";
        F094 f94 = new F094(workingSet);
        String output = "";
        int index =1;
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQuery().keySet()) {
            System.out.println(index++);
            System.out.println(qid);
            
//            fileWriterContinue(f94.print(qid), saveAddress);   
        }
        System.out.println("F94.txt" + " done");
    }

    public void outputF095() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F95"+ workingSet.getSFileNumber()+ ".txt";
        F095 f95 = new F095(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
//                System.out.println(f95.print(qid, docid));
                fileWriterContinue(f95.print(qid, docid), saveAddress);
//                output += f95.print(qid, docid);

            }
        }
//        writeOutputToFile(output,saveAddress);
        System.out.println("F95.txt" + " done");
    }

    public void outputF096() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F96"+ workingSet.getSFileNumber()+ ".txt";
        F096 f96 = new F096(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f96.print(qid, docid), saveAddress);

            }
        }

        System.out.println("F96.txt" + " done");

    }

    public void outputF097() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F97"+ workingSet.getSFileNumber()+ ".txt";
        F097 f97 = new F097(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f97.print(qid, docid), saveAddress);

            }
        }
        System.out.println("F97.txt" + " done");

    }

    public void outputF098() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F98"+ workingSet.getSFileNumber()+ ".txt";
        F098 f98 = new F098(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f98.print(qid, docid), saveAddress);

            }
        }

        System.out.println("F98.txt" + " done");

    }

    public void outputF099() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F99"+ workingSet.getSFileNumber()+ ".txt";
        F099 f99 = new F099(workingSet);
        String output = "";
//        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                System.out.println(f99.print(qid, docid));
//                fileWriterContinue(f99.print(qid, docid), saveAddress);

            }
        }
        System.out.println("F99.txt" + " done");

    }

    public void outputF100() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F100"+ workingSet.getSFileNumber()+ ".txt";
        F100 f100 = new F100(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f100.print(qid, docid), saveAddress);

            }
        }

        System.out.println("F100.txt" + " done");

    }
    

    public void outputF101() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F101"+ workingSet.getSFileNumber()+ ".txt";
        F101 f101 = new F101(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f101.print(docid), saveAddress);

            }
        }

        System.out.println("F101.txt" + " done");

    }

    public void outputF102() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F102"+ workingSet.getSFileNumber()+ ".txt";
        F102 f102 = new F102(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            fileWriterContinue(f102.print(qid), saveAddress);
        }

        System.out.println("F102.txt" + " done");

    }

    public void outputF103() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F103"+ workingSet.getSFileNumber()+ ".txt";
        F103 f103 = new F103(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            fileWriterContinue(f103.print(qid), saveAddress);
        }

        System.out.println("F103.txt" + " done");

    }

    public void outputF104() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F104"+ workingSet.getSFileNumber()+ ".txt";
        F104 f104 = new F104(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            fileWriterContinue(f104.print(qid), saveAddress);
        }

        System.out.println("F104.txt" + " done");

    }

    public void outputF105() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F105"+ workingSet.getSFileNumber()+ ".txt";
        F105 f105 = new F105(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f105.print(qid, docid), saveAddress);

            }
        }

        System.out.println("F105.txt" + " done");

    }
    
    public void outputF116() throws Exception {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F116"+ workingSet.getSFileNumber()+ ".txt";
        F116 f116 = new F116(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f116.print(qid, docid), saveAddress);

            }
        }

        System.out.println("F116.txt" + " done");

    }

    public void outputF106() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F106"+ workingSet.getSFileNumber()+ ".txt";
        F106 f106 = new F106(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f106.print(qid, docid), saveAddress);

            }
        }

        System.out.println("F106.txt" + " done");

    }
    public void outputF107() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F107"+ workingSet.getSFileNumber()+ ".txt";
        F107 f107 = new F107(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            f107.execute(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f107.print(qid,docid), saveAddress);

            }
        }

        System.out.println("F107.txt" + " done");

    }
    public void outputF108() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F108"+ workingSet.getSFileNumber()+ ".txt";
        F108 f108 = new F108(workingSet);
        String output = "";
        writeOutputToFile(output, saveAddress);
        for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
//            System.out.println(qid);
            f108.execute(qid);
            for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
//                System.out.println(docid);
                fileWriterContinue(f108.print(qid,docid), saveAddress);

            }
        }

        System.out.println("F108.txt" + " done");

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

    public void fileWriterContinue(String content, String fileName) {
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
