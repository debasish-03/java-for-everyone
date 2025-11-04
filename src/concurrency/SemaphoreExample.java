package concurrency;

import java.util.concurrent.Semaphore;


/**
 * ------------------------------------------------------------------------------------
 *  WHAT IS A SEMAPHORE?
 * ------------------------------------------------------------------------------------
 * - A `Semaphore` maintains a set of permits.
 * - Threads acquire permits before accessing a resource.
 * - If no permits are available, the thread blocks until one is released.
 *
 * - Commonly used to:
 *      - Limit concurrent access (e.g., database connections, API calls, etc.)
 *      - Implement resource pools
 *      - Control rate of concurrent execution
 *
 * ------------------------------------------------------------------------------------
 * KEY CONCEPTS:
 * ------------------------------------------------------------------------------------
 * Permits:
 *     - Think of permits as "tokens" to access the resource.
 *     - `acquire()` → take a permit (waits if none available)
 *     - `release()` → give back a permit
 *
 *  Fairness:
 *     - You can create a Semaphore as "fair" (`new Semaphore(permits, true)`),
 *       which grants permits in the order they were requested (FIFO).
 *     - Default is non-fair (faster but not ordered).
 *
 * ------------------------------------------------------------------------------------
 * REAL-WORLD USE CASES:
 * ------------------------------------------------------------------------------------
 * - Limiting concurrent API calls
 * - Controlling access to a shared resource (DB connections, sockets, etc.)
 * - Implementing bounded thread pools
 * - Managing rate limits in distributed systems
 *
 * ====================================================================================
 */

public class SemaphoreExample {

    // Semaphore with 2 permits → only 2 threads can access resource at once
    private static final Semaphore printerSemaphore = new Semaphore(2, true);

    public static void main(String[] args) {
        // Create 5 user threads simulating print jobs
        for (int i = 1; i <= 5; i++) {
            new Thread(new PrinterTask(), "User-" + i).start();
        }
    }

    static class PrinterTask implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting for printer...");
                printerSemaphore.acquire(); // acquire permit before accessing resource

                System.out.println(Thread.currentThread().getName() + " is printing...");
                Thread.sleep((long) (Math.random() * 2000) + 1000);

                System.out.println(Thread.currentThread().getName() + " finished printing...");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                printerSemaphore.release(); // release permit after finishing
            }
        }
    }
}
