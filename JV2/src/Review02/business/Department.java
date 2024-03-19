package Review02.business;

import Review02.emp.Accounts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Department {
    private List<Accounts> list;
    private final String filePath = "src/Review02/account.txt";
    private File file = new File(filePath);
    Scanner sc = new Scanner(System.in);
    public Department(){
        list = new ArrayList<>();
    }
    void firstLogin() {
        if(!file.exists()){
            System.out.println("account.txt file is not exists");
        }else{
            // get data to list
            loadFile(file);
            // check
            while (true){
                try{
                    boolean isExist = false;
                    Accounts acc = input();

                    if(acc.getUser().equals("admin")
                            && acc.getPassword().equals("123")){
                        isExist = true;
                    }

                    if(isExist) {
                        System.out.println("Login Successfully!!!");
                        break;
                    }else{
                        throw new Exception("Your user or password is not correct!!");
                    }
                }catch (Exception err){
                    System.err.println(err.getMessage());
                }
            }
            menu();
        }
    }
    void login(){
        // input
        Accounts acc = input();

        if(check(acc)){
            System.out.println("Login Successfully!!!");
        }else{
            System.out.println("Your user or password is not correct!!");
        }
    }

    void addNew(){
        var acc = input();
        // duplicate check
        if(searchByUser(acc.getUser()) == null){
            list.add(acc);
        }
    }
    private Accounts searchByUser(String user){
        for(var acc : list) {
            if(user.equals(acc.getUser())){
                return acc;
            }
        }
        return null;
    }

    private boolean check(Accounts acc){
        for(var account : list){
            if(acc.getUser().equals(account.getUser())
                    && acc.getPassword().equals(account.getPassword())){
                return true;
            }
        }
        return false;
    }

    private void saveFile(File file) {
        BufferedWriter bw = null;
        FileWriter fw;
        try{
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for(var acc : list){
                String data = acc.getUser() + "#" + acc.getPassword();
                bw.write(data);
                bw.newLine();
            }
            System.out.println("Added Successfully !!");
        }catch (IOException e){
            System.err.println(e.getMessage());
        } finally {
            try{
                if(bw != null){
                    bw.flush();
                    bw.close();
                }

            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
    }

    private void loadFile(File file) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;

            // Add accounts to list
            while ((line = br.readLine()) != null) {
                String[] temp = line.split("#");
                list.add(new Accounts(temp[0], temp[1]));
            }
            System.out.println("Load Data Successfully");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
    }
        }
    private Accounts input(){
        String user,pass;
        while (true) {
            try {
                System.out.println("Enter User: ");
                user = sc.nextLine().trim();
                if (user.isBlank()) {
                    throw new Exception("User must be not blank!");
                }
                break;
            } catch (Exception e) {
                System.err.println("Invalid User - error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Enter Password: ");
                pass = sc.nextLine().trim();
                if (pass.isBlank()) {
                    throw new Exception("Password must be not blank!");
                }
                break;
            } catch (Exception e) {
                System.err.println("Invalid Password - error: " + e.getMessage());
            }
        }

        return new Accounts(user, pass);
    }
    void menu() {
        /* Menu */
        int choice = 0;

        do {
            System.out.println("Welcome to Account Management System");
            System.out.println("\t\tMenu");
            System.out.println("\t1. Login");
            System.out.println("\t2. Add account");
            System.out.println("\t3. Exit");

            System.out.print("Your choice is: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> login();
                case 2 -> addNew();
                case 3 -> {
                    try{
                        Runtime.getRuntime().exec("notepad " + filePath);
                    }catch (IOException e){
                        System.err.println(e.getMessage());
                    }
                    saveFile(file);
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }

            System.out.println("Are you want to continue ?");
            System.out.println("\t1. Yes");
            System.out.println("\t2. No");
            System.out.print("Your choice is: ");
            int c = Integer.parseInt(sc.nextLine());

            if (c == 2) {
                System.exit(0);
            }

        } while (choice != 3);
    }

    public static void main(String[] args)  {
        var de = new Department();
        de.firstLogin();
    }
}
