package java8features.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOperations {
    public static void main(String[] args) {

        // 1. filter
        List<String> names = Arrays.asList("Debasish", "Ashutosh", "Akshaya", "Amit", "Divya", "Parasuram", "Amit");
        List<String> filteredList = names.stream().filter(x -> x.startsWith("A")).toList();
        System.out.println(filteredList);

        // 2. map
        List<String> list = names.stream().map(String::toUpperCase).toList();
        System.out.println(list);

        // 3. sorted
        List<Integer> numbers = Arrays.asList(2, 1, 3, 6, 4, 5 ,9, 2, 3);
        // Descending order
        List<Integer> list1 = numbers.stream().sorted((a, b) -> b - a).toList();
        System.out.println(list1);
        // Ascending order
        List<Integer> list2 = numbers.stream().sorted().toList();
        System.out.println(list2);

        // 4. distinct
        List<String> distinct = names.stream().distinct().toList();
        System.out.println(distinct);

        // 5.limit
        List<Integer> list3 = numbers.stream().limit(2).toList();
        System.out.println(list3);

        // 6. skip
        List<String> list4 = names.stream().skip(2).toList();
        System.out.println(list4);

        // 7. peek
        long count = Stream.iterate(1, x -> x + 1).skip(10).limit(100).peek(System.out::println).count();

        // 8. flatMap
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("banana", "mango"),
                Arrays.asList("mango", "kiwi"),
                Arrays.asList("kiwi", "orange")
        );

        List<String> list5 = listOfLists.stream()
                .flatMap(Collection::stream)
                .map(String::toUpperCase)
                .distinct()
                .toList();
        System.out.println(list5);

        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are powerful",
                "flatMap is useful"
        );

        List<String> list6 = sentences.stream()
                .flatMap(x -> Arrays.stream(x.split(" ")))
                .map(String::toUpperCase)
                .toList();
        System.out.println(list6);


    }
}
