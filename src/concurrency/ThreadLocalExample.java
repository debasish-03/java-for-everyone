package concurrency;



/*
*
* The ThreadLocal class is used to create thread local variables which can only be read and written by the same thread.
* For example, if two threads are accessing code having reference to same threadLocal variable
* then each thread will not see any modification to threadLocal variable done by other thread.
*
* ThreadLocal Methods:
* public T get() => Returns the value in the current thread's copy of this thread-local variable.
* protected T initialValue() => Returns the current thread's "initial value" for this thread-local variable.
* public void remove() => Removes the current thread's value for this thread-local variable.
* public void set(T value) => Sets the current thread's copy of this thread-local variable to the specified value.
*
*
* */
public class ThreadLocalExample {
    // Each thread will have its own Integer value
    private static final ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Runnable task = () -> {
            int val = threadLocalValue.get();
            val += 1;
            threadLocalValue.set(val);

            System.out.println(Thread.currentThread().getName()+ " => Value: " + threadLocalValue.get());
        };

        // Create multiple thread
        Thread t1 = new Thread(task, "Thread - 1");
        Thread t2 = new Thread(task, "Thread - 2");
        Thread t3 = new Thread(task, "Thread - 3");
        Thread t4 = new Thread(task, "Thread - 4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
