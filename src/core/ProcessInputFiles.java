/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.index.Indexer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amin
 */
public class ProcessInputFiles {

    /*
    CREATES A HASHMAP OF THE QUERIES ENTITIES FROM THE GIVEN FILE
     */
    private static HashMap<String, ArrayList<String>> createQueryHashMap(File fin) throws IOException {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        // Construct BufferedReader from FileReader
        BufferedReader br = new BufferedReader(new FileReader(fin));

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] lineSplit = line.split(":");

            String[] entities = lineSplit[1].substring(1).split(",");
            ArrayList<String> entList = new ArrayList<String>();

            entList.addAll(Arrays.asList(entities));
            map.put(lineSplit[0], entList);
        }

        br.close();
        return map;
    }

    private static void storeQueryHashMap(String queryFile, String outputName) {
        try {
            HashMap<String, ArrayList<String>> map = createQueryHashMap(new File(queryFile));
            String saveAddress = queryFile.substring(0, 11) + "/serialized/" + outputName;
            serializeHashMap(map, saveAddress);
        } catch (IOException ex) {
            Logger.getLogger(ProcessInputFiles.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void storeDocumentHashMap(String documentFile, String outputName) {
        try {
            HashMap<String, ArrayList<String>> map = createDocumentHashMap(new File(documentFile));
            String saveAddress = documentFile.substring(0, 11) + "/serialized/" + outputName;
            serializeHashMap(map, saveAddress);
        } catch (IOException ex) {
            Logger.getLogger(ProcessInputFiles.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void storeQueryRelatedDocumentHashMap(String[] letorFiles, String outputName) {
        try {
            File[] input = new File[letorFiles.length];
            for (int i = 0; i < input.length; i++) {
                input[i] = new File(letorFiles[i]);
            }
            HashMap<String, ArrayList<String>> map = createQueryRelatedDocumentHashMap(input);
            String saveAddress = letorFiles[0].substring(0, 11) + "/serialized/" + outputName;
            serializeHashMap(map, saveAddress);
        } catch (IOException ex) {
            Logger.getLogger(ProcessInputFiles.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
    This method takes a hashmap and saves it to the given address 
    the file Extention is .ser which is automatically added to the file
     */
    private static void serializeHashMap(HashMap<String, ArrayList<String>> map, String address) {
        try {
            FileOutputStream fileOut = new FileOutputStream(address + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in" + address + ".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /*
    deserializing the hashmap from the given address and return it to the caller
    if object is not of the type Hashmap then exception will be raised
     */
    public static HashMap<String, ArrayList<String>> deserializeHashMap(String address) {
        try {
            HashMap<String, ArrayList<String>> map;
            FileInputStream fileIn = new FileInputStream(address + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            map = (HashMap<String, ArrayList<String>>) in.readObject();
            in.close();
            fileIn.close();
            return map;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return null;
        }
    }

    /* 
    LOAD QUERY FILES AND CREATE HASHMAP
    THEN SAVE THE OBJECTS OF HASHMAPS USING SERIALIZATION
    HASHMAPS CAN BE LOADED INTO MEMORY WHENEVER NEEDED
     */
    public static void queryHashMapCreateAndSaveRunner() {
        String[] queryFiles = {"./data/2007/2007-queries.Linked", "./data/2008/2008-queries.Linked"};
        String[] outputNames = {"2007-queries-Hashmap", "2008-queries-Hashmap"};
        for (int i = 0; i < queryFiles.length; i++) {
            storeQueryHashMap(queryFiles[i], outputNames[i]);
        }
    }

    /* 
    LOAD Letor S1-S5 query-doc FILES AND CREATE HASHMAP
    THEN SAVE THE OBJECTS OF HASHMAPS USING SERIALIZATION
    HASHMAPS CAN BE LOADED INTO MEMORY WHENEVER NEEDED
     */
    public static void queryRelatedDocumentHashMapCreateAndSaveRunner() {
        String[][] letorFiles = {{"./data/2007/letor/S1.txt", "./data/2007/letor/S2.txt", "./data/2007/letor/S3.txt", "./data/2007/letor/S4.txt", "./data/2007/letor/S5.txt"}, {"./data/2008/letor/S1.txt", "./data/2008/letor/S2.txt", "./data/2008/letor/S3.txt", "./data/2008/letor/S4.txt", "./data/2008/letor/S5.txt"}};
        String[] outputNames = {"2007-queryRelatedDocuments-Hashmap", "2008-queryRelatedDocuments-Hashmap"};
        for (int i = 0; i < letorFiles.length; i++) {
            storeQueryRelatedDocumentHashMap(letorFiles[i], outputNames[i]);
        }
    }

    /* 
    LOAD Document FILES AND CREATE HASHMAP
    THEN SAVE THE OBJECTS OF HASHMAPS USING SERIALIZATION
    HASHMAPS CAN BE LOADED INTO MEMORY WHENEVER NEEDED
     */
    public static void documentHashMapCreateAndSaveRunner() {
        String[] documentFiles = {"./data/2007/MQ2007Entities.txt", "./data/2008/MQ2008Entities.txt"};
        String[] outputNames = {"2007-documents-Hashmap", "2008-documents-Hashmap"};
        for (int i = 0; i < documentFiles.length; i++) {
            storeDocumentHashMap(documentFiles[i], outputNames[i]);
        }
    }

    /*
    CREATES A HASHMAP OF THE DOCUMENTS ENTITIES FROM THE GIVEN FILE
     */
    private static HashMap<String, ArrayList<String>> createDocumentHashMap(File fin) throws IOException {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        // Construct BufferedReader from FileReader
        BufferedReader br = new BufferedReader(new FileReader(fin));

        String line = null;
        while ((line = br.readLine()) != null) {
            String docId = line.substring(0, line.indexOf("\t"));
            String entitiesWithConf = line.substring(line.indexOf("\t", line.indexOf("\t") + 1) + 1);
//            System.out.println(entitiesWithConf);
            String[] ent_conf = entitiesWithConf.split(" ");
            ArrayList<String> entList;
            if (map.containsKey(docId)) {
                entList = map.get(docId);
            } else {
                entList = new ArrayList<>();
            }
            for (String temp : ent_conf) {
//                System.out.println(temp);
//                System.out.println(temp.split("\\|")[0]);
//                System.out.println(temp.substring(0, temp.indexOf("|")));
                entList.add(temp.split("\\|")[0]);
            }
            map.put(docId, entList);
        }
        br.close();
        return map;
    }

    /*
    CREATES A HASHMAP OF THE QUERY-DOCUMENTS ENTITIES FROM THE GIVEN FILE
    EACH QUERY IS RELATED TO SOME DOCUMENTS
    QUERY IS THE KEY AND ARRAY LIST OF DOCUMENTS IS THE VALUE OF THE HASHMAP
     */
    private static HashMap<String, ArrayList<String>> createQueryRelatedDocumentHashMap(File[] fin) throws IOException {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        // Construct BufferedReader from FileReader
        for (File input : fin) {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line = null;
            while ((line = br.readLine()) != null) {
                String qId = line.split(" ")[1].substring(4);
                String docId = line.split(" ")[50];
                ArrayList<String> docList;
                if (map.containsKey(qId)) {
                    docList = map.get(qId);
                } else {
                    docList = new ArrayList<>();
                }
                docList.add(docId);
                map.put(qId, docList);
            }
            br.close();
            System.out.println(map.keySet().size() + " qid are added in total");
            int total=0;
            for(String key : map.keySet()){
                total += map.get(key).size();
            }
            System.out.println("total number of query-document relation " + total);
        }
        return map;
    }
}
