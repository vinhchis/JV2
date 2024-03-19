/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session06_DataStructure;

import java.io.FileReader;
import java.util.Properties;


public class Ex05_Properties {
    public static void main(String[] args) throws Exception{
        
        FileReader fr = new FileReader("src/Session06_DataStructure/app_vi_VN.properties");
        Properties properties = new Properties();
        properties.load(fr);
        
        System.out.println(properties.get("greeting"));
        System.out.println(properties.get("farewell"));
        System.out.println(properties.get("inquiry"));
    }
}
