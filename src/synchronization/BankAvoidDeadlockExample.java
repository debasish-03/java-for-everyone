package synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAvoidDeadlockExample {

    public static void main(String[] args) {
        Account accountA = new Account("Account-A", 1000);
        Account accountB = new Account("Account-B", 1000);

        Thread t1 = new Thread(() -> transfer(accountA, accountB, 100), "Thread-1");
        Thread t2 = new Thread(() -> transfer(accountB, accountA, 200), "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("\n=== Final Balances ===");
        System.out.println(accountA.getName() + ": " + accountA.getBalance());
        System.out.println(accountB.getName() + ": " + accountB.getBalance());
    }

    public static void transfer(Account from, Account to, int amount) {
        Account first = from.getName().compareTo(to.getName()) < 0 ? from : to;
        Account second = from.getName().compareTo(to.getName()) < 0 ? to : from;

        try {
            first.getLock().lock();
            second.getLock().lock();

            if (from.getBalance() >= amount) {
                from.withdraw(amount);
                to.deposit(amount);
                System.out.println(Thread.currentThread().getName() + " transferred " + amount +
                        " from " + from.getName() + " to " + to.getName());
            } else {
                System.out.println(Thread.currentThread().getName() + ": Insufficient funds in " + from.getName());
            }
        } finally {
            second.getLock().unlock();
            first.getLock().unlock();
        }
    }

    static class Account {
        private int balance;
        private final String name;
        private final Lock lock = new ReentrantLock();

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

        public void deposit(int amount) {
            balance += amount;
        }

        public void withdraw(int amount) {
            balance -= amount;
        }

        public Lock getLock() {
            return lock;
        }

    }
}
