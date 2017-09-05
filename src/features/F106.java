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
public class F106 {
    
    private WorkingSet workingSet;

    public F106(WorkingSet ws) {
        workingSet = ws;
    }

    public double execute(String qid, String docid) {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getQueryEntities(docid);
        HashSet<String> intersection = workingSet.ArrayListIntersection(qEntities, dEntities);
        double sum = 0;
        double c = workingSet.getNumberOfAllExistingDocsInSet();
        for (String entity : intersection) {
            double f1 = ((double) workingSet.getEntityFrequencyInDoc(docid, entity)) / ((double)workingSet.getNumberOfDocEntities(docid));
            double f2 = (c / ((double) workingSet.getEntityFrequencyInAllDocs(entity)));
            
            sum += Math.log10((f1 * f2) +1);
        }
        return sum;
    }
    
}
