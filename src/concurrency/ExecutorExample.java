package concurrency;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ===================== EXECUTOR FRAMEWORK EXAMPLE =====================
 * ------------------------------------------------------------------------------------
 * 💡 WHAT IS AN EXECUTOR?
 * ------------------------------------------------------------------------------------
 * - The `Executor` framework (introduced in Java 5) provides a **higher-level replacement**
 *   for manually creating and managing threads (`new Thread()`).
 * - It decouples **task submission** from **thread management**.
 * - Allows you to focus on *what* to run, not *how* it runs.
 *
 * ------------------------------------------------------------------------------------
 * KEY COMPONENTS:
 * ------------------------------------------------------------------------------------
 * 1. **Executor (Interface):**
 *      - Simplest interface with a single method `execute(Runnable command)`.
 *      - Example: `executor.execute(() -> System.out.println("Task running"));`
 *
 * 2. **ExecutorService (Sub-interface of Executor):**
 *      - Adds lifecycle management (shutdown, awaitTermination, etc.)
 *      - Supports task submission that returns `Future` for async results.
 *      - Implementations: `ThreadPoolExecutor`, `ScheduledThreadPoolExecutor`
 *
 * 3. **Executors (Utility Class):**
 *      - Factory methods to create different types of thread pools:
 *          `newFixedThreadPool(int nThreads)` – Fixed number of threads.
 *          `newCachedThreadPool()` – Creates threads as needed, reuses idle ones.
 *          `newSingleThreadExecutor()` – Single worker thread.
 *          `newScheduledThreadPool()` – For delayed or periodic tasks.
 *
 * ------------------------------------------------------------------------------------
 *  EXAMPLE DESCRIPTION:
 * ------------------------------------------------------------------------------------
 * - Creates a fixed thread pool of 3 threads.
 * - Submits 10 simple tasks simulating user requests.
 * - Each task runs concurrently, demonstrating reuse of worker threads.
 * - ExecutorService gracefully shuts down after all tasks complete.
 * ------------------------------------------------------------------------------------
 *  REAL-WORLD USE CASES:
 * ------------------------------------------------------------------------------------
 * Handling web service requests concurrently.
 * Batch or scheduled background processing.
 * Parallel data processing or file uploads.
 * Task orchestration systems.
 * ------------------------------------------------------------------------------------
 * BEST PRACTICES:
 * ------------------------------------------------------------------------------------
 * - Always call `shutdown()` after submitting tasks.
 * - Use `awaitTermination()` to block until completion.
 * - For periodic jobs, prefer `ScheduledExecutorService`.
 * - Avoid creating too many threads — prefer bounded thread pools.
 * ====================================================================================
 */

public class ExecutorExample {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " => Counting - " + i);
            }
        };

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(task);

        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executor;
        poolExecutor.shutdown();
    }
}
