/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;

/**
 *
 * @author Parastoo
 */
public class GraphDBConnection {
    private static RepositoryConnection DBpediaConnection ;
    
    public static synchronized RepositoryConnection getInstance()
    {
        try{
            if (DBpediaConnection == null)
            {
                String serverUrl = "http://localhost:7200";
                String repoID = "DBpedia";

                RemoteRepositoryManager manager = new RemoteRepositoryManager(serverUrl);
                manager.initialize();
                Repository repo = manager.getRepository(repoID);
                repo.initialize();
                DBpediaConnection = repo.getConnection();
            }
        }
        catch(Exception e)
        { 
            System.out.println(e);
        }
       return DBpediaConnection;  
    }
    
    public static void close(){
        try{
            DBpediaConnection.close();
        }
        catch(Exception ex){
            System.out.println("connection did not close properly");
        }    
    }
    
}
