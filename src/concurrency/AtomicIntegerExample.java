package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ===================== ATOMICINTEGER EXAMPLE =====================
 *  WHAT IS AtomicInteger?
 * ------------------------------------------------------------------------------------
 * - `AtomicInteger` is part of the `java.util.concurrent.atomic` package.
 * - It provides **lock-free, thread-safe** operations on integers.
 * - It uses low-level **Compare-And-Swap (CAS)** instructions at the CPU level,
 *   allowing multiple threads to update a variable concurrently without contention.
 * ------------------------------------------------------------------------------------
 *  USE CASE:
 * ------------------------------------------------------------------------------------
 * - Multiple threads increment a shared counter concurrently.
 * - We want to ensure that no race conditions occur.
 * - Using a normal `int` or even `Integer` would cause race conditions
 *   unless explicitly synchronized.
 * ------------------------------------------------------------------------------------
 * METHODS IN AtomicInteger (most commonly used):
 * ------------------------------------------------------------------------------------
 * - `get()`: Returns the current value.
 * - `set(int newValue)`: Sets to a new value.
 * - `incrementAndGet()`: Atomically increments by one and returns the updated value.
 * - `getAndIncrement()`: Atomically increments by one and returns the previous value.
 * - `addAndGet(int delta)`: Atomically adds the given value and returns the updated result.
 * - `compareAndSet(int expect, int update)`: Atomically sets to `update`
 *   if the current value equals `expect`.
 */

public class AtomicIntegerExample {

    // Shared AtomicInteger counter (thread-safe)
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        // Create a task that increments the counter 250 times per thread
        Runnable task = () -> {
            for (int i = 0; i < 250; i++) {
                int newValue = counter.incrementAndGet(); // atomic operation
                System.out.println(Thread.currentThread().getName() + " incremented counter to " + newValue);

                try {
                    Thread.sleep(5); // simulate small delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Create multiple thread and incrementing the same counter
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");
        Thread t4 = new Thread(task, "Thread-4");
        Thread t5 = new Thread(task, "Thread-5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Print final value
        System.out.println("Final Counter value: " + counter.get());
    }
}
