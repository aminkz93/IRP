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
    private static String abstractHashMap2007BM25  = "./data/2007/index/Abstract/BM25";
    private static String abstractHashMap2007TFIDF = "./data/2007/index/Abstract/TFIDF";
    private static String abstractHashMap2008BM25  = "./data/2008/index/Abstract/BM25";
    private static String abstractHashMap2008TFIDF = "./data/2008/index/Abstract/TFIDF";
    private static String commentHashMap2007BM25   = "./data/2007/index/Comment/BM25";
    private static String commentHashMap2007TFIDF  = "./data/2007/index/Comment/TFIDF";
    private static String commentHashMap2008BM25   = "./data/2008/index/Comment/BM25";
    private static String commentHashMap2008TFIDF  = "./data/2008/index/Comment/TFIDF";
    private static HashMap<String, String> documentAbstractHashMap;
    private static HashMap<String, String> documentCommentHashMap;
    private static HashMap<String, ArrayList<String>> documentHashMap;
    

    public static void documentsIndexerRunner() {
        try {
            documentHashMap = ProcessInputFiles.deserializeHashMap("./data/2007/serialized/2007-documents-Hashmap");
            indexDocumentHashMap(documentHashMap,indexDirectory2007);
            
            documentHashMap = ProcessInputFiles.deserializeHashMap("./data/2008/serialized/2008-documents-Hashmap");
            indexDocumentHashMap(documentHashMap,indexDirectory2008);
            
            documentAbstractHashMap = ProcessInputFiles.deserializeHashMapStringString("./data/2007/serialized/2007-documentsAbstract-Hashmap");
            indexDocumentHashMapStringString(documentAbstractHashMap, abstractHashMap2007BM25 , new BM25Similarity());
            indexDocumentHashMapStringString(documentAbstractHashMap, abstractHashMap2007TFIDF, new DefaultSimilarity());
            
            documentAbstractHashMap = ProcessInputFiles.deserializeHashMapStringString("./data/2008/serialized/2008-documentsAbstract-Hashmap");
            indexDocumentHashMapStringString(documentAbstractHashMap, abstractHashMap2008BM25 , new BM25Similarity());
            indexDocumentHashMapStringString(documentAbstractHashMap, abstractHashMap2008TFIDF, new DefaultSimilarity());
            
            documentCommentHashMap = ProcessInputFiles.deserializeHashMapStringString("./data/2007/serialized/2007-documentsComment-Hashmap");
            indexDocumentHashMapStringString(documentCommentHashMap, commentHashMap2007BM25 , new BM25Similarity());
            indexDocumentHashMapStringString(documentCommentHashMap, commentHashMap2007TFIDF, new DefaultSimilarity());
            
            documentCommentHashMap = ProcessInputFiles.deserializeHashMapStringString("./data/2008/serialized/2008-documentsComment-Hashmap");
            indexDocumentHashMapStringString(documentCommentHashMap, commentHashMap2008BM25 , new BM25Similarity());
            indexDocumentHashMapStringString(documentCommentHashMap, commentHashMap2008TFIDF, new DefaultSimilarity());
            
            
        } catch (Exception ex) {
            Logger.getLogger(DocumentsIndexer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void indexDocumentHashMap(HashMap<String, ArrayList<String>> documentHashMap, String indexDir) throws IOException, Exception {
        Indexer indexer = new Indexer(indexDir , new BM25Similarity());
        indexer.indexHashMapStringArrayList(documentHashMap);
        indexer.close();
        
    }
    
    private static void indexDocumentHashMapStringString(HashMap<String, String> documentHashMap, String indexDir , Similarity similarity) throws IOException, Exception {
        Indexer indexer = new Indexer(indexDir , similarity);
        indexer.indexHashMapStringString(documentHashMap);
        indexer.close();
        
    }

}
