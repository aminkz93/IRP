/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.SPARQLUtil;
import core.CoreServices;
import features.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;

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
        
        
        F8.executeQuery();
	

    }
    

}
