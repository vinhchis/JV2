/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session06_DataStructure;

import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

class DoiTuong {

    String code, name;
    Scanner sc;

    public DoiTuong() {
        sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        name = sc.nextLine();
        System.out.println("Enter code: ");
        code = sc.nextLine();

    }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}

public class Ex01_Enumeration {

    Vector<DoiTuong> list = new Vector<>();

    void addNew(DoiTuong doituong) {

        list.add(doituong);
    }

    void display() {
        Enumeration enums = list.elements();
        while(enums.hasMoreElements()){
            System.out.println(enums.nextElement());
        }
    }

    public static void main(String[] args) {
        var ex01 = new Ex01_Enumeration();
        ex01.addNew(new DoiTuong());
        ex01.display();
    }
}
