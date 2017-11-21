/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.index;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Parastoo
 */
public class WikiPediaIndexer {
    protected Map<String, List<String>> termFrequency;
    
    public void calculateTF(String query, String document) throws Exception{
        List<String> queryTokens = Stemmer.stem(query);
        List<String> documentTokens = Stemmer.stem(document);
        
        
        
    }
}
