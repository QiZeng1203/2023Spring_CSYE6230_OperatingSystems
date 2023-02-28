import java.lang.Thread;
public class Main {
    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors());

//  Method 1: extends Thread
//        Thread th1 = new MyThread();
//        Thread th2 = new MyThread();
//        Thread th3 = new MyThread();

//  Method 2: implement Runnable
        Runnable target = new MyRunnable();
        Thread th1 = new Thread(target);
        Thread th2 = new Thread(target);
        Thread th3 = new Thread(target);

        th1.start();
        th2.start();
        th3.start();

        for(int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

// Method 1: extends Thread
//class MyThread extends Thread {
//    @Override
//    public void run() {
//        for(int i=0; i < 5; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);
//        }
//    }
//}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for(int i=0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}