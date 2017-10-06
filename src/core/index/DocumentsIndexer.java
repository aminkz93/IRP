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

/**
 *
 * @author amin
 */
public class DocumentsIndexer {

    private static String indexDirectory2007 = "./data/2007/index/";
    private static String indexDirectory2008 = "./data/2008/index/";
    private static HashMap<String, ArrayList<String>> documentHashMap;

    public static void documentsIndexerRunner() {
        try {
            documentHashMap = ProcessInputFiles.deserializeHashMap("./data/2007/serialized/2007-documents-Hashmap");
            indexDocumentHashMap(documentHashMap,indexDirectory2007);
            documentHashMap = ProcessInputFiles.deserializeHashMap("./data/2008/serialized/2008-documents-Hashmap");
            indexDocumentHashMap(documentHashMap,indexDirectory2008);
        } catch (Exception ex) {
            Logger.getLogger(DocumentsIndexer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void indexDocumentHashMap(HashMap<String, ArrayList<String>> documentHashMap, String indexDir) throws IOException, Exception {
        Indexer indexer = new Indexer(indexDir);
        indexer.index(documentHashMap);
        indexer.close();
        
    }

}
