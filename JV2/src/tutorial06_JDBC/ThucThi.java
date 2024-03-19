/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutorial06_JDBC;

//  Đối Tượng
import java.sql.*;
import java.util.Scanner;
import tutorial01_Collection.QuanLi;

class DoiTuong {

    String code, name;
    int price;
    Scanner sc = new Scanner(System.in);

    void input() {
        System.out.println("Enter code: ");
        code = sc.nextLine();
        System.out.println("Enter name: ");
        name = sc.nextLine();
        System.out.println("Enter price: ");
        price = Integer.parseInt(sc.nextLine());

    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Code : %s\t,Name : %s\t,Price : %d", code, name, price);
    }
}

//  Quản Lý
class QuanLy {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public QuanLy() {
        cnn = DBConnect.makeConnection();
    }

    void select() throws SQLException {
        DoiTuong doituong = new DoiTuong();
        String query = "select * from Item";

//        Create ResultSet to Item table
        st = cnn.prepareStatement(query);
        rs = st.executeQuery();

//        Display Information
        while (rs.next()) {
            doituong.setCode(rs.getString(1)); //rs.getString(1)
            doituong.setName(rs.getString(2));
            doituong.setPrice(rs.getInt(3));
            System.out.println(doituong);
        }

    }

    void insert(String code, String name, int price) throws SQLException {
        String query = "insert into Item values(?,?,?)";
        st = cnn.prepareStatement(query);
        /////////////////////////////////////////////////////
        st.setString(1, code);
        st.setString(2, name);
        st.setInt(3, price);

        /////////////////////////////////////////////////////
        int count = st.executeUpdate();
        if (count == 0) {
            System.out.println("Nothing to save");
        } else {
            System.out.println("Insert successfully!");
        }
//        Display Information
    }

    void update(String code, String name, int price) throws SQLException {
        String query = "update Item set ItemName = ?, Rate = ? where ICode = ?";
        st = cnn.prepareStatement(query);
        st.setString(1, name);
        st.setInt(2, price);
        st.setString(3, code);
        int count = st.executeUpdate();
        if (count == 0) {
            System.out.println("Nothing to update");
        }
    }

    void delete(String code) throws SQLException {
        String query = "delete from Item where ICode = ? ";
        st = cnn.prepareStatement(query);
        st.setString(1, code);
        int count = st.executeUpdate();
        if (count == 0) {
            System.out.println("Nothing to delete");
        }
    }

    void addNew(DoiTuong doituong) throws SQLException {
        doituong = new DoiTuong();
        doituong.input();
        insert(doituong.getCode(), doituong.getName(), doituong.getPrice());
    }

    void display() throws SQLException {
        select();
    }

    void remove(String code) throws SQLException {
        delete(code);
        System.out.println("Deleted successfully!");
    }
}

public class ThucThi {

    QuanLy ql = new QuanLy();

    void menu() throws SQLException{
        int num;
        Scanner sc = new Scanner(System.in);
        var quanly = new QuanLi();
        do {
            System.out.println("---------- Menu ----------");
            System.out.println("1. Add");
            System.out.println("2. Display");
            System.out.println("3. Remove");
            System.out.println("4. Exit");

            System.out.println("---------------------------");
            System.out.println("Enter num [1-4]:");

            num = sc.nextInt();
            switch (num) {
                case 1 ->
                    ql.addNew(new DoiTuong());
                case 2 ->
                    ql.display();
                case 3 -> {
                    String code;
                    System.out.println("Enter Item code: ");
                    code = sc.next();
                    ql.remove(code);
                            }
                case 4 -> {
                    System.out.println("Program exit....");
                    System.exit(0);
                }
            }
            sc.nextLine();
            System.out.println("Continue Y/N:");
            String ch;
            ch = sc.nextLine();
            ch = ch.toUpperCase();
            if (!ch.equalsIgnoreCase("Y")) {
              System.out.println("Program exit....");
                System.exit(0);
            }
        } while (num != 4);
    }

    public static void main(String[] args) throws SQLException {
        var thucthi = new ThucThi();
        thucthi.menu();
    }
}
