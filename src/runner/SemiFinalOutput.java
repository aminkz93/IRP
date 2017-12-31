/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.WorkingSet;
import features.F003;
import features.F069;
import features.F092;
import features.F094;
import features.F111;
import features.F112;
import features.F113;
import features.F114;
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
 */
public class SemiFinalOutput {

    private WorkingSet workingSet;
    private String[] featureValues;

    public SemiFinalOutput(WorkingSet workingSet) {
        this.workingSet = workingSet;
        featureValues = new String[4];
    }

    public void outputSelectedFeatures() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException, Exception {
        File inputFile = new File("./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        output003();
        output069();
        output092();
        output094();
        output111();
        output112();
        output113();
        output114();
    }

    private void output003() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        File inputFile = new File("./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        String saveAddress1 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/003" + workingSet.getSFileNumber() + "_Res.txt";
        String saveAddress2 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/003" + workingSet.getSFileNumber() + "_Min.txt";
        String saveAddress3 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/003" + workingSet.getSFileNumber() + "_Max.txt";
        String saveAddress4 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/003" + workingSet.getSFileNumber() + "_Mean.txt";
        String output = "";
        writeOutputToFile(output, saveAddress1);
        writeOutputToFile(output, saveAddress2);
        writeOutputToFile(output, saveAddress3);
        writeOutputToFile(output, saveAddress4);

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;

        while ((line = br.readLine()) != null) {
//            lineCount++;
//            System.out.println("line :"+ lineCount);

            String qId = line.split(" ")[1].substring(4);

            if (workingSet.getQueryEntities(qId).size() == 0) {
                continue;
            }

            String docId = line.split(" ")[50];
            F003 f003 = new F003(workingSet);
            featureValues = f003.print(qId, docId);
            fileWriterContinue(featureValues[0] + "\n", saveAddress1);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[1] + "\n", saveAddress2);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[2] + "\n", saveAddress3);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[3] + "\n", saveAddress4);
        }
        br.close();
        System.out.println("feature 003 " + workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
    }
    private void output069() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        File inputFile = new File("./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        String saveAddress1 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/069" + workingSet.getSFileNumber() + "_Res.txt";
        String saveAddress2 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/069" + workingSet.getSFileNumber() + "_Min.txt";
        String saveAddress3 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/069" + workingSet.getSFileNumber() + "_Max.txt";
        String saveAddress4 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/069" + workingSet.getSFileNumber() + "_Mean.txt";
        String output = "";
        writeOutputToFile(output, saveAddress1);
        writeOutputToFile(output, saveAddress2);
        writeOutputToFile(output, saveAddress3);
        writeOutputToFile(output, saveAddress4);

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;

        while ((line = br.readLine()) != null) {
//            lineCount++;
//            System.out.println("line :"+ lineCount);

            String qId = line.split(" ")[1].substring(4);

            if (workingSet.getQueryEntities(qId).size() == 0) {
                continue;
            }

            String docId = line.split(" ")[50];
            F069 f069 = new F069(workingSet);
            featureValues = f069.print(docId);
            fileWriterContinue(featureValues[0] + "\n", saveAddress1);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[1] + "\n", saveAddress2);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[2] + "\n", saveAddress3);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[3] + "\n", saveAddress4);
        }
        br.close();
        System.out.println("feature 069 " + workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
    }
    private void output092() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException, Exception {
        File inputFile = new File("./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        String saveAddress1 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/092" + workingSet.getSFileNumber() + "_Res.txt";
        String saveAddress2 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/092" + workingSet.getSFileNumber() + "_Min.txt";
        String saveAddress3 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/092" + workingSet.getSFileNumber() + "_Max.txt";
        String saveAddress4 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/092" + workingSet.getSFileNumber() + "_Mean.txt";
        String output = "";
        writeOutputToFile(output, saveAddress1);
        writeOutputToFile(output, saveAddress2);
        writeOutputToFile(output, saveAddress3);
        writeOutputToFile(output, saveAddress4);

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;

        while ((line = br.readLine()) != null) {
//            lineCount++;
//            System.out.println("line :"+ lineCount);

            String qId = line.split(" ")[1].substring(4);

            if (workingSet.getQueryEntities(qId).size() == 0) {
                continue;
            }

            String docId = line.split(" ")[50];
            F092 f092 = new F092(workingSet);
            featureValues = f092.print(qId);
            fileWriterContinue(featureValues[0] + "\n", saveAddress1);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[1] + "\n", saveAddress2);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[2] + "\n", saveAddress3);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[3] + "\n", saveAddress4);
        }
        br.close();
        System.out.println("feature 092 " + workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
    }
    private void output094() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException, Exception {
        File inputFile = new File("./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        String saveAddress1 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/094" + workingSet.getSFileNumber() + "_Res.txt";
        String saveAddress2 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/094" + workingSet.getSFileNumber() + "_Min.txt";
        String saveAddress3 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/094" + workingSet.getSFileNumber() + "_Max.txt";
        String saveAddress4 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/094" + workingSet.getSFileNumber() + "_Mean.txt";
        String output = "";
        writeOutputToFile(output, saveAddress1);
        writeOutputToFile(output, saveAddress2);
        writeOutputToFile(output, saveAddress3);
        writeOutputToFile(output, saveAddress4);

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;

        while ((line = br.readLine()) != null) {
//            lineCount++;
//            System.out.println("line :"+ lineCount);

            String qId = line.split(" ")[1].substring(4);

            if (workingSet.getQueryEntities(qId).size() == 0) {
                continue;
            }

            String docId = line.split(" ")[50];
            F094 f094 = new F094(workingSet);
            featureValues = f094.print(qId, docId);
            fileWriterContinue(featureValues[0] + "\n", saveAddress1);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[1] + "\n", saveAddress2);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[2] + "\n", saveAddress3);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[3] + "\n", saveAddress4);
        }
        br.close();
        System.out.println("feature 094 " + workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
    }
    private void output111() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        File inputFile = new File("./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        String saveAddress1 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/111" + workingSet.getSFileNumber() + "_Res.txt";
        String saveAddress2 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/111" + workingSet.getSFileNumber() + "_Min.txt";
        String saveAddress3 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/111" + workingSet.getSFileNumber() + "_Max.txt";
        String saveAddress4 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/111" + workingSet.getSFileNumber() + "_Mean.txt";
        String output = "";
        writeOutputToFile(output, saveAddress1);
        writeOutputToFile(output, saveAddress2);
        writeOutputToFile(output, saveAddress3);
        writeOutputToFile(output, saveAddress4);

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;

        while ((line = br.readLine()) != null) {
//            lineCount++;
//            System.out.println("line :"+ lineCount);

            String qId = line.split(" ")[1].substring(4);

            if (workingSet.getQueryEntities(qId).size() == 0) {
                continue;
            }

            String docId = line.split(" ")[50];
            F111 f111 = new F111(workingSet);
            f111.execute(qId);
            featureValues = f111.print(qId, docId);
            fileWriterContinue(featureValues[0] + "\n", saveAddress1);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[1] + "\n", saveAddress2);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[2] + "\n", saveAddress3);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[3] + "\n", saveAddress4);
        }
        br.close();
        System.out.println("feature 111 " + workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
    }
    
    private void output112() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        File inputFile = new File("./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        String saveAddress1 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/112" + workingSet.getSFileNumber() + "_Res.txt";
        String saveAddress2 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/112" + workingSet.getSFileNumber() + "_Min.txt";
        String saveAddress3 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/112" + workingSet.getSFileNumber() + "_Max.txt";
        String saveAddress4 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/112" + workingSet.getSFileNumber() + "_Mean.txt";
        String output = "";
        writeOutputToFile(output, saveAddress1);
        writeOutputToFile(output, saveAddress2);
        writeOutputToFile(output, saveAddress3);
        writeOutputToFile(output, saveAddress4);

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;

        while ((line = br.readLine()) != null) {
//            lineCount++;
//            System.out.println("line :"+ lineCount);

            String qId = line.split(" ")[1].substring(4);

            if (workingSet.getQueryEntities(qId).size() == 0) {
                continue;
            }

            String docId = line.split(" ")[50];
            F112 f112 = new F112(workingSet);
            f112.execute(qId);
            featureValues = f112.print(qId, docId);
            fileWriterContinue(featureValues[0] + "\n", saveAddress1);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[1] + "\n", saveAddress2);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[2] + "\n", saveAddress3);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[3] + "\n", saveAddress4);
        }
        br.close();
        System.out.println("feature 112 " + workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
    }
    
    private void output113() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        File inputFile = new File("./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        String saveAddress1 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/113" + workingSet.getSFileNumber() + "_Res.txt";
        String saveAddress2 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/113" + workingSet.getSFileNumber() + "_Min.txt";
        String saveAddress3 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/113" + workingSet.getSFileNumber() + "_Max.txt";
        String saveAddress4 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/113" + workingSet.getSFileNumber() + "_Mean.txt";
        String output = "";
        writeOutputToFile(output, saveAddress1);
        writeOutputToFile(output, saveAddress2);
        writeOutputToFile(output, saveAddress3);
        writeOutputToFile(output, saveAddress4);

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;

        while ((line = br.readLine()) != null) {
//            lineCount++;
//            System.out.println("line :"+ lineCount);

            String qId = line.split(" ")[1].substring(4);

            if (workingSet.getQueryEntities(qId).size() == 0) {
                continue;
            }

            String docId = line.split(" ")[50];
            F113 f113 = new F113(workingSet);
            f113.execute(qId);
            featureValues = f113.print(qId, docId);
            fileWriterContinue(featureValues[0] + "\n", saveAddress1);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[1] + "\n", saveAddress2);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[2] + "\n", saveAddress3);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[3] + "\n", saveAddress4);
        }
        br.close();
        System.out.println("feature 113 " + workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
    }
    private void output114() throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        File inputFile = new File("./data/" + workingSet.getWorkingSetName() + "/letor/" + workingSet.getSFileNumber() + ".txt");
        String saveAddress1 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/114" + workingSet.getSFileNumber() + "_Res.txt";
        String saveAddress2 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/114" + workingSet.getSFileNumber() + "_Min.txt";
        String saveAddress3 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/114" + workingSet.getSFileNumber() + "_Max.txt";
        String saveAddress4 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/114" + workingSet.getSFileNumber() + "_Mean.txt";
        String output = "";
        writeOutputToFile(output, saveAddress1);
        writeOutputToFile(output, saveAddress2);
        writeOutputToFile(output, saveAddress3);
        writeOutputToFile(output, saveAddress4);

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;

        while ((line = br.readLine()) != null) {
//            lineCount++;
//            System.out.println("line :"+ lineCount);

            String qId = line.split(" ")[1].substring(4);

            if (workingSet.getQueryEntities(qId).size() == 0) {
                continue;
            }

            String docId = line.split(" ")[50];
            F114 f114 = new F114(workingSet);
            f114.execute(qId);
            featureValues = f114.print(qId, docId);
            fileWriterContinue(featureValues[0] + "\n", saveAddress1);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[1] + "\n", saveAddress2);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[2] + "\n", saveAddress3);
            fileWriterContinue(qId + "-" + docId + " " + featureValues[3] + "\n", saveAddress4);
        }
        br.close();
        System.out.println("feature 114 " + workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
    }

