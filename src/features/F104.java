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
public class F104 {

    private WorkingSet workingSet;

    public F104(WorkingSet ws) {
        workingSet = ws;
    }

    public double execute(String qid) {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        double sum = 0;
        double c = workingSet.getNumberOfAllExistingDocsInSet();
        for (String entity : qEntities) {
            sum += Math.log10((c / ((double) workingSet.getEntityFrequencyInAllDocs(entity))) + 1);
        }
        return sum;
    }
    public String print(String qid) {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        String output = qid + " :\n";
        double c = workingSet.getNumberOfAllExistingDocsInSet();
        double result;
        for (String entity : qEntities) {
            result = Math.log10((c / ((double) workingSet.getEntityFrequencyInAllDocs(entity))) + 1);
            output += "\t" + entity + " : " + result + "\n";
        }
//        output += "------------------------------------\n";
        return output;
    }

}
