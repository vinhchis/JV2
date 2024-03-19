package session02_IO;

//  1. Helper - check validation
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

class Helper {

    static boolean checkValid(String regax, String input) {
        return Pattern.matches(regax, input);
    }
}
//  2. DoiTuong - make a Model class

class Chi {

    Scanner sc = new Scanner(System.in);
    //    1. Properties
    String code, name;
//    2. input() method

    void input() {
        String reCode = "^[A]\\d{3}$";
        String reName = "^\\w{6,}$";

        do {
            System.out.println("Enter code: ");
            code = sc.nextLine();
            if(!Helper.checkValid(reCode, code)){
                System.out.println("Code invalid!");
            }

        } while (!Helper.checkValid(reCode, code));

        do {
            System.out.println("Enter name: ");
            name = sc.nextLine();
            if(!Helper.checkValid(reName, name))
            {
                System.out.println("Name invalid!");
            }

        } while (!Helper.checkValid(reName, name));

    }
//    3. Override

    @Override
    public String toString() {
        return code + " - " + name;
    }
}
//  3. QuanLy - make a Controller class

class Long {

    TreeMap<String, Chi> list;

    public Long() {
        list = new TreeMap<>();
    }

    void addNew(Chi doituong) {
        doituong = new Chi();
        doituong.input();
        list.put(doituong.code, doituong);
    }

    void display() {
        if (list.isEmpty()) {
            System.out.println("Nothing to display");
        } else {
            list.forEach((code, doituong) -> {
                System.out.println(doituong);
            });

        }
    }

    void saveFile(String filename) {
        Scanner sc = new Scanner(System.in);
        try {
            File file = new File("src/tutorial1_IO_Save/" + filename + ".txt");
            if (!file.exists()) {
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                for (var d : list.values()) {
                    bw.write(d.toString());
                    bw.newLine();
                }
                System.out.println("Save successfully!");
                bw.flush();
                bw.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

//  4. ThucThi - make a View class
public class Tu {

    void menu() throws IOException{
        String tmp;
        String filename = null;
        Scanner sc = new Scanner(System.in);
        Long ql = new Long();

        do {
            System.out.println("---------- Menu ----------");
            System.out.println("add:  Add ");
            System.out.println("show: Display");
            System.out.println("save:  Save");
            System.out.println("exit: Exit");
            System.out.println("---------------------------");
            System.out.println("Enter num [1-4]:");

            tmp = sc.nextLine();
            switch (tmp) {
                case "add" -> {
                    ql.addNew(new Chi());
                }
                case "show" -> {
                    ql.display();
                }
                case "save" -> {
                    System.out.println("Enter filename: ");
                    filename = sc.nextLine();
                    ql.saveFile(filename);
                }
                case "exit" -> {
                    System.out.println("Program exit....");
                    Runtime.getRuntime().exec("notepad src/tutorial1_IO_Save/" + filename + ".txt");
                    System.exit(0);
                }
            }
            sc.nextLine();
            System.out.println("Continue Y/N:");
            String ch;
            ch = sc.nextLine();
            ch = ch.toUpperCase();
            if (ch.equalsIgnoreCase("Y")) {
                System.out.println("---------- Menu ----------");
                System.out.println("1. Add new Retangle");
                System.out.println("2. Display");
                System.out.println("3. Save");
                System.out.println("4. Exit");
                System.out.println("---------------------------");
                System.out.println("Enter num [1-4]:");
            } else {
                System.out.println("Program exit....");
                System.exit(0);
            }
        } while (!tmp.equalsIgnoreCase("exit"));
    }

    public static void main(String[] args) throws IOException{
        new Tu().menu();
    }
}
