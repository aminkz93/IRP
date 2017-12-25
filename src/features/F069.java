package features;

import core.CoreServices;
import core.WorkingSet;
import core.index.IndexSearcher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
    public String print() throws IOException {
        String result = "";
        int i =0;
        for (String entity :workingSet.getEntityTitle().keySet()) {
            for (String value : workingSet.getEntityTitle().get(entity)) {
                if(workingSet.getPageLength().containsKey(value))
//                    result += entity +" "+ workingSet.getPageLength().get(value)+"\n";
                     result += workingSet.getEntitytitle(entity) +" "+ workingSet.getPageLength().get(value)+"\n";
            }
            System.out.println(i++);
        }
        return result;
    }
    
}
