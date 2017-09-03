/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.Entity;
import java.util.ArrayList;

/**
 *
 * @author amin
 */
public class F98 {
    
    public static double execute(String qid,String docid){
        ArrayList<String> dEntities = Entity.getQueryEntities(docid);
        double f95 = (double)features.F96.execute(qid, docid);
        return f95/ ((double) dEntities.size()); //must be tested to make sure it returns double or int -:?
        
    }
    
}
