package synchronization;

public class DeadlockExample {
    public static void main(String[] args) {
        final String resource1 = "Printer";
        final String resource2 = "Scanner";

        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread-1: Locked "+ resource1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

                System.out.println("Thread-1: Waiting to lock "+ resource2);
                synchronized (resource2) {
                    System.out.println("Thread-1: Locked "+ resource2);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread-2: Locked "+ resource2);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

                System.out.println("Thread-2: Waiting to lock "+ resource1);
                synchronized (resource1) {
                    System.out.println("Thread-2: Locked "+ resource1);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
