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
public class Ex03_Stack {
    public static void main(String[] args) {
        Stack even = new Stack();
        Stack odd = new Stack();
        
        for(int i = 1 ; i <= 10; i++){
            if(i%2 == 0){
                even.push(i);
            }
            else{
                odd.push(i);
            }
        }
        System.out.println("Even numbers: " + even);
        System.out.println("Odd numbers: " + odd);
    }
}
