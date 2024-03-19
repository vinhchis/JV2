/*
FIleInputStream & FileOutPutStream : ByteStream
    - used to read/write data (in bytes) to files.
        + FileInput <- FileInputStream
        + FileOutput <- FileOutPutStream
    - only method
        + read() - đọc từng bytes
        + write() - ghi từng bytes
    - nên dùng khi đọc ghi theo từng byte mà thôi
    - hạn chế sử dụng nếu muốn sử lý nội dung bên trong file
*/

package session02_IO;

import java.io.*;

public class Ex04_FileInput_Output_Stream {
    String path = "src/session02_IO/data/";
    File file = new File(path + "TestFile.txt");
    String filename = "CopyFile";
    void fileCopy() throws IOException {
        try{
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(path + filename + ".txt");
            int ch;
            while((ch = fis.read()) != -1){
                fos.write(ch);
            }
            System.out.println("Successfully!");
            fis.close();
            fos.close();
        }catch (FileNotFoundException e){
            System.err.println("Not Found" + e.getMessage());
        }

    }
    public static void main(String[] args) throws IOException {
        var ex04 = new Ex04_FileInput_Output_Stream();
        ex04.fileCopy();
    }
}
