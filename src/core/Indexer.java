/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;



public class Indexer {
    private final IndexWriter writer;
    
    public Indexer(String dir) throws IOException {
        Directory indexDir = FSDirectory.open(Paths.get(dir));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
        cfg.setOpenMode(OpenMode.CREATE);
        writer = new IndexWriter(indexDir, cfg);
    }
    
    protected Document getDocument(String docId ,ArrayList<String> documentEntites) throws Exception {
        Document doc = new Document();  
        
        String result = StringUtils.join(documentEntites, ", ");
        doc.add(new TextField("contents", result, Field.Store.YES)); 
        doc.add(new StringField("DocId", docId,Field.Store.YES));

        return doc;
    }

    private void indexFile(String docId ,ArrayList<String> documentEntites) throws Exception {
            Document doc = getDocument(docId , documentEntites);
            writer.addDocument(doc);
    }
    
    public int index(HashMap<String, ArrayList<String>> document) throws Exception {
        for (String key : document.keySet()) {
            indexFile(key, document.get(key));
        }
        return writer.numDocs();
    }
    
  
    public void close() throws IOException {
	writer.close();
    }
   
    

}

