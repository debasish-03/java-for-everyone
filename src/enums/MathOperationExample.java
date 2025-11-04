package enums;

public class MathOperationExample {
    public static void main(String[] args) {
        double x = 10, y = 5;
        for (Operation op: Operation.values()) {
            System.out.println("x="+ x + ", y=" + y + " " + op + " => " + op.apply(x, y));
        }
    }
}

enum Operation {
    ADD {
        public double apply(double x, double y) { return x + y;}
    },
    SUBTRACT {
        public double apply(double x, double y) { return x - y;}
    },
    MULTIPLY {
        public double apply(double x, double y) { return x * y;}
    },
    DIVIDE {
        public double apply(double x, double y) { return x / y;}
    };

    // Abstract method implemented by each constant
    public abstract double apply(double x, double y);
}
