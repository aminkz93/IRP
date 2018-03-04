/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.sql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author arshad-lab
 */
public class SqlConnection {
    public static MysqlDataSource dataSource;
    public static Connection connection;
    public static Statement statement;

    public static void getInstance (){
        dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123qwe");
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("wikipedia");
        dataSource.setUseUnicode(true);
        dataSource.setCharacterEncoding("UTF-8");
        dataSource.setUseUnicode(true);
        try{
            connection =dataSource.getConnection();
            statement = connection.createStatement();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static int execute(String query){
        try{
            int resultSet = statement.executeUpdate(query);
            return resultSet;
        }
        catch(SQLException ex){
            System.err.println("statement can not be executed :  "+query+"reason: "+ex.getMessage() );
            return 0;
        }
    }
}
