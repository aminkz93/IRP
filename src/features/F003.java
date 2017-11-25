
package features;

import core.CoreServices;
import core.WorkingSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 *
 * @author Parastoo
 */
public class F003 {

    private WorkingSet workingSet;
    
    public F003(WorkingSet ws){
        workingSet = ws;
    }
    private static float compareCategories(String[] queryCategories, String[] docCategories) {
        float result = 0;
        try {
            for(String q : queryCategories){
                for(String d : docCategories){
                    if(q.equals(d))
                        result ++;
                }
            }
            return result / (float)Math.max(queryCategories.length, docCategories.length);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
        
    }

    public static float getCategoriesSimilarity(String qId, String docId) {
        float matchedCategories = 0;
//        List<String[]> docsCategories = new ArrayList<String[]>();
        String[] queryCategories = CoreServices.getPageCategory(Integer.parseInt(qId));
        
        String[] docCategories = CoreServices.getPageCategory(Integer.parseInt(docId));
//        docsCategories.add(docCategories);
        
        
//        for (String[] item : docsCategories) {
            System.out.println(F003.compareCategories(queryCategories , docCategories));
            matchedCategories += F003.compareCategories(queryCategories , docCategories);
//        }
        
        return matchedCategories / (float)docCategories.length + queryCategories.length -matchedCategories;
        
    }
    
    public String print(String qid,String docid){
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
//        String output = qid + " _ " + docid + " :\n";
        String output = "";
        double result;
        for(String q : qEntities){
            for(String d : dEntities ){
                result = getCategoriesSimilarity(q, d);
//                output+= "\t" + q + " : " + result + "\n";
                output+= q + "-"+ d +" " + result + "\n";
            }
        }
//        output += "------------------------------------\n";
        return output;
    }
}
