package session03_Thread;

class Me extends Thread{
    public void run(){
        for(int i = 0; i< 5; i++){
            String s = String.format("Me [ID: %d]", currentThread().getId());
            System.out.println(s);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class You extends Thread{
    public void run(){
        for(int i = 0; i< 5; i++){
            String s = String.format("You [ID: %d]", currentThread().getId());
            System.out.println(s);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class Ex03_MultiThreading {
    public static void main(String[] args) {
        var me = new Me();
        var you = new You();

        me.start();
        you.start();
    }
}
