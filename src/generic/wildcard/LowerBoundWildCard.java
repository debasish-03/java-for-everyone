package generic.wildcard;

import java.util.ArrayList;
import java.util.List;

public class LowerBoundWildCard {
    public static void main(String[] args) {

        // List of Animal (superclass of Cat)
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        animals.add(new Dog());  // another subclass of Animal

        // You can pass List<Animal> to addCat() because Animal is a supertype of Cat
        addCat(animals);

        // You can also pass List<Cat>
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        cats.add(new RedCat());
        addCat(cats);

        // You cannot pass List<RedCat> — RedCat is a *subtype* of Cat, not a supertype
        // List<RedCat> redCats = new ArrayList<>();
        // addCat(redCats); // Compile-time error
    }

    /**
     * Upper bound Wildcard − ? extends Type.
     * Lower bound Wildcard − ? super Type.
     * Unbounded Wildcard − ?
     * <p>
     * Lower bounded wildcard:
     * Accepts List of Cat or any of its *superclasses* (Cat, Animal, Object)
     */
    public static void addCat(List<? super Cat> list) {
        // You can safely add Cat or its subclasses
        list.add(new Cat());
        list.add(new RedCat());

        // You cannot add Animal or Dog (superclass types)
        // list.add(new Animal()); //  Compile-time error

        System.out.println("List after adding cats:");
        list.forEach(System.out::println);
    }
}

class Animal {
    @Override
    public String toString() {
        return "Animal";
    }
}

class Cat extends Animal {
    @Override
    public String toString() {
        return "Cat";
    }
}

class RedCat extends Cat {
    @Override
    public String toString() {
        return "RedCat";
    }
}

class Dog extends Animal {
    @Override
    public String toString() {
        return "Dog";
    }
}
