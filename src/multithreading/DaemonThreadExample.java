package multithreading;

public class DaemonThreadExample {
    public static void main(String[] args) {
        DaemonThread t1 = new DaemonThread();
        DaemonThread t2 = new DaemonThread();
        DaemonThread t3 = new DaemonThread();

        t1.setName("daemon-thread-1");
        t1.setDaemon(true);

        t2.setName("thread-2");
        t3.setName("thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }
}


class DaemonThread extends Thread {

    public void run() {
        System.out.println("Current Thread: "+ Thread.currentThread().getName());
        if(Thread.currentThread().isDaemon()) {
            while (true) {
                // daemon thread will automatically die when all user thread completes its task
            }
        }

        for (int i = 0; i <= 5; i++) {
            System.out.println("Running `"+ Thread.currentThread().getName() +"` thread, Count: "+ i );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}