
package features;

import core.SPARQLUtil;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

/**
 *
 * @author Parastoo
 */
public class F8 {
    
    public static void executeQuery() throws Exception {
            
//        String query = " 	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
//				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
//				+ " PREFIX : <http://dbpedia.org/resource/>"
//				+ " PREFIX d: <http://dbpedia.org/ontology/> "
//				+ " SELECT distinct ?albumName ?artistName "
//				+ " WHERE "
//				+ " { "
//				+ " ?album d:producer :Timbaland . "
//				+ " ?album d:musicalArtist ?artist ."
//				+ " ?album rdfs:label ?albumName . "
//				+ " ?artist rdfs:label ?artistName ."
//				+ " FILTER ( lang(?artistName) = \"en\")"
//				+ " FILTER ( lang(?albumName) = \"en\" )" + " }";
        
        String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                       "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
                       "PREFIX dbp: <http://dbpedia.org/resource/>" +
                       "PREFIX dbpo: <http://dbpedia.org/ontology/>" +
                       "SELECT (count(distinct ?y) as ?count)" +
                       "WHERE"+
                       "{"+
                       "dbp:Barack_Obama dbpo:wikiPageExternalLink ?y ."+
                       "}";
        ResultSet results = core.CoreServices.executeSparqlQuery(query);
        ResultSetFormatter.out(System.out, results);

//        while (results.hasNext()) {
//
//            QuerySolution soln = results.next();
//
//            Literal albumName = soln.getLiteral("albumName");
//            Literal artistName = soln.getLiteral("artistName");
//
//            System.out.println(albumName+"--"+artistName);
//
//        }

    }
}
