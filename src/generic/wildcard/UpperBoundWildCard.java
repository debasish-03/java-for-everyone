package generic.wildcard;

import java.util.Arrays;
import java.util.List;

public class UpperBoundWildCard {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        System.out.println("sum = " + sum(integerList));

        List<Double> doubleList = Arrays.asList(1.2, 2.3, 3.5);
        System.out.println("sum = " + sum(doubleList));
    }

    /*
    * The question mark (?), represents the wildcard, stands for unknown type in generics.
    * There may be times when you'll want to restrict the kinds of types that are allowed to be passed to a type parameter.
    * For example, a method that operates on numbers might only want to accept instances of Number or its subclasses.
    *
    * To declare an upper bounded Wildcard parameter, list the ?, followed by the `extends` keyword, followed by its upper bound.
    * */

    public static double sum(List<? extends Number> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0.0;
        }
        return numbers.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }
}
