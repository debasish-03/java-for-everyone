package generic.collection;

import java.util.HashSet;
import java.util.Set;

public class GenericSet {
    public static void main(String[] args) {

        Set<Integer> integerSet = new HashSet<Integer>();

        integerSet.add(10);
        integerSet.add(11);

        Set<String> stringSet = new HashSet<String>();

        stringSet.add("Hello World");
        stringSet.add("Hi World");


        for(Integer data: integerSet) {
            System.out.printf("Integer Value :%d\n", data);
        }

        for (String s : stringSet) {
            System.out.printf("String Value :%s\n", s);
        }
    }
}
