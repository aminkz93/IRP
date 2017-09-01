/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.CoreServices;
import core.ProcessInputFiles;
import features.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author amin
 */
public class run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception  {
        CoreServices core = new CoreServices();
//        System.out.print(F23.getPageLegth(14653));
////        System.out.println(core.getPageInfoHtml(3649517));
//        System.out.println(F24.getNumberOfPageWatchers(3649517));
//        System.out.println(core.parseInt("123,234"));
//        CoreServices.getNumberOfSearchResult("link");

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
    HashMap<String, ArrayList<String>> map = ProcessInputFiles.deserializeHashMap("./data/2008/serialized/2008-documents-Hashmap");
    System.out.println(map.keySet().size());

    }
    

}
