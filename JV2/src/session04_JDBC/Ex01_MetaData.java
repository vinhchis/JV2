package session04_JDBC;

import java.sql.*;

public class Ex01_MetaData {
    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";

    public Ex01_MetaData(){
        cnn = DBConnect.makeConnection(account, password, database);
    }

    boolean checkConnect() {
        return cnn != null;
    }

    void showMetaData() throws SQLException {
        if(checkConnect()) {
            String query = "select * from Item";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsm = rs.getMetaData();

            int columns = rsm.getColumnCount();

            System.out.println("Item Information");

            for (int i = 1; i <= columns; i++) {
                var code = rsm.getColumnName(i);
                var type = rsm.getColumnTypeName(i);
                String s = String.format("column[%s] has datatype: %s", code, type);
                System.out.println(s);
            }
        }
    }



    public static void main(String[] args) throws SQLException {
        new Ex01_MetaData().showMetaData();
    }
}
/*
- Metadata - number, types, properties of columns ResultSet
    - getMetaData -> trả về metadata
    - ResultSetMetaData - lưu thông tin về metadata của 1 ResultSet
        + getColumnCount() -> số lượng column của 1 table
        - sử dụng index để lấy thông tin từng column
            + getColumnName(index) -> tên cột
            + getColumnTypeName(index) -> tên Type Name cột
 */