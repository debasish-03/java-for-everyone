package java8features.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperators {

    public static void main(String[] args) {
        List<Integer> list = Stream.iterate(1, x -> x + 1)
                .limit(100)
                .toList();

        // 1. collect
        List<Integer> collect = list.stream().skip(99).collect(Collectors.toList());
        List<Integer> list1 = list.stream().skip(90).toList();
        System.out.println(collect);
        System.out.println(list1);

        // 2. forEach
        list.stream().skip(90).forEach(x -> {
            int square = x * x;
            System.out.println("Square of " + x +": " + square);
        });

        // 3. reduce
        Optional<Integer> reduce = list.stream().reduce(Integer::sum);
        System.out.println(reduce.get());

        // or
        int reduce1 = list.stream().reduce(Integer::sum).orElse(0);
        System.out.println(reduce1);

        // or
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);

        // or
        int value = list.stream().reduce(0, Integer::sum);
        System.out.println(value);

        // 4. count
        System.out.println(list.stream().filter(x -> x % 2 == 0).count());

        // 5. anyMatch, allMatch, noneMatch
        System.out.println(list.stream().anyMatch(x -> x%2 == 0));
        System.out.println(list.stream().allMatch(x -> x%2 == 0));
        System.out.println(list.stream().noneMatch(x -> x%2 == 0));

        // 6. findFirst, findAny
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());

        // 7. toArray()
        Object[] array = Stream.of(1, 2, 3).toArray();

        // 8. min / max
        System.out.println("max: " + Stream.of(2, 44, 69).max((o1, o2) -> o1 -o2).get());
        System.out.println("min: " + Stream.of(2, 44, 69).min(Comparator.naturalOrder()).get());

        // Example: Filtering and Collecting Names
        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");
        System.out.println(names.stream().filter(x -> x.length() > 3).toList());

        // Example: Squaring and Sorting Numbers
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 6);
        System.out.println(numbers.stream().map(x -> x * x).sorted().toList());

        // Example: Summing Values
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(integers.stream().reduce(Integer::sum).get());

        // Example:  Counting Occurrences of a Character
        String sentence = "Hello world";
        System.out.println(sentence.chars().filter(x -> x == 'l').count());

        // Example
        // Streams cannot be reused after a terminal operation has been called
        Stream<String> stream = names.stream();
        stream.forEach(System.out::println);
        // List<String> list1 = stream.map(String::toUpperCase).toList(); // exception

    }
}
