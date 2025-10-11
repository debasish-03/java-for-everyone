package java8features;


import java.util.List;

public class LambdaExpressions {
    public static void main(String[] args) {

        // lambda expression for calculating sum
        Operation sum = Integer::sum;
        System.out.println(sum.calculate(10, 20));

        // lambda expression for calculating difference
        Operation difference = (a, b) -> a - b;
        System.out.println(difference.calculate(20, 10));

        // lambda with forEach
        List<Integer> numList = List.of(1, 2, 3, 4, 5, 6);
        numList.forEach(System.out::println);

        // lambda with multiple statement
        numList.forEach( n -> {
            String str = "Number: " + n;
            System.out.println(str);
        });
    }

}

@FunctionalInterface
interface Operation {
    int calculate(int a, int b);
}