package concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
* Multiple Thread can read simultaneously
* One thread can write at a time
*
* */
public class ReadWriteLockExample {

    public static void main(String[] args) {
        ConfigCache cache = new ConfigCache();
        cache.updateConfig("app.name", "ShoppingApp");
        cache.updateConfig("timeout", "500");

        Runnable reader1 = () -> {
            for (int i = 0; i <= 100; i++) {
                cache.getConfig("app.name");
            }
        };
        Runnable reader2 = () -> {
            for (int i = 0; i <= 10; i++) {
                cache.getConfig("timeout");
            }
        };


        Runnable writer1 = () -> {
            cache.updateConfig("app.name", "EKartApp");
        };
        Runnable writer2 = () -> {
            cache.updateConfig("timeout", String.valueOf(new Random().nextInt(1000)));
        };

        // Start 2 reader threads and 2 writer
        Thread r1 = new Thread(reader1, "Reader-1");
        Thread r2 = new Thread(reader2, "Reader-2");
        Thread w1 = new Thread(writer1, "Writer-1");
        Thread w2 = new Thread(writer2, "Writer-2");

        r1.start();
        r2.start();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        w1.start();
        w2.start();
    }

}

class ConfigCache {
    private final Map<String, String> configData = new HashMap<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    // Read operation multiple thread can access
    public String getConfig(String key) {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading key: " + key + " ,Value: " + configData.get(key));
            Thread.sleep(300); // simulate latency
            return configData.get(key);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            readLock.unlock();
        }
    }

    // Write operation (only one thread at a time)
    public void updateConfig(String key, String value) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " updating " + key + " = " + value);
            Thread.sleep(500);
            configData.put(key, value);
            System.out.println(Thread.currentThread().getName() + " updated " + key);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }
}
