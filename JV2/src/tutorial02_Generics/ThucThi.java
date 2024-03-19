
package tutorial02_Generics;

import java.util.Scanner;
public class ThucThi {
    void menu(){
    int num ;
        Scanner sc = new Scanner(System.in);
        var quanly = new QuanLi();
       
        do{
            System.out.println("---------- Menu ----------");
            System.out.println("1. Add");
            System.out.println("2. Display");
            System.out.println("3. Exit");
            System.out.println("---------------------------");
            System.out.println("Enter num [1-3]:");
           
            num = sc.nextInt();
            switch(num){
                case 1 -> quanly.addNew();
                case 2 -> quanly.display();
                case 3 ->{
                    System.out.println("Program exit....");
                    System.exit(0);
                } 
            }
            sc.nextLine();
            System.out.println("Continue Y/N:");
            String ch;
            ch = sc.nextLine();
            ch = ch.toUpperCase();
            if(ch.equalsIgnoreCase("Y")){
                System.out.println("---------- Menu ----------");
                System.out.println("1. Add");
                System.out.println("2. Display");
                System.out.println("3. Exit");
                System.out.println("---------------------------");
                System.out.println("Enter num [1-3]:");
            }
            else{
                System.out.println("Program exit....");
                System.exit(0);
            }
        }while(num != 3);
    }
    
    public static void main(String[] args) {
        new ThucThi().menu();
    }
}
