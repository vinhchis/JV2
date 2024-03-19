/*
* ZipInputStream & ZipOutputStream : Byte Stream
    - đọc ghi dữ liệu dưới dạng file nén
    - nếu chứa nhiều file -> xử lí thông tin từng file dưới dạng Entry
    - đọc ghi từng file ?
*/
package session02_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Ex08_Zipfile {
    public static void main(String[] args) {
        File file = new File("src/session02_IO/data/Sample.zip");

        try{
            FileInputStream fis = new FileInputStream(file);
            ZipInputStream zis = new ZipInputStream(fis);

            ZipEntry entry = null;
            while((entry = zis.getNextEntry()) != null){
                System.out.println(entry.getName() + " - " + entry.getSize() + "KB");
            }
            zis.close();
            fis.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

    }
}
