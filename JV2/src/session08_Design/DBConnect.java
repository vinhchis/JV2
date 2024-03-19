/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session08_Design;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class DBConnect {
    static Connection cnn;
    
    public static Connection makeConnection(){
        String dbUrl = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;integratedSecurity=true; databaseName=StrongHold ";
        try {
            cnn = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cnn;
    }
}