//    private void runFeatures(String qid, String docid, String featureNumber) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
//        //--inja tamame feature haro ejra kon lineCount too araye featureValues bnvis
//
//        int index = 0;
//        F003 f003 = new F003(workingSet);
//        F069 f069 = new F069(workingSet);
//        F092 f092 = new F092(workingSet);
//        F111 f111 = new F111(workingSet);
//        F112 f112 = new F112(workingSet);
//        F113 f113 = new F113(workingSet);
//        F114 f114 = new F114(workingSet);
//
//        featureValues[index++] = f003.print(qid, docid);
////        System.out.println("F95 Execution Finished");
//        featureValues[index++] = f069.print(docid);
////        System.out.println("F96 Execution Finished");
//        featureValues[index++] = f092.print(qid);
////        System.out.println("F97 Execution Finished");
//        featureValues[index++] = f094.print(qid, docid);
////        System.out.println("F98 Execution Finished");
//        featureValues[index++] = f111.print(qid, docid);
////        System.out.println("F99 Execution Finished");
//        featureValues[index++] = f112.print(qid, docid);
////        System.out.println("F100 Execution Finished");
//        featureValues[index++] = f113.print(qid, docid);
////        System.out.println("F101 Execution Finished");
//        featureValues[index++] = f114.print(qid, docid);
////        System.out.println("F102 Execution Finished");
//
//    }

    /*
    CREATES A HASHMAP OF THE QUERY-DOCUMENTS ENTITIES FROM THE GIVEN FILE
    EACH QUERY IS RELATED TO SOME DOCUMENTS
    QUERY IS THE KEY AND ARRAY LIST OF DOCUMENTS IS THE VALUE OF THE HASHMAP
     */
