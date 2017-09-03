/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author amin
 */
public class WorkingSet {

    private HashMap<String, ArrayList<String>> query;// = new HashMap<String, ArrayList<String>>();
    private HashMap<String, ArrayList<String>> document;
    private HashMap<String, ArrayList<String>> queryRelatedDocument;
    private String setAddress;

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

    public WorkingSet(String setAddress) {
        this.setAddress = setAddress;
        loadFiles();
    }

    public ArrayList<String> getQueryEntities(String qId) {
        return query.get(qId);
    }

    public ArrayList<String> getDocumentEntities(String docId) {
        return document.get(docId);
    }

    public int getEntityFrequencyInDoc(String docId, String entity) {
        ArrayList<String> docEnt = new ArrayList<>(document.get(docId));
        ArrayList<String> entityList = new ArrayList<>();
        entityList.add(entity);
        docEnt.retainAll(entityList);
        return docEnt.size();
    }

    public HashSet<String> ArrayListIntersection(ArrayList<String> qEntities, ArrayList<String> dEntities) {
        HashSet<String> q = new HashSet<>(qEntities);
        HashSet<String> d = new HashSet<>(dEntities);
        q.retainAll(d);
        return q;
    }

    public int getNumberOfDocEntities(String docId) {
        return document.get(docId).size();
    }

    private void loadFiles() {

    }
}
