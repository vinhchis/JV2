package Review02;

import Review02.emp.Accounts;

public class Test {
    public static void main(String[] args) {
        String line = "abc#112";
        int shape_index = line.indexOf('#');

        String user = line.substring(0, shape_index);
        String pass = line.substring(shape_index + 1);

        System.out.println("user " + user + " _ " + "pass: " + pass);
    }
}
