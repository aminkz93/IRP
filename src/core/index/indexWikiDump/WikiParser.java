/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.index.indexWikiDump;


import java.nio.ByteBuffer;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author arshad-lab
 */
public class WikiParser {
    
    private String Title ;
    private String RedirectTitle;
    private String Abstract;
    private ArrayList<String> ExternalLinks;
    private ArrayList<String> Categories;
    private ArrayList<String> SeeAlso;
    public boolean parseAndInsert(String page ){
        try{
            ExternalLinks = new ArrayList<>();
            Categories = new ArrayList<>();
            SeeAlso = new ArrayList<>();
            
            boolean startAbstract =false;
            boolean startCategory =false;
            boolean startSeeAlso =false;

            String categoriesString = "";
            String seeAlsoString ="";

            String[] lines = page.split(System.getProperty("line.separator"));

            for (String line : lines) {
                try{
                    //get Title
                    if(line.contains("<title>")){
                        line =line.replace("    <title>", "");
                        line =line.replace("</title>","");
                        line =line.trim();
                        line =line.replace(" ", "_");
                        line =line.replace("'", "''");
                        Title=line;
                    }
                    //get redirect
                    if(line.contains("<redirect")){
                        String linesplit[] = line.split("\"");
                        RedirectTitle = linesplit[1].replace(" ", "_");
                    }
                    
                    //getAbstract
                    if(line.contains("<text xml:space=\"preserve\">")){
                        Abstract = Abstract +"\n"+ line;
                        startAbstract = true;
                    }
                    if(startAbstract && line.substring(0,2).equals("==")){
                         startAbstract = false;
                    }
                    else if(startAbstract){
                        Abstract = Abstract +"\n"+ line;
                    }
                    
                    //get SeeAlsoString
                    if(line.contains("==See also==")){
                        startSeeAlso = true;
                    }
                    else if(startSeeAlso && line.substring(0,2).equals("==") ){
                        startSeeAlso = false;
                    }
                    else if(startSeeAlso){
                        seeAlsoString = seeAlsoString + line +"\n";
                    }
                    //get categoriesString
                    if(line.contains("[[Category:")){
                        categoriesString = categoriesString + line +"\n";
                    }
                    
                }
                catch(Exception ex){
//                    System.out.println("the line has error! the line is: "+line+" the error is :"+ex.getMessage());
                }
            }
            Abstract =Abstract.replace("'", "''");
            Abstract =Abstract.replaceAll("[^\\x20-\\x7e]", "");
            SeeAlso = parseSeeAlso (seeAlsoString);
            Categories = parseCategories (categoriesString);
            
            WikiPage wikipage = new WikiPage(Title, RedirectTitle, Abstract, ExternalLinks, Categories, SeeAlso);
            return wikipage.insertToSql();
        }
        catch(Exception ex){
            System.err.println("error occured in initailizing : "+ex.getMessage());
            return false;
        }
    }

    private ArrayList<String> parseExternalLinks(String externalLinksString) {
        ArrayList<String> externalLinks = new ArrayList<>();
        try{
            String[] lines = externalLinksString.split(System.getProperty("line.separator"));
            for (String line : lines) {

            }
        }
        catch(Exception ex){
            System.out.println("exception in parse externalLinksString");
        }
        return externalLinks;
    }

    private ArrayList<String> parseSeeAlso(String seeAlsoString) {
        
        ArrayList<String> seeAlsoes = new ArrayList<>();
        try{
            String[] lines = seeAlsoString.split(System.getProperty("line.separator"));
            for (String line : lines) {
                if( line != "" && line.substring(0, 4).equals("* [[")){
                    line =line.replace("* [[", "");
                    String[] splitedLines = line.split("]]");
                    for (String splitedLine : splitedLines[0].split("\\|")) {
                        splitedLine =splitedLine.trim();
                        splitedLine = splitedLine.replace(" ", "_");
                        splitedLine = splitedLine.replace("'", "''");
                        seeAlsoes.add(splitedLine);
                    }
                    
                }
            }
        }
        catch(Exception ex){
            System.out.println("exception in parse seeAlsoString");
        }
        return seeAlsoes;
    }

    private ArrayList<String> parseCategories(String categoriesString) {
        ArrayList<String> categories = new ArrayList<>();
        try{
            String[] lines = categoriesString.split(System.getProperty("line.separator"));
            for (String line : lines) {
                line =line.replace("[[Category:", "");
                line = line.split("]]")[0];
                line = line.replace("|", "");
                line = line.trim();
                line = line.replace(" ", "_");
                line = line.replace("'", "''");
                categories.add(line);
                
            }
        }
        catch(Exception ex){
            System.out.println("exception in parse categoriesString");
        }
        return categories;
    }

}
