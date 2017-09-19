/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.WorkingSet;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author amin
 */
public class F105 {
    private WorkingSet workingSet;

    public F105(WorkingSet ws) {
        workingSet = ws;
    }

    public double execute(String qid, String docid) {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
        HashSet<String> intersection = workingSet.ArrayListIntersection(qEntities, dEntities);
        double sum = 0;
        double c = workingSet.getNumberOfAllExistingDocsInSet();
        for (String entity : intersection) {
            double f2 = Math.log10((c / ((double) workingSet.getNumberOfDocsContainingEntity(entity)))+1);
            double f1 = Math.log10(((double) workingSet.getEntityFrequencyInDoc(docid, entity)) / ((double)workingSet.getNumberOfDocEntities(docid)) );
            sum += f1 * f2;
        }
        return sum;
    }
    public String print(String qid,String docid){
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
        HashSet<String> intersection = workingSet.ArrayListIntersection(qEntities, dEntities);
        String output = qid + " _ " + docid + " :\n";
        double c = workingSet.getNumberOfAllExistingDocsInSet();
        double result;
        for(String entity : intersection){
            double f2 = Math.log10((c / ((double) workingSet.getNumberOfDocsContainingEntity(entity)))+1);
            double f1 = Math.log10(((double) workingSet.getEntityFrequencyInDoc(docid, entity)) / ((double)workingSet.getNumberOfDocEntities(docid)) );
            result = f1 * f2;
            output+= "\t" + entity + " : " + result + "\n";
        }
        output += "------------------------------------\n";
        return output;
    }
    
}
