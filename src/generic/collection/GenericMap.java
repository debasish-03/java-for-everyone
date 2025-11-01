package generic.collection;

import java.util.HashMap;
import java.util.Map;

public class GenericMap {
    public static void main(String[] args) {

        Map<Integer,Integer> integerMap
                = new HashMap<Integer,Integer>();

        integerMap.put(1, 10);
        integerMap.put(2, 11);

        Map<String,String> stringMap = new HashMap<String,String>();

        stringMap.put("1", "Hello World");
        stringMap.put("2","Hi World");


        System.out.printf("Integer Value :%d\n", integerMap.get(1));
        System.out.printf("String Value :%s\n", stringMap.get("1"));


        for (Integer integer : integerMap.keySet()) {
            System.out.printf("Integer Value :%d\n", integer);
        }
        for (String s : stringMap.values()) {
            System.out.printf("String Value :%s\n", s);
        }
    }
}
