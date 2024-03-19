/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutorial05_IO_Load_Save;

//  1.CHA - make Absstract class

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class CHA{
    abstract String screenInfo();
}
//  2. DoiTuong - make subclass
class DoiTuong extends CHA{
    String username,password;
    
    public DoiTuong(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    @Override
    public String screenInfo(){
        return username + "#" +password;
    }
}
//  3.QuanLy - make a Controller class
class QuanLy{
    List<DoiTuong> list;
    Scanner sc;
    File file;
    
    public QuanLy(){
        list = new ArrayList<>();
        sc = new Scanner(System.in);
        file = new File("src/tutorial05_IO_Load_Save/Account.txt");
        try {
            load();
            Login();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    void loadFile(){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String data;
            while((data = br.readLine()) != null){
                String[] tmp = data.split("#");
                list.add(new DoiTuong( tmp[0],tmp[1]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    void load(){
        if(list.isEmpty()){
            loadFile();
        }
        else{
            list.removeAll(list);
            loadFile();
        }
    }
    
    DoiTuong input(){
        System.out.println("Enter username: ");
        String user = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        
        return new DoiTuong(user, password);
    }
    
    boolean search(DoiTuong doituong){
        loadFile();
        for(var d : list){
            if((d.username.equals(doituong.username)) && (d.password.equals(doituong.password)) ){
                return true;
            }
        }
        return false;
    }
    
    void Login(){
        boolean isOK;
        do {            
            DoiTuong doituong = input();
            isOK = search(doituong);
            if(!isOK){
                System.out.println("Username or Password is incorrect");
            }
            else{
                System.out.println("Login successfully!");
                break;
            }
        } while (!isOK);
        
    }
    
    void saveFile() throws IOException{
        boolean isOK = false;
        do {            
            DoiTuong doituong = input();
            isOK = search(doituong);
            if(isOK){
                System.out.println("Account exist");
            }
            else{
                FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.newLine();
                bw.write(doituong.screenInfo());
                bw.flush();
                bw.close();
                System.out.println("Register successfull");
            }
        } while (isOK);
    }
    
    void addnew(){
        while(true){
        try {
            saveFile();
            break;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        }
        
    }
}


//  4. Thuc Thi
public class ThucThi {
    QuanLy ql = new QuanLy();
//    1. menu method
    void menu() throws IOException {
        String tmp1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("---------- Menu ----------");
            System.out.println("login:  Login ");
            System.out.println("register: Register");
            System.out.println("exit: Exit");
            System.out.println("---------------------------");
            System.out.println("Enter num [login | register | exit]:");

            tmp1 = sc.nextLine();
            switch (tmp1) {
                case "login" -> {
                    ql.Login();
                }
                case "register" -> {
                    ql.addnew();
                }
                case "exit" -> {
                    System.out.println("Program exit....");
                    Runtime.getRuntime().exec("notepad src/tutorial05_IO_Load_Save/Account.txt");
                    System.exit(0);
                }
            }
            System.out.println("Continue Y/N:");
            String ch;
            ch = sc.nextLine();
            ch = ch.toUpperCase();
            if (!ch.equalsIgnoreCase("Y")) {
                System.out.println("Program exit....");
                System.exit(0);
            }
        } while (!tmp1.equalsIgnoreCase("exit"));
    }
    
//    2. menu method
    public static void main(String[] args) throws IOException{
        var tt = new ThucThi();
        tt.menu();
    }
}
