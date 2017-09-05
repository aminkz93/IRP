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
public class F096 {
    
    private WorkingSet workingSet;
    
    public F096(WorkingSet ws){
        workingSet = ws;
    }
    
    public double execute(String qid,String docid){
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getQueryEntities(docid);
        HashSet<String> intersection = workingSet.ArrayListIntersection(qEntities, dEntities);
        double sum = 0;
        for(String entity : intersection){
            sum += Math.log10(workingSet.getEntityFrequencyInDoc(docid, entity) + 1);
        }
        return sum;
        //log(c(e ,de)+1) must be caculated if c(e, de) is provided
    }
    
}
