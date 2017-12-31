/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.WorkingSet;
import java.util.ArrayList;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

/**
 *
 * @author Parastoo
 */
public class F092 {
    private WorkingSet workingSet;

    public F092(WorkingSet ws) {
        workingSet = ws;
    }
//    public static String executeQuery(String qEntity) throws Exception {  
//        
//        String count = null ;
//        //if(core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)) != null){
////            String entityTitle = core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)); 
////            entityTitle = entityTitle.replace("'", "");
//            String query ="PREFIX dbr: <http://dbpedia.org/resource/> " +
//                           "PREFIX dbo: <http://dbpedia.org/ontology/> " +
//                           "SELECT (count(?object) as ?count) " +
//                           "WHERE { ?subject dbo:wikiPageExternalLink ?object . ?object dbo:wikiPageID "+
//                           qEntity +
//                           ".}";
//            ResultSet results = core.CoreServices.executeSparqlQuery(query);
//            while (results.hasNext()) {
//                QuerySolution soln = results.next();
//
//                Literal countLiteral = ((Literal) soln.get("count"));
//                count = countLiteral.getString();
//                System.out.println(count);
//
//            }
//        //}
//
//        return count;
//    }
    public int executeQuery(String qEntity) throws Exception {  
        
        int count =0 ;
        if(workingSet.getEntityTitle().containsKey(qEntity)){
            for (String value : workingSet.getEntityTitle().get(qEntity)) {
                if(workingSet.getExternalLinks().containsKey(value)){
                    count = workingSet.getExternalLinks().get(value).size();
                }

            }
        }

        return count;
    }
    public String[] print(String qid) throws Exception {
        String [] results = new String[4];
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        int result;
        String stringOutput = "";
        double mean = 0;
        double min = 0;
        double max = 0;
        int count = 0;
        for (String entity : qEntities) {
            result = executeQuery(entity);
            stringOutput += entity + " " + result + "\n";
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
    public String print() throws Exception {
       
        String output = ""; 
        int result;
        for (String entity : workingSet.getEntityTitle().keySet()) {
            result = executeQuery(entity);
            output += workingSet.getEntitytitle(entity) + " " + result + "\n";
        }
        return output;
    }
}
