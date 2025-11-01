package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockWithTimeoutExample {
    private static final Lock lock = new ReentrantLock();

    public static void task() {
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " acquired lock.");
                    Thread.sleep(3000);
                } finally {
                    System.out.println(Thread.currentThread().getName() + " Released lock");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire lock (timeout).");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(TryLockWithTimeoutExample::task, "Thread-1");
        Thread t2 = new Thread(TryLockWithTimeoutExample::task, "Thread-2");
        Thread t3 = new Thread(TryLockWithTimeoutExample::task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
