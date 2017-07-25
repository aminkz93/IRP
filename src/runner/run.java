/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.CoreServices;
import features.F1;
import features.F23;
import features.F24;

/**
 *
 * @author amin
 */
public class run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CoreServices core = new CoreServices();
//        System.out.print(F23.getPageLegth(14653));
////        System.out.println(core.getPageInfoHtml(3649517));
//        System.out.println(F24.getNumberOfPageWatchers(3649517));
//        System.out.println(core.parseInt("123,234"));
//        CoreServices.getNumberOfSearchResult("link");

        String[] qu = {"ios", "what", "android", "apple"};

        for (int i = 0; i < 4; i++) {
            System.out.println(F1.getNumberOfGoogleSearchResult(qu[i]));
        }
    }

}
