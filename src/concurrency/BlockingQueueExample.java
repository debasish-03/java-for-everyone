package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) {
        // Create a BlockingQueue with capacity = 5
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

        // Producer task
        Runnable producerTask = () -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.put(i); // waits if queue is full
                    System.out.println(Thread.currentThread().getName() + " produced: " + i);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumerTask = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Integer value = queue.take();
                    System.out.println(Thread.currentThread().getName() + " consumed: " + value);
                    Thread.sleep(600);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };


        // Start multiple producers and consumers
        Thread producer1 = new Thread(producerTask, "Producer-1");
        Thread producer2 = new Thread(producerTask, "Producer-2");
        Thread consumer1 = new Thread(consumerTask, "Consumer-1");
        Thread consumer2 = new Thread(consumerTask, "Consumer-2");

        producer1.start();
        consumer1.start();

//        producer2.start();
//        consumer2.start();
    }
}
