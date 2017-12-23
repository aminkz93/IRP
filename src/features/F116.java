/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.ProcessInputFiles;
import core.WorkingSet;
import static features.F021.executeQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class F116 {
    private WorkingSet workingSet;
    HashSet<String> pairs = new HashSet<>();
    HashMap<String, String> titles = new HashMap<>();
    public F116(WorkingSet ws) {
        workingSet = ws;
    }
    public void execute(){
        
        for (String key : workingSet.entityTitle.keySet()) {
            try{
            String title1 = workingSet.entityTitle.get(key).get(0);
            String title2 = workingSet.entityTitle.get(key).get(1);
            if(workingSet.getTypes().containsKey(title1))
            {
                titles.put(key, title1);
                System.out.println("1"+key);
            }
            else if(workingSet.getTypes().containsKey(title2))
            {
                titles.put(key, title2);
                System.out.println("2"+key);
            }
            else{
                System.out.println("out of hashmap");
                titles.put(key, title1);
            }
                }
            catch(Exception ex) {
                System.out.println("error");
            }
        }
        
        ProcessInputFiles.serializeStringHashMap(titles, "./data/Total/serialized/Titles-Hashmap");
    }
    public String print(String qid, String did) throws Exception {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(did);
        
       String output =""; // qid + " " + did +" :\n";
        String result;
        for (String qentity : qEntities) {
            for (String dentity : dEntities) {
                if(!pairs.contains(qentity+"-"+dentity)){
                    output += workingSet.entityTitle.get(qentity) + " " + workingSet.entityTitle.get(dentity)+"\n";
                    pairs.add(qentity+"-"+dentity);
                }
            }
        }
//        output += "------------------------------------\n";
        return output;
    }
}
