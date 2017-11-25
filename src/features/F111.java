/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.WorkingSet;
import core.index.Stemmer;
import core.index.WikiPediaRanker;
import java.util.ArrayList;
import java.util.List;
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
            if(countLiteral != null)
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
            if(countLiteral != null)
                wikiComment = countLiteral.getString();
            System.out.println(wikiComment);

        }
        //}

        return wikiAbstract + " " +wikiComment;
    }
    public String print(String qid, String docid) throws Exception {
        if(workingSet.getQueryTopic().containsKey(qid)){
            String q = workingSet.getQueryTopic().get(qid);
            List<String> dEntity = workingSet.getDocumentEntities(docid);
            List<String> qEntity = workingSet.getQueryEntities(qid);
            String output ="" ;//qid +" :\n";
            WikiPediaRanker wi = new WikiPediaRanker();
            int result = 0;
            for (String dentity :dEntity) {
                
                String docAbstarct = executeQuery(dentity);
                wi.insertDocAbstract(dentity, docAbstarct);
                
            }
            
            for (String dentity :dEntity) {
                wi.index();
                List<String> queryTokens = Stemmer.stem(q);

                for(String queryToken : queryTokens )
                    result += wi.search(queryToken,dentity);

                for(String qentity :qEntity){
                    output += qentity + "-" + dentity + " "+result + "\n";
                }
            }
            

    //        output += "------------------------------------\n";
            return output;
        }
        else
            return "";
    }
}
