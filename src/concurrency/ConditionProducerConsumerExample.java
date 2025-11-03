package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * ===================== PRODUCER-CONSUMER USING LOCK & CONDITION =====================
 *
 * This example demonstrates the classic Producer-Consumer problem using Java Concurrency
 * utilities — specifically `ReentrantLock` and `Condition`. It showcases how multiple threads
 * can coordinate safely when accessing a shared resource.
 *
 * ------------------------------------------------------------------------------------
 *  KEY CONCEPTS DEMONSTRATED:
 * ------------------------------------------------------------------------------------
 * 1. **ReentrantLock**:
 *      - Provides explicit locking, more flexible than `synchronized`.
 *      - Must always be paired with `unlock()` in a `finally` block.
 *
 * 2. **Condition Variables**:
 *      - Allow fine-grained thread communication, similar to `wait()` / `notify()`.
 *      - Created using `lock.newCondition()`.
 *      - `await()` releases the lock and suspends the thread until signaled.
 *      - `signal()` or `signalAll()` wakes up waiting threads.
 *
 * 3. **Producer-Consumer Coordination**:
 *      - The `SharedBufferData` acts as a bounded queue (with capacity).
 *      - Producers wait when the buffer is full.
 *      - Consumers wait when the buffer is empty.
 *      - Each action is guarded by the same lock.
 *
 * 4. **Thread Safety**:
 *      - Access to the shared queue is always protected by `lock.lock()` / `lock.unlock()`.
 *      - Conditions ensure no missed notifications between threads.
 *
 * ------------------------------------------------------------------------------------
 *  WORKING MECHANISM:
 * ------------------------------------------------------------------------------------
 * → Producer threads call `produce()`:
 *      - Acquire lock
 *      - If the queue is full, call `full.await()` to wait
 *      - Otherwise, add an item and signal consumers via `empty.signal()`
 *
 * → Consumer threads call `consume()`:
 *      - Acquire lock
 *      - If the queue is empty, call `empty.await()` to wait
 *      - Otherwise, remove an item and signal producers via `full.signal()`
 *
 * ------------------------------------------------------------------------------------
 *  WHY THIS APPROACH IS BETTER THAN SYNCHRONIZED:
 * ------------------------------------------------------------------------------------
 * - Allows multiple conditions (unlike `wait/notify`, which uses a single monitor condition).
 * - Better performance under heavy concurrency.
 * - Easier to reason about and debug using explicit locks.
 *
 * ------------------------------------------------------------------------------------
 *  EXECUTION FLOW (example output):
 * ------------------------------------------------------------------------------------
 * Producer-1 produced: 1
 * Consumer-2 consumed: 1
 * Producer-2 produced: 2
 * Consumer-1 waiting — buffer empty
 * ...
 *
 * ------------------------------------------------------------------------------------
 *  SUMMARY:
 * ------------------------------------------------------------------------------------
 * - Demonstrates clean, modern Java concurrency best practices.
 * - Suitable for backend systems, schedulers, or data pipelines where producers and consumers
 *   operate at different speeds.
 * - Easily extendable to add metrics, logging, or custom queue logic.
 *
 * ====================================================================================
 */
public class ConditionProducerConsumerExample {
    public static void main(String[] args) {
        SharedBufferData<Integer> buffer = new SharedBufferData<>(3);

        Runnable producer = () -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    buffer.produce(i);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumer = () -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    buffer.consume();
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        };


        Thread producer1 = new Thread(producer, "Producer-1");
        Thread producer2 = new Thread(producer, "Producer-2");
        Thread consumer1 = new Thread(consumer, "Consumer-1");
        Thread consumer2 = new Thread(consumer, "Consumer-2");

        producer1.start();
        consumer1.start();

//        producer2.start();
//        consumer2.start();
    }
}

class SharedBufferData<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();

    public SharedBufferData(int capacity) {
        this.capacity = capacity;
    }

    public void produce(T item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + " waiting - buffer full");
                full.await(); // wait until space is available
            }
            queue.offer(item);
            System.out.println(Thread.currentThread().getName() + " produced: " + item);
            empty.signal(); // wake up an empty consumer
        } finally {
            lock.unlock();
        }
    }

    public T consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " waiting - buffer empty");
                empty.await(); // wait until item is available
            }

            T item = queue.poll();
            System.out.println(Thread.currentThread().getName() + " consumed: " + item);
            full.signal(); // wake up a waiting producer
            return item;
        } finally {
            lock.unlock();
        }
    }

}
