package Review04;

import session04_JDBC.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QuanLy {
    Scanner sc = new Scanner(System.in);
    final Connection cnn = DBConnect.makeConnection("sa", "123456", "DoituongDB");
    PreparedStatement st;
    ResultSet rs;

    public QuanLy() {
        while (true) {
            if (login()) {
                System.out.println("Welcome to Application");
                break;
            } else {
                System.err.println("Your username or password is incorrect!!!");
            }
        }
    }

    boolean login() {
        var d = new DoiTuong();

        d.input();

        return check(d, true);

    }

    boolean check(DoiTuong d, boolean isFirst) {
        String query = "select * from Doituong where [user] = ?";
        if (isFirst) {
            query += "and pinCode = ?";
        }
        try {
            st = cnn.prepareStatement(query);
            st.setString(1, d.getUser());
            if (isFirst) {
                st.setString(2, d.getPinCode());
            }
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    void addNew() {
        var d = new DoiTuong();

        while(true){
            System.out.println("Enter your information:");
            d.input();
            if (check(d, false)) {
                System.err.println("Account was existed!!!. Please, FILL AGAIN");
            }else{
                break;
            }
        }

        insert(d);
        System.out.println("Account added successfully!!!");
    }

    void insert(DoiTuong d) {
        String query = "insert into [Doituong] values (?, ?)";
        try {
            st = cnn.prepareStatement(query);
            st.setString(1, d.getUser());
            st.setString(2, d.getPinCode());
            int count = st.executeUpdate();
            if (count != 0) {
                System.out.println("Successfully");
            } else {
                System.out.println("Fail");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void menu() {
        /* Menu */
        int choice = 0;

        do {
            System.out.println("\t\tMenu");
            System.out.println("\t1. Login");
            System.out.println("\t2. Save to Database");
            System.out.println("\t3. Quit");

            System.out.print("Your choice is: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> {
                    if (login()) {
                        System.out.println("Login Successfully");
                    } else {
                        System.err.println("Fail to Login!!");
                    }
                }
                case 2 -> addNew();
                case 3 -> {
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

    public static void main(String[] args) {
        new QuanLy().menu();
    }
}

