/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import core.CoreServices;
import core.ProcessInputFiles;
import core.WorkingSet;
import features.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import core.index.DocumentsIndexer;
import core.index.indexWikiDump.WikiDocument;
import core.sql.SqlConnection;

/**
 *
 * @author amin
 */
public class run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        CoreServices core = new CoreServices();
        SqlConnection.getInstance();
        WikiDocument.documentsIndexerRunner();
        
        
//String s = "insert into info (title,redirectTitle,abstract,category,seeAlso) values ('Albedo','null','null\n" +
//"      <text xml:space=\"preserve\">{{Other uses}}\n" +
//"      <text xml:space=\"preserve\">{{Other uses}}\n" +
//"{{Use dmy dates|date=June 2013}}\n" +
//"[[File:Albedo-e hg.svg|thumb|Percentage of diffusely reflected sunlight in relation to various surface conditions]]\n" +
//"Albedo ({{IPAc-en|æ|l|ˈ|b|iː|d|oʊ}}) ([[Latin]] albedo, &quot;whiteness&quot;) is the measure of diffusive reflection of solar radiation out of the total solar radiation received by a body, for example a planetary body such as Earth. It is [[Dimensionless number|dimensionless]] and measured on a scale from zero (corresponding to a [[black body]] that absorbs all incident radiation) to one (corresponding to a body that reflects all incident radiation).\n" +
//"Surface albedo is defined as the ratio of irradiance reflected to the irradiance received by a surface. The proportion reflected is not only determined by properties of the surface itself, but also by the spectral and angular distribution of solar radiation reaching the Earths surface.&lt;ref&gt;{{Cite book|url=http://curry.eas.gatech.edu/Courses/6140/ency/Chapter9/Ency_Atmos/Reflectance_Albedo_Surface.pdf|title=Reflectance and albedo, surface. Encyclopedia of the Atmosphere, JR Holton and JA Curry (eds.)|last=Coakley|first=J.A.|publisher=Academic Press|year=2003|isbn=|location=|pages=1914–1923}}&lt;/ref&gt; These factors vary with atmospheric composition, geographic location and time (see [[Position of the Sun]]). While bi-hemispherical [[reflectance]] is calculated for a single angle of incidence (i.e., for a given position of the sun), albedo is the directional integration of reflectance over all solar angles in a given period. The temporal resolution may range from seconds (as obtained from flux measurements) to daily, monthly or annual averages.\n" +
//"Unless given for a specific wavelength (spectral albedo), albedo refers to the entire [[Sunlight|spectrum of solar radiation]].&lt;ref&gt;{{Cite journal|last=Henderson-Sellers|first=A.|last2=Wilson|first2=M.F.|year=1983|title=Albedo observations of the Earths surface for climate research|jstor=37357|journal=Philosophical Transactions of the Royal Society of London A|volume= 309| issue =  1508, The Study of the Ocean and the Land Surfacefrom Satellites|pages=pp. 285–294|bibcode=1983RSPTA.309..285H|doi=10.1098/rsta.1983.0042}}&lt;/ref&gt; Due to measurement constraints, it is often given for the spectrum in which most solar energy reaches the surface (approximately between 0.3 and 3 μm). This spectrum includes [[Visible spectrum|visible light]] (0.39-0.7 μm), which explains why surfaces with a low albedo appear dark (e.g., trees absorb most radiation), whereas surfaces with a high albedo appear bright (e.g., snow reflects most radiation).\n" +
//"Albedo is an important concept in [[climatology]], [[astronomy]], and environmental management (e.g., as part of the [[Leadership in Energy and Environmental Design]] (LEED) program for sustainable rating of buildings). The average albedo of the Earth at the top of the atmosphere, its planetary albedo, is 30 to 35% because of cloud cover, but widely varies locally across the surface because of different geological and environmental features.&lt;ref&gt;Environmental Encyclopedia, 3rd ed., Thompson Gale, 2003, {{ISBN|0-7876-5486-8}}&lt;/ref&gt;\n" +
//"The term albedo was introduced into optics by [[Johann Heinrich Lambert]] in his 1760 work [[Photometria]].','Climate_forcing,Climatology,Electromagnetic_radiation,Land_surface_effects_on_climate,Radiometry,Scattering,_absorption_and_radiative_transfer_(optics),Radiation','Cool_roof,Daisyworld,Emissivity,Exitance,Global_dimming,Irradiance,Kirchhoff's_law_of_thermal_radiation,Opposition_surge,Polar_see-saw,Solar_radiation_management')";
//       s= s.replaceAll("'","''");
//        System.out.println(s);
        
