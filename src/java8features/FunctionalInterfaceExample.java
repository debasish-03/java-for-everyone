package java8features;

public class FunctionalInterfaceExample implements Speak {
    public static void main(String[] args) {
        FunctionalInterfaceExample s = new FunctionalInterfaceExample();
        s.speak("hello");
    }

    @Override
    public void speak(String msg) {
        System.out.println(msg);
    }
}

@FunctionalInterface
interface Speak{
    void speak(String msg);
}
