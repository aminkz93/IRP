/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.jena.tdb.store.Hash;

/**
 *
 * @author Parastoo
 */
public class WikiPediaRanker {
    
    public Map<String,Map<String, Integer> > termFrequencies =  new HashMap<>();
    public Map<String, Integer> invertedDocFrequency = new HashMap<>() ;
    public Map<String, String> docsAbstract = new HashMap<>();
    
    public void insertDocAbstract(String docEntity,String docAbstract){
        docsAbstract.put(docEntity,docAbstract);
    }
    
    public void index() throws Exception{
        for (Map.Entry<String, String> entry : docsAbstract.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            List<String> documentTokens = Stemmer.stem(value);
            Map<String ,Integer> termFrequency = new HashMap<>();
            for (int i = 0; i < documentTokens.size(); i++) {
                if(termFrequency.containsKey(documentTokens.get(i))){
                    termFrequency.put(documentTokens.get(i),termFrequency.get(documentTokens.get(i))+1);
                }
                else{
                    termFrequency.put(documentTokens.get(i),1);
                }
            }
            termFrequencies.put(key, termFrequency);
        }
        
    }
    
    public void calcIdf() throws Exception{
        
        Iterator it = termFrequencies.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String key = (String) pair.getKey();
            Map<String, Integer> termFrequency = (Map<String , Integer>) pair.getValue();
            Iterator it2 = termFrequencies.entrySet().iterator();
            for (int i = 0; i < 10; i++) {
                if(invertedDocFrequency.containsKey(key)){
                    invertedDocFrequency.put(key, invertedDocFrequency.get(key)+1);
                }
                else
                    invertedDocFrequency.put(key,1);
            }
            
        }
        
        
    }
    
    public  int search( String queryToken, String docEntity ){
        if(termFrequencies.get(docEntity).containsKey(queryToken))
             return termFrequencies.get(docEntity).get(queryToken);
        else 
            return 0;
    }
    
    public float Tf_Idf (String token,String docEntity){
        float result = 0;
        float tf =0;
        float idf = 0;
        if(termFrequencies.get(termFrequencies).containsKey(token))
            tf = (float) Math.log(termFrequencies.get(termFrequencies).get(token));
        if(invertedDocFrequency.containsKey(token))
            idf = (float) Math.log(termFrequencies.size()/(float)invertedDocFrequency.get(token));
        return tf*idf;
    }
    
   
    
}
