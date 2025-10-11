package java8features;

import java.util.function.BiFunction;

public class MethodReference {

    public static void main(String[] args) {
        Say s = MethodReference::say;
        s.say();

        BiFunction<Integer, Integer, Integer> adder1 = Arithmatic::add;
        BiFunction<Integer, Float, Float> adder2 = Arithmatic::add;
        BiFunction<Float, Float, Float> adder3 = Arithmatic::add;

        System.out.println(adder1.apply(10, 20));
        System.out.println(adder2.apply(10, 20.0f));
        System.out.println(adder3.apply(10.0f, 20.0f));
    }

    public static void say() {
        System.out.println("Hello, I am Dev!");
    }
}

@FunctionalInterface
interface Say {
    void say();
}


class Arithmatic {
    public static int add(int a, int b) {
        return a + b;
    }

    public static float add(int a, float b) {
        return a + b;
    }

    public static float add(float a, float b) {
        return a + b;
    }
}