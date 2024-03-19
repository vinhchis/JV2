/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session08_Design;

import java.util.*;

class DoiTuong {

    String code, name;

    public DoiTuong(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}

interface DoiTuongDao {

    List<DoiTuong> select();

    void insert(DoiTuong doituong);
}

class DoiTuongDaoImpl implements DoiTuongDao {

    public List<DoiTuong> list;

    @Override
    public void insert(DoiTuong doituong) {
        list = new ArrayList<>();
        list.add(doituong);
        System.out.println("Insert Successfully!");
    }

    @Override
    public List<DoiTuong> select() {
        return list;
    }
}

public class Ex01_DAO {

    DoiTuongDao dao = new DoiTuongDaoImpl();
    Scanner sc = new Scanner(System.in);

    DoiTuong input() {
        String code, name;
        System.out.println("Enter code: ");
        code = sc.nextLine();
        System.out.println("Enter name: ");
        name = sc.nextLine();
        return new DoiTuong(code, name);
    }

    void addNew() {
        DoiTuong doituong = input();
        dao.insert(doituong);
    }

    void display() {
        for (var doituong : dao.select()) {
            System.out.println(doituong);
        }
    }

    public void menu() {
        String option;
        String cont;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("------------------------------------");
            System.out.println("\t1. Add new");
            System.out.println("\t2. Display");
            System.out.println("\t3. Exit");
            System.out.println("------------------------------------");
            System.out.println("Enter Your choice: ");
            option = sc.nextLine();
            switch (option) {
                case "add":
                    addNew();
                    break;
                case "display":
                    display();
                    break;
                case "exit":
                    System.out.println("Program exit.....");
                    System.exit(0);
                default:
                    System.out.println("Your choice is incorrect!");
                    break;

            }
            System.out.println("Continue(Y/N)?");
            cont = sc.nextLine().toUpperCase();
            if (!cont.equalsIgnoreCase("Y")) {
                System.out.println("Program exit....");
                System.exit(0);
            }
        } while (!option.equals("exit"));

    }

    public static void main(String[] args) {
        var ex01 = new Ex01_DAO();
        ex01.menu();
    }
}
