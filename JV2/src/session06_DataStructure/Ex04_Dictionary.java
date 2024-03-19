/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session06_DataStructure;

import java.util.*;

/**
 *
 * @author USER
 */
public class Ex04_Dictionary {
    public static void main(String[] args) {
        Dictionary<String,String> dictionary = new Hashtable<>();
        Scanner sc = new Scanner(System.in);
        dictionary.put("Hello","Bon jour");
        dictionary.put("one", "un");
        dictionary.put("two","deux");
        dictionary.put("three", "trois");
        
        System.out.println("Translate English into French: ");
        Enumeration<String> enums = dictionary.keys();
        
        while(enums.hasMoreElements()){
            var key = enums.nextElement();
            String s = String.format("%s\t-\t%s",key,dictionary.get(key));
            System.out.println(s);
        }
        
//        Translate
    Hashtable translate = new Hashtable();
    translate.putAll((Map)dictionary);
    String word;
        do {            
            System.out.println("Enter word: ");
            word = sc.nextLine();
            if(translate.containsKey(word)){
                String s = String.format("%s\t: %s", word,dictionary.get(word));
                System.out.println(s);
            }
            else{
                System.out.println("Word not found");
            }
        } while (!word.equals("end"));
    
    }
}
