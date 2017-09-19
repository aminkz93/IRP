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
public class F101 {
    private WorkingSet workingSet;

    public F101(WorkingSet ws) {
        workingSet = ws;
    }

    public double execute(String docid) {
        return workingSet.getNumberOfDocEntities(docid);
    }
    public String print(String docid){
        String output = docid + " : " +workingSet.getNumberOfDocEntities(docid);
        output += "------------------------------------\n";
        return output;
    }
    
}
