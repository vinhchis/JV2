/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session08_Design;

import java.util.*;

/**
 *
 * @author USER
 */
public class Ex02_InternationalLization {
    Locale viLocale, usLocale;
    ResourceBundle currentBundle,viBundle,usBundle;
    
    public Ex02_InternationalLization(){
        viLocale = new Locale("vi","VN");
        usLocale = new Locale("en","US");
        viBundle = ResourceBundle.getBundle("session08_Design/app_vi_VN",viLocale);
        usBundle = ResourceBundle.getBundle("session08_Design/app_en_US",usLocale);
    }
    
    void display(){
        System.out.println("Greeting message: " + currentBundle.getString("greeting"));
        System.out.println("Farewell message: " + currentBundle.getString("farewell"));
        System.out.println("Inquiry message: " + currentBundle.getString("inquiry"));
    }
    
    void menu(){
//        currentBundle = usBundle;
        currentBundle = viBundle;
        display();
    }
    
    public static void main(String[] args) {
        new Ex02_InternationalLization().menu();
    }
}
