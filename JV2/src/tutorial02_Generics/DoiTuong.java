
package tutorial02_Generics;
import java.util.Scanner;
public class DoiTuong {
    int code;
    String name;
    Scanner sc = new Scanner(System.in);
    
    public DoiTuong(){
        System.out.println("Enter code:");
        code = Integer.parseInt(sc.nextLine());
        System.out.println("Enter name:");
        name = sc.nextLine();
    }
    
    @Override
    public String toString() {
        String s = " Code: " + code + "\tName: " + name;
        return s;
    }
}
