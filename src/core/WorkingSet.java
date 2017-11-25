/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


/**
 *
 * @author amin
 */
public class WorkingSet {

    private HashMap<String, ArrayList<String>> query;
    private HashMap<String, ArrayList<String>> document;
    private HashMap<String, ArrayList<String>> queryRelatedDocument;
    private HashMap<String, String> queryTopic;
    private String indexDirectory;
    private String workingSetName;
    private String SFileNumber;

    public String getSFileNumber() {
        return SFileNumber;
    }
    
    public String getIndexDirectory(){
        return indexDirectory;
    }
    
    public HashMap<String,String> getQueryTopic() {
        return queryTopic;
    }
    
    public HashMap<String, ArrayList<String>> getQueryRelatedDocument() {
        return queryRelatedDocument;
    }

    public HashMap<String, ArrayList<String>> getQuery() {
        return query;
    }

    public HashMap<String, ArrayList<String>> getDocument() {
        return document;
    }
    
    public String getWorkingSetName() {
        return workingSetName;
    }

    public int getNumberOfAllExistingDocsInSet() {
        return document.keySet().size();
    }

    public int getNumberOfDocsContainingEntity(String entity) {
        int count = 0;
        for (String docId : document.keySet()) {
            if (document.get(docId).contains(entity)) {
                count++;
            }
        }
        return count;
    }

    public int getEntityFrequencyInAllDocs(String entity) {
        int count = 0;
        for (String docId : document.keySet()) {
            count += getEntityFrequencyInDoc(docId, entity);
        }
        return count;
    }

    /*
    workingSetName IS NAME OF THE SET IN WHICH THE RELEVANT HASHMAPS ARE SOTRED
    THE CONSTRUCTOR TAKES THE workingSetName AND LOAD THE HASHMAP INTO ITS HASHMAPS
    AND IS THEN RETURNED TO CALLER ALONG WITH ALL NEEDED METHODS
    ADDRESS : "./data/2007/serialized/"
     */
    public WorkingSet(String workingSetName, String SFileNumber) throws FileNotFoundException, UnsupportedEncodingException {
        this.workingSetName = workingSetName;
        this.SFileNumber = SFileNumber;
        loadFiles(workingSetName);
        writeDocHashMapToFile();
    }

    public ArrayList<String> getQueryEntities(String qId) {
        if(query.get(qId) != null)
            return query.get(qId);
        else
            return new ArrayList<String>();
    }

    public ArrayList<String> getDocumentEntities(String docId) {
        if(document.get(docId) != null)
            return document.get(docId);
        else
            return new ArrayList<>();
    }

    public int getEntityFrequencyInDoc(String docId, String entity) {
        ArrayList<String> docEnt = new ArrayList<>(document.get(docId));
        ArrayList<String> entityList = new ArrayList<>();
        entityList.add(entity);
        docEnt.retainAll(entityList);
        return docEnt.size();
    }

    public HashSet<String> ArrayListIntersection(ArrayList<String> qEntities, ArrayList<String> dEntities) {
        if(qEntities==null || dEntities==null){
            return new HashSet<>();
        }
        HashSet<String> q = new HashSet<>(qEntities);
        HashSet<String> d = new HashSet<>(dEntities);
        q.retainAll(d);
        return q;
    }

    public int getNumberOfDocEntities(String docId) {
        if(document.get(docId) != null)
            return document.get(docId).size();
        else 
            return 0;
    }

    /*
    WORKING SET NAME IS 2007 OR 2008
    OR ANY OTHER SET WHICH MAY BE ADDED LATER
     */
    private void loadFiles(String workingSetName) {
        System.out.println("Loading Serialized Files " + workingSetName);
        String address = "./data/" + workingSetName + "/serialized/";
//        "./data/2007/serialized/2007-queryRelatedDocuments-Hashmap"
        indexDirectory = "./data/"+ workingSetName +"/index/";
        query = ProcessInputFiles.deserializeHashMap(address + workingSetName + "-queries-Hashmap");
        System.out.println("Loading Query Hashmap Done");
        queryRelatedDocument = ProcessInputFiles.deserializeHashMap(address + workingSetName + "-queryRelatedDocuments-Hashmap"+this.SFileNumber);
        System.out.println("Loading queryRelatedDocument Hashmap Done");
        document = ProcessInputFiles.deserializeHashMap(address + workingSetName + "-documents-Hashmap");
        System.out.println("Loading Documents Hashmap Done");
        queryTopic = ProcessInputFiles.deserializeHashMapStringString(address + workingSetName + "-queriesTopic-Hashmap");
        System.out.println("Loading queriesTopic Hashmap Done");
    }

    private void writeDocHashMapToFile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("the-file-name-queryrelated.txt", "UTF-8");
        Iterator it = queryRelatedDocument.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String data = (String) pair.getKey()+": ";
            data += String.join(",", queryRelatedDocument.get(pair.getKey()));
            writer.println(data);
        }
        writer.close();
    }
}
