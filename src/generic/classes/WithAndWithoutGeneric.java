package generic.classes;

import java.util.ArrayList;
import java.util.List;

public class WithAndWithoutGeneric {
    public static void main(String[] args) {

        /*
        * Without Generics Example
        * Without generics, you need to manually typecast the value
        */
        List list = new ArrayList();

        list.add("hello");
        list.add(10);

        String firstValue = (String) list.get(0);
        int secondValue = (int) list.get(1);

        System.out.println("First Value: " + firstValue + " ,Second Value: " + secondValue);

        /*
        * With generics example
        * With generics you will get compile time type safety
        * No need to manually caste
        * */

        List<String> strs = new ArrayList<>();
        strs.add("hello");
        strs.add("Dev!");
        strs.forEach(System.out::println);
    }
}
