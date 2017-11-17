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
public class F099 {

    private WorkingSet workingSet;

    public F099(WorkingSet ws) {
        workingSet = ws;
    }

    public double execute(String qid, String docid) {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
        HashSet<String> intersection = workingSet.ArrayListIntersection(qEntities, dEntities);
        double sum = 0;
        double c = workingSet.getNumberOfAllExistingDocsInSet();
        for (String entity : intersection) {
            sum += Math.log10(c / ((double) workingSet.getNumberOfDocsContainingEntity(entity)));
        }
        return sum;
    }
    
//    public String print(String qid, String docid) {
//        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
//        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
//        HashSet<String> intersection = workingSet.ArrayListIntersection(qEntities, dEntities);
//        String output = qid + " _ " + docid + " :\n";
//        double result;
//        double c = workingSet.getNumberOfAllExistingDocsInSet();
//        for (String entity : intersection) {
//            result = Math.log10(c / ((double) workingSet.getNumberOfDocsContainingEntity(entity)));
//            output += "\t" + entity + " : " + result + "\n";
//        }
//        output += "------------------------------------\n";
//        return output;
//    }
    
    public String print(String qid, String docid) {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
        HashSet<String> intersection = workingSet.ArrayListIntersection(qEntities, dEntities);
        String output = ""; //qid + " _ " + docid + " :\n";
        double result;
        double c = workingSet.getNumberOfAllExistingDocsInSet();
        for (String entity : intersection) {
            result = Math.log10(c / ((double) workingSet.getNumberOfDocsContainingEntity(entity)));
            output +=entity + "-" + entity+ " " + result +  "\n";
        }
//        output += "------------------------------------\n";
        return output;
    }
}
