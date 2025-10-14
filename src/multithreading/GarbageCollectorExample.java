package multithreading;

public class GarbageCollectorExample {

    protected void finalize() {
        System.out.println("`finalize` called");
    }
    public static void main(String[] args) {
        GarbageCollectorExample g1 = new GarbageCollectorExample();
        GarbageCollectorExample g2 = new GarbageCollectorExample();

        g1 = null;
        g2 = null;

        System.gc();

    }
}
