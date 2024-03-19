package session04_JDBC;

import java.sql.*;

public class Ex02_Statement {
    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";

    Statement st;
    ResultSet rs;

    public Ex02_Statement() {
        cnn = DBConnect.makeConnection(account, password, database);
    }

    boolean checkConnect() {
        return cnn != null;
    }

    void closeResource() {
        try {
            if (cnn != null) {
                cnn.close();
            }
            if (st != null) {
                st.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    void select() {
        try {
            // Query String
            String query = "select * from Item";
            // Create ResultSet to Item Table
            st = cnn.createStatement();
            rs = st.executeQuery(query);
            // Display Information
            while (rs.next()) {
                var code = rs.getString("ICode"); // rs.getString(1)
                var name = rs.getString(2);
                var price = rs.getInt(3);
                String s = String.format("%15s\t%30s\t%d", code, name, price);
                System.out.println(s);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeResource();
        }
    }

    void insert(String code, String name, int price) {
        try {
            // Query String
            String query = String.format("INSERT INTO Item VALUES('%s', '%s', %d)", code, name, price);
            // Create ResultSet to Item Table
            st = cnn.createStatement();
            int count = st.executeUpdate(query);
            // Display Information
            if (count == 0) {
                System.out.println("Nothing no display");
            }
            else{
                System.out.println("Insert Successfully!!!");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeResource();
        }

    }

    void update(String newName, String code) {
        try{
            // update Item set ItemName = 'LALA' Where ICode = 'New01';
            // Query String
            String query = String.format("update Item set ItemName = '%s' Where ICode = '%s'", newName, code);

            // Create ResultSet to Item Table
            st = cnn.createStatement();
            int count = st.executeUpdate(query);
            // Display Information
            if (count == 0) {
                System.out.println("Nothing no display");
            }else{
                System.out.println("Update Successfully");
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        finally {
            closeResource();
        }
    }

    void delete(String code) {
        try{
            // Query String
            String query = String.format("delete from Item where ICode = '%s'", code);

            // Create ResultSet to Item Table
            st = cnn.createStatement();
            int count = st.executeUpdate(query);
            // Display Information
            if (count == 0) {
                System.out.println("Nothing no display");
            } else {
                System.out.println("Deleted Successfully!!!");
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        finally {
            closeResource();
        }

    }


    public static void main(String[] args) throws SQLException {
        var ex02 = new Ex02_Statement();
        // 1. select query
//        ex02.select();

        // 2.1. Insert Query
//        ex02.insert("F10", "VChi12", 2000);

        // 2.2. Update Query
//        ex02.update("Hung Hung", "F10");

        // 2.3. Delete Query
//        ex02.delete("F10");
    }
}

/*
    Statement Object - must be created for query execution
    - not required any parameters to be passed
    - Connection.createStatement() - create Statement
    - Statement Interface chia làm 3 implements:
        1. Statement -> Statement interface : execute SQL queries that do not require any parameters to be passed
        2. PreparedStatement -> Statement interface : executes a precompiled SQL Statement with or without 'IN' parameters
        3. CallableStatement -> PreparedStatement interface : executes a call to stored procedure
    Statement implement
        method - retrieves information
        - Statement.executeQuery(sql) -> retrieves information (SELECT clause)
        - Statement.executeUpdate(sql -> retrieves information (Insert, Update, Delete, ... clause)
        - Statement.execute(sql) -> execute sql statements that returns more than one result set (dùng chung cho 2 method)
    ResultSet Object -  receive and store the data in the same form as it is returned from the SQL queries
*/

