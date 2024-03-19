package Final.entities;

import java.util.Scanner;
import java.util.regex.Pattern;

class Helper{
    static boolean IsValidInput(String regex, String input){
        return Pattern.matches(regex, input);
    }
}
public class Staff {
    private String code, name, gender;
    private double salary;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    Scanner sc = new Scanner(System.in);

    public Staff(){
        this.code = "";
        this.name = "";
        this.gender = "";
        this.salary = 0f;
    }
    public Staff(String code, String name, String gender, double salary){
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
    }



    public boolean validate(){
        String rCode = "^\\w\\d{5}$";
        String rName = "^[a-zA-Z0-9 ]*$";
        String rGender = "^(Male|Female)$";
        String rSalary = "^\\d+(\\.\\d+)?$";

        return Helper.IsValidInput(rCode , this.code) && Helper.IsValidInput(rName , this.name)
                && Helper.IsValidInput(rGender , this.gender) && Helper.IsValidInput(rSalary , String.valueOf(this.salary));

    }

    public void input(){
        String input;
        while (true) {
            try {
                System.out.println("Enter Code: ");
                this.code = sc.nextLine().trim();

                System.out.println("Enter Name: ");
                this.name = sc.nextLine().trim();

                System.out.println("Enter Gender(Male or Female): ");
                this.gender = sc.nextLine().trim();

                System.out.println("Enter Salary: ");
                this.salary = Integer.parseInt(sc.nextLine().trim());

                if (!validate()){
                    throw new Exception("Your Input isn't valid!!!. Please again!~");
                }

                break;
            } catch (Exception e) {
                System.err.println("Invalid - error: " + e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Staff {code = %10s, name = %10s, gender = %s, salary = %.2f $}", code, name, gender, salary);
    }
}
