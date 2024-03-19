package session03_Thread;

public class Ex01_Thread extends Thread {

    @Override
    public void run(){
        var id = Thread.currentThread().getId();
        var name = Thread.currentThread().getName();

        System.out.printf("Thread with name: %s, id: %d", name, id);
    }

    public static void main(String[] args) {
        new Ex01_Thread().start();
    }
}
