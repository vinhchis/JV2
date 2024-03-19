package session04_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex04_Scrollable {
    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";

    PreparedStatement st;
    ResultSet rs;

    public Ex04_Scrollable() throws SQLException {
        cnn = DBConnect.makeConnection(account, password, database);
        loadData();
    }

    void loadData() throws SQLException {
        String query = "select * from Item";
        st = cnn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery();
    }

    void firstRecord() throws SQLException {
        rs.first();
        var code = rs.getString(1);
        var name = rs.getString(2);
        var price = rs.getInt(3);
        display(code, name, price);
    }

    void lastRecord() throws SQLException{
        rs.last();
        var code = rs.getString(1);
        var name = rs.getString(2);
        var price = rs.getInt(3);
        display(code, name, price);
    }

    void display(String code, String name, int price){
        String s = String.format("%15s\t%30s\t%d", code, name, price);
        System.out.println(s);
    }

    void closeResource() throws SQLException{
        if((cnn != null) ||(st != null) || (cnn != null)){
            rs.close();
            st.close();
            cnn.close();
        }
    }




    public static void main(String[] args) throws SQLException {
        var ex05 = new Ex04_Scrollable();
        ex05.firstRecord();
        ex05.lastRecord();
        ex05.closeResource();
    }
}
/*
*   - sử dụng hàm tạo ResultSet với tham số resultSetType
*       1. prepareStatement()
*       2. createStatement()
*       3. prepareCall() - thường dùng cho stored procedure
*   - resultSetConcurrency - a result set is read-only or updatable
            + ResultSet.CONCUR_READ_ONLY
            + ResultSet.CONCUR_UPDATABLE
* */