//    private void run(File fin, String featureNumber) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
//        String saveAddress1 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/" + featureNumber + workingSet.getSFileNumber() + "_Res.txt";
//        String saveAddress2 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/" + featureNumber + workingSet.getSFileNumber() + "_Min.txt";
//        String saveAddress3 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/" + featureNumber + workingSet.getSFileNumber() + "_Max.txt";
//        String saveAddress4 = "./output/semiFinal/" + workingSet.getWorkingSetName() + "/" + featureNumber + workingSet.getSFileNumber() + "_Mean.txt";
//        String output = "";
//        writeOutputToFile(output, saveAddress1);
//        writeOutputToFile(output, saveAddress2);
//        writeOutputToFile(output, saveAddress3);
//        writeOutputToFile(output, saveAddress4);
//
//        BufferedReader br = new BufferedReader(new FileReader(fin));
//        String line = null;
////        BufferedReader brCount = new BufferedReader(new FileReader(fin));
////        System.out.println(workingSet.getSFileNumber() + " lines :" + brCount.lines().count());
////        int lineCount=0;
//        while ((line = br.readLine()) != null) {
////            lineCount++;
////            System.out.println("line :"+ lineCount);
//
//            String qId = line.split(" ")[1].substring(4);
//
//            if (workingSet.getQueryEntities(qId).size() == 0) {
//                continue;
//            }
//
//            String docId = line.split(" ")[50];
//
//            runFeatures(qId, docId, featureNumber);
//            String resultLine = line.split("#")[0];
//            for (int i = 0; i < featureValues.length; i++) {
//
//                resultLine += index++ + ":" + new DecimalFormat("#0.000000").format(featureValues[i]) + " ";
//            }
//            resultLine += "#" + line.split("#")[1];
//            resultLine += "\n";
//            fileWriterContinue(resultLine, saveAddress);
//        }
//        br.close();
//        System.out.println(workingSet.getWorkingSetName() + " - " + workingSet.getSFileNumber() + " done");
//    }

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
