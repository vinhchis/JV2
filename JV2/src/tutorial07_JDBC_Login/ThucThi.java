/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutorial07_JDBC_Login;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;



sealed class CHA permits DoiTuong {

    Scanner sc = new Scanner(System.in);

    private boolean check(String regax, String input) {
        return Pattern.matches(regax, input);
    }

    protected String getName() {
        String reName = "^\\w+$";
        String name;
        do {
            name = sc.nextLine();
            if (!check(reName, name)) {
                System.out.println("Username is invalid!");
            }
        } while (!check(reName, name));
        return name;
    }

    protected String getPassword() {
        String rePass = "^\\d+$";
        String password;
        do {
            password = sc.nextLine();
            if (!check(rePass, password)) {
                System.out.println("Password is invalid");
            }
        } while (!check(rePass, password));
        return password;
    }
}

final class DoiTuong extends CHA {

    String username, password;

    void input() {
        System.out.println("Enter username: ");
        username = getName();
        System.out.println("Enter password: ");
        password = getPassword();
    }

}


class QuanLy {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public QuanLy() {
        cnn = DBConnect.makeConnection();
        login();
    }

    void insert(String username, String password) throws SQLException {
        String query = "insert into [user] values (?,?)";
        st = cnn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, password);
        int count = st.executeUpdate();
        if (count == 0) {
            System.out.println("Register fails");
        }
    }

    boolean load(String username, String password) {

        String query = "select * from [user] where username = ? and password = ?";
        try {
            st = cnn.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());     
        }
                   return false;

    }

    void login(){
        DoiTuong doituong;
        do {
            doituong = new DoiTuong();
            doituong.input();
            if (!load(doituong.username, doituong.password)) {
                System.out.println("Incorrrect user name or password");
            }
        } while (!load(doituong.username, doituong.password));
    }

    void addNew() throws SQLException {
        DoiTuong doituong = new DoiTuong();
        do {
            doituong = new DoiTuong();
            doituong.input();
            if (load(doituong.username, doituong.password)) {
                System.out.println("Account exist!");
            }
        } while (!load(doituong.username, doituong.password));

        insert(doituong.username, doituong.password);
    }
}

public class ThucThi {

    QuanLy ql = new QuanLy();
    void menu() throws SQLException {
        

        int num;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("---------- Menu ----------");
            System.out.println("1. Login");
            System.out.println("2. Register Member");
            System.out.println("3. Exit");

            System.out.println("---------------------------");
            System.out.println("Enter num [1-3]:");

            num = sc.nextInt();
            switch (num) {
                case 1 ->
                    ql.login();
                case 2 ->
                    ql.addNew();
                case 3 -> {
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
        } while (num != 3);
    }

    public static void main(String[] args) throws SQLException {
        var thucthi = new ThucThi();
        thucthi.menu();
    }
}
