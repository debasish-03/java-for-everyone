package concurrency;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {

        // Create a scheduled thread pool with 3 threads
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        // One-time task — executes once after 2 seconds
        scheduler.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + " → One-time task executed after delay at " + LocalTime.now());
        }, 2, TimeUnit.SECONDS);


        // Fixed-rate task — runs every 3 seconds (measured from start of last execution)
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " → Fixed-rate task executed at " + LocalTime.now());
        }, 1, 3, TimeUnit.SECONDS);

        // Fixed-delay task — runs every 4 seconds (measured from end of last execution)
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + " → Fixed-delay task executed at " + LocalTime.now());
            try {
                Thread.sleep(1000); // simulate variable processing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 1, 4, TimeUnit.SECONDS);

        // Let the scheduler run tasks for 15 seconds, then shut it down gracefully
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Shutting down scheduler gracefully.");
        scheduler.shutdown();
    }
}
