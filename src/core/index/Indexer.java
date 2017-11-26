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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;



public class Indexer {
    private final IndexWriter writer;
    
    public Indexer(String dir , Similarity similarity) throws IOException {
        Directory indexDir = FSDirectory.open(Paths.get(dir));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
        cfg.setSimilarity(similarity);
        cfg.setOpenMode(OpenMode.CREATE);
        writer = new IndexWriter(indexDir, cfg);
    }
    
    protected Document getDocumentArrayList(String docId ,ArrayList<String> documentEntites) throws Exception {
        Document doc = new Document();  
        
        String result = StringUtils.join(documentEntites, ", ");
        doc.add(new TextField("contents", result, Field.Store.YES)); 
        doc.add(new StringField("DocId", docId,Field.Store.YES));

        return doc;
    }
    
    protected Document getDocumentString(String dEntity ,String document) throws Exception {
        Document doc = new Document();  
        if(document != null){
            doc.add(new TextField("contents", document, Field.Store.YES)); 
            doc.add(new StringField("DocId", dEntity,Field.Store.YES));
        }
            return doc;
        
    }

    private void indexFileArrayList(String docId ,ArrayList<String> documentEntites) throws Exception {
            Document doc = getDocumentArrayList(docId , documentEntites);
            writer.addDocument(doc);
    }
    
    private void indexFileString(String dEntity ,String document) throws Exception {
            Document doc = getDocumentString(dEntity , document);
            writer.addDocument(doc);
    }
    
    public int indexHashMapStringArrayList(HashMap<String, ArrayList<String>> document) throws Exception {
        for (String key : document.keySet()) {
            indexFileArrayList(key, document.get(key));
        }
        return writer.numDocs();
    }
    
    public int indexHashMapStringString(HashMap<String, String> document) throws Exception {
        for (String key : document.keySet()) {
            indexFileString(key, document.get(key));
        }
        return writer.numDocs();
    }
  
    public void close() throws IOException {
	writer.close();
    }
   
    

}

