/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.index.indexWikiDump;

import core.ProcessInputFiles;
import core.index.DocumentsIndexer;
import core.index.Indexer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arshad-lab
 */
public class WikiDocument {
    
    private static String wikiFile = "/home/arshad-lab/mohaddeseh/enwiki-20180101-pages-articles.xml";
//    private static String wikiFile = "/home/arshad-lab/mohaddeseh/topMillionLines.txt";
    private static String wikiIndexDirectory = "./data/Total/IndexWiki";

    public static void documentsIndexerRunner() throws FileNotFoundException, IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(wikiFile);
            sc = new Scanner(inputStream, "UTF-8");
            String sCurrentLine;
            String page ="";
            boolean isNewPage =false;
            

            while (sc.hasNextLine()) {
                sCurrentLine = sc.nextLine();
                if(sCurrentLine.contains("<page>")){
                    isNewPage =true;
                    page = "";
                }
                else if(sCurrentLine.contains("</page>")){
                    page = page +"\n"+sCurrentLine;
                    WikiParser wp = new WikiParser();
                    wp.parseAndInsert(page);
                    isNewPage =false;

                }
                if(isNewPage){
                    page = page +"\n"+ sCurrentLine;
                }
                    
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (Exception ex) {
            Logger.getLogger(DocumentsIndexer.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
                System.out.println("file closed");
            }
        }

    }
    
}
