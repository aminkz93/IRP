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
public class F090 {
    private WorkingSet workingSet;

    public F090(WorkingSet ws) {
        workingSet = ws;
    }
    public static String executeQuery(String qEntity) throws Exception {  
        
        String count = null ;
        //if(core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)) != null){
//            String entityTitle = core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)); 
//            entityTitle = entityTitle.replace("'", "");
            String query ="PREFIX dbr: <http://dbpedia.org/resource/> " +
                           "PREFIX dbo: <http://dbpedia.org/ontology/> " +
                           "SELECT (count(?object) as ?count) " +
                           "WHERE { ?subject dbo:wikiPageExternalLink ?object . ?subject dbo:wikiPageID "+
                           qEntity +
                           ".}";
            ResultSet results = core.CoreServices.executeSparqlQuery(query);
            while (results.hasNext()) {
                QuerySolution soln = results.next();

                Literal countLiteral = ((Literal) soln.get("count"));
                count = countLiteral.getString();
                System.out.println(count);

            }
        //}

        return count;
    }
    public String print(String qid) throws Exception {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        String output = qid +" :\n";
        String result;
        for (String entity : qEntities) {
            result = executeQuery(entity);
            if(result != null)
                output += "\t" + entity + " : " + result + "\n";
            else
                output += "\t" + entity + " : " + "null" + "\n";
        }
        output += "------------------------------------\n";
        return output;
    }
}
