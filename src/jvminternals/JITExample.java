package jvminternals;

public class JITExample {
    public static void main(String[] args) {
        for (int i = 0; i < 10_000_000; i++) compute();
    }
    static int compute() { return 42 * 42; }
}