//        System.out.print(F23.getPageLegth(14653));
////        System.out.println(core.getPageInfoHtml(3649517));
//        System.out.println(F24.getNumberOfPageWatchers(3649517));
//        System.out.println(core.parseInt("123,234"));
//        CoreServices.getNumberOfSearchResult("link");
//        retainallTest();
//        CreateOutput out = new CreateOutput(new WorkingSet("2007","S1"));
//        LetorOutput letorOutput = new  LetorOutput(new  WorkingSet("2007", "S1"));
//        letorOutput.createLetorFileWithNewFeatures(47);
//       out.outputF095();
//        out.outputF096();
//        out.outputF097();
//        out.outputF098();
//        out.outputF099();
//        out.outputF100();
//        out.outputF101();
//        out.outputF102();
//        out.outputF103();
//        out.outputF104();
//        out.outputF105();
//        out.outputF106();
//        out.outputF107();
//            QentityDentity ee = new QentityDentity(new WorkingSet("2008","S1"));
//            ee.createEntityEntityPairFile();
//        WorkingSet ws = new WorkingSet("2007");
//       System.out.println(ws.getQuery().containsKey("315"));
//       for(String qid : ws.getQuery().keySet()){
//           System.out.println(qid + " : ");
//           for(String ent : ws.getQuery().get(qid)){
//               System.out.println(ent);
//           }

//           System.out.println("--------------------------");
//       }
//            createFiles();
    }

    public static void retainallTest() {
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
//    HashMap<String, ArrayList<String>> map = ProcessInputFiles.deserializeHashMap("./data/2008/serialized/2008-documents-Hashmap");
//    System.out.println(map.keySet().size());
//    ProcessInputFiles.queryRelatedDocumentHashMapCreateAndSaveRunner();
//        HashMap<String, ArrayList<String>> map = ProcessInputFiles.deserializeHashMap("./data/2007/serialized/2007-queryRelatedDocuments-Hashmap");
//        System.out.println(map.keySet().size());
//        double total=0;
//        for (String key : map.keySet()) {
//            total += map.get(key).size();
//        }
//        System.out.println(total);
//        testDocumentsEntitiesForRepeatedEntity();

    }
    
    public static void retainAlltest(){
        ArrayList<String> test = new ArrayList<>();
        test.add("amin");
        test.add("ali");
        test.add("amir");
        test.add("amin");
        test.add("amin");
        ArrayList<String> t = new ArrayList<>();
        t.add("amin");
        test.retainAll(t);
        System.out.println(test.size());
        System.out.println(test.get(0));
    }

    public static void testDocumentsEntitiesForRepeatedEntity() {
        HashMap<String, ArrayList<String>> map = ProcessInputFiles.deserializeHashMap("./data/2007/serialized/2007-queries-Hashmap");
        for (String key : map.keySet()) {
            int listSize = map.get(key).size();
            Set<String> set = new HashSet<String>(map.get(key));
            int setSize = set.size();
            System.out.println("list : " + listSize + "\t setSize: " + setSize + "\t" + (setSize == listSize));
            if (setSize != listSize) {
                System.out.println("key of repeated : " + key);
            }
        }
    }
    
    public static void createFiles(){
        ProcessInputFiles.documentHashMapCreateAndSaveRunner();
        ProcessInputFiles.queryHashMapCreateAndSaveRunner();
        ProcessInputFiles.queryRelatedDocumentHashMapCreateAndSaveRunner();
        core.index.DocumentsIndexer.documentsIndexerRunner();
    }

}
