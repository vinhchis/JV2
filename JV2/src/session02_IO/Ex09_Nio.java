package session02_IO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Ex09_Nio {
    public static void main(String[] args) {
        RandomAccessFile copy, paste;
        FileChannel from, to;
        try {
            copy = new RandomAccessFile("src/session02_IO/data/TestBRW.txt", "rw");
            from = copy.getChannel();

            paste = new RandomAccessFile("src/session02_IO/data/CopyTestBRW.txt", "rw");
            to = paste.getChannel();

            long count = from.size();
            to.transferFrom(from, 0, count);

            System.out.println("Successfully!");
        } catch (FileNotFoundException e){
            e.getStackTrace();
        } catch(IOException e){
            e.getStackTrace();
        }
    }
}
