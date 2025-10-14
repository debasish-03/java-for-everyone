package synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AvoidDeadlockExample {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            boolean lock1Acquired = false;
            boolean lock2Acquired = false;
            try {
                lock1Acquired = lock1.tryLock();
                lock2Acquired = lock2.tryLock();

                if (lock1Acquired && lock2Acquired) {
                    System.out.println("Thread-1 acquired both locks");
                } else {
                    System.out.println("Thread-1 could not acquire both locks");
                }
            } finally {
                if (lock1Acquired) lock1.unlock();
                if (lock2Acquired) lock2.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            boolean lock1Acquired = false;
            boolean lock2Acquired = false;
            try {
                lock2Acquired = lock2.tryLock();
                lock1Acquired = lock1.tryLock();

                if (lock1Acquired && lock2Acquired) {
                    System.out.println("Thread-2 acquired both locks");
                } else {
                    System.out.println("Thread-2 could not acquire both locks");
                }
            } finally {
                if (lock1Acquired) lock1.unlock();
                if (lock2Acquired) lock2.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
