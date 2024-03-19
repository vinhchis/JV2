/*
* FileReader & FileWriter (character Stream)
   - used to read/write data (in characters) to files.
        + FileWriter (character) -> OutputStreamWriter (character to byte) -> Writer(character)
        + FileReader(character) -> InputStreamWriter(byte to character) -> Reader(character)
   - chỉ có 1 method:
        +   para = char | number(ASCII), char[], string
        - write()
        - read()
   - nên dùng khi đọc ghi theo từng k tự mà thôi
   - hạn chế sử dụng nếu muốn sử lý nội dung bên trong file
*/
package session02_IO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ex03_File_Class {
    String filePath = "src/session02_IO/data/TestFile.txt";
    File file = new File(filePath);
    void doCreate()
    {
        try{
            if(!file.exists()){
                file.createNewFile();
                System.out.println("New file was created!");
            }else{
                System.out.println("File is existed!");
            }
        }catch (IOException err){
            System.err.println(err.getMessage());
        }

    }

    void doWrite() {
        String data = "Data is inserted!";
        try{
            FileWriter writer = new FileWriter(file);

            writer.write(data);
            writer.write('\n');
            writer.write(53); // 53 (ASCII code) -> 5
            writer.write('\n');
            writer.write(data);

            System.out.println("File has been written successful");
            writer.close();

        }catch (IOException err){
            System.err.println(err.getMessage());
        }
    }

    void doRead(){
        try {
            FileReader reader = new FileReader(file);
            int character;
            while ((character = reader.read()) != -1) {
                // Xử lý ký tự
                System.out.print((char) character);
            }
            System.out.println("\nFile has been read successful");
            reader.close();
        }catch (IOException err){
            System.err.println(err.getMessage());
        }
    }

    void doDelete(){
        if(!file.exists()){
            System.err.println("File is not found");
        }else{
            file.delete();
            System.out.println("File has been deleted");
        }
    }

    public static void main(String[] args)
    {
        var fc = new Ex03_File_Class();
        fc.doCreate();

        fc.doWrite();

        fc.doRead();

        fc.doDelete();
    }
}
