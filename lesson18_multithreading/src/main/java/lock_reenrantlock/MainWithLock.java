package lock_reenrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainWithLock {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        PrintSynchroLockThread printSynchroLock = new PrintSynchroLockThread(lock);

        Thread threadOne =
                new Thread(printSynchroLock::print, "one");
        Thread threadTwo =
                new Thread(printSynchroLock::print, "two");

        threadOne.start();
        threadTwo.start();
    }
}
