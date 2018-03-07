/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.ProcessInputFiles;
import core.WorkingSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import core.index.Stemmer;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import core.index.WikiPediaRanker;
import features.F116;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import static org.apache.jena.tdb.sys.FileRef.file;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;


/**
 *
 * @author amin
 */
public class run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

//        ProcessInputFiles.entityTitleHashMapCreateAndSaveRunner();
//        SummerizeFile.summerize("instance_types_en.ttl", "SummerizedTypes.Entity", new WorkingSet("2007","S1"));
       
//
//        br.close();
//        WorkingSet ws = new WorkingSet("2008", "S1");
//        System.out.println(ws.getEntityTitle().get("1047024"));
//        test1();
//        testsparqlQuery();
//        ProcessInputFiles.entityTitleHashMapCreateAndSaveRunner();
//           DocumentsIndexer.documentsIndexerRunner();
//           ProcessInputFiles.categoryHashMapCreateAndSaveRunner();
//        ProcessInputFiles.pageLengthHashMapCreateAndSaveRunner();
//        
//        ProcessInputFiles.categoryHashMapCreateAndSaveRunner();
//        int count = 0;
//        WorkingSet ws = new WorkingSet("2008", "S1");
//        System.out.println("total entity is:"+ ws.entityTitle.size());
//        for (String key :ws.entityTitle.keySet()) {
//            ArrayList<String> values = ws.entityTitle.get(key);
//            if( ws.getCategory().containsKey(values.get(0)) ){
//                count +
//                
//            }
////            
//            else if(values.size()>=2 && ws.getCategory().containsKey(values.get(1))){
//                count++;
//        
//            }
//            else 
//                System.out.println(key);
////            
//        }
//        System.out.println("the number of used:"+count);
//        System.out.println("category size:"+ws.getCategory().size());
        
//        WorkingSet ws =new WorkingSet("2008","S1");
//        ws.getEntitytitle("29486330");
        
//        F116 f = new F116(new WorkingSet("2007","S1"));
//        f.execute();
        //
//        out.outputF092();
//        out.outputF094();
//           WorkingSet ws = new WorkingSet("2007", "S1");
//           HashSet<String> hs = new HashSet<>();
//           for(String key : ws.getEntityTitle().keySet()){
//               for (String value: ws.getEntityTitle().get(key)) {
//                   hs.add(value);
//               }
//           }
//           System.out.println(hs.size());
//           File PageLength = new File("./data/Total/SummerizedTypes.Entity");
//           PageLength.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(new File("./data/Total/instance_types_en.ttl")));
        String line =null; 
        while((line = br.readLine())!= null )
        {
            if(line.contains("Long-tailed_duck")){
                System.out.println(line);
            }
        }
        br.close();
//        BufferedWriter bw = new BufferedWriter(new FileWriter(PageLength));
//        int i =0;
//        String line = null;
//        br.readLine();
//        while ((line = br.readLine()) != null) {
//            String[] lineSplit = line.split(">");
//            if(lineSplit.length>0){
//                String key = lineSplit[0].substring(29,lineSplit[0].length());
//                if(hs.contains(key)){
//                    bw.write(String.format("%s%n",line));
////                    hs.remove(key);
//                }
//            }
//        }
//        System.out.println("here");
//        System.out.println(hs.size());
//        for (String s : hs) {
//            System.out.println(s);
//        }
//        br.close();
//        bw.close();
//        
           
//           out.outputF069();
        
           
//          CreateOutput out = new CreateOutput(new WorkingSet("2008", "S1"));
//          out.outputF069();
//        testwikipediaIndexer();
//        WorkingSet ws =new WorkingSet("2008", "S1");
//        Iterator it = ws.getDocument().entrySet().iterator();
//        HashSet<String> hashset =  new HashSet<>();
//        int i=0;
//        double start = System.nanoTime();
//        for(String docid : ws.getDocument().keySet()){
//            
//            for(String entity :ws.getDocumentEntities(docid)){
//                
//                
//                if(!hashset.contains(entity)){
//                    i++;
//                    System.out.println(i);
////                    System.out.println(F112.executeQuery(entity));
//                    System.out.println("_____________________________________");
//                    
//                    hashset.add(entity);
//                }
//                if(i>50){
//                    break;
//                }
//                    
//            }
//            if(i>50)
//                break;
//            
//        }
//        System.out.println(System.nanoTime() - start);
//        
//        
//        for (int i = 0; i < 1; i++) {
//            
//            Map.Entry pair = (Map.Entry)it.next();
//            String data = (String) pair.getKey();
//            ArrayList<String> value = (ArrayList<String>) pair.getValue();
//            for(String s : value){
//                if(!hashset.contains(s)){
//                    System.out.println(F112.executeQuery(s));
//                    hashset.add(s);
//                }
//            }
//            System.out.println(hashset.size());
//        }
//        
//        
//        
//        System.out.println(F112.executeQuery("14849"));
//        System.out.println(F112.executeQuery("24113"));
//        System.out.println(F112.executeQuery("1917015"));
//        System.out.println(F112.executeQuery("86359"));
//        System.out.println(F112.executeQuery("11090"));
//        System.out.println(F112.executeQuery("17616"));
//        System.out.println(F112.executeQuery("2424150"));
//        System.out.println(F112.executeQuery("66719"));
//        System.out.println(F112.executeQuery("374662"));
//        System.out.println(F112.executeQuery("18973622"));
//        
//        test1();
//        test2();
//        CoreServices core = new CoreServices();
//        System.out.print(F23.getPageLegth(14653));
////        System.out.println(core.getPageInfoHtml(3649517));
//        System.out.println(F24.getNumberOfPageWatchers(3649517));
//        System.out.println(core.parseInt("123,234"));
//        CoreServices.getNumberOfSearchResult("link");
//        retainallTest();
        
