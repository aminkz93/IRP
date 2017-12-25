/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.WorkingSet;
import core.index.IndexSearcher;
import core.index.Stemmer;
import core.index.WikiPediaRanker;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;

/**
 *
 * @author Parastoo
 */
public class F112 {
    private WorkingSet workingSet;
    private IndexSearcher indexSearcher;
    private HashMap<String, Double> resultsDoc;

    public F112(WorkingSet ws) throws IOException {
        workingSet = ws;
        indexSearcher = new IndexSearcher(workingSet.getIndexTFIDFAbstractDirectory(), new DefaultSimilarity());

    }

    public void execute(String qid) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        resultsDoc = new HashMap<>();
        if (!workingSet.getQueryTopic().get(qid).isEmpty()) {
            String query = workingSet.getQueryTopic().get(qid);
            TopDocs result = indexSearcher.search(query);
            for (ScoreDoc sc : result.scoreDocs) {
                Document doc = indexSearcher.IndexSearcher.doc(sc.doc);
                double score = sc.score;
                resultsDoc.put(doc.get("DocId"), score);
            }
        }

    }
    public String print(String qid, String docid) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        List<String> dEntity = workingSet.getDocumentEntities(docid);
        List<String> qEntity = workingSet.getQueryEntities(qid);
        String q = workingSet.getQueryTopic().get(qid);
        String output = "";
        double result;
        for (String qentity : qEntity) {
            for (String dentity : dEntity) {
                if(workingSet.getEntityTitle().containsKey(dentity)){
                    for(String title : workingSet.getEntityTitle().get(dentity)){
                        if (resultsDoc.containsKey(title)) {
                            result = resultsDoc.get(title);
                            System.out.println(title);
                        } else {
                            result = 0;
                        }
//                        output += qentity + "-" + dentity + " " + result + "\n";
                        output += workingSet.getEntitytitle(qentity) + "-" + workingSet.getEntitytitle(dentity) + " " + result + "\n";
                    }
                }
                
            }
        }
        

//        output += "------------------------------------\n";
        return output;
    }
    
    
    
//    private WorkingSet workingSet;
//
//    public F112(WorkingSet ws) {
//        workingSet = ws;
//    }
//    public static String executeQuery(String qEntity) throws Exception {  
//        
//        String wikiAbstract = null ;
//        String wikiComment = null;
//        String AbstractQuery ="PREFIX dbr: <http://dbpedia.org/resource/>\n" +
//                        "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
//                        "SELECT ?abstract\n" +
//                        "WHERE {?subject dbo:abstract ?abstract .\n" +
//                        "       ?subject dbo:wikiPageID "+
//                        qEntity
//                        +".\n" +
//                        "       FILTER langMatches(lang(?abstract),'en') \n" +
//                        "\n" +
//                        "}";
//        ResultSet results = core.CoreServices.executeSparqlQuery(AbstractQuery);
//        while (results.hasNext()) {
//            QuerySolution soln = results.next();
//
//            Literal countLiteral = ((Literal) soln.get("abstract"));
//            if(countLiteral != null)
//                wikiAbstract = countLiteral.getString();
//
//        }
////        String CommentQuery ="PREFIX dbr: <http://dbpedia.org/resource/>\n" +
////                        "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
////                        "\n" +
////                        "\n" +
////                        "SELECT ?comment\n" +
////                        "WHERE {?subject rdfs:comment ?comment .\n" +
////                        "       ?subject dbo:wikiPageID "+
////                        qEntity+
////                        ".\n" +
////                        "       FILTER langMatches(lang(?comment),'en') \n" +
////                        "\n" +
////                        "}";
////        results = core.CoreServices.executeSparqlQuery(AbstractQuery);
////        while (results.hasNext()) {
////            QuerySolution soln = results.next();
////
////            Literal countLiteral = ((Literal) soln.get("comment"));
////            if(countLiteral != null)
////                wikiComment = countLiteral.getString();
////            System.out.println(wikiComment);
////
////        }
//        //}
//
//        return wikiAbstract ; //+ " " +wikiComment;
//    }
//    public String print(String qid, String docid) throws Exception {
//        if(workingSet.getQueryTopic().containsKey(qid)){
//            String q = workingSet.getQueryTopic().get(qid);
//            List<String> dEntity = workingSet.getDocumentEntities(docid);
//            List<String> qEntity = workingSet.getQueryEntities(qid);
//            String output ="" ;
//            WikiPediaRanker wi = new WikiPediaRanker();
//            for (String dentity :dEntity) {
//                String docAbstarct = executeQuery(dentity);
//                wi.insertDocAbstract(docAbstarct,dentity);
//
//            }
//            int result = 0;
//            for (String dentity :dEntity) {
//                wi.index();
//                wi.calcIdf();
//                List<String> queryTokens = Stemmer.stem(q);
//                for (String queryToken : queryTokens ) {
//                    result += wi.Tf_Idf(queryToken, dentity);
//                }
//                
//                for(String qentity :qEntity){
//                    output += qentity + "-" + dentity +" "+ result + "\n";
//                }
//            }
//            return output;
//        }
//        else
//            return "";
//    }    
}
