/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.WorkingSet;
import java.util.ArrayList;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;

/**
 *
 * @author Parastoo
 */
public class F115 {
    private WorkingSet workingSet;

    public F115(WorkingSet ws) {
        workingSet = ws;
    }
    public static ArrayList<String> executeQuery(String qEntity) throws Exception {  
        
        ArrayList<String> count = new ArrayList<>();
        try{
            String query ="PREFIX dbo: <http://dbpedia.org/ontology/> " +
                           "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                           "PREFIX dbr: <http://dbpedia.org/resource/> " +
                           "SELECT ?wikiID " +
                           "WHERE { ?subject dbo:wikiPageID "+
                           "?predicate ?object. ?subject dbo:wikiPageID "+
                           qEntity+
                           " ; rdfs:seeAlso ?seeAlso ."+
                           "?seeAlso dbo:wikiPageID ?wikiID }";
            TupleQueryResult results = core.CoreServices.executeSparqlQueryLocal(query);
            while (results.hasNext()) {
                BindingSet soln = results.next();
                Value countValue = soln.getValue("count");
                count.add(countValue.toString());
                System.out.println(count);
            }
        }
        catch(Exception ex){
            System.out.println(qEntity +" did not execute!");
        }
            
        return count;
    }
//    public String print(String qid) throws Exception {
//        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
//        String output = qid +" :\n";
//        String result;
//        for (String entity : qEntities) {
//            result = executeQuery(entity);
//            if(result != null)
//                output += "\t" + entity + " : " + result + "\n";
//            else
//                output += "\t" + entity + " : " + "null" + "\n";
//        }
//        output += "------------------------------------\n";
//        return output;
//    }

}
