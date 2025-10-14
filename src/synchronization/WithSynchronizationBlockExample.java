package synchronization;

class SynchronizedPrinter {
    public void printTable(int n) {
        // Only one thread can enter this block at a time for this printer object
        synchronized (this) {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + (n * i));
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

public class WithSynchronizationBlockExample {
    public static void main(String[] args) {
        SynchronizedPrinter printer = new SynchronizedPrinter(); // shared resource

        Thread t1 = new Thread(() -> printer.printTable(5), "Thread-A");
        Thread t2 = new Thread(() -> printer.printTable(100), "Thread-B");

        t1.start();
        t2.start();
    }
}
