package java8features.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {

        List<Integer> numList = List.of(1, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        long evenCount = numList.stream()
                .filter(x -> x % 2 == 0)
                .count();

        long oddCount = numList.stream()
                .filter(x -> x % 2 != 0)
                .count();

        System.out.println("Even number count: " + evenCount);
        System.out.println("Odd number count: " + oddCount);


        // Creating stream
        // 1. From Collections
        List<Integer> list = Arrays.asList(1, 2, 3);
        Stream<Integer> stream = list.stream();

        // 2. From Arrays
        String[] str = {"Hello", "Hi", "WhatsApp"};
        Stream<String> stream1 = Arrays.stream(str);

        // 3. Using Stream.of
        Stream<Integer> stream2 = Stream.of(1, 2, 3);

        // 4. Infinite streams
        Stream<Integer> generate = Stream.generate(() -> 1);
        Stream<Integer> iterate = Stream.iterate(1, x -> x + 1);

    }
}
