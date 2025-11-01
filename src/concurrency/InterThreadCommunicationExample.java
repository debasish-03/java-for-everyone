package concurrency;



/*
*
* Inter-thread communication in Java means two or more threads sharing data and
* coordinating actions with each other — usually via shared objects.
* Java provides built-in mechanisms for this coordination using: wait(), notify(), notifyAll()
* These methods are defined in java.lang.Object, not Thread, because they act on the shared object’s monitor lock.
*
*
* Key Rules:
*   - These methods must be called inside a synchronized block.
*   - The thread must own the object’s monitor lock (i.e., it entered a synchronized block on that object).
*   - wait() releases the lock temporarily and pauses the thread.
*   - notify() wakes up one waiting thread.
*   - notifyAll() wakes up all waiting threads on the same monitor.
*
* */
public class InterThreadCommunicationExample {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                buffer.produce(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                buffer.consume();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}

class SharedBuffer {
    private int data;
    private boolean hasData = false;

    // Producer put data in the buffer
    public synchronized void produce(int data) {
        try {
            // If buffer already has data wait until consumer consumes it
            while (hasData) {
                wait(); // releases lock and wait
            }

            // produce new data
            this.data = data;
            System.out.println("Produced data=" + data);
            hasData = true;

            // notify consumer that data is available
            notify();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Consumer consumes data from buffer
    public synchronized void consume() {
        try {
            //If no data, wait for producer to produce data
            while (!hasData) {
                wait();
            }

            // Consume data
            System.out.println("Consume data="+ this.data);
            hasData = false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
