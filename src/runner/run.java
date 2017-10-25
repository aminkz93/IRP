/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.CoreServices;
import core.ProcessInputFiles;
import core.WorkingSet;
import features.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import core.index.DocumentsIndexer;

/**
 *
 * @author amin
 */
public class run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        CoreServices core = new CoreServices();
//        System.out.print(F23.getPageLegth(14653));
////        System.out.println(core.getPageInfoHtml(3649517));
//        System.out.println(F24.getNumberOfPageWatchers(3649517));
//        System.out.println(core.parseInt("123,234"));
//        CoreServices.getNumberOfSearchResult("link");
//        retainallTest();
//        CreateOutput out = new CreateOutput(new WorkingSet("2007","S1"));
//        LetorOutput letorOutput = new  LetorOutput(new  WorkingSet("2007", "S1"));
//        letorOutput.createLetorFileWithNewFeatures(47);
//       out.outputF095();
//        out.outputF096();
//        out.outputF097();
//        out.outputF098();
//        out.outputF099();
//        out.outputF100();
//        out.outputF101();
//        out.outputF102();
//        out.outputF103();
//        out.outputF104();
//        out.outputF105();
//        out.outputF106();
//        out.outputF107();
            QentityDentity ee = new QentityDentity(new WorkingSet("2008","S1"));
            ee.createEntityEntityPairFile();
//        WorkingSet ws = new WorkingSet("2007");
//       System.out.println(ws.getQuery().containsKey("315"));
//       for(String qid : ws.getQuery().keySet()){
//           System.out.println(qid + " : ");
//           for(String ent : ws.getQuery().get(qid)){
//               System.out.println(ent);
//           }
//           System.out.println("--------------------------");
//       }
//            createFiles();
    }

    public static void retainallTest() {
        //        String[] qu = {"ios", "what", "android", "apple"};
//
//        for (int i = 0; i < 4; i++) {
//            System.out.println(F1.getNumberOfGoogleSearchResult(qu[i]));
//        }
//        
//        List<Integer> docsId = new ArrayList<Integer>();
//        docsId.add(5079506);
//        docsId.add(18787);
//        docsId.add(42010);
//        docsId.add(2126660);
//        System.out.println(F3.getCategoriesSimilarity(18646, docsId));
//        System.out.println(CoreServices.getTitle(6013119));
//        F8.executeQuery();
//        ProcessInputFiles.queryHashMapCreateAndSaveRunner();
//        ProcessInputFiles.documentHashMapCreateAndSaveRunner();
//        ProcessInputFiles.createDocumentHashMap(new File("./data/2008/MQ2008Entities.txt"));
//    HashMap<String, ArrayList<String>> map = ProcessInputFiles.deserializeHashMap("./data/2008/serialized/2008-documents-Hashmap");
//    System.out.println(map.keySet().size());
//    ProcessInputFiles.queryRelatedDocumentHashMapCreateAndSaveRunner();
//        HashMap<String, ArrayList<String>> map = ProcessInputFiles.deserializeHashMap("./data/2007/serialized/2007-queryRelatedDocuments-Hashmap");
//        System.out.println(map.keySet().size());
//        double total=0;
//        for (String key : map.keySet()) {
//            total += map.get(key).size();
//        }
//        System.out.println(total);
//        testDocumentsEntitiesForRepeatedEntity();

    }
    
    public static void retainAlltest(){
        ArrayList<String> test = new ArrayList<>();
        test.add("amin");
        test.add("ali");
        test.add("amir");
        test.add("amin");
        test.add("amin");
        ArrayList<String> t = new ArrayList<>();
        t.add("amin");
        test.retainAll(t);
        System.out.println(test.size());
        System.out.println(test.get(0));
    }

    public static void testDocumentsEntitiesForRepeatedEntity() {
        HashMap<String, ArrayList<String>> map = ProcessInputFiles.deserializeHashMap("./data/2007/serialized/2007-queries-Hashmap");
        for (String key : map.keySet()) {
            int listSize = map.get(key).size();
            Set<String> set = new HashSet<String>(map.get(key));
            int setSize = set.size();
            System.out.println("list : " + listSize + "\t setSize: " + setSize + "\t" + (setSize == listSize));
            if (setSize != listSize) {
                System.out.println("key of repeated : " + key);
            }
        }
    }
    
    public static void createFiles(){
        ProcessInputFiles.documentHashMapCreateAndSaveRunner();
        ProcessInputFiles.queryHashMapCreateAndSaveRunner();
        ProcessInputFiles.queryRelatedDocumentHashMapCreateAndSaveRunner();
        core.index.DocumentsIndexer.documentsIndexerRunner();
    }

}
