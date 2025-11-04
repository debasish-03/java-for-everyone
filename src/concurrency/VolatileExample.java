package concurrency;

public class VolatileExample {

    // Shared flag variable visible to all threads
    private static volatile boolean running = true;

    public static void main(String[] args) {

        // Worker thread
        Thread worker = new Thread(() -> {
            while (running) {
                System.out.println("Worker running...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Worker stopped...");
        });

        worker.start();

        // Main thread sleeps for 2 seconds, then stops the worker
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread requested stop...");
        running = false; // Volatile ensures visibility to worker thread
    }
}
