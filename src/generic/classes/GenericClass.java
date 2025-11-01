package generic.classes;




public class GenericClass {
    public static void main(String[] args) {
        System.out.println("============= Single Type Generic Class ===============");
        MyGenericClass<Integer> objIntegerType = new MyGenericClass<>();
        objIntegerType.set(50);
        System.out.println(objIntegerType.get());

        MyGenericClass<String> objStringType = new MyGenericClass<>();
        objStringType.set("Hello World!");
        System.out.println(objStringType.get());

        System.out.println("============= Multiple Type Generic Class ===============");
        GenericClassMultipleType<Integer, String> obj1 = new GenericClassMultipleType<>();
        obj1.set(10,"Hello World");
        System.out.printf("Integer Value :%d\n", obj1.getFirst());
        System.out.printf("String Value :%s\n", obj1.getSecond());

        GenericClassMultipleType<String, String> obj2 = new GenericClassMultipleType<>();
        obj2.set("Message","Hello World");
        System.out.printf("String Value :%s\n", obj2.getFirst());
        System.out.printf("String Value :%s\n", obj2.getSecond());
    }
}

/*
* The common generic type parameters are as follows:
* T - Type, and is mainly used to represent first generic type parameter
* S - Type, and is mainly used to represent second generic type parameter
* U - Type, and is mainly used to represent third generic type parameter
* V − Type, and is mainly used to represent fourth generic type parameter
* E - Element, and is mainly used by Java Collections framework
* K - Key, and is mainly used to represent parameter type of key of a map
* V - Value, and is mainly used to represent parameter type of value of a map
* N - Number, and is mainly used to represent numbers
* */
class MyGenericClass<T> {
    private T t;

    void set(T t) {
        this.t = t;
    }

    T get() {
        return t;
    }
}

class GenericClassMultipleType<T, S> {
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



