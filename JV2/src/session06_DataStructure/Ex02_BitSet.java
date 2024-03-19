/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session06_DataStructure;

import java.util.BitSet;

/**
 *
 * @author USER
 */
public class Ex02_BitSet {
    public static void main(String[] args) {
        BitSet even = new BitSet();
        BitSet odd = new BitSet();
        
        for(int i = 1 ; i <= 10; i++){
            if(i%2 == 0){
                even.set(i);
            }
            else{
                odd.set(i);
            }
        }
        System.out.println("Even numbers: " + even);
        System.out.println("Odd numbers: " + odd);
    }
}
