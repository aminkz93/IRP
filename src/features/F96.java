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
public class F96 {
    public static int execute(String qid,String docid){
        ArrayList<String> qEntities = Entity.getQueryEntities(qid);
        ArrayList<String> dEntities = Entity.getQueryEntities(docid);
        
        return intersection(qEntities, dEntities).size();
        //log(c(e ,de)+1) must be caculated if c(e, de) is provided
    }
    
    private static ArrayList<String> intersection(ArrayList<String> qEntities, ArrayList<String> dEntities){
        ArrayList<String> output =  new ArrayList<String>();
        for(String s : qEntities){
            if(dEntities.contains(s)){
                output.add(s);
            }
        }
        return output;
    }
    
}
