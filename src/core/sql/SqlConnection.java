/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.sql;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author arshad-lab
 */
public class SqlConnection {
    public static MysqlDataSource dataSource ;
    public static Connection connection;
    public static Statement statement;
    public static  void getInstance(){
        dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("searchengine");
        dataSource.setUseUnicode(true);
        dataSource.setCharacterEncoding("UTF-8");
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static int execute(String query){
        try {
            int resultSet = statement.executeUpdate(query);

            return resultSet ; 
        } catch (SQLException e) {
            System.out.println("this insert statement Can not be Executed: "+ query + " the reason is:"+e.getMessage());
            return 0;
        }
    }
}
