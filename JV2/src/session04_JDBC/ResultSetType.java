package session04_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetType {
    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";


    public ResultSetType() {
        cnn = DBConnect.makeConnection(account, password, database);
    }

    public void testTypeOfResultSet(int resultSetType) {
        // ResultSet block
        try {
            String query = "select ICode, Rate from Item Where ICode = ?";
            PreparedStatement pst = cnn.prepareStatement(query, resultSetType);
            pst.setString(1, "RKSK-B");
            ResultSet rs = pst.executeQuery();
            // end of ResultSet block
            String icode;
            int rate;
            Statement st = cnn.createStatement();
            st.executeUpdate("UPDATE Item set Rate=600 WHERE ICode = 'RKSK-B'");

            while (rs.next()) {
                icode = rs.getString("ICode");
                rate = rs.getInt("Rate");
                System.out.println("ICode: " + icode);
                System.out.println("Rate: " + rate);
            }
        } catch (SQLException ce) {
            System.out.println(ce.getMessage());
        }
    }


    public static void main(String[] args) {
        // 1. ResultSet.TYPE_FORWARD_ONLY
//        new DataDemo().testTypeOfResultSet(ResultSet.TYPE_FORWARD_ONLY); // 500

        // 2. ResultSet.TYPE_SCROLL_INSENSITIVE
        new ResultSetType().testTypeOfResultSet(ResultSet.TYPE_SCROLL_INSENSITIVE); // 500

        // 3. ResultSet.TYPE_SCROLL_SENSITIVE
//        new DataDemo().testTypeOfResultSet(ResultSet.TYPE_SCROLL_SENSITIVE); // 600
    }
}
/*
    Ban đầu:
    + ICode: RKSK-B
    + Rate: 500

    3 loại resultSetType:
            - NOT SCROLLABLE
                - ResultSet.TYPE_FORWARD (default) - only move forward(first-row -> last-row) and not updatable
            - SCROLLABLE - di chuyển con trở bất kì
                1. ResultSet.TYPE_SCROLL_INSENSITIVE. - scrollable + not updatable
                2. ResultSet.TYPE_SCROLL_SENSITIVE - scrollable + updatable
                    - updateInt(), updateString(),... theo row đang duyệt
                * sensitive - có thể thay đổi

 */
