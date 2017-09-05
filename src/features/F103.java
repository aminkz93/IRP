/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.WorkingSet;
import java.util.ArrayList;

/**
 *
 * @author amin
 */
public class F103 {
    private WorkingSet workingSet;

    public F103(WorkingSet ws) {
        workingSet = ws;
    }

    public double execute(String qid) {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        double sum = 0;
        double c = workingSet.getNumberOfAllExistingDocsInSet();
        for (String entity : qEntities) {
            sum += Math.log10(Math.log10(c / ((double) workingSet.getNumberOfDocsContainingEntity(entity))));
        }
        return sum;
    }
}
