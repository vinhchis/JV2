package session05_DesignPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. Model
class Student{
    String code;
    String name;

    public Student(String code, String name){
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}

// 2. DAO Interface
interface DAO {
   List<Student> select();
   void insert(Student stu);
}

// 3. Implement DAO interface
class DAOObject implements DAO{
    List<Student> list;

    public DAOObject(){
        list = new ArrayList<>();
    }

    @Override
    public List<Student> select() {
        return this.list;
    }

    @Override
    public void insert(Student stu) {
        list.add(stu);
        System.out.println("Insert Successfully!!!");
    }
}

// 4. App
public class Ex01_DAO {
    DAOObject dao = new DAOObject();
    Scanner sc = new Scanner(System.in);

    Student input() {
        String code, name;
        System.out.println("Enter code: ");
        code = sc.nextLine();
        System.out.println("Enter name: ");
        name = sc.nextLine();
        return new Student(code, name);
    }

    void addNew(){
        Student stu = input();
        dao.insert(stu);
    }

    void display(){
        System.out.println("List Student:");
        for(var stu : dao.select()){
            System.out.println(stu);
        }
    }

    public static void main(String[] args) {
        var ex01 = new Ex01_DAO();

        for (int i = 0; i < 3; i++) {
            ex01.addNew();
        }

        ex01.display();
    }
}
/*
* DAO - Data Access Object
* */