/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.CoreServices;

/**
 *
 * @author amin
 */
public class F1 {
    
    public static double getNumberOfGoogleSearchResult(String query){
        return CoreServices.getNumberOfSearchResult(query);
    }
    
}
