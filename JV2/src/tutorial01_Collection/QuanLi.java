
package tutorial01_Collection;

import java.util.ArrayList;

public class QuanLi {
//    int max,next;
//    DoiTuong[] doituong;
    ArrayList list;
    public QuanLi() {
//        max = 2;
//        next = 0;
//        doituong = new DoiTuong[max];
          list = new ArrayList();
          list.add(new DoiTuong(1,"1"));
          list.add(new DoiTuong(2,"2"));
    }
    void addNew(){
       DoiTuong doituong = new DoiTuong();
       list.add(doituong);
//       next++;
         
    }
    void display(){
//        for (int i = 0; i < next; i++) {
//            System.out.println(doituong[i]);
//        }
        for(var value : list){
            System.out.println(value);
        }
    }
}
