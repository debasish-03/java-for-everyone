package multithreading;

public class CPUInfo {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors (logical cores): " + cores);
    }
}
