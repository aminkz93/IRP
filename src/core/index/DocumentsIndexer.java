/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.index;

import core.ProcessInputFiles;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;

/**
 *
 * @author amin
 */
public class DocumentsIndexer {

    private static String indexDirectory2007       = "./data/2007/index/Total/BM25";
    private static String indexDirectory2008       = "./data/2008/index/Total/BM25";
    private static String abstractHashMapBM25      = "./data/Total/index/Abstract/BM25";
    private static String abstractHashMapTFIDF     = "./data/Total/index/Abstract/TFIDF";
    private static String commentHashMapBM25      = "./data/Total/index/Comment/BM25";
    private static String commentHashMapTFIDF     = "./data/Total/index/Comment/TFIDF";
    private static String abstractAddress          = "./data/Total/Abstracts.Entity";
    private static String commentAddress           = "./data/Total/ShortAbstracts.Entity";
    
    

    public static void documentsIndexerRunner() {
        try {
//            documentHashMap = ProcessInputFiles.deserializeHashMap("./data/2007/serialized/2007-documents-Hashmap");
//            indexDocumentHashMap(documentHashMap,indexDirectory2007);
//            
//            documentHashMap = ProcessInputFiles.deserializeHashMap("./data/2008/serialized/2008-documents-Hashmap");
//            indexDocumentHashMap(documentHashMap,indexDirectory2008);
            
            
            indexDocument(abstractAddress, abstractHashMapBM25 , new BM25Similarity());
            indexDocument(abstractAddress, abstractHashMapTFIDF, new DefaultSimilarity());
            
            indexDocument(commentAddress, commentHashMapBM25 , new BM25Similarity());
            indexDocument(commentAddress, commentHashMapTFIDF, new DefaultSimilarity());
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(DocumentsIndexer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void indexDocumentHashMap(HashMap<String, ArrayList<String>> documentHashMap, String indexDir) throws IOException, Exception {
        Indexer indexer = new Indexer(indexDir , new BM25Similarity());
        indexer.indexHashMapStringArrayList(documentHashMap);
        indexer.close();
        
    }
    
    private static void indexDocument(String documentAddress, String indexDir , Similarity similarity) throws IOException, Exception {
        Indexer indexer = new Indexer(indexDir , similarity);
        indexer.indexDocument(documentAddress);
        indexer.close();
        
    }

}
