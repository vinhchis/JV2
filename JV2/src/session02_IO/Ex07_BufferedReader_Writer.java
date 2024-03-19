/*
BufferWriter, BufferReader : Character Stream (txt)
    + Loại: Stream ký tự
    + Mục đích: Đọc ghi dữ liệu ký tự với bộ đệm
    + Ưu điểm: Hiệu quả hơn FileReader/FileWriter khi đọc ghi nhiều dữ liệu
        - Viẹc ghi dữ liệu theo nhóm lớn (buffer) hiệu quả hơn so với ghi từng byte một
        - giảm số lần gọi hàm
        - dữ liệu được sắp xếp trong bộ đệm hiểu quả bằng thuật toán
        - Kích thước buffer có thể tuỳ chỉnh


    - Writer <- BufferWriter (FileWriter)
    - Reader <- BufferReader (FileReader)

*/

package session02_IO;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class Ex07_BufferedReader_Writer {
    File file = new File("src/session02_IO/data/TestBRW.txt");

    void doWrite(){
        BufferedWriter bw = null;
        List<Person> list = new LinkedList<>();
        list.add(new Person("Hung", "17171"));
        list.add(new Person("Tu", "f20"));

        try{
            FileWriter fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);

            for(var person : list){
                bw.write(person.toString());
                bw.newLine();
            }

            System.out.println("Successfully!!");

        }catch (IOException e){
            System.err.println(e.getMessage());
        } finally {
            try{
                if(bw != null){
                    bw.flush(); //
                    bw.close(); // Required
                }
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
    }

    void doRead(){
        BufferedReader br = null;
        try
        {
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            List<Person> list = new LinkedList<>();
            String line;
            int i = 1;
            while((line = br.readLine()) != null){
                String[] temp = line.split("_");
                System.out.printf("Person(%2d): code = %10s, name = %10s\n",++i, temp[0], temp[1]);
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        } finally {
            try{
                if(br != null){
                    br.close();
                }
            }catch(IOException e){
                System.err.println(e.getMessage());

            }

        }
    }

    public static void main(String[] args) {
        var ex07 = new Ex07_BufferedReader_Writer();
        ex07.doWrite();
        ex07.doRead();
    }
}
