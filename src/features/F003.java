
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
    private static float compareCategories(ArrayList<String> queryCategories, ArrayList<String> docCategories) {
        float result = 0;
        try {
            for(String q : queryCategories){
                for(String d : docCategories){
                    if(q.equals(d))
                        result ++;
                }
            }
            
            return result ;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
        
    }

    public  float getCategoriesSimilarity(String qId, String docId) {
        float matchedCategories = 0;
        try{
            ArrayList<String> queryCategories = workingSet.getCategory().get(workingSet.getEntityTitle().get(qId).get(0));
            if(queryCategories.isEmpty()){
                queryCategories = workingSet.getCategory().get(workingSet.getEntityTitle().get(qId).get(1));
            }

            ArrayList<String> docCategories = workingSet.getCategory().get(workingSet.getEntityTitle().get(docId).get(0));
            if(queryCategories.isEmpty()){
                docCategories = workingSet.getCategory().get(workingSet.getEntityTitle().get(docId).get(1));
            }

                System.out.println(F003.compareCategories(queryCategories , docCategories));
                matchedCategories += F003.compareCategories(queryCategories , docCategories);
                
            float result = matchedCategories / ((float)docCategories.size() + queryCategories.size() -matchedCategories);
            
            return result;
        }
        catch(Exception ex){
            return 0;
        }
        
    }
    
    public String print(String qid,String docid){
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
        String stringOutput = "";
        double mean = 0;
        double min = 0;
        double max = 0;
        int count = 0;
        double result;
        for(String q : qEntities){
            for(String d : dEntities ){
                result = getCategoriesSimilarity(q, d);
                stringOutput+= q + "-"+ d +" " + result + "\n";
                mean += result;
                if(min > result)
                    min =result;
                if(max < result)
                    max =result;
                count++;
            }
        }
        return stringOutput;
    }
//    public double mean(String qid,String docid){
//        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
//        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
//        double output = 0;
//        double result;
//        int count = 0;
//        for(String q : qEntities){
//            for(String d : dEntities ){
//                result = getCategoriesSimilarity(q, d);
//                output += result;
//                count ++;
//            }
//        }
//        return output/(double)count;
//    }
//    public double min(String qid,String docid){
//        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
//        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
//        double output = 0;
//        double result;
//        for(String q : qEntities){
//            for(String d : dEntities ){
//                result = getCategoriesSimilarity(q, d);
//                if(result < output)
//                    output = result;
//            }
//        }
//        return output;
//    }
//    public double max(String qid,String docid){
//        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
//        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
//        double output = 0;
//        double result;
//        for(String q : qEntities){
//            for(String d : dEntities ){
//                result = getCategoriesSimilarity(q, d);
//                if(result > output)
//                    output = result;
//            }
//        }
//        return output;
//    }
}
