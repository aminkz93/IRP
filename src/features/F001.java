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
    
    public  double getNumberOfGoogleSearchResult(String query) throws Exception{
        return CoreServices.getNumberOfSearchResult(query);
    }
    
    public String execute(String entity){
        String output = "";
        try{
            String entityName = workingSet.getEntityTitle().get(entity).get(0).replaceAll("_", " ");
            System.out.println(entityName);
            double result = getNumberOfGoogleSearchResult(entityName);

            output += entity + " " + result + "\n";
        }
        catch(Exception ex){
            output += entity + " " + "exception\n";
        }
        
        return output;
    }
    public String print(){
        String output = "";
        for(String q : workingSet.getEntityTitle().keySet()){
            try{
                double result = getNumberOfGoogleSearchResult(workingSet.getEntityTitle().get(q).get(0));
                
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
