/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.index.indexWikiDump;

import core.sql.SqlConnection;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author arshad-lab
 */
public class WikiPage {
    private String Title ;
    private String RedirectTitle;
    private String Abstract;
    private ArrayList<String> ExternalLinks;
    private ArrayList<String> Categories;
    private ArrayList<String> SeeAlso;

    public WikiPage(String Title, String RedirectTitle, String Abstract, ArrayList<String> ExternalLinks, ArrayList<String> Categories, ArrayList<String> SeeAlso) {
        this.Title = Title;
        this.RedirectTitle = RedirectTitle;
        this.Abstract = Abstract;
        this.ExternalLinks = ExternalLinks;
        this.Categories = Categories;
        this.SeeAlso = SeeAlso;
    }

    public String getTitle() {
        return Title;
    }

    public String getRedirectTitle() {
        return RedirectTitle;
    }

    public String getAbstract() {
        return Abstract;
    }

    public ArrayList<String> getExternalLinks() {
        return ExternalLinks;
    }

    public ArrayList<String> getCategories() {
        return Categories;
    }

    public ArrayList<String> getSeeAlso() {
        return SeeAlso;
    }

    
    @Override
    public String toString() {
        return "WikiPage{" + "Title=" + Title 
                + ", RedirectTitle=" + RedirectTitle 
                + ", Abstract=" + Abstract 
                + ", Categories=" 
                + StringUtils.join(Categories, ",") + ", SeeAlso=" +"\n"+ StringUtils.join(SeeAlso, ",")+ '}';
        
            
        
    }
    public boolean insertToSql(){
        try{
            String query = "insert into info (title,redirectTitle,abstract,category,seeAlso) "+
                    "values ("+
                    "'"+Title+"',"+
                    "'"+RedirectTitle+"',"+
                    "'"+Abstract+"',"+
                    "'"+StringUtils.join(Categories, ",")+"',"+
                    "'"+StringUtils.join(SeeAlso, ",")+"')";
            SqlConnection.execute(query);
            System.out.println("page : "+Title +" inserted!");
            return true;
        }
        catch(Exception ex){
            System.err.println("exception in inserting page :"+ Title +" to sql: "+ex.getMessage());
            return false; 
        }
    }
    
}
