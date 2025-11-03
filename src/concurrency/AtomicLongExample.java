package concurrency;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongExample {

    private static final AtomicLong counter = new AtomicLong(0);

    public static void main(String[] args) {

        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                long newCount = counter.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " incremented counter to " + newCount);

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Print final value
        System.out.println("Final Counter value: " + counter.get());
    }
}
