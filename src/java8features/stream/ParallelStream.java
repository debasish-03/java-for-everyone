package java8features.stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ParallelStream {

    public static void main(String[] args) {
        // List Initialization
        List<Integer> numbers = Stream.iterate(1, x -> x + 1).limit(20000).toList();

        // Sequential Stream performance
        long start = System.currentTimeMillis();
        List<Long> longStream = numbers.stream().map(ParallelStream::factorial).toList();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Sequential stream performance: "+ elapsed + " ms");

        start = System.currentTimeMillis();
        List<Long> longStream1 = numbers.parallelStream().map(ParallelStream::factorial).toList();
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Sequential stream performance: "+ elapsed + " ms");


        // Cumulative Sum
        // [1, 2, 3, 4, 5] --> [1, 3, 6, 10, 15]
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        AtomicInteger sum =  new AtomicInteger(0);
        List<Integer> cumulativeSum = numbers1.stream().map(sum::addAndGet).toList();
        System.out.println("Expected cumulative sum: [1, 3, 6, 10, 15]");
        System.out.println("Actual result with parallel stream: " + cumulativeSum);

    }
    private static long factorial(int n) {
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
