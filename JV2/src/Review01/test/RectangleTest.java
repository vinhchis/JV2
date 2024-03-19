package Review01.test;

import Review01.works.Rectangle;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

class Helper{
    static boolean IsValidInput(String regex, String input){
        return Pattern.matches(regex, input);
    }
}

public class RectangleTest {
    private ArrayList<Rectangle> list;
    private final String filePath = "src/Review01/Rectangle.txt";
    private File file = new File(filePath);

    Scanner sc = new Scanner(System.in);
    public RectangleTest(){
        list = new ArrayList<>();
        addRawData();
    }

    void addRawData(){
        list.add(new Rectangle(10.2, 2));
        list.add(new Rectangle(2.90, 4.2));
        list.add(new Rectangle(5.6, 6.10));

    }

    void addNew(){
        double w ,h;
        String input;
        String regex = "^[-+]?[0-9]+(\\.[0-9]+)?$";

        while (true) {
            try {
                System.out.println("Enter Width: ");
                input = sc.nextLine().trim();
                if (!Helper.IsValidInput(regex, input)) {
                    throw new Exception("Width must be number");
                }
                w = Double.parseDouble(input);
                break;
            } catch (Exception e) {
                System.err.println("Invalid Width - error: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Enter Height: ");
                input = sc.nextLine().trim();

                if (!Helper.IsValidInput(regex, input)) {
                    throw new Exception("Height must be must be number");
                }
                h = Double.parseDouble(input);
                break;
            } catch (Exception e) {
                System.err.println("Invalid Width - error: " + e.getMessage());
            }
        }
        var rectangle = new Rectangle(w,h);
        list.add(rectangle);
    }


    void saveFile() throws IOException {
        // create file
        if(!file.exists()){
            file.createNewFile();
        } else{
            // Write

            // FileWriter (character) -> OutputStreamWriter (character to byte) -> Writer(character)
            FileWriter writer = new FileWriter(file);
            for(var rectangle : list){
                String data = rectangle.getWidth() + "#" + rectangle.getHeight();
                writer.write(data);
                writer.write("\n");
            }
            writer.close();

        }

    }

    void display(){
        System.out.println("List of Rectangles");
        for (Rectangle rectangle : list) {
            System.out.println(rectangle);
        }
    }


    void menu() throws IOException{
        /* Menu */
        int choice = 0;

        do {
            System.out.println("\t\tMenu");
            System.out.println("\t1. Add new Rectangle");
            System.out.println("\t2. Display");
            System.out.println("\t3. Save");
            System.out.println("\t4. Quit");

            System.out.print("Your choice is: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> addNew();
                case 2 -> display();
                case 3 -> saveFile();
                case 4 -> {
                    Runtime.getRuntime().exec("notepad " + filePath);
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

        } while (choice != 4);

    }

    public static void main(String[] args) throws IOException {
        new RectangleTest().menu();
    }
}
