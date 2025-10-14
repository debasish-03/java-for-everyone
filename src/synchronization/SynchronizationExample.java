package synchronization;

public class SynchronizationExample {
    public static void main(String[] args) {

        // without synchronization
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                Counter.increment();
            }

        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                Counter.increment();
            }

        });

        t1.start();
        t2.start();

        // synchronization
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                SynchronizedCounter.increment();
            }

        });

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                SynchronizedCounter.increment();
            }

        });

        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Without synchronization: "+ Counter.getCount());
        System.out.println("With synchronization: "+ SynchronizedCounter.getCount());
    }
}

class Counter {
    private static int count = 0;
    public static void increment() {
        count++;
    }
    public static int getCount() {
        return count;
    }
}

class SynchronizedCounter {
    private static int count = 0;
    public static synchronized void increment() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}

