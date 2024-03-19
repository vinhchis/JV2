/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session02_FXML;

/**
 *
 * @author levin
 */
public class Item {
    String code, name;
    int price;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    
    
    
    public Item(String code, String name,int price){
        this.code = code;
        this.name = name;
        this.price = price;
    }
    
    
}
