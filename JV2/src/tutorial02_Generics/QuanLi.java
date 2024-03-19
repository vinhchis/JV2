package tutorial02_Generics;

import java.util.HashMap;

public class QuanLi {
//    int max,next;
//    DoiTuong[] doituong;

    int i = 0;
    HashMap<Integer, DoiTuong> ht;

    public QuanLi() {
        ht = new HashMap<>();
    }

    void addNew() {
        DoiTuong doituong = new DoiTuong();
        ht.put(++i, doituong);
    }

    void display() {
//        if (ht.size() > 0) {
//            ht.forEach((k, v) -> {
//                System.out.println("ID: " + k + v);
//            });
//        } else {
//            System.out.println("Nothing to display");
//        }
        
        if(ht.isEmpty()){
        System.out.println("Nothing to display");
        }
        
        else{
         ht.forEach((k, v) -> {
                System.out.println("ID: " + k + v);
            });
        }
    }
}
