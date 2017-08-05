
package features;

import core.CoreServices;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Parastoo
 */
public class F3 {


    private static float compareCategories(String[] queryCategories, String[] docCategories) {
        float result = 0;
        for(String q : queryCategories){
            for(String d : docCategories){
                if(q.equals(d))
                    result ++;
            }
        }
        return result / (float)Math.max(queryCategories.length, docCategories.length);
    }

    public static float getCategoriesSimilarity(int queryId, List<Integer> docsId) {
        float matchedCategories = 0;
        List<String[]> docsCategories = new ArrayList<String[]>();
        String[] queryCategories = CoreServices.getPageCategory(queryId);
        for(int docId : docsId){
            String[] docCategories = CoreServices.getPageCategory(docId);
            docsCategories.add(docCategories);
        }
        
        for (String[] item : docsCategories) {
            matchedCategories += F3.compareCategories(queryCategories , item);
        }
        
        return matchedCategories / (float)docsCategories.size();
        
    }
}
