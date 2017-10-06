/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.WorkingSet;
import features.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amin
 */
public class CreateOutput {

    private WorkingSet workingSet;

    public CreateOutput(WorkingSet workingSet) {
        this.workingSet = workingSet;
    }

    public void outputF095() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F95.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F96.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F97.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F98.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F99.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F100.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F101.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F102.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F103.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F104.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F105.txt";
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

    public void outputF106() {
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F106.txt";
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
        String saveAddress = "./output/" + workingSet.getWorkingSetName() + "/F107.txt";
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
