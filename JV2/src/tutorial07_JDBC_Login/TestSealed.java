package tutorial07_JDBC_Login;

sealed class Animal permits Dog{
    String name;
}

final class Dog extends Animal{
    public Dog(String name){
        this.name = name;
    }
    public void bark(){
        System.out.println(name + "'s dog bark Gau Gau");
    }
}
public class TestSealed {
    public static void main(String[] args) {
        Dog dog = new Dog("Tu");
        dog.bark();
    }
}
/*
Give a program (not snippet) for sealed class demotration. Description:

    A sealed class with one permitted Subclass and the class has one attribute.
    A Subclass has a method and main method. The subclass has to use Superclass attribute.

* */
