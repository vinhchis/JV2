package session01_util_package;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ex01_GetElements {
    List days;

    public Ex01_GetElements(){
        days = new ArrayList();
        days.add("Tuesday");
        days.add("Thursday");
        days.add("Saturday");
    }

    void display(){
        for(var day : days){
            System.out.println(day);
        }

        Iterator it = days.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        days.forEach(day -> {
            System.out.println(day);
        });

    }

    public static void main(String[] args) {
        new Ex01_GetElements().display();
    }
}
