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
public class F094 {
    private WorkingSet workingSet;

    public F094(WorkingSet ws) {
        workingSet = ws;
    }
    public static String executeQuery(String qEntity) throws Exception {  
        
        String type = null ;
        //if(core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)) != null){
//            String entityTitle = core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)); 
//            entityTitle = entityTitle.replace("'", "");
            String query ="PREFIX dbr: <http://dbpedia.org/resource/> " +
                           "PREFIX dbo: <http://dbpedia.org/ontology/> " +
                           "SELECT (?type) " +
                           "WHERE { ?subject rdf:type ?type . ?subject dbo:wikiPageID "+
                           qEntity +
                           ".}";
            ResultSet results = core.CoreServices.executeSparqlQuery(query);
            while (results.hasNext()) {
                QuerySolution soln = results.next();

                Literal countLiteral = ((Literal) soln.get("type"));
                type = countLiteral.getString();
                System.out.println(type);

            }
        //}

        return type;
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
