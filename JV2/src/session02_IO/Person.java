package session02_IO;

import java.io.Serializable;

class Person implements Serializable {
    String name, code;

    public Person(String name, String code){
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return code + " _ " + name;
    }
}