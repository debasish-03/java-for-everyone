package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) {

        // custom thread factory to name threads clearly
        ThreadFactory customThreadFactory = new ThreadFactory() {
            private final AtomicInteger count = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("My-thread-" + count.incrementAndGet());
                return t;
            }
        };

        // Create ThreadPoolExecutor with custom parameters
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                                   // core pool size
                4,                                               // max pool size
                10,                                              // keep-alive time
                TimeUnit.SECONDS,                                // time-unit
                new ArrayBlockingQueue<>(5),             // bounded queue
                customThreadFactory,                             // custom thread names
                new ThreadPoolExecutor.CallerRunsPolicy()        // rejection policy
        );

        // submit 10 tasks to simulate load
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " started by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " finished by " + Thread.currentThread().getName());
            });
        }

        // Gracefully shutdown executor
        executor.shutdown();
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks completed. Executor terminated.");
    }
}
