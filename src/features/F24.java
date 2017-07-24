package features;

import core.CoreServices;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by amin on 7/21/17.
 */
public class F24 {
    public static int getNumberOfPageWatchers(int pageId){
       String html =  CoreServices.getPageInfoHtml(pageId);
       Document doc = Jsoup.parse(html);
       Element element = doc.select("#mw-pageinfo-watchers").first();
       String numberOfWatchers=Jsoup.parse(element.html()).getAllElements().first().text();
       return CoreServices.parseInt(numberOfWatchers.substring(24,numberOfWatchers.length()));





    }
}
