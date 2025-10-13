package multithreading;

public class ThreadExample {
    public static void main(String[] args) {
        usingThreadClass();
        usingRunnableInterface();

    }
    private static void usingRunnableInterface() {
        Thread t1 = new Thread(new MyRunnable("thread-1"));
        Thread t2 = new Thread(new MyRunnable("thread-2"));
        Thread t3 = new Thread(new MyRunnable("thread-3"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("All thread have finished.");
    }

    private static void usingThreadClass() {
        MyThread t1 = new MyThread("Thread-1");
        MyThread t2 = new MyThread("Thread-2");
        MyThread t3 = new MyThread("Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("All thread have finished.");
    }
}

class MyRunnable implements Runnable {
    private final String name;
    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(name + " - Count: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name + " finished execution.");
    }
}

class MyThread extends Thread {
    public MyThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(getName() + " - Count: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(getName() + " finished execution.");
    }
}
