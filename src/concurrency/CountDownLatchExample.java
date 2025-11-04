package concurrency;

import java.util.concurrent.CountDownLatch;



/**
 * ------------------------------------------------------------------------------------
 * WHAT IS COUNTDOWNLATCH?
 *
 * ------------------------------------------------------------------------------------
 * - It is initialized with a **count** (e.g., number of tasks or threads).
 * - Each time a thread finishes a task, it calls `countDown()`.
 * - Threads waiting on the latch call `await()` — they are **blocked**
 *   until the count reaches zero.
 *
 * ------------------------------------------------------------------------------------
 *  KEY CONCEPTS:
 * ------------------------------------------------------------------------------------
 * Thread Synchronization:
 *     - `CountDownLatch` is often used to ensure that a main thread
 *       waits for other worker threads to complete before proceeding.
 *
 * One-time use:
 *     - Once the count reaches zero, the latch cannot be reset.
 *     - For reusable synchronization, use `CyclicBarrier` or `Phaser`.
 */
public class CountDownLatchExample {
    public static void main(String[] args) {
        int numberOfWorker = 3;
        CountDownLatch latch = new CountDownLatch(numberOfWorker);

        // Start worker thread
        for (int i = 1; i <= numberOfWorker; i++) {
            new Thread(new Worker(latch), "Worker-" + i).start();
        }

        System.out.println("Main thread waiting for workers to finish...");

        // Wait until all workers finish
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All workers finished — Main thread proceeding.");
    }

    static class Worker implements Runnable {
        private final CountDownLatch latch;

        Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " started");
                // Simulate work
                Thread.sleep((long) (Math.random() * 2000) + 1000);
                System.out.println(Thread.currentThread().getName() + " finished work");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        }
    }
}


