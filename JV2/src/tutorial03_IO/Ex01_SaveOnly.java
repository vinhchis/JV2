package tutorial03_IO;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

class DoiTuong {

    String code, name;

    public DoiTuong(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}

public class Ex01_SaveOnly {

    String path = "src/tutorial03_IO/";
    List<DoiTuong> list;

    public Ex01_SaveOnly() {
        list = new ArrayList<>();
        list.add(new DoiTuong("Code01", "A"));
        list.add(new DoiTuong("Code02", "B"));
    }

    void useBufferWriter() throws IOException {
        File file = new File(path + "useBufferWriter.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        for (var doituong : list) {
            bw.write(doituong.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
        System.out.println("Save by 'bw' successfully!");

    }

    void useDataOutputStream() throws FileNotFoundException, IOException {
        File file = new File(path + "useDataOutputStream.dat");
        FileOutputStream fos = new FileOutputStream(file, true);
        DataOutputStream dos = new DataOutputStream(fos);
        for (var doituong : list) {
            dos.writeChars(doituong.toString());
        }
        dos.flush();
        dos.close();
        System.out.println("Save by 'dos' successfully!");
    }

    void useObjectOutputStream() throws FileNotFoundException, IOException {
        File file = new File(path + "useObjectOutputStream.bin");
        FileOutputStream fos = new FileOutputStream(file, true);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        for (var doituong : list) {
            os.writeChars(doituong.toString());
        }
        os.flush();
        os.close();
        System.out.println("Save by 'os' successfully!");
        
    }

    public static void main(String[] args) throws IOException {
        var ex01 = new Ex01_SaveOnly();
        ex01.useBufferWriter();
        ex01.useDataOutputStream();
        ex01.useObjectOutputStream();
    }
}
