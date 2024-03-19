package session05_DesignPattern;

import java.util.Locale;
import java.util.ResourceBundle;

public class Ex02_Internationalization {
    Locale viLocale, usLocale;
    ResourceBundle currentBundle, viBundle, usBundle;

    public Ex02_Internationalization(){
        this.viLocale = new Locale("vi", "VN");
        this.usLocale = new Locale("en", "US");
        this.viBundle = ResourceBundle.getBundle("session05_DesignPattern/app_vi_VN", viLocale);
        this.usBundle = ResourceBundle.getBundle("session05_DesignPattern/app_en_US", usLocale);
    }

    void display(){
        System.out.println("Greeting message: " + currentBundle.getString("greeting"));
        System.out.println("Farewell message: " + currentBundle.getString("farewell"));
        System.out.println("Inquiry message: " + currentBundle.getString("inquiry"));
    }

    void menu(){
        currentBundle = viBundle;
//        currentBundle = usBundle;
        display();
    }

    public static void main(String[] args) {
        var ex02 = new Ex02_Internationalization();
        ex02.menu();
    }

}
