/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.WorkingSet;
import core.sqlConnection.SqlConnection;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Parastoo
 */
public class SFileMerger {
    public HashSet<String> EntityPairs = new HashSet<>();
    public void merge() throws FileNotFoundException, UnsupportedEncodingException{
        for (int i = 1; i < 6; i++) {
            WorkingSet workingSet = new WorkingSet("2007", "S"+i);
            for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
                for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
                    ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
                    ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
                    for(String q : qEntities){
                        for(String d : dEntities ){
                            if(!q.equals("") && !q.equals(" ") && !d.equals("")&& !d.equals(" "))
                                EntityPairs.add(q+"-"+d);
                        }
                    }

                }
            }
        }
        for (int i = 1; i < 6; i++) {
            WorkingSet workingSet = new WorkingSet("2008", "S"+i);
            for (String qid : workingSet.getQueryRelatedDocument().keySet()) {
                for (String docid : workingSet.getQueryRelatedDocument().get(qid)) {
                    ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
                    ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
                    for(String q : qEntities){
                        for(String d : dEntities ){
                            EntityPairs.add(q+"-"+d);
                        }
                    }

                }
            }
        }
        SqlConnection.getInstance();
        SqlConnection.execute("delete from idonlinesparql");
        String query= "";
        for (String entityPair :EntityPairs) {
            query = "insert into idonlinesparql(entityPair) values ('"+
                    entityPair+"')";
            SqlConnection.execute(query);
        }
        
    }
}
