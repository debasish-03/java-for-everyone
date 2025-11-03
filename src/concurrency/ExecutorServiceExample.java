package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {
    public static void main(String[] args) {

        // Create a fixed thread pool with 3 worker threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit 10 concurrent tasks to the executor
        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            executorService.execute(() -> {
                System.out.println("Task: " + taskId + " started by thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task: " + taskId + " completed by thread: " + Thread.currentThread().getName());
            });
        }

        // No more new tasks will be accepted
        executorService.shutdown();

        try {
            // Wait for all tasks to finish before proceeding
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdown();  // force shutdown if tasks are stuck
            }
        } catch (InterruptedException e) {
            executorService.shutdown();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks completed. Executor shutting down.");
    }
}
