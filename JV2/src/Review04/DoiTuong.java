package Review04;

import java.util.Scanner;
import java.util.regex.Pattern;
public class DoiTuong {
    private String user, pinCode;
    Scanner sc = new Scanner(System.in);

    public String getUser() {
        return user;
    }

    public String getPinCode() {
        return pinCode;
    }
    void input() {
        /*
            user: not blank.
            pinCode: 6 digits in fixed length
        */
        String user, pinCode;
        String rName = "^\\w+$";
        String rPinCode = "^\\d{6}$";
        while (true) {
            try {
                System.out.println("Enter username: ");
                user = sc.nextLine().trim();

                if (!Pattern.matches(rName, user)) {
                    throw new Exception("Username must not be empty!!!");
                }
                break;
            } catch (Exception e) {
                System.err.println("Invalid Username - error: " + e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.println("Enter pinCode: ");
                pinCode = sc.nextLine().trim();

                if (!Pattern.matches(rPinCode, pinCode)) {
                    throw new Exception("PinCode must be must have 6 digits in fixed length");
                }
                break;
            } catch (Exception e) {
                System.err.println("Invalid PinCode - error: " + e.getMessage());
            }
        }

        this.user = user;
        this.pinCode = pinCode;
    }
}
