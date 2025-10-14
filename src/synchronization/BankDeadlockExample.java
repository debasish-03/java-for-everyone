package synchronization;

public class BankDeadlockExample {
    public static void main(String[] args) {
        Account accountA = new Account("Account-A", 1000);
        Account accountB = new Account("Account-B", 1000);

        Thread t1 = new Thread(() -> transfer(accountA, accountB, 100), "Thread-1");
        Thread t2 = new Thread(() -> transfer(accountB, accountA, 200), "Thread-2");

        t1.start();
        t2.start();

    }

    public static void transfer(Account from, Account to, int amount) {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " locked " + from.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " locked " + from.getName());
                from.withdraw(amount);
                to.deposit(amount);

                System.out.println(Thread.currentThread().getName() + " transferred " + amount +
                        " from " + from.getName() + " to " + to.getName());
            }
        }
    }

    static class Account {
        private int balance;
        private final String name;

        public Account(String name, int balance) {
            this.name = name;
            this.balance = balance;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public synchronized void deposit(int amount) {
            balance += amount;
        }

        public synchronized void withdraw(int amount) {
            balance -= amount;
        }
    }
}
