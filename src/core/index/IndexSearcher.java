/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.index;

/**
 *
 * @author Parastoo
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.text.ParseException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.FSDirectory;


public class IndexSearcher {
    
    public  org.apache.lucene.search.IndexSearcher IndexSearcher =null;
    
    public IndexSearcher(String indexDir) throws IOException{
        IndexReader rdr = DirectoryReader.open(FSDirectory.open(Paths.get(indexDir)));
        IndexSearcher = new org.apache.lucene.search.IndexSearcher(rdr);
        IndexSearcher.setSimilarity(new BM25Similarity()); 
    }
    
    public  TopDocs search(String q) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        
        
        QueryParser parser = new QueryParser("contents" ,new StandardAnalyzer());                                 
	Query query = parser.parse(q);
        TopDocs hits = IndexSearcher.search(query, 100);
        
        return hits;
        
        
    }
    
    public double scoring(String q ) throws org.apache.lucene.queryparser.classic.ParseException, IOException{
        
        
        QueryParser parser = new QueryParser("contents" ,new StandardAnalyzer());                                 
	Query query = parser.parse(q);
        TopDocs hits = IndexSearcher.search(query, 100);
        for (ScoreDoc result : IndexSearcher.search(query, Integer.MAX_VALUE).scoreDocs) {
            System.out.println(result.doc + "\t" + result.score);
        }
        return 0 ;
        
        
    }
    
    
    public void searchQueries(String queryDirectory, String directoryIndex) throws FileNotFoundException, IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException{
        BufferedReader br = new BufferedReader(new FileReader(queryDirectory));
        StringBuilder sb = new StringBuilder();
        String line ; 
        Writer writer = null;
        
        
    }
    
    
    
    public void close() throws IOException{
        IndexSearcher.getIndexReader().close();
    }


  
}
