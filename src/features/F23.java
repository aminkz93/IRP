package features;

import core.CoreServices;

/**
 * Created by amin on 7/21/17.
 */
public class F23 {
    public static int getPageLegth(int pageId){
        return CoreServices.parseInt(CoreServices.getLength(pageId));

    }
}
