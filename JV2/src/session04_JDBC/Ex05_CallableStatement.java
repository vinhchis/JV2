package session04_JDBC;

import java.sql.*;

public class Ex05_CallableStatement {
    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";

    CallableStatement cs;
    ResultSet rs;

    public Ex05_CallableStatement(){
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
            if (cs != null) {
                cs.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    void display(String item) throws SQLException {
        try{
            // string stored procedure
            String query = "{call sp_ItemInfo(?)}"; // native
            // Create Scrollable ResultSet
            cs = cnn.prepareCall(query);
            cs.setString(1, item);
            rs = cs.executeQuery();
            // Display Information
            while(rs.next()){
                var code = rs.getString("ICode"); // rs.getString(1)
                var name = rs.getString(2);
                var price = rs.getInt(3);
                String s = String.format("%15s\t%30s\t%d", code, name, price);
                System.out.println(s);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            closeResource();
        }
    }

    public static void main(String[] args) throws SQLException {
        var ex04 = new Ex05_CallableStatement();
        ex04.display("RKSK-B");
        ex04.closeResource();
    }
}
/*
Procedure was made before run
////////////////////////////
create procedure sp_ItemInfo
@code varchar(10)
as
select * from Item
where ICode = @code
////////////////////////////

exec sp_ItemInfo 'RKSK-B'
 */

/*
    CallableStatement -
     Các bước tạo
        1. create a stored procedure (before)
            - from RDBMS
            - from make a Statement
       2. create a Callable Object - call stored procedure
    - Callable Object - use call a  stored procedure
    - IN and OUT parameters


 */