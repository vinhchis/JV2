/*
ObjectOutputStream, ObjectInputStream - Byte Stream (.bin - binary)
    OutputStream <- ObjectOutputStream
    InputStream <- ObjectInputStream
    + Loại: Stream đối tượng
    + Mục đích: Đọc ghi các đối tượng Java từ/vào tệp
    + Ưu điểm: Phù hợp với lưu trữ các đối tượng Java
*/
package session02_IO;

import java.io.*;
import java.util.ArrayList;

public class Ex06_OjectInput_Output_Stream {
    File file = new File("src/session02_IO/data/Test.bin");

    void doWrite(){
        ObjectOutputStream os = null;
        try{
            FileOutputStream fos = new FileOutputStream(file);
            os = new ObjectOutputStream(fos);
            var object = new Person("Hoang", "F12");
            os.writeObject(object);
            os.writeObject(new Person("Hanh", "F14"));
            System.out.println("Successfully!!");
        }catch (IOException e){
            System.err.println(e.getMessage());
        } finally {
            try{
                if(os != null){
                    os.flush();
                    os.close(); // Required
                }
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
    }

    void doRead(){
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream is = new ObjectInputStream(fis);
            ArrayList<Person> list = new ArrayList<>();

            while (true) {
                try {
                    list.add((Person)is.readObject());
                    // Xử lý object
                } catch (EOFException e) {
                    // Đã đến cuối file
                    break;
                }
            }



            System.out.println("List Person: ");
            list.forEach(System.out::println);
            fis.close();
            is.close();
        }catch (IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        var ex06 = new Ex06_OjectInput_Output_Stream();
        ex06.doWrite();
        ex06.doRead();
    }
}