//        CreateOutput out = new CreateOutput(new WorkingSet("2007","S1"));
        
//        LetorOutput letorOutput = new  LetorOutput(new  WorkingSet("2007", "S1"));
//        letorOutput.createLetorFileWithNewFeatures(47);
//        F003 f = new F003();
//        List<Integer> l = new ArrayList<>();
//        l.add(9888963);
//        l.add(1522020);
//        l.add(33898);
//        l.add(23223308);
//        l.add(21076839);
//        l.add(524024);
      //  f.getCategoriesSimilarity(6251,l);
//        System.out.println( F003.getCategoriesSimilarity(6251,l));
//           out.outputF078();
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

    public static void test1() throws FileNotFoundException, UnsupportedEncodingException, Exception{
//        ProcessInputFiles.queryTopicHashMapCreateAndSaveRunner();
        
        CreateOutput out = new CreateOutput(new WorkingSet("2007","S1"));
        out.outputF069();
        out.outputF092();
        out.outputF094();
        out.outputF003();
        out.outputF111();
        out.outputF112();
        out.outputF113();
        out.outputF114();
        
    }
    public static void test2() throws FileNotFoundException, UnsupportedEncodingException, Exception{
        ProcessInputFiles.queryTopicHashMapCreateAndSaveRunner();
        CreateOutput out = new CreateOutput(new WorkingSet("2007","S1"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        
        out = new CreateOutput(new WorkingSet("2007","S2"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        out = new CreateOutput(new WorkingSet("2007","S3"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        out = new CreateOutput(new WorkingSet("2007","S4"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        out = new CreateOutput(new WorkingSet("2007","S5"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        out = new CreateOutput(new WorkingSet("2008","S1"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        out = new CreateOutput(new WorkingSet("2008","S2"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        out = new CreateOutput(new WorkingSet("2008","S3"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        out = new CreateOutput(new WorkingSet("2008","S4"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        out = new CreateOutput(new WorkingSet("2008","S5"));
        out.outputF095();
        out.outputF096();
        out.outputF097();
        out.outputF098();
        out.outputF099();
        out.outputF100();
        out.outputF101();
        out.outputF102();
        out.outputF103();
        out.outputF104();
        out.outputF105();
        out.outputF106();
        out.outputF107();
        out.outputF108();
        
        
    }
    
    public static void testwikipediaIndexer() throws Exception{
        WikiPediaRanker wikiRanker = new WikiPediaRanker();
        wikiRanker.insertDocAbstract("1","this is a test abstract doc , set query to test it");
        wikiRanker.insertDocAbstract("2","this is a test2 abstract doc , this is for testing idf");
        
        wikiRanker.index();
        wikiRanker.calcIdf();
        List<String> queryTokens = Stemmer.stem("this is a query");
        for (String token : queryTokens) {
            wikiRanker.search(token, "1");
            wikiRanker.search(token, "3");
            wikiRanker.Tf_Idf(token, "1");
            wikiRanker.Tf_Idf(token, "2");
            wikiRanker.Tf_Idf(token, "3");
        }
        
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
//        ProcessInputFiles.documentHashMapCreateAndSaveRunner();
//        ProcessInputFiles.queryHashMapCreateAndSaveRunner();
//        ProcessInputFiles.queryRelatedDocumentHashMapCreateAndSaveRunner();
        ProcessInputFiles.queryTopicHashMapCreateAndSaveRunner();
        core.index.DocumentsIndexer.documentsIndexerRunner();
    }

    
    public static void testsparqlQuery() throws SQLException, IOException{
        String serverUrl = "http://localhost:7200";
        String repoID = "DBpedia";
        
        
        RemoteRepositoryManager manager = new RemoteRepositoryManager(serverUrl);
        manager.initialize();
        Repository repo = manager.getRepository(repoID);
        repo.initialize();
        
        
//        Repository db = new SailRepository(new MemoryStore());
//        db.initialize();

        // Open a connection to the database
        try (RepositoryConnection conn = repo.getConnection();) {
                

                // We do a simple SPARQL SELECT-query that retrieves all resources of type `ex:Artist`,
                // and their first names.
                String queryString ="PREFIX dbr: <http://dbpedia.org/resource/>\n" +
                                    "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                                    "SELECT ?type \n" +
                                    "    WHERE {dbr:Apollo rdf:type ?type .}";

                TupleQuery query = conn.prepareTupleQuery(queryString);

                // A QueryResult is also an AutoCloseable resource, so make sure it gets closed when done.
                try (TupleQueryResult result = query.evaluate()) {
                    while (result.hasNext()) {
                        BindingSet solution = result.next();
                        System.out.println("?type = " + solution.getValue("type"));
                    }
                   
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
        }
        finally {
                // Before our program exits, make sure the database is properly shut down.
                repo.shutDown();
        }
    }
}
