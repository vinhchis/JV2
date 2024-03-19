package Final.service;

import Final.entities.Staff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StaffService {
    private TreeMap<String, Staff> staffList;

    public Scanner sc = new Scanner(System.in);

    private final String filePath = "src/Final/entities/Staffs.txt";
    private File file = new File(filePath);

    public StaffService(){
        staffList = new TreeMap<>();
        addRawData();
    }

    private void addRawData(){
        staffList.put("f1", new Staff("f1", "A", "Male", 9002));
        staffList.put("f2", new Staff("f2", "B", "Male", 9002));
        staffList.put("f3", new Staff("f3", "C", "Male", 9002));
        staffList.put("f4", new Staff("f4", "D", "Male", 9002));

    }

    void menu() throws IOException {
        /* Menu */
        int choice = 0;

        do {
            System.out.println("\t\tMenu");
            System.out.println("\t1. Create a new Staff");
            System.out.println("\t2. Find Staff by Code");
            System.out.println("\t3. Staff List");
            System.out.println("\t4. Exit");

            System.out.print("Your choice is: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> addNew();
                case 2 -> searchByCode();
                case 3 -> displayAllStaffs();
                case 4 -> {
                    writeData();
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

    private void writeData(){
        try {
        if(!file.exists()){
                file.createNewFile();
        } else{
            // Write

            // FileWriter (character) -> OutputStreamWriter (character to byte) -> Writer(character)
            FileWriter writer = new FileWriter(file);
            for(Map.Entry<String, Staff> entry: staffList.entrySet()){
                var staff = entry.getValue();
                String data = staff.getCode()+ "#" + staff.getName() + "#" + staff.getGender() + "#" + staff.getSalary();
                writer.write(data);
                writer.write("\n");
            }
            writer.close();

        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void addNew() {
        var staff = new Staff();
        staff.input();
        if(staff.validate()){
            staffList.put(staff.getCode(), staff);
            System.out.println("Add successfully!!!");
        }else{
            System.err.println("Your Staff Information is not valid!!!");
        }
    }

    private void  searchByCode() {
        System.out.println("Enter Code: ");
        String code = sc.nextLine().trim();

        if(staffList.containsKey(code)){
            System.out.println("Your result: " +  staffList.get(code));
        }else{
            System.out.println("Nothing to display");
        }

    }


    private void displayAllStaffs() {
        if(staffList.isEmpty()){
            System.out.println("Nothing to display!!!");
        }else{
            System.out.println("\tStaffs List");
            for (var staff : staffList.values()){
                System.out.println(staff);
            }
//            for(Map.Entry<String, Staff> entry: staffList.entrySet()){
//                System.out.println(entry.getValue());
//            }
        }

    }

    public static void main(String[] args) throws IOException {
        new StaffService().menu();
    }
}
