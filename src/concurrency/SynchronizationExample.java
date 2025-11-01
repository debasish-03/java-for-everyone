package concurrency;

public class SynchronizationExample {
    public static void main(String[] args) {
        MyPrinter printer = new MyPrinter();

        MyThread t1 = new MyThread("Thread - 1", printer);
        MyThread t2 = new MyThread("Thread - 2", printer);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            t1.interrupt();
            t2.interrupt();
        }

    }
}

class MyPrinter {
    public void printCount() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + "Counter - " + i);
        }
    }
}

class MyThread extends Thread {
    private Thread thread;
    private final String threadName;
    private final MyPrinter printer;

    MyThread(String threadName, MyPrinter printer) {
        this.threadName = threadName;
        this.printer = printer;
    }

    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " starting.");
        synchronized (printer) {
            printer.printCount();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " stopping.");
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

}
