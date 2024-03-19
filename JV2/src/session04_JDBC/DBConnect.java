package session04_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    static Connection cnn;
    public static Connection makeConnection(String account, String password, String database){
        //  JDBC database URL for SQL
        // jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
        String serverName = "localhost";
        String port = "1433";
        String dbUrl = String.format("jdbc:sqlserver://%s:%s;trustServerCertificate=true;databaseName=%s", serverName, port, database);
        try{
            // Register JDBC driver
            cnn = DriverManager.getConnection(dbUrl, account, password);
        }catch(SQLException err){
            System.err.println(err.getMessage());
        }
        return cnn;
    }
}
/*
    - DriverManager - establish the database connection
        - DriverManager.getConnection() :
            1. Matching the URL and Driver
            2. Connecting to the database
 */