package concurrency;

public class ThreadLocalRequestContextExample {
    public static void main(String[] args) {
        BusinessService service = new BusinessService();

        Runnable user1 = () -> {
            RequestContext.setUser("User-001");
            service.processRequest();
            RequestContext.clear();
        };

        Runnable user2 = () -> {
            RequestContext.setUser("User-002");
            service.processRequest();
            RequestContext.clear();
        };

        Thread t1 = new Thread(user1, "Thread - 1");
        Thread t2 = new Thread(user2, "Thread - 2");

        t1.start();
        t2.start();
    }
}

class RequestContext {
    private static final ThreadLocal<String> userContext = new ThreadLocal<>();

    public static void setUser(String userId) {
        userContext.set(userId);
    }

    public static String getUser() {
        return userContext.get();
    }

    public static void clear() {
        userContext.remove();
    }
}

class BusinessService {
    public void processRequest() {
        String user = RequestContext.getUser();
        System.out.println(Thread.currentThread().getName() + " processing request for user: " + user);
    }
}
