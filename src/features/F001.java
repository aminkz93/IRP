/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.CoreServices;
import core.WorkingSet;
import java.util.ArrayList;

/**
 *
 * @author amin
 */
public class F001 {
    private WorkingSet workingSet;
    
    public F001(WorkingSet ws){
        workingSet = ws;
    }
    
    public  double getNumberOfGoogleSearchResult(String query){
        return CoreServices.getNumberOfSearchResult(query);
    }
    
    public String print(){
        String output = "";
        for(String q : workingSet.getEntityTitle().keySet()){
            try{
                double result = getNumberOfGoogleSearchResult(workingSet.getEntityTitle().get(q).get(0));
                if(result ==0){
                    result = getNumberOfGoogleSearchResult(workingSet.getEntityTitle().get(q).get(1));
                }
                output += q + " " + result;
            }
            catch(Exception ex){
                System.out.println("this entity did not complete: "+ q);
            }
        }
//        output += "------------------------------------\n";
        return output;
    }
}
