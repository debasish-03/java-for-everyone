package multithreading;

public class CpuBoundTest {
    public static void main(String[] args) throws InterruptedException {
        int availableCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available logical cores: " + availableCores);

        int numThreads = availableCores * 2; // test with 2× the number of cores
        System.out.println("Starting " + numThreads + " CPU-bound threads...");

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new CpuTask(), "CPU-Thread-" + i);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long end = System.currentTimeMillis();
        System.out.println("All threads finished in: " + (end - start) + " ms");
    }
}

class CpuTask implements Runnable {
    @Override
    public void run() {
        long sum = 0;
        // heavy CPU loop
        for (int i = 0; i < 200_000_000; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}

