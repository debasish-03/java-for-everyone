package generic.classes;

import java.util.List;

public class ParameterizedGenericClass {

    public static void main(String[] args) {
        Pair<Integer, List<String>> p1 = new Pair<>();

        List<String> messages = List.of("Hi", "Hello", "Bye");

        p1.set(10, messages);

        System.out.println("First value: " + p1.getFirst());
        System.out.println("Second value; "+ p1.getSecond());
    }
}

class Pair<T, S> {
    private T t;
    private S s;

    public void set(T t, S s) {
        this.t = t;
        this.s = s;
    }

    public T getFirst() {
        return t;
    }

    public S getSecond() {
        return s;
    }
}
