package concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {

    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    public static void main(String[] args) {
        Runnable task = () -> {
            // only one thread should initialize
            if (initialized.compareAndSet(false, true)) {
                System.out.println(Thread.currentThread().getName() + " initialization starting.");
                try {
                    // write your system initialization logic
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " initialization completed.");
            } else {
                System.out.println(Thread.currentThread().getName() + " detected initialization already done.");
            }
        };

        // Create multiple worker threads attempting the same initialization
        Thread t1 = new Thread(task, "Worker-1");
        Thread t2 = new Thread(task, "Worker-2");
        Thread t3 = new Thread(task, "Worker-3");
        Thread t4 = new Thread(task, "Worker-4");

        // Start all threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
