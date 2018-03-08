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
public class F021 {
    private WorkingSet workingSet;

    public F021(WorkingSet ws) {
        workingSet = ws;
    }
    public static String executeQuery(String qEntity , String dEntity) throws Exception {  
        
        String count = null ;
        String query ="PREFIX  dbo:  <http://dbpedia.org/ontology/>\n" +
                        "PREFIX  dbp:  <http://dbpedia.org/property/>\n" +
                        "\n" +
                        "select (COUNT(DISTINCT ?predicate) AS ?Totalpredicate)\n" +
                        "WHERE\n" +
                        "  { ?entity1  dbo:wikiPageID  "+
                        qEntity+
                        " .\n" +
                        "    ?entity2  dbo:wikiPageID  "+
                        dEntity+
                        " .\n" +
                        "    ?entity1   ?predicate  ?entity2\n" +
                        "  }";
        TupleQueryResult results = core.CoreServices.executeSparqlQueryLocal(query);
        while (results.hasNext()) {
            BindingSet soln = results.next();

            Value countLiteral =  soln.getValue("Totalpredicate");
            count = countLiteral.toString();
            System.out.println(count);
        }
        return count;
    }
    public String print(String qid, String did) throws Exception {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(did);
        String output = qid + "-" + did +" :\n";
        String result;
        for (String qentity : qEntities) {
            for (String dentity : dEntities) {
                result = String.valueOf(1/(float)1+Float.parseFloat(executeQuery(qentity,dentity))+Float.parseFloat(executeQuery(dentity,qentity)));
                if(result != null)
                    output += "\t" + qentity + "-" + dentity +" : " + result + "\n";
                else
                    output += "\t" + qentity + "-" + dentity +" : " + "null" + "\n";
            }
        }
        output += "------------------------------------\n";
        return output;
    }
}