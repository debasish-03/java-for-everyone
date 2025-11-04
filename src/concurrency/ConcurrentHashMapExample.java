package concurrency;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

    public static void main(String[] args) {
        // Shared concurrent map (e.g., product inventory)
        ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();

        // Producer task (adds or updates items)
        Runnable producer = () -> {
            String[] items = {"Apple", "Banana", "Mango", "Orange"};
            for (String item: items) {
                int quantity = (int) (Math.random() * 100);
                inventory.put(item, quantity);
                System.out.println(Thread.currentThread().getName() + " updated " + item + " -> " + quantity);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Consumer task (reads items)
        Runnable consumer = () -> {
            for (int i = 0; i < 5; i++) {
                inventory.forEach((k, v) ->
                        System.out.println(Thread.currentThread().getName() + " read " + k + " -> " + v));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Start multiple producer and consumer threads
        Thread producer1 = new Thread(producer, "Producer-1");
        Thread producer2 = new Thread(producer, "Producer-2");
        Thread consumer1 = new Thread(consumer, "Reader-1");
        Thread consumer2 = new Thread(consumer, "Reader-2");

        producer1.start();
        consumer1.start();

//        producer2.start();
//        consumer2.start();

    }
}
