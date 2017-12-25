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

/**
 *
 * @author Parastoo
 */
public class F114 {
    private WorkingSet workingSet;
    private IndexSearcher indexSearcher;
    private HashMap<String, Double> resultsDoc;

    public F114(WorkingSet ws) throws IOException {
        workingSet = ws;
        indexSearcher = new IndexSearcher(workingSet.getIndexTFIDFCommentDirectory(), new BM25Similarity());

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
    public String[] print(String qid, String docid) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        String [] results = new String[4];
        List<String> dEntity = workingSet.getDocumentEntities(docid);
        List<String> qEntity = workingSet.getQueryEntities(qid);
        String q = workingSet.getQueryTopic().get(qid);
        String stringOutput = "";
        double mean = 0;
        double min = 0;
        double max = 0;
        int count = 0;
        double result;
        for (String qentity : qEntity) {
            for (String dentity : dEntity) {
                try{
                    if(workingSet.getEntityTitle().containsKey(dentity)){
                        for(String title : workingSet.getEntityTitle().get(dentity)){
                            if (resultsDoc.containsKey(title)) {
                                result = resultsDoc.get(title);
                            } else {
                                result = 0;
                            }
                            stringOutput += qentity + "-" + dentity + " " + result + "\n";
                            mean += result;
                            if(min > result)
                                min =result;
                            if(max < result)
                                max =result;
                            count++;
                        }
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                    stringOutput = "";
                    mean = 0;
                    min = 0;
                    max = 0;
                    count = 0;
                }
            }
        }
        if (count != 0)
            mean = mean /(double)count ;
        results[0] = stringOutput;
        results[1] = String.valueOf(min);
        results[2] = String.valueOf(max);
        results[3] = String.valueOf(mean);
        
        return results;
    }
}
