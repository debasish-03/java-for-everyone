package generic.method;

public class MultiBoundGenericType {

    public static void main(String[] args) {
        System.out.printf("Max of %d, %d and %d is %d\n\n",
                3, 4, 5, maximum( 3, 4, 5 ));

        System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n",
                6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ));
    }

    /*
    * Syntax => public static <T extends Number & Comparable<T>> T maximum(T x, T y, T z)
    * Where `maximum` − maximum is a generic method
    * T − The generic type parameter passed to generic method. It can take any Object.
    *
    * The T is a type parameter passed to the generic class Box and should be subtype of Number class
    * and must implements Comparable interface.
    * In case a class is passed as bound, it should be passed first before interface otherwise compile time error will occur.
    * */
    public static <T extends Number & Comparable<T>> T maximum(T t1, T t2, T t3) {
        T max = t1;

        if (t2.compareTo(max) > 0) max = t2;
        if (t3.compareTo(max) > 0) max = t3;

        return max;
    }
}
