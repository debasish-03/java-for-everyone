package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {
    private static final int MAX_WORKER = 10;

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(MAX_WORKER);

        for (int i = 1; i <= MAX_WORKER; i++) {
            service.execute(new WorkerThread("Task-" + i));
        }

        service.shutdown();
        try {
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks completed successfully!");
    }
}

class WorkerThread implements Runnable {
    private final String message;

    public WorkerThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Start -> " + message);
        processMessage();
        System.out.println("[" + Thread.currentThread().getName() + "] End -> " + message);
    }

    private void processMessage() {
        try {
            for (int i = 0; i < 10_000_000; i++) {} // simulate CPU work
            Thread.sleep(1000); // simulate I/O
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
