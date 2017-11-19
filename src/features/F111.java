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
public class F111 {
    private WorkingSet workingSet;

    public F111(WorkingSet ws) {
        workingSet = ws;
    }
    public static String executeQuery(String qEntity) throws Exception {  
        
        String wikiAbstract = null ;
        String wikiComment = null;
        //if(core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)) != null){
//            String entityTitle = core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)); 
//            entityTitle = entityTitle.replace("'", "");
            String AbstractQuery ="PREFIX dbr: <http://dbpedia.org/resource/>\n" +
                            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                            "SELECT ?abstract\n" +
                            "WHERE {?subject dbo:abstract ?abstract .\n" +
                            "       ?subject dbo:wikiPageID "+
                            qEntity
                            +".\n" +
                            "       FILTER langMatches(lang(?abstract),'en') \n" +
                            "\n" +
                            "}";
            ResultSet results = core.CoreServices.executeSparqlQuery(AbstractQuery);
            while (results.hasNext()) {
                QuerySolution soln = results.next();

                Literal countLiteral = ((Literal) soln.get("abstract"));
                wikiAbstract = countLiteral.getString();
                System.out.println(wikiAbstract);

            }
            String CommentQuery ="PREFIX dbr: <http://dbpedia.org/resource/>\n" +
                            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                            "\n" +
                            "\n" +
                            "SELECT ?comment\n" +
                            "WHERE {?subject rdfs:comment ?comment .\n" +
                            "       ?subject dbo:wikiPageID "+
                            qEntity+
                            ".\n" +
                            "       FILTER langMatches(lang(?comment),'en') \n" +
                            "\n" +
                            "}";
            results = core.CoreServices.executeSparqlQuery(AbstractQuery);
            while (results.hasNext()) {
                QuerySolution soln = results.next();

                Literal countLiteral = ((Literal) soln.get("comment"));
                wikiComment = countLiteral.getString();
                System.out.println(wikiComment);

            }
        //}

        return wikiAbstract + " " +wikiComment;
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
