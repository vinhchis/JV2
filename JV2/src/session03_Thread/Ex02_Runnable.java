package session03_Thread;

public class Ex02_Runnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Implements Runnable");
    }

    public static void main(String[] args) {
        var ex02 = new Ex02_Runnable();

        Thread thread = new Thread(ex02, "Ex02_Runnable");
        System.out.println("Thread Name: " + thread.getName() + " _" + "Thread id: " + thread.getId());
        thread.start();
    }
}
