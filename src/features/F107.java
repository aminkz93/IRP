/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.index.IndexSearcher;
import core.WorkingSet;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

/**
 *
 * @author Parastoo
 */
public class F107 {

    private WorkingSet workingSet;
    private IndexSearcher indexSearcher;
    private HashMap<String, Double> resultsDoc;

    public F107(WorkingSet ws) throws IOException {
        workingSet = ws;
        indexSearcher = new IndexSearcher(workingSet.getIndexDirectory());

    }

    public void execute(String qid) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        resultsDoc = new HashMap<>();
        if (!workingSet.getQueryEntities(qid).isEmpty()) {
            String qEntities = StringUtils.join(workingSet.getQueryEntities(qid), ", ");
            TopDocs result = indexSearcher.search(qEntities);
            for (ScoreDoc sc : result.scoreDocs) {
                Document doc = indexSearcher.IndexSearcher.doc(sc.doc);
                double score = sc.score;
                resultsDoc.put(doc.get("DocId"), score);
            }
        }

    }

    public double execute(String qid, String docid) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        
            execute(qid);
            if (resultsDoc.containsKey(docid)) 
                return resultsDoc.get(docid);
            else 
                return 0;
            
        
    }
    public String print(String qid, String docid) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        String qEntities = StringUtils.join(workingSet.getQueryEntities(qid), ", ");
        String output = qid + " _ " + docid + " :\n";
        double result;

        if (resultsDoc.containsKey(docid)) {
            result = resultsDoc.get(docid);
        } else {
            result = 0;
        }

        output += "\t" + result + "\n";

        output += "------------------------------------\n";
        return output;
    }

//    private ArrayList<String> intersection(ArrayList<String> qEntities, ArrayList<String> dEntities){
//        ArrayList<String> output =  new ArrayList<>();
//        for(String s : qEntities){
//            if(dEntities.contains(s)){
//                output.add(s);
//            }
//        }
//        return output;
//    }
}
