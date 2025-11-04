package jvminternals;

public class MemoryExample {
    public static void main(String[] args) {
        for (int i = 0; i < 10_000; i++) {
            String obj = new String("Data" + i);
        }
    }
}
