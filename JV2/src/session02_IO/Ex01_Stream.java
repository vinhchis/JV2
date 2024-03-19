package session02_IO;

import java.util.Scanner;

public class Ex01_Stream {

    public static void main(String[] args) {
        //1. Standard in Stream
        Scanner sc = new Scanner(System.in);

        String output;
        do {
            // 2. Standard out Stream
            System.out.print("Enter a String: ");
            output = sc.nextLine();
            if(output.isBlank()){
                // 3. Standard err Stream
                System.err.println("String is required");
                break;
            }

            System.out.println("String: " + output);
        } while(!output.equalsIgnoreCase("end"));
    }
}
