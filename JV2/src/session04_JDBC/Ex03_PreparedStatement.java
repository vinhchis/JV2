package session04_JDBC;

import java.sql.*;

public class Ex03_PreparedStatement {
    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";

    PreparedStatement st;
    ResultSet rs;

    public Ex03_PreparedStatement(){
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
        try{
            // Parameterized Query
            String query = "select * from Item";
            // Create ResultSet to Item Table
            st = cnn.prepareStatement(query);
            rs = st.executeQuery();
            // Display Information
            while(rs.next()){
                var code = rs.getString("ICode"); // rs.getString(1)
                var name = rs.getString(2);
                var price = rs.getInt(3);
                String s = String.format("%15s\t%30s\t%d", code, name, price);
                System.out.println(s);
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeResource();
        }

    }

    void insert(String code, String name, int price){
        try{
            // Parameterized Query
            String query = "INSERT INTO Item VALUES(?, ?, ?)";
            // Create ResultSet to Item Table
            st = cnn.prepareStatement(query);
            //  Passing Parameters
            st.setString(1, code);
            st.setString(2, name);
            st.setInt(3, price);
            // Display Information
            int count = st.executeUpdate();
            if(count == 0) {
                System.out.println("Nothing no display");
            }
            else{
                System.out.println("Insert Successfully!!!");
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeResource();
        }

    }

    void update(String newName, String code){
        try{
            // Parameterized Query
            String query = "update Item set ItemName = ? where ICode = ?";

            // Create ResultSet to Item Table
            st = cnn.prepareStatement(query);
            // Passing Parameters
            st.setString(1, newName);
            st.setString(2, code);
            // Display Information
            int count = st.executeUpdate();
            if(count == 0) {
                System.out.println("Nothing no display");
            }else{
                System.out.println("Update Successfully");
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeResource();
        }

    }

    void delete(String code) {
        try{
            // Parameterized Query
            String query = "delete from Item where ICode = ?";

            // Create ResultSet to Item Table
            st = cnn.prepareStatement(query);
            // Passing Parameters
            st.setString(1, code);
            // Display Information
            int count = st.executeUpdate();
            if(count == 0) {
                System.out.println("Nothing no display");
            }
            else {
                System.out.println("Deleted Successfully!!!");
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeResource();
        }

    }


    public static void main(String[] args) {
        var ex03 = new Ex03_PreparedStatement();

        // 1. select query
//        ex03.select();

        // 2.1. Insert Query
//        ex03.insert("F10", "VChi12", 2000);

        // 2.2. Update Query
//        ex03.update("Hung Hung", "F10");

        // 2.3. Delete Query
//        ex03.delete("F10");
    }
}

/*
    2. PreparedStatement -> Statement interface : dynamic Collection
        - executes a precompiled SQL Statement with or without 'IN' parameters
        - implements the execution of dynamic SQL statements with parameters.
        - tạo ra cái khung trước khi ta truyền param zo SQL query

 */
