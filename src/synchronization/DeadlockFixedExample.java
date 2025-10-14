package synchronization;

public class DeadlockFixedExample {

    public static void main(String[] args) {
        /*
        *
        * This works because:
        * Both threads lock resources in the same sequence (Printer → Scanner).
        * There’s no circular waiting condition — one of the four conditions of a deadlock.
        * Hence, deadlock is avoided.
        *
        *
         */
        final String resource1 = "Printer";
        final String resource2 = "Scanner";

        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread-1: Locked " + resource1);

                try { Thread.sleep(100); } catch (InterruptedException e) { }

                synchronized (resource2) {
                    System.out.println("Thread-1: Locked " + resource2);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            // Fix: lock in same order as Thread-1
            synchronized (resource1) {
                System.out.println("Thread-2: Locked " + resource1);

                try { Thread.sleep(100); } catch (InterruptedException e) { }

                synchronized (resource2) {
                    System.out.println("Thread-2: Locked " + resource2);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
