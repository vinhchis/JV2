
package tutorial03_IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Ex02_Load_Save {
    List<String> list;
    public Ex02_Load_Save(){
        list = new ArrayList<>();
    }
    
    void loadFile (File file){
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String data;
            
            while((data=br.readLine()) != null){
                String[] tmp = data.split("#");
                list.add(tmp[0]);
                list.add(tmp[1]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally{
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        
        }
    }
    
    void saveFile(File file, String s){
        
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    void display(){
    list.forEach((element) ->{
        System.out.println(element);
    });
    }
    public static void main(String[] args) {
        var ex02 = new Ex02_Load_Save();
        File file = new File("src/tutorial03_IO/Account.txt");
        String s = "fpt#123";
        ex02.loadFile(file);
//        ex02.saveFile(file, s);
        ex02.display();
    }
}
