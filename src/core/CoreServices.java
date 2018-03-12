/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.google.gson.JsonArray;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 *
 * @author amin
 */
//CoreServices
public class CoreServices {
    private static final String USER_AGENT
            = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    /*
    get the page id and returns a json file of the String format
    */
    private static String getJsonById(int pageId){
        String json;
        try {
            String url = "https://en.wikipedia.org/w/api.php?action=query&prop=info&pageids="+pageId+"&inprop=url&format=json";
            json = Jsoup.connect(url).ignoreContentType(true).execute().body();
            
        } catch (IOException ex) {
            
            json = null;
        }
        return json;
    }
    public static Document getHtmlDocument(int entityId){
        String entity = getTitle(entityId);
        String url = "https://en.wikipedia.org/wiki/" + entity;
        
        try {
            Document htmlDocument = Jsoup.connect(url).get();
            return htmlDocument;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static double getNumberOfSearchResult(String query) throws Exception{
        String searchURL = "https://www.google.com/search?site=&source=hp&q="+query+"&oq="+query+"&gs_l=psy-ab.3..35i39k1l2j0i67k1l2.890.1193.0.1300.4.4.0.0.0.0.204.372.0j1j1.2.0....0...1.1.64.psy-ab..2.1.203.0.QEjnRPYjPFQ";
        Element element = null;
//        try {
            Document htmlDocument = Jsoup.connect(searchURL).get();
            element = htmlDocument.select("#resultStats").first();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if(element!=null) {
            String result = Jsoup.parse(element.html()).text().replaceAll("[^0-9]", "");
            return Double.parseDouble(result.substring(0,result.length()-3));
        }else {
            return 0;
        }
    }

    public static String getPageInfoHtml(int pageId){
        String title = getTitle(pageId);
        String[] words = title.split(" ");
        StringBuffer sb = new StringBuffer();
        for(String word : words){
            sb.append(word);
            sb.append("_");
        }
        String pageName=sb.substring(1,sb.length()-2);
        String url = "https://en.wikipedia.org/w/index.php?title=" + pageName + "&action=info";

        try {
            return Jsoup.connect(url).ignoreContentType(true).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    
    private static String getJsonFieldValue(String json, String fieldName, int pageId){
        try{
        JsonElement jsonElement= new JsonParser().parse(json);
        JsonObject jRoot = jsonElement.getAsJsonObject()
                .getAsJsonObject("query")
                .getAsJsonObject("pages")
                .getAsJsonObject(String.valueOf(pageId));
        if(jRoot.get(fieldName) != null)
            return jRoot.get(fieldName).toString();
        else 
            return null ;
        }
        catch(Exception ex){
            return null;
        }

    }

    public static String getTitle(int pageId){
        try {
            return getJsonFieldValue(getJsonById(pageId),"title",pageId);
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public static String getSparqlTitle(int pageId){
        String title = getTitle(pageId);
        if(title != null){
            title = title.replace(" ", "_");
            title = title.replace("'", "");
            title = title.replace(",", "_");
            title = title.replace("(", "_");
            title = title.replace(")", "_");
            title = title.replace("[", "_");
            title = title.replace("]", "_");
            title = title.replace("/", "_");
            title = title.replace("\\", "_");
            
            return title.substring(1,title.length()-1);
        }
        else{
            return null;
        }
    }
    public static String getLength(int pageId){
        return getJsonFieldValue(getJsonById(pageId),"length",pageId);
    }
    /*
        this method get all categories of entity 
     */
    public static String[] getPageCategory(int pageId){
        try {
            String[] categories  = null;
            String[] words = null;
            String title = getTitle(pageId);
            if(title != null){
             words = title.split(" ");}
            StringBuffer sb = new StringBuffer();
            for(String word : words){
                sb.append(word);
                sb.append("_");
            }
            String pageName=sb.substring(1,sb.length()-2);
            String url = "https://en.wikipedia.org/w/api.php?action=query&titles=" + pageName + "&prop=categories&format=json";

        
            String jsonCategory = Jsoup.connect(url).ignoreContentType(true).execute().body();
            JsonElement jsonElement= new JsonParser().parse(jsonCategory);
            JsonObject jRoot = jsonElement.getAsJsonObject()
                    .getAsJsonObject("query")
                    .getAsJsonObject("pages")
                    .getAsJsonObject(String.valueOf(pageId));
        
            JsonArray JCategory  = jRoot.getAsJsonArray("categories");
            categories = new String[JCategory.size()];
            for (int i = 0; i < JCategory.size(); i++) {
                JsonObject item = JCategory.get(i).getAsJsonObject();
                String itemCategory = item.get("title").toString();
                itemCategory = itemCategory.substring(10,itemCategory.length()-1);
                categories[i] = itemCategory;
            }
            
            return categories;
        } catch (IOException e) {
//            e.printStackTrace();
            return null;
        }

    }
    /*
        this method parse Integer from string that includes ',' as separators
     */
    public static int parseInt(String input){
        StringBuilder sb =  new StringBuilder();

        for(int i=0;i<input.length();i++){
            if( input.charAt(i)==',')
                continue;
            sb.append(input.charAt(i));


        }
        return Integer.parseInt(sb.toString());
    }
    
    public static ResultSet executeSparqlQuery(String query){
        
        //return SPARQLUtil.INSTANCE.dbpediaQuery(query);
        return dbpediaQuery(query);
        
    }
    public static  ResultSet dbpediaQuery (String queryString)
    {
        Query query = QueryFactory.create(queryString);
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
        ResultSet results = queryExecution.execSelect();
        queryExecution.close();
        return results;

    }
    
    public static TupleQueryResult executeSparqlQueryLocal(String queryString){
        
        TupleQuery query = GraphDBConnection.getInstance().prepareTupleQuery(queryString);
        TupleQueryResult result = query.evaluate();
        return result;
    }




}
