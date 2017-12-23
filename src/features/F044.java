/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import core.WorkingSet;
import java.util.ArrayList;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;

/**
 *
 * @author Parastoo
 */
public class F044 {
    private WorkingSet workingSet;

    public F044(WorkingSet ws) {
        workingSet = ws;
    }
    public String executeQuery(String qEntity, String dEntity) throws Exception {  
        String result = null;
        try{
            ArrayList<String> e1 = ComputeE(qEntity);
            ArrayList<String> e2 = ComputeE(dEntity);
            float w = ComputeW();
            ArrayList<String> intersection = intersection(e1, e2);
            float value = (float)(
                    1- (
                    (
                    Math.log(Math.max( e1.size(),e2.size())) - Math.log(intersection.size())
                    )
                    /
                    (float) 
                    (
                    Math.log(w) - Math.log(Math.min(e1.size(), e2.size()))
                    )
                    )
                    );
            
            result = String.valueOf(value);
            
        }
        catch(Exception ex){
            System.out.println("error for "+ qEntity+"-"+dEntity);
        }
        return result;
    }
    
    private ArrayList<String> ComputeE(String entity){
        ArrayList<String> value = new ArrayList<>() ;
        try{
            String query ="PREFIX  dbo:  <http://dbpedia.org/ontology/>\n" +
                            "PREFIX  dbp:  <http://dbpedia.org/property/>\n" +
                            "SELECT  ?subject\n" +
                            "WHERE\n" +
                            "  { { ?entity   dbo:wikiPageID  "+
                            entity+" .\n" +
                            "      ?subject  ?predicate      ?entity\n" +
                            "      FILTER strstarts(str(?subject), \"http://dbpedia.org/resource/\") }}";
            TupleQueryResult results = core.CoreServices.executeSparqlQueryLocal(query);
            while (results.hasNext()) {
                BindingSet soln = results.next();
                Value countValue = soln.getValue("subject");
                value.add(countValue.toString());
                System.out.println(value);
            }
        }
        catch(Exception ex){
            System.out.println(entity +" did not execute!");
        }
            
        return value;
    }
    
    private float ComputeW(){
        float count = 0 ;
        try{
            String query ="SELECT  (COUNT(DISTINCT ?entity) AS ?totalNumber)\n" +
                            "WHERE\n" +
                            "  {   { ?entity  ?predicate  ?object\n" +
                            "        FILTER strstarts(str(?entity), \"http://dbpedia.org/resource/\")\n" +
                            "      }\n" +
                            "    UNION\n" +
                            "      { ?subject  ?predicate1  ?entity\n" +
                            "        FILTER strstarts(str(?entity), \"http://dbpedia.org/resource/\")\n" +
                            "      }\n" +
                            "  }";
            TupleQueryResult results = core.CoreServices.executeSparqlQueryLocal(query);
            while (results.hasNext()) {
                BindingSet soln = results.next();
                Value countValue = soln.getValue("totalNumber");
                count = Float.parseFloat(countValue.toString());
                System.out.println(count);
            }
        }
        catch(Exception ex){
            System.out.println(" did not execute!");
        }
            
        return count;
    }
    
    private ArrayList<String> intersection(ArrayList<String> qEntities, ArrayList<String> dEntities){
        ArrayList<String> output =  new ArrayList<String>();
        for(String s : qEntities){
            if(dEntities.contains(s)){
                output.add(s);
            }
        }
        return output;
    }
    
    public String print(String qid,String did) throws Exception {
        ArrayList<String> qEntities = workingSet.getQueryEntities(qid);
        ArrayList<String> dEntities = workingSet.getDocumentEntities(did);
        String output = qid +" :\n";
        String result;
        for (String qEntity : qEntities) {
            for (String dEntity : dEntities) {
                result = executeQuery(qEntity,dEntity);
                if(result != null)
                    output += "\t" + qEntity + " : " + result + "\n";
                else
                    output += "\t" + qEntity + " : " + "null" + "\n";
            }
            
        }
        output += "------------------------------------\n";
        return output;
    }
}
