package generic.wildcard;

import java.util.Arrays;
import java.util.List;

public class UnBoundWildCard {


    public static void main(String args[]) {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        printAll(integerList);
        List<Double> doubleList = Arrays.asList(1.2, 2.3, 3.5);
        printAll(doubleList);
        List<String> stringList = Arrays.asList("Hello", "Hi");
        printAll(stringList);

    }

    public static void printAll(List<?> list) {
        for (Object item : list)
            System.out.println(item + " ");
    }
}
