/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.WorkingSet;
import java.util.ArrayList;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

/**
 *
 * @author Parastoo
 */
public class F094 {
    private WorkingSet workingSet;

    public F094(WorkingSet ws) {
        workingSet = ws;
    }
//    public static String executeQuery(String qEntity) throws Exception {  
//        
//        String type = null ;
//        //if(core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)) != null){
////            String entityTitle = core.CoreServices.getSparqlTitle(core.CoreServices.parseInt(qEntity)); 
////            entityTitle = entityTitle.replace("'", "");
//            String query ="PREFIX dbr: <http://dbpedia.org/resource/> " +
//                           "PREFIX dbo: <http://dbpedia.org/ontology/> " +
//                           "SELECT (?type) " +
//                           "WHERE { ?subject rdf:type ?type . ?subject dbo:wikiPageID "+
//                           qEntity +
//                           ".}";
//            ResultSet results = core.CoreServices.executeSparqlQuery(query);
//            while (results.hasNext()) {
//                QuerySolution soln = results.next();
//
//                Literal countLiteral = ((Literal) soln.get("type"));
//                type = countLiteral.getString();
//                System.out.println(type);
//
//            }
//        //}
//
//        return type;
//    }
    public double executeQuery(String qEntity, String dEntity) throws Exception {  
        String typeqEntity = "" ;
        String typedEntity = "" ;
        double result = 0;
        try{
            for (String value : workingSet.getEntityTitle().get(qEntity)) {
                if(workingSet.getTypes().containsKey(value))
                    typeqEntity = workingSet.getTypes().get(value);
            }
            
            for (String value : workingSet.getEntityTitle().get(dEntity)) {
                if(workingSet.getTypes().containsKey(value))
                    typedEntity = workingSet.getTypes().get(value);
            }
            
            if(typedEntity.equals(typeqEntity))
                result = 1;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
    public String[] print(String qid, String docid) throws Exception {
        String [] results = new String[4];
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(docid);
        String stringOutput = "";
        double mean = 0;
        double min = 0;
        double max = 0;
        int count = 0;
        double result;
        for (String qEntity : qEntities) {
            for (String dEntity : dEntities) {
                result = executeQuery(qEntity,dEntity);
                stringOutput +=  qEntity + "-"+ dEntity + " " + result + "\n";
                mean += result;
                if(min > result)
                    min =result;
                if(max < result)
                    max =result;
                count++;
            }
        }
        mean = mean /(double)count ;
        results[0] = stringOutput;
        results[1] = String.valueOf(min);
        results[2] = String.valueOf(max);
        results[3] = String.valueOf(mean);
        
        return results;
    }
}
