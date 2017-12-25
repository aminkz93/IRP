package features;

import core.CoreServices;
import core.WorkingSet;
import core.index.IndexSearcher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by amin on 7/21/17.
 */
public class F069 {
    private WorkingSet workingSet;

    public F069(WorkingSet ws) throws IOException {
        workingSet = ws;
    }
    public static int getPageLegth(int pageEntity){
        return CoreServices.parseInt(CoreServices.getLength(pageEntity));
        
    }
    public String[] print(String docid){
        String [] results = new String[4];
        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
        String stringOutput = "";
        double mean = 0;
        double min = 0;
        double max = 0;
        int count = 0;
        double result =0;
        for(String d : dEntities ){
            for (String value : workingSet.getEntityTitle().get(d)) {
                if(workingSet.getPageLength().containsKey(value)){
                    result = Double.parseDouble(workingSet.getPageLength().get(value));
                }
            }
            stringOutput += d + " " + result +"\n";
            mean += result;
            if(min > result)
                min =result;
            if(max < result)
                max =result;
            count++;
        }
        
        mean = mean /(double)count ;
        results[0] = stringOutput;
        results[1] = String.valueOf(min);
        results[2] = String.valueOf(max);
        results[3] = String.valueOf(mean);
        
        return results;
    }
    
}
