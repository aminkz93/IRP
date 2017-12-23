/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.WorkingSet;
import core.index.IndexSearcher;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.DefaultSimilarity;

/**
 *
 * @author Parastoo
 */
public class F113 {
    private WorkingSet workingSet;
    private IndexSearcher indexSearcher;
    private HashMap<String, Double> resultsDoc;

    public F113(WorkingSet ws) throws IOException {
        workingSet = ws;
        indexSearcher = new IndexSearcher(workingSet.getIndexBM25CommentDirectory(), new BM25Similarity());

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
    
    
}
