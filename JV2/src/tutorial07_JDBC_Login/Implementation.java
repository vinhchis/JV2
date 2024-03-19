package tutorial07_JDBC_Login;
import session04_JDBC.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

// 1. Parent -> sealed class
sealed class Parent permits Child{
    Scanner sc = new Scanner(System.in);
    private  boolean check(String regex, String input){
        return Pattern.matches(regex, input);
    }
    protected String getName(){
        String reName ="^\\w+$";
        String name;

        do{
            name = sc.nextLine();
            if(!check(reName, name)){
                System.err.println("Username is invalid!!!");
            }
        }while (!check(reName, name));
        return name;
    }

    protected String getPassword(){
        String rePass ="^\\w+$";
        String pass;

        do{
            pass = sc.nextLine();
            if(!check(rePass, pass)){
                System.err.println("Password is invalid!!!");
            }
        }while (!check(rePass, pass));
        return pass;
    }
}

// 2. Object -> extends Parent class
final class Child extends Parent{
    String userName, password;

    void input(){
        System.out.println("Enter username: ");
        userName = getName();
        System.out.println("Enter password: ");
        password = getPassword();
    }
}
// 3. Controller
class Controller{
    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";

    PreparedStatement st;
    ResultSet rs;

    public Controller(){
        cnn = DBConnect.makeConnection(account, password, database);
        login();
    }

    void insert(String userName, String password){
        String query = "insert into [user] values (?, ?)";
        try {
            st = cnn.prepareStatement(query);
            st.setString(1, userName);
            st.setString(2, password);
            int count = st.executeUpdate();
            if(count != 0){
                System.out.println("Successfull");
            }else{
                System.out.println("Fail");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // f: login
    boolean check(String userName, String password){
        String query = "select * from [user] where username=? and password = ?";
        try {

            st = cnn.prepareStatement(query);
            st.setString(1, userName);
            st.setString(2, password);
            rs = st.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    boolean check(String userName){
        String query = "select * from [user] where username=?";
        try {

            st = cnn.prepareStatement(query);
            st.setString(1, userName);
            rs = st.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    void addNew(){
        Child child;

        do {
            child = new Child();
            child.input();
            if (check(child.userName)) {
                System.err.println("Account was existed!!!");
            }
        }while (check(child.userName));

        insert(child.userName, child.password);
        System.out.println("Account added successfully!!!");
    }

    void login(){
        Child child;

        do {
            child = new Child();
            child.input();
            if (!check(child.userName, child.password)) {
                System.err.println("Your username or password is incorrect!!!");
            }else {
                System.out.println("Login Successful");
            }
        }while (!check(child.userName, child.password));
    }
}
// 4. Implementation
public class Implementation {

    Scanner sc = new Scanner(System.in);
    Controller c = new Controller();
    void menu() {
        /* Menu */
        int choice = 0;

        do {
            System.out.println("\t\tMenu");
            System.out.println("\t1. Login");
            System.out.println("\t2. Register");
            System.out.println("\t3. Quit");

            System.out.print("Your choice is: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> c.login();
                case 2 -> c.addNew();
                case 3-> {
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }

            System.out.println("Are you want to continue");
            System.out.println("\t1. Yes");
            System.out.println("\t2. No");
            System.out.print("Your choice is: ");
            int c = Integer.parseInt(sc.nextLine());

            if (c == 2) {
                System.exit(0);
            }

        } while (choice != 3);

    }
    public static void main(String[] args) throws SQLException {
        new Implementation().menu();
    }
}
