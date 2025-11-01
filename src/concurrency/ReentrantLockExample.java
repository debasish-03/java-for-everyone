package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();

    private static void increment() {
        lock.lock();
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " => counter: " + counter);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 1; i <= 10; i++) {
                increment();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");
        Thread t4 = new Thread(task, "Thread-4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
