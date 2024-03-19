/*
Byte Stream - (file: .data)
    - cung cấp các phương thực để đọc ghi dữ liệu 1 cách rõ ràng theo kiểu nguyên thuỷ
- DataInputStream: Đọc dữ liệu kiểu nguyên thủy từ Stream
    - InputStream <- FilterInputStream <- DataInputStream
- DataOutputStream: Ghi dữ liệu kiểu nguyên thủy vào Stream
    - OutputStream <- FilterOutputStream <- DataOutputStream
- không nên dùng nếu muốn đọc theo String (not primary type)

 */

package session02_IO;

import java.io.*;

public class Ex05_DataInput_Output_Steam {
    File file = new File("src/session02_IO/data/testDIOS.dat");

    void doWrite(){
        DataOutputStream dos = null;
        try{
            FileOutputStream fos = new FileOutputStream(file, true);
            dos = new DataOutputStream(fos);

            dos.writeInt(500);
            dos.writeDouble(50.5d);
            dos.writeChar('A');
            dos.writeBoolean(true);
        }catch (IOException e){
            System.err.println(e.getMessage());
        } finally {
            try{
                if(dos != null){
                    dos.flush(); // force data out to stream to file
                    dos.close(); // Required
                }
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
    }

    void doRead(){
        try{
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            String s = String.format("%d - %.2f - %c - %s", dis.readInt(), dis.readDouble(), dis.readChar(),dis.readBoolean());
            System.out.println(s);

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        var ex05 = new Ex05_DataInput_Output_Steam();
        ex05.doWrite();
        ex05.doRead();
    }
}
