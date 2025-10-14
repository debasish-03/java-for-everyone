package synchronization;

class Printer {
    public void printTable(int n) {
        // No synchronization here!
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

public class WithoutSynchronizationBlockExample {
    public static void main(String[] args) {
        Printer printer = new Printer(); // shared resource

        Thread t1 = new Thread(() -> printer.printTable(5), "Thread-A");
        Thread t2 = new Thread(() -> printer.printTable(100), "Thread-B");

        t1.start();
        t2.start();
    }
}
